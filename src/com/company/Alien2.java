package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alien2 extends Foe {
    private BufferedImage body;
    private boolean left = true;

    {
        try{
            body = ImageIO.read(new File("images/alien2.png"));
        }catch(IOException e){

        }
    }

    public Alien2(int x){
        setBody(body);
        setPosition(new Vector(x, -20));
        setValues(80,80, 10);
        setBounds();
        setBulletType(FOE_BULLET);
        setRail(150);
        setShootFrequency(1500);
    }


    @Override
    public void pattern() {
        if(left){
            move(FOE_LEFT);
            if(getPosition().getX() <= 50) {
                left = false;
            }
            }else{
                move(FOE_RIGHT);
                if(getPosition().getX() >= Game.width - 50){
                    left = true;
                }
            }
        }



    }

