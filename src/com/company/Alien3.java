package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alien3 extends Foe {
    private BufferedImage body;
    private boolean left = true;

    {
        try{
            body = ImageIO.read(new File("images/alien3.png"));
        }catch(IOException e){

        }
    }

    public Alien3(int x){
        setBody(body);
        setPosition(new Vector(x, -20));
        setValues(150,150, 25);
        setBounds();
        setRail(120);
        setShootFrequency(1000);
    }

    @Override
    public void pattern() {
     /*   if(left){
            move(FOE_LEFT);
            if(getPosition().getX() <= 50) {
                left = false;
            }
        }else{
            move(FOE_RIGHT);
            if(getPosition().getX() >= Game.width - 50){
                left = true;
            }
        }*/
    }

    @Override
    public void shoot(){
        if(isAlive() && isTouchable()) {
            Vector velocity = new Vector(Game.ship.getPosition().getX() - getPosition().getX(),
                    Game.ship.getPosition().getY() - (getPosition().getY() + getHeight()/2 + 10));
            velocity = new Vector(velocity.getX() / 40, velocity.getY() / 40);
            Game.bullets.add(new Bullet(getPosition().getX(), getPosition().getY() + getHeight()/2 + 10, velocity, true));
        }
    }

}
