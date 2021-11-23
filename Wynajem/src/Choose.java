import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Choose extends JFrame {
    JLabel l1;
    JButton rent, remove, extend;

    Choose(Connection connection){
        Font f = new Font("Arial", Font.BOLD, 24);
        l1 = new JLabel("I want to ...");
        l1.setFont(f);
        l1.setBounds(70, 40, 200, 40);

        rent = new JButton("Rent a car");
        rent.setBounds(70, 120, 200, 30);

        extend = new JButton("Extend a car");
        extend.setBounds(70, 170, 200, 30);

        remove = new JButton("Return a car");
        remove.setBounds(70, 220, 200, 30);

        add(l1);
        add(rent);
        add(extend);
        add(remove);

        setLayout(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Home(connection);
            }
        });

        extend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Home3(connection);
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Home2(connection);
            }
        });
    }
}
