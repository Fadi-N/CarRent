import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Register extends JFrame {
    JFrame f;
    JLabel id, name, surname, phone, email ;
    JTextField t1, t2, t3, t4, t5;
    JButton register;

    Register(Connection connection){
        id = new JLabel("Personal id number");
        id.setBounds(70, 40, 200, 20);

        t1 = new JTextField();
        t1.setBounds(70, 60, 200, 30);

        name = new JLabel("Name");
        name.setBounds(70, 100, 100, 20);

        t2 = new JTextField();
        t2.setBounds(70, 120, 200, 30);

        surname = new JLabel("Surname");
        surname.setBounds(70, 160, 100, 20);

        t3 = new JTextField();
        t3.setBounds(70, 180, 200, 30);

        phone = new JLabel("Phone number");
        phone.setBounds(70, 220, 200, 30);

        t4 = new JTextField();
        t4.setBounds(70, 242, 200, 30);

        email = new JLabel("Email");
        email.setBounds(70, 280, 100, 20);

        t5 = new JTextField();
        t5.setBounds(70, 300, 200, 30);

        register = new JButton("Register");
        register.setBounds(170, 340, 100, 30);

        add(id);
        add(t1);
        add(name);
        add(t2);
        add(surname);
        add(t3);
        add(phone);
        add(t4);
        add(email);
        add(t5);
        add(register);

        setLayout(null);
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String sql = "INSERT INTO uzytkownik VALUES (?, ?, ?, ?, ?)";
                System.out.println("Connected");
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, t1.getText());
                    pstmt.setString(2, t2.getText());
                    pstmt.setString(3, t3.getText());
                    pstmt.setString(4, t4.getText());
                    pstmt.setString(5, t5.getText());
                    pstmt.executeUpdate();
                    setVisible(false);
                    f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Registered!!");
                    new Login();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
    }
}
