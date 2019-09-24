package com.company;

import java.awt.image.BufferedImage;

public class PlayableShip {
    private BufferedImage body;
    private int width;
    private int height;

    public PlayableShip(BufferedImage body, int width, int height){
        this.body = body;
        this.width = width;
        this.height = height;
    }

    public BufferedImage getBody() {
        return body;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
