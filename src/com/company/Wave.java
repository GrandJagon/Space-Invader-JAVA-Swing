package com.company;

import java.util.ArrayList;

public class Wave {
    private ArrayList<Foe> wave = new ArrayList<>();

    public Wave(){

    }

    public void addFoe(String foeType, int position){
        if(foeType.equals("a")){
            this.wave.add(new Alien1(position));
        }
        if(foeType.equals("b")){
            this.wave.add(new Alien2(position));
        }
        if(foeType.equals("c")){
            this.wave.add(new Alien3(position));
        }
        if(foeType.equals("d")){
            this.wave.add(new Alien4(position));
        }
    }

    public void launch(){
        for (Foe foe : wave) {
            Game.entities.add(foe);
            Game.foes.add(foe);
        }
    }









}
