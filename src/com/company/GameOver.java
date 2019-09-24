package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameOver extends JPanel {
    private JLabel label = new JLabel("GAME OVER");
    private JLabel label2 = new JLabel("Hit space to go back to menu");
    Font font = new Font("monospaced", Font.BOLD,  30);
    Font font2 = new Font("monospaced", Font.BOLD, 20);
    private int fade = 0;

    public GameOver(){
        this.setLayout(new GridLayout(2, 1));
        this.setSize(800, 600);
        this.setVisible(true);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label2.setHorizontalAlignment(JLabel.CENTER);
        this.label.setFont(font);
        this.label2.setFont(font2);
        this.add(label);
        this.add(label2);
        this.setBackground(new Color(0,0,0,0));
        new Timer(20, fadeIn).start();
    }

    AbstractAction fadeIn = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(fade >= 100){
                Thread.currentThread().interrupt();
                return;
            }
                setBackground(new Color(0,0,0, fade));
                label.setForeground(new Color(255,255,255, fade));
                label2.setForeground(new Color(255,255,255, fade));
                repaint();
                revalidate();
                fade++;
            }
    };
}
