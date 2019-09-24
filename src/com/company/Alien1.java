package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alien1 extends Foe {
    private BufferedImage body;

    {
        try{
        body = ImageIO.read(new File("images/alien1.png"));
    }catch(IOException e){

     }
    }

    public Alien1(int x){
        setBody(body);
        setPosition(new Vector (x, -20));
        setValues(50,50, 5);
        setBounds();
        setBulletType(FOE_BULLET);
        setRail(100);
        setShootFrequency(1000);
    }



    @Override
    public void pattern() {

    }






}
