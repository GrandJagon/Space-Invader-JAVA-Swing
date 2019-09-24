package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {
    JLabel title = new JLabel("SPACE INVADER");
    JLabel newGame = new JLabel("New Game");
    JLabel gunShop = new JLabel("Garage");
    JLabel exit = new JLabel("Exit");
    Font font = new Font("monospaced", Font.BOLD,  30);
    Font font2 = new Font("monospaced", Font.BOLD, 20);
    Border border = BorderFactory.createLineBorder(Color.WHITE, 3);

    public Menu() {
        this.setLayout(new GridLayout(4, 1));
        this.setSize(400, 600);
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setFont(font);
        this.title.setForeground(Color.WHITE);
        this.newGame.setHorizontalAlignment(JLabel.CENTER);
        this.newGame.setFont(font);
        this.newGame.setForeground(Color.WHITE);
        this.gunShop.setHorizontalAlignment(JLabel.CENTER);
        this.gunShop.setFont(font);
        this.gunShop.setForeground(Color.WHITE);
        this.exit.setHorizontalAlignment(JLabel.CENTER);
        this.exit.setFont(font);
        this.exit.setForeground(Color.WHITE);
        this.add(title);
        this.add(newGame);
        this.add(gunShop);
        this.add(exit);
        this.getContentPane().setBackground(Color.BLACK);

        this.newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                Game game = new Game();
                game.init();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                newGame.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                newGame.setBorder(null);
            }
        });

        this.gunShop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                Garage garage = new Garage();
                garage.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                gunShop.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                gunShop.setBorder(null);
            }
        });

        this.exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                exit.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                exit.setBorder(null);
            }
        });
    }





}
