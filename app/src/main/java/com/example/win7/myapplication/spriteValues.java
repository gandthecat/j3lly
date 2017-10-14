package com.example.win7.myapplication;
import java.util.Random;
/**
 * Created by gandthecat on 17/01/2017.
 */

    public class spriteValues{
    public String jellyName;
    public int jellyArrayLocX;
    public int jellyArrayLocY;
    public int spriteIntegerValue;
    public int androidImageViewX;
    public int androidImageViewY;

public spriteValues(String s, int w, int x, int g, int h, int z){
    this.jellyName = s;
    this.jellyArrayLocX = w;
    this.jellyArrayLocY = x;
    this.androidImageViewX = g;
    this.androidImageViewY = h;
    this.spriteIntegerValue = z;
        }

    public void setJellyName(String s1){
        this.jellyName = s1;
    }

    public String getJellyName(){
        return this.jellyName;
    }

    public void setJellyArrayLocX(int cl){
        this.jellyArrayLocX = cl;
    }

    public int getJellyArrayLocX(){
        return this.jellyArrayLocX;
    }

    public void setJellyArrayLocY(int c2){
        this.jellyArrayLocY = c2;
    }

    public int getJellyArrayLocY(){
        return this.jellyArrayLocY;
    }

    public int getSpriteIntegerValue(){return this.spriteIntegerValue;}

    public void setSpriteIntegerValue(int siv){this.spriteIntegerValue = siv;}

    public int getAndroidImageViewX(){return this.androidImageViewX;}

    public void setAndroidImageViewX(int y){this.androidImageViewX = y;}

    public int getAndroidImageViewY(){return this.androidImageViewY;}

    public void setAndroidImageViewY(int w){this.androidImageViewY = w;}

    public String toString(){
        return this.jellyName + " " + this.jellyArrayLocX + " " + this.jellyArrayLocY + " " + this.androidImageViewX + " " + this.androidImageViewY + " " + this.spriteIntegerValue;
    }
}