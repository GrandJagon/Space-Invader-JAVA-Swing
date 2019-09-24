package com.company;

public interface Constants {
    public static final int SPEED = 6;
    public static final Vector LEFT = new Vector(-SPEED,0);
    public static final Vector RIGHT = new Vector(SPEED,0);
    public static final Vector UP = new Vector(0,-SPEED);
    public static final Vector DOWN = new Vector(0,SPEED);
    public static final Vector STOP = new Vector(0,0);
    public static final Vector FOE_LEFT = new Vector(-3,0);
    public static final Vector FOE_RIGHT = new Vector(3,0);
    public static final Vector FOE_DOWN = new Vector(0,1);
    public static final Vector SHIP_BULLET = new Vector(0, -15);
    public static final Vector FOE_BULLET = new Vector(0, 15);
}
