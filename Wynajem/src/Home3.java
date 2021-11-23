import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Home3 extends  JFrame{
    JLabel id, pass, end;
    JTextField t1, t2;
    JButton submit, back;
    JDateChooser endDate;

    Home3(Connection connection){

        back = new JButton("Back");
        back.setBounds(20, 40, 100, 30);

        id = new JLabel("Personal id number");
        id.setBounds(70, 100, 200, 20);

        t1 = new JTextField();
        t1.setBounds(70, 120, 200, 30);

        pass = new JLabel("Code");
        pass.setBounds(70, 160, 200, 20);

        t2 = new JTextField();
        t2.setBounds(70, 180, 200, 30);

        end = new JLabel("Extend rental to");
        end.setBounds(70, 220, 100, 20);

        endDate = new JDateChooser();
        endDate.setBounds(70, 240, 200, 30);

        submit = new JButton("Extend");
        submit.setBounds(170, 300, 100, 30);

        add(back);
        add(id);
        add(t1);
        add(pass);
        add(t2);
        add(end);
        add(endDate);
        add(submit);

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

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String pid = t1.getText();
                String p = t2.getText();
                java.sql.Date d2 = new java.sql.Date(endDate.getDate().getTime());
                //System.out.printf("TEXT %s ", pass);
                String sql = "UPDATE wynajem a SET termin_oddania = '" + d2 + "' WHERE uzytkownik_pesel = '" + pid + "' AND kod = '" + p + "'";
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
