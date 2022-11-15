package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {

    JFrame frame = new JFrame();
    JButton button = new JButton(new ImageIcon("resources/icons/Start-Button-Vector-PNG.png"));


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
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(button);
    }
}
