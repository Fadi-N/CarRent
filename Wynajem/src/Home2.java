import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Home2 extends JFrame {
    JLabel l1;
    JTextField t1;
    JButton b1, back;
    String pass;

    Home2(Connection connection){
        back = new JButton("Back");
        back.setBounds(20, 40, 100, 30);

        l1 = new JLabel("Please, enter return code:");
        l1.setBounds(70, 140, 200, 20);

        t1 = new JTextField();
        t1.setBounds(70, 160, 200, 30);

        b1 = new JButton("Return");
        b1.setBounds(170, 200, 100, 30);

        add(back);
        add(l1);
        add(t1);
        add(b1);

        setLayout(null);
        setVisible(true);
        setSize(400,400);
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

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pass = t1.getText();
                System.out.printf("TEXT %s ", pass);
                String sql = "UPDATE auta a SET dostepnosc = 1 WHERE EXISTS (SELECT * FROM wynajem w INNER JOIN  wynajem_auta_fk wa ON w.wynajem_id = wa.wynajem_wynajem_id WHERE wa.auta_numer_rejestracyjny = a.numer_rejestracyjny  AND w.kod = '" + pass + "') ";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.executeUpdate();
                }catch (Exception e){
                    System.out.println(e);
                }
                setVisible(false);
                new Thanks(connection);
            }
        });

    }

}
