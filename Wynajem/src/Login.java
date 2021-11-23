import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Login extends JFrame {
    JFrame ff;
    JLabel l1, l2, l3;
    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;
    Connection connection;


    Login(){
        Font f = new Font("Arial", Font.BOLD, 24);

        l1 = new JLabel("Login Page");
        l1.setFont(f);
        l1.setBounds(70, 40, 200, 40);

        l2 = new JLabel("UserName");
        l2.setBounds(70, 100, 100, 20);

        t1 = new JTextField();
        t1.setBounds(70, 120, 200, 30);

        l3 = new JLabel("Password");
        l3.setBounds(70, 170, 100, 20);

        t2 = new JPasswordField();
        t2.setBounds(70, 190, 200, 30);

        b1 = new JButton("Login");
        b1.setBounds(180, 240, 90, 30);

        b2 = new JButton("Register");
        b2.setBounds(70, 240, 90, 30);

        add(l1);
        add(l2);
        add(t1);
        add(l3);
        add(t2);
        add(b1);
        add(b2);

        setLayout(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                try {
                    connection = DriverManager.getConnection("#", t1.getText(), String.valueOf(t2.getPassword()));
                    System.out.println("Connected");
                    new Choose(connection);
                }catch (Exception e){
                    System.out.println(e);
                    ff = new JFrame();
                    JOptionPane.showMessageDialog(ff, "Error!!");
                    new Login();
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                try {
                    connection = DriverManager.getConnection("#", "#", "#");
                    System.out.println("Connected");
                    new Register(connection);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });

    }

    public static void main(String[] args) {
        new Login();
    }

}
