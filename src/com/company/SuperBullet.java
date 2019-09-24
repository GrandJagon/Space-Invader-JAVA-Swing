package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SuperBullet extends Bullet {
    private BufferedImage body;

    {
        try {
            body = ImageIO.read(new File("images/Dylan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBody(body);
    }


    public SuperBullet(int x, int y, Vector v, boolean b) {
        super(x, y, v, b);
        setBounds(new Rectangle2D.Double(getPosition().getX() - 50, getPosition().getY()- 50, 80,80));
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(body, getPosition().getX()-85, getPosition().getY()-75, 150, 150, null);
    }

    @Override
    public void updateBounds(){
        getBounds().setFrame(getPosition().getX() - 50, getPosition().getY() - 50, 80, 80);
    }
}
