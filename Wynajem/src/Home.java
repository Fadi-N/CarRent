import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class Home  extends JFrame {
    JFrame f;
    JLabel id, dealer, start, end ;
    JTextField textField1;
    JComboBox comboBox1;
    JButton submit, back;
    JDateChooser startDate, endDate;

    java.sql.Date now1;

    Home(Connection connection){
        back = new JButton("Back");
        back.setBounds(20, 40, 100, 30);

        id = new JLabel("Personal id number");
        id.setBounds(70, 100, 200, 20);

        textField1 = new JTextField();
        textField1.setBounds(70, 120, 200, 30);

        dealer = new JLabel("Car dealer");
        dealer.setBounds(70, 160, 100, 20);

        comboBox1 = new JComboBox();
        comboBox1.setBounds(70, 180, 200, 30);

        start = new JLabel("Start of rental");
        start.setBounds(70, 220, 100, 20);

        startDate = new JDateChooser();

        LocalDateTime now = LocalDateTime.now();
        now1= java.sql.Date.valueOf(now.toLocalDate());
        startDate.setDate(now1);
        startDate.setEnabled(false);

        startDate.setBounds(70, 240, 200, 30);

        end = new JLabel("End of rental");
        end.setBounds(70, 280, 100, 20);

        endDate = new JDateChooser();
        endDate.setBounds(70, 300, 200, 30);

        submit = new JButton("Submit");
        submit.setBounds(170, 360, 100, 30);

        add(back);
        add(id);
        add(textField1);
        add(dealer);
        add(comboBox1);
        add(start);
        add(startDate);
        add(end);
        add(endDate);
        add(submit);

        try {
            System.out.println("Connected");

            //Combo fill
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nazwa FROM salony");
            while (resultSet.next()){
                String name = resultSet.getString("nazwa");
                comboBox1.addItem(name);
            }
        }catch (Exception e){
            System.out.println(e);
        }


        setLayout(null);
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    setVisible(false);
                    new Choose(connection);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = textField1.getText();
                    String value = (String)comboBox1.getSelectedItem();
                    java.sql.Date d1 = java.sql.Date.valueOf(String.valueOf(now1));
                    java.sql.Date d2 = new java.sql.Date(endDate.getDate().getTime());
                    setVisible(false);
                    new Offer(connection, id, value, d1, d2);
                }catch (Exception e){
                    System.out.println(e);
                    f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Complete the missing fields");
                }
            }
        });
    }
}
