import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Thanks extends JFrame {
    JLabel l1, l2, l3;
    Timer timer;
    int second = 10;

    Thanks(Connection connection){
        Font f = new Font("Arial", Font.BOLD, 24);
        l1 = new JLabel("Success!!!");
        l1.setFont(f);
        l1.setBounds(120, 140, 200, 40);

        l2 = new JLabel("Thanks for using my app :)");
        l2.setFont(f);
        l2.setBounds(30, 170, 350, 40);

        l3 = new JLabel();
        l3.setBounds(200, 300, 200, 30);

        add(l1);
        add(l2);
        add(l3);

        setLayout(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second--;
                l3.setText("Back to Start Menu in : " + second);
                if (second == 0){
                    setVisible(false);
                    new Choose(connection);
                }
            }
        });
        timer.start();
    }
}
