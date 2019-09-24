package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Bullet {
    private Vector position;
    private Vector velocity;
    private boolean foe = false;
    private BufferedImage body;
    private Rectangle2D bounds;
    {
        try {
            body = ImageIO.read(new File("images/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bullet(int x, int y, Vector v, boolean foe){
        this.position = new Vector(x,y);
        this.velocity = v;
        this.bounds = new Rectangle2D.Double(position.getX(), position.getY(), 10,10);
        this.foe = foe;
    }

    public void move(){
        this.position.add(velocity);
        updateBounds();
    }

    public void paint(Graphics g){
        g.drawImage(body,this.position.getX(), this.position.getY()-5, 10,10, null );
    }

    public void setVelocity(Vector v){
        velocity = v;
    }

    public boolean isFoeBullet(){
        return foe;
    }

    public Vector getPosition(){
        return position;
    }

    public void setBody(BufferedImage body){
        this.body = body;
    }

    public void setBounds(Rectangle2D bounds){
        this.bounds = bounds;
    }

    public void updateBounds(){
        bounds.setFrame(position.getX(), position.getY()-5, 10,10);
    }

    public Rectangle2D getBounds(){
        return bounds;
    }

}
