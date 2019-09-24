package com.company;

public abstract class Foe extends Entity implements Constants {
    private Vector bullet_type;
    private int rail;
    private int shootFrequency;
    private boolean touchable;

    public void setBulletType(Vector v){
        this.bullet_type = v;
    }


    public void pop(){
        if(getPosition().getY() < rail){
            move(Constants.FOE_DOWN);
        }else{
            setTouchable();
        }
    }


    public abstract void pattern();

    public void shoot(){
        if(isAlive() && isTouchable()) {
            Game.bullets.add(new Bullet((getPosition().getX() + getWidth() / 2) - 20, (getPosition().getY() + getHeight()/2) + 25, bullet_type, true));
        }
    }

    public void setRail(int r){
        this.rail = r;
    }

    public void setTouchable(){
        touchable = true;
        System.out.println("touchable");
    }

    public boolean isTouchable(){return touchable;}


    public void setShootFrequency(int shootFrequency){
        this.shootFrequency = shootFrequency;
    }

    public int getShootFrequency(){
        return shootFrequency;
    }
}
