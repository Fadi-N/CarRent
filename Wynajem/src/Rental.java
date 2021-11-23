import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Rental extends JFrame {
    JLabel l1, l2, l3, l4;
    Timer timer;
    int second = 10;

    Rental(Connection connection, String code){


        l1 = new JLabel("E-ticket number: ");
        l1.setBounds(70, 120, 200, 30);

        Font f = new Font("Arial", Font.BOLD, 24);

        l2 = new JLabel(code);
        l2.setFont(f);
        l2.setBounds(170, 120, 200, 30);

        l3 = new JLabel("Screen the code & Enjoy :)");
        l3.setFont(f);
        l3.setBounds(30, 190, 350, 30);

        l4 = new JLabel();
        l4.setBounds(200, 300, 200, 30);

        add(l1);
        add(l2);
        add(l3);
        add(l4);

        setLayout(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                l4.setText("Back to Start Menu in : " + second);
                if (second == 0){
                    setVisible(false);
                    new Choose(connection);
                }
            }
        });
        timer.start();
    }

}
