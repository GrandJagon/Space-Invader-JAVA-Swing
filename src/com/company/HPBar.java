package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HPBar extends JPanel {
    public static int hp;
    private BufferedImage heart;

    {
        try{
            heart = ImageIO.read(new File("images/heart.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public HPBar(int hp){
        this.hp = hp;
    }

    public void drawHPBar(Graphics g){
        int x = 20;
        for(int i = 0; i<this.hp ; i++){
            g.drawImage(heart, 20 + i*x,50,15,15,null);
        }
    }


    public static void loseHP(){
        HPBar.hp--;
    }
}
