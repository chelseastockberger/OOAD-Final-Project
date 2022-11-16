package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {

    JFrame frame = new JFrame();
    JButton button = new JButton(new ImageIcon("resources/icons/Start-Button-Vector-PNG.png"));
    JLabel label = new JLabel("Controls: WASD to move, Enter to attack.");


    public void draw()
    {
        button.setBounds(650,700,232,112);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1536,950);
        label.setBounds(650, 500, 300,300);
        frame.add(label);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(button);
    }
}
