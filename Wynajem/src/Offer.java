import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Offer extends JFrame{
    JButton back, rent;
    JTable info;
    String val, code = "";
    int rent_id;

    Offer(Connection connection, String ids, String value, Date  d1, Date d2){
        System.out.printf("%s, %s, %s, %s\n", ids, value, d1, d2 );

        back = new JButton("Back");
        back.setBounds(20, 40, 100, 30);

        rent = new JButton("Rent");
        rent.setBounds(20, 650, 500, 30);

        add(back);

        try {
            System.out.println("Connected");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM auta WHERE salony_nazwa = '" + value + "' AND dostepnosc = 1");
            String[] columns = {"Marka", "Model", "Rok", "Moc", "Paliwo", "Koszt", "Dostepnosc", "Salon"};
            List<Object[]> sets = new ArrayList<Object[]>();

            while (resultSet.next()){
                String numer = resultSet.getString("numer_rejestracyjny");
                String marka = resultSet.getString("marka");
                String model = resultSet.getString("model");
                String rok = resultSet.getString("rok");
                String moc = resultSet.getString("moc_silnika");
                String rodzaj = resultSet.getString("rodzaj_paliwa");
                String koszt = resultSet.getString("koszt");
                String dostepnosc = resultSet.getString("dostepnosc");
                sets.add(new String[]{numer, marka, model, rok, moc, rodzaj, koszt, dostepnosc} );
            }
            info = new JTable(sets.toArray(new Object[sets.size()][]), columns);
            info.setBounds(20, 100, 500, 500);
            add(info);
        }catch (Exception e){
            System.out.println(e);
        }

        add(rent);

        setLayout(null);
        setVisible(true);
        setSize(550,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        info.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent ae) {
                int col = 0;
                int selectedRow = info.getSelectedRow();
                val = info.getModel().getValueAt(selectedRow, col).toString();
                if(selectedRow == -1){
                    System.out.println("no selection");
                }else{
                    System.out.println("Selected row : " + selectedRow + " \nSELECTED COLUMN " + val);
                }
            }
        });

        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                String sql = "UPDATE auta SET dostepnosc = 0 WHERE numer_rejestracyjny like ?";
                String sql1 = "INSERT INTO wynajem(uzytkownik_pesel, salony_nazwa, termin_wynajmu, termin_oddania, kod) VALUES (?, ?, ?, ?, ?) ";
                String sql2 = "INSERT INTO wynajem_auta_fk(auta_numer_rejestracyjny, wynajem_wynajem_id) VALUES (?, ?)";

                Random random = new Random();
                String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$";
                for (int i = 0; i < 4; i++) {
                    code = code + alphabet.charAt(random.nextInt(alphabet.length()));
                }
                System.out.printf("CODE : %s ", code);
                try {
                    System.out.println("Connected");
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, val);
                    pstmt.executeUpdate();

                    String returnCols[] = { "wynajem_id" };
                    PreparedStatement pstmt1 = connection.prepareStatement(sql1, returnCols);
                    pstmt1.setString(1, ids);
                    pstmt1.setString(2, value);
                    pstmt1.setDate(3,  d1);
                    pstmt1.setDate(4,  d2);
                    pstmt1.setString(5, code);
                    pstmt1.executeUpdate();


                    ResultSet rs = pstmt1.getGeneratedKeys();
                    if (rs.next()) {
                        rent_id = rs.getInt(1);
                    }

                    PreparedStatement pstmt2 = connection.prepareStatement(sql2);
                    pstmt2.setString(1, val);
                    pstmt2.setInt(2, rent_id);
                    pstmt2.executeUpdate();

                }catch (Exception e){
                    System.out.println(e);
                }
                new Rental(connection, code);

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    setVisible(false);
                    new Home(connection);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
    }

}
