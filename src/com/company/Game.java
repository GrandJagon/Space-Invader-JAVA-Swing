package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends JFrame implements KeyListener, Constants, Runnable {
    public static int height = 600;
    public static int width = 800;
    private BufferedImage background;
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private boolean showHitBox = false;
    private boolean ingame;
    public static boolean gameOver = false;
    private boolean alreadyShot = false;
    private HPBar hpbar;
    private Image offImage;
    private Graphics2D offGraphics;
    public static Ship ship;
    public static CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Foe> foes = new CopyOnWriteArrayList<>();
    private Launcher launcher;

    {
        try {
            background = ImageIO.read(new File("images/space.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        this.setSize(width, height);
        this.setVisible(true);
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        this.ship = new Ship(width/2, height-50);
        entities.add(this.ship);
        paint(this.getGraphics());
        hpbar = new HPBar(5);
        ingame = true;
        launcher = new Launcher();
        new Thread(this).start();
        new Thread(foesShoot).start();
        new Thread(launcher).start();
    }

    public void drawEntities(){
        for (Entity entity : entities) {
            if(!entity.isTouched()){
                offGraphics.drawImage(entity.getBody(), entity.getPosition().getX() - entity.getWidth() / 2, entity.getPosition().getY() - entity.getHeight() / 2
                        , entity.getWidth(), entity.getHeight(), null);
                if(showHitBox){
                    offGraphics.setPaint(Color.WHITE);
                    offGraphics.draw(entity.getBounds());
                    offGraphics.setPaint(Color.RED);
                    offGraphics.drawOval(entity.getPosition().getX(), entity.getPosition().getY(), 5,5);
                }
            }else{
                entity.unTouched();
            }
        }
    }

    public void drawBullets(){
        for (Bullet bullet : bullets) {
            if(bullet.getPosition().getY() < height && bullet.getPosition().getY() > 0){
                bullet.paint(offGraphics);
                if(showHitBox){
                    offGraphics.setPaint(Color.WHITE);
                    offGraphics.draw(bullet.getBounds());
                    offGraphics.setPaint(Color.RED);
                    offGraphics.drawOval(bullet.getPosition().getX(), bullet.getPosition().getY(), 5,5);
                }
                }
        }
    }


    @Override
    public void paint(Graphics g){
        if(offGraphics == null){
            offImage = createImage(width, height);
            offGraphics = (Graphics2D) offImage.getGraphics();
        }
        if(ingame) {
            offGraphics.drawImage(background, 0, 0, width, height, null);
            hpbar.drawHPBar(offGraphics);
            drawEntities();
            drawBullets();
            g.drawImage(offImage, 0, 0, width, height, null);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(!alreadyShot) {
                bullets.add(ship.shoot());
                alreadyShot = true;
            }
            if(!ingame){
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            ship.setInvincibility();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            alreadyShot = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }

    }

    public void shipMove(){
        if(left && ship.getPosition().getX() >= 5){
            ship.move(LEFT);
        }
        if(right && ship.getPosition().getX() < width - 60){
            ship.move(RIGHT);
        }
        if(up && ship.getPosition().getY() >= height/2){
            ship.move(UP);
        }
        if(down && ship.getPosition().getY() < height - 60){
            ship.move(DOWN);
        }
    }

    public void bulletsMove(){
        for (Bullet bullet :bullets) {
            bullet.move();
            if(bullet.getPosition().getY() < 0 || bullet.getPosition().getY() > height){
                bullets.remove(bullet);
                bullet.setVelocity(STOP);
            }
        }
    }

    public void foesMove(){
        for (Foe foe : foes) {
            if(!foe.isTouchable()){
                foe.pop();
            }else if(!foe.isAlive()){
                foe.deathAnimation();
            }
            else{
                foe.pattern();
            }
        }
    }

    public void checkCollision(){
            for (Bullet bullet : bullets) {
                for (Entity entity : entities) {
                    if (entity.getBounds().intersects(bullet.getBounds())) {
                        if (entity instanceof Foe) {
                            if (!((Foe) entity).isTouchable()|| bullet.isFoeBullet()) {
                                return;
                            }
                        }
                        if (entity.isTouched()) {
                            bullets.remove(bullet);
                            bullet.setVelocity(STOP);
                            return;
                        }
                        entity.touched();
                        bullets.remove(bullet);
                        bullet.setVelocity(STOP);
                    }
                }
            }

        }


    @Override
    public void run(){
        long startTime, frameNumber, delay, frozen;

        frameNumber = -1;
        delay = 20;
        startTime = System.currentTimeMillis();

        while(ingame){
            if (!ship.isAlive()) {
                gameOver();
            }
            frameNumber++;
            shipMove();
            foesMove();
            bulletsMove();
            checkCollision();
            update(getGraphics());
            try{
                startTime += delay;
                Thread.sleep(Math.max(0,startTime - System.currentTimeMillis()));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    Runnable foesShoot = ()->{
        Random rand = new Random();
        while(ingame){
            if(!foes.isEmpty()){
                for (Foe foe :foes) {
                    try {
                        Thread.sleep(rand.nextInt(foe.getShootFrequency()));
                        foe.shoot();
                    }catch(InterruptedException e){

                    }
                }
            }
        }
    };

    public void gameOver(){
        ingame = false;
        launcher.clearWaves();
        entities.clear();
        foes.clear();
        bullets.clear();
        GameOver gameOver = new GameOver();
        add(gameOver);
    }


}
