package com.example.win7.myapplication;
import java.util.Random;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by gandthecat on 15/01/2017.
 */

public class ImageAdapter extends BaseAdapter {

    Integer[][] sprites = new Integer[9][9];
    Integer[] sprites2 = new Integer[81];
    static Random rand = new Random();
    private static Context context;
    static int x1, y1;
    private static int FirstSwappable;
    private static int SecondSwappable;
    public static String t = "";
    public static int[] stateOfSprites = new int[4];
    public static spriteValues sv1 = null;
    public static spriteValues sv2 = null;
    public static final int GGSIZE = 81;
    public int selectDeselect = 0;
    public static boolean matchSprite = false;
    public static boolean matchSprite2;
    public static int count = 0;
    public static ArrayList<spriteValues> spritesToStore = new ArrayList<spriteValues>();
    public static ArrayList<spriteValues> spritesToStore2 = new ArrayList<spriteValues>();

    public ImageAdapter(){
        /*default constructor*/
    }
    public ImageAdapter(Context applicationContext) {
        if(spritesToStore.size() == 0){
            fillSprites();
        }
        context = applicationContext;
    }

    public void fillSprites() {
        int c = 0;
        String s1 = "";
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int n = rand.nextInt(6) + 1;
                sprites2[c] = n;
                switch (n) {
                    case 1:
                        sprites[x][y] = R.drawable.bluejelly;
                        s1 = "Blueberry";
                        break;
                    case 2:
                        sprites[x][y] = R.drawable.greenjelly;
                        s1 = "Green Lime";
                        break;
                    case 3:
                        sprites[x][y] = R.drawable.yellowjelly;
                        s1 = "Lemon";
                        break;
                    case 4:
                        sprites[x][y] = R.drawable.blackjelly;
                        s1 = "Black Cherry";
                        break;
                    case 5:
                        sprites[x][y] = R.drawable.purplejelly;
                        s1 = "Grape";
                        break;
                    case 6:
                        sprites[x][y] = R.drawable.redjelly;
                        s1 = "Raspberry";
                        break;
                }
                spritesToStore.add(c, new spriteValues(s1, x, y,((55) + (x * 108)), ((297) + (y * 106)), sprites[x][y]));
                matches(c);
                c = c + 1;
                for(int w = 1; w < 81; w++){
                    matches(c - w);
                }
            }
        }
    }

    public static void columnClear(int y1, int j1){
        spriteValues v1;
        for(int j = 0; j < j1; j++){
            for(int i = 1; i < 4; i ++){
                if(j == (j1 - 1)){
                    v1 = spriteChange(spritesToStore.get((j < (j1 - 1) ? ((y1 - 9) - (j * 9) + i) : (y1 - (j * 9) + i)) - 1));
                    spritesToStore.get((j < (j1 - 1) ? (y1 - (j * 9) + i) : (y1 - (j * 9) + i)) - 1).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                    spritesToStore.get((j < (j1 - 1) ? (y1 - (j * 9) + i) : (y1 - (j * 9) + i)) - 1).setJellyName(v1.getJellyName());
                }
                else {
                    v1 = spritesToStore.get((j < (j1 - 1) ? ((y1 - 9) - (j * 9) + i) : (y1 - (j * 9) + i)) - 1);
                    spritesToStore.get((j < (j1 - 1) ? (y1 - (j * 9) + i) : (y1 - (j * 9) + i)) - 1).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                    spritesToStore.get((j < (j1 - 1) ? (y1 - (j * 9) + i) : (y1 - (j * 9) + i)) - 1).setJellyName(v1.getJellyName());
                }
            }
        }
    }

    public static void rowClear(int c1, int c2){
        spriteValues v1;
        for(int j = 0; j < ((c2 >= 9 && c2 <= 17) ? 2 : 3); j++){
            v1 = spriteChange(spritesToStore.get(c2 + c1));
            spritesToStore.get(c2 + c1).setSpriteIntegerValue(v1.getSpriteIntegerValue());
            spritesToStore.get(c2 + c1).setJellyName(v1.getJellyName());
            c1 = c1 - 9;
        }
    }

    public static void columnMatchCheck(int y){
        if((y < GGSIZE) && ((y + 1) < GGSIZE) && ((y + 2) < GGSIZE)) {
            if((spritesToStore.get(y).getSpriteIntegerValue() == spritesToStore.get(y + 1).getSpriteIntegerValue()) && (spritesToStore.get(y + 1).getSpriteIntegerValue() == spritesToStore.get(y + 2).getSpriteIntegerValue())) {
                matchSprite = true;
                if(y >= 0 && (y + 2) <= 8) {
                    columnClear(y, 1);
                }
                if(y >= 9 && (y + 2) <= 17){
                    columnClear(y, 2);
                }
                if(y >= 18 && (y + 2) <= 26){
                    columnClear(y, 3);
                }
                if(y >= 27 && (y + 2) <= 35){
                    columnClear(y, 4);
                }
                if(y >= 36 && (y + 2) <= 44){
                    columnClear(y, 5);
                }
                if(y >= 45 && (y + 2) <= 53){
                    columnClear(y, 6);
                }
                if(y >= 54 && (y + 2) <= 62){
                    columnClear(y, 7);
                }
                if(y >= 63 && (y + 2) <= 71){
                    columnClear(y, 8);
                }
                if(y >= 72 && (y + 2) <= 80){
                    columnClear(y, 9);
                }
            }
        }
    }

    public static void rowMatchCheck(int y){
        if((y < GGSIZE) && ((y + 9) < GGSIZE) && ((y + 18) < GGSIZE)) {
            if(y <= 62){
                if((spritesToStore.get(y).getSpriteIntegerValue() == spritesToStore.get(y + 9).getSpriteIntegerValue()) && (spritesToStore.get(y + 9).getSpriteIntegerValue() == spritesToStore.get(y + 18).getSpriteIntegerValue())) {
                    matchSprite = true;
                    if(y >= 0 && y <= 8){
                        rowClear(18,y);
                    }
                    if(y >= 9 && y <= 17){
                        spriteValues v1 = spritesToStore.get(y - 9);
                        spritesToStore.get(y + 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 18).setJellyName(v1.getJellyName());

                        v1 = spriteChange(spritesToStore.get(y - 9));
                        spritesToStore.get(y - 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 9).setJellyName(v1.getJellyName());

                        rowClear(9,y);
                    }
                    if(y >= 18 && y <= 26){
                        spriteValues v1 = spritesToStore.get(y - 18);
                        spritesToStore.get(y + 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 9);
                        spritesToStore.get(y + 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 18).setJellyName(v1.getJellyName());

                        rowClear(0,y);
                    }
                    if(y >= 27 && y <= 35){
                        spriteValues v1 = spritesToStore.get(y - 18);
                        spritesToStore.get(y + 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 9);
                        spritesToStore.get(y + 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 18).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 27);
                        spritesToStore.get(y).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y).setJellyName(v1.getJellyName());

                        rowClear(-9,y);
                    }
                    if(y >= 36 && y <= 44){
                        spriteValues v1 = spritesToStore.get(y - 18);
                        spritesToStore.get(y + 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 9);
                        spritesToStore.get(y + 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 18).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 36);
                        spritesToStore.get(y - 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 27);
                        spritesToStore.get(y).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y).setJellyName(v1.getJellyName());

                        rowClear(-18,y);
                    }
                    if(y >= 45 && y <= 53){
                        spriteValues v1 = spritesToStore.get(y - 18);
                        spritesToStore.get(y + 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 9);
                        spritesToStore.get(y + 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 18).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 36);
                        spritesToStore.get(y - 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 45);
                        spritesToStore.get(y - 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 18).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 27);
                        spritesToStore.get(y).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y).setJellyName(v1.getJellyName());

                        rowClear(-27,y);
                    }
                    if(y >= 54 && y <= 62){
                        spriteValues v1 = spritesToStore.get(y - 9);
                        spritesToStore.get(y + 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 18).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 18);
                        spritesToStore.get(y + 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y + 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 36);
                        spritesToStore.get(y - 9).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 9).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 45);
                        spritesToStore.get(y - 18).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 18).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 27);
                        spritesToStore.get(y).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y).setJellyName(v1.getJellyName());

                        v1 = spritesToStore.get(y - 54);
                        spritesToStore.get(y - 27).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                        spritesToStore.get(y - 27).setJellyName(v1.getJellyName());

                        rowClear(-36,y);
                    }
                }
            }
        }
    }

    public static boolean matchedBoardChecker(int c){
        if(c >= 3){
            if((spritesToStore2.get(c).getSpriteIntegerValue() == spritesToStore2.get(c - 1).getSpriteIntegerValue()) && (spritesToStore2.get(c - 1).getSpriteIntegerValue() == spritesToStore2.get(c - 2).getSpriteIntegerValue())
                    && (spritesToStore2.get(c).getJellyName().equals(spritesToStore2.get(c - 1).getJellyName()) && (spritesToStore2.get(c - 1).getJellyName().equals(spritesToStore2.get(c - 2).getJellyName())))
                    ){
                    for(int i = 0; i < 9; i++){
                        if(c == (i * 9) || c == ((i * 9) + 1)){
                            return false;
                        }
                    }
                return true;
            }
        }
        if(c >= 18){
            if((spritesToStore2.get(c).getSpriteIntegerValue() == spritesToStore2.get(c - 9).getSpriteIntegerValue()) && (spritesToStore2.get(c - 9).getSpriteIntegerValue() == spritesToStore2.get(c - 18).getSpriteIntegerValue())
                    && (spritesToStore2.get(c).getJellyName().equals(spritesToStore2.get(c - 9).getJellyName()) && (spritesToStore2.get(c - 9).getJellyName().equals(spritesToStore2.get(c - 18).getJellyName()))))
            {
                if(c >= 0 && c <= 17){
                    return false;
                }
                    return true;

            }
        }
        return false;
    }

    public static void matches(int i){
        if(i >= 3){
            if((spritesToStore.get(i).getSpriteIntegerValue() == spritesToStore.get(i - 1).getSpriteIntegerValue()) && (spritesToStore.get(i - 1).getSpriteIntegerValue() == spritesToStore.get(i - 2).getSpriteIntegerValue())) {
                for(int x = 0; x < 3; x++){
                    spriteValues v1 = spriteChange(spritesToStore.get((i - x)));
                    spritesToStore.get((i - x)).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                    spritesToStore.get((i - x)).setJellyName(v1.getJellyName());
                }
            }
        }

        if(i >= 18){
            if((spritesToStore.get(i).getSpriteIntegerValue() == spritesToStore.get(i - 9).getSpriteIntegerValue()) && (spritesToStore.get(i - 9).getSpriteIntegerValue() == spritesToStore.get(i - 18).getSpriteIntegerValue())) {
                for (int x = 0; x < 3; x++) {
                    spriteValues v1 = spriteChange(spritesToStore.get((i - x)));
                    spritesToStore.get((i - x)).setSpriteIntegerValue(v1.getSpriteIntegerValue());
                    spritesToStore.get((i - x)).setJellyName(v1.getJellyName());
                }
            }
        }
    }

    public static spriteValues spriteChange(spriteValues sp1){
        Random rand = new Random();
        String jName = sp1.getJellyName();

        while(sp1.getJellyName() == jName){
            int n = rand.nextInt(6) + 1;
            switch(n){
                case 1:
                    sp1.setSpriteIntegerValue(R.drawable.bluejelly);
                    sp1.setJellyName("Blueberry");
                    break;
                case 2:
                    sp1.setSpriteIntegerValue(R.drawable.greenjelly);
                    sp1.setJellyName("Green Lime");
                    break;
                case 3:
                    sp1.setSpriteIntegerValue(R.drawable.blackjelly);
                    sp1.setJellyName("Black Cherry");
                    break;
                case 4:
                    sp1.setSpriteIntegerValue(R.drawable.purplejelly);
                    sp1.setJellyName("Grape");
                    break;
                case 5:
                    sp1.setSpriteIntegerValue(R.drawable.redjelly);
                    sp1.setJellyName("Raspberry");
                    break;
                case 6:
                    sp1.setSpriteIntegerValue(R.drawable.yellowjelly);
                    sp1.setJellyName("Lemon");
                    break;
            }
        }
        return sp1;
    }

    @Override
    public Object getItem(int position) {
        return sprites2[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        //Number of data elements to be displayed
        return sprites2.length;
    }

    public static void incrementScore(){
        count = count + 60;
    }

    public static int getScore(){
        return count;
    }

    public View getView(int position, View convertView, ViewGroup groupView) {
        ImageView iView = new ImageView(context);
        iView.setImageResource(spritesToStore.get(position).getSpriteIntegerValue());
        iView.setBackgroundResource(R.drawable.transparency_background);
        iView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iView.setLayoutParams(new GridView.LayoutParams(100, 100));
        iView.setPadding(1,1,1,1);

        iView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] values = new int[2];
                v.getLocationOnScreen(values);
                x1 = values[0];
                y1 = values[1];
                setXCoordinates(x1, y1);
                ImageAdapter.matchSprite2 = false;
                int swap1;
                int swap2;
                if (selectDeselect < 3) {
                    v.setBackgroundColor(Color.YELLOW);
                    stateOfSprites[selectDeselect % 4] = x1;
                    selectDeselect = selectDeselect + 1;
                    stateOfSprites[selectDeselect % 4] = y1;
                    selectDeselect = selectDeselect + 1;
                }
                int a1 = Math.abs(stateOfSprites[1] - stateOfSprites[3]);
                int a2 = Math.abs(stateOfSprites[0] - stateOfSprites[2]);

                    if (stateOfSprites[0] > 0 && stateOfSprites[1] > 0 && stateOfSprites[2] > 0 && stateOfSprites[3] > 0 && (a1 == 106 || a2 == 108)) {
                        if ((stateOfSprites[0] == stateOfSprites[2] && Math.abs(stateOfSprites[1] - stateOfSprites[3]) == 106) || (stateOfSprites[1] == stateOfSprites[3] && Math.abs(stateOfSprites[0] - stateOfSprites[2]) == 108)) {
                            swap1 = convertBoardPosition(0, stateOfSprites[0], stateOfSprites[1]);
                            sv1 = new spriteValues(
                                    spritesToStore.get(swap1).getJellyName(),
                                    spritesToStore.get(swap1).getJellyArrayLocX(),
                                    spritesToStore.get(swap1).getJellyArrayLocY(),
                                    spritesToStore.get(swap1).getAndroidImageViewX(),
                                    spritesToStore.get(swap1).getAndroidImageViewY(),
                                    spritesToStore.get(swap1).getSpriteIntegerValue());
                            swap2 = convertBoardPosition(0, stateOfSprites[2], stateOfSprites[3]);
                            sv2 = new spriteValues(
                                    spritesToStore.get(swap2).getJellyName(),
                                    spritesToStore.get(swap2).getJellyArrayLocX(),
                                    spritesToStore.get(swap2).getJellyArrayLocY(),
                                    spritesToStore.get(swap2).getAndroidImageViewX(),
                                    spritesToStore.get(swap2).getAndroidImageViewY(),
                                    spritesToStore.get(swap2).getSpriteIntegerValue());

                            FirstSwappable = swap2;
                            SecondSwappable = swap1;
                            ImageAdapter.spritesToStore2 = ImageAdapter.spritesToStore;
                            ImageAdapter.spritesToStore2.get(ImageAdapter.FirstSwappable).setSpriteIntegerValue(sv1.getSpriteIntegerValue());
                            ImageAdapter.spritesToStore2.get(ImageAdapter.FirstSwappable).setJellyName(sv1.getJellyName());
                            ImageAdapter.spritesToStore2.get(ImageAdapter.SecondSwappable).setSpriteIntegerValue(sv2.getSpriteIntegerValue());
                            ImageAdapter.spritesToStore2.get(ImageAdapter.SecondSwappable).setJellyName(sv2.getJellyName());

                            for (int x = 0; x < 81; x++) {
                                ImageAdapter.matchSprite2 = matchedBoardChecker(x);
                                if(ImageAdapter.matchSprite2){
                                    incrementScore();
                                    break;
                                }
                            }
                    }
                }

                if(!ImageAdapter.matchSprite2){
                    t = ImageAdapter.spritesToStore.get(convertBoardPosition(0, stateOfSprites[0], stateOfSprites[1])).getJellyName();
                    if (stateOfSprites[0] > 0
                            && stateOfSprites[1] > 0
                            && stateOfSprites[2] > 0
                            && stateOfSprites[3] > 0
                            && (a1 <= 106 && a2 <= 108)) {
                        if (((stateOfSprites[0] != stateOfSprites[2] && Math.abs(stateOfSprites[1] - stateOfSprites[3]) != 106) || (stateOfSprites[1] != stateOfSprites[3] && Math.abs(stateOfSprites[0] - stateOfSprites[2]) != 108))) {
                                ImageAdapter.spritesToStore2.get(ImageAdapter.FirstSwappable).setSpriteIntegerValue(sv2.getSpriteIntegerValue());
                                ImageAdapter.spritesToStore2.get(ImageAdapter.FirstSwappable).setJellyName(sv2.getJellyName());
                                ImageAdapter.spritesToStore2.get(ImageAdapter.SecondSwappable).setSpriteIntegerValue(sv1.getSpriteIntegerValue());
                                ImageAdapter.spritesToStore2.get(ImageAdapter.SecondSwappable).setJellyName(sv1.getJellyName());
                                stateOfSprites[0] = 0;
                                stateOfSprites[1] = 0;
                                stateOfSprites[2] = 0;
                                stateOfSprites[3] = 0;
                        }
                    }
                }
            }
        });
        return iView;
    }

    public void setXCoordinates(int x, int y){
        x1 = x;
        y1 = y;
    }

    public int convertBoardPosition(int round, int x, int y){
        for(int e = 0; e < 81; e ++) {
            if(spritesToStore.get(e).getAndroidImageViewX() == x && spritesToStore.get(e).getAndroidImageViewY() == y) {
                if (e == 0) {
                    round = 0;
                }
                if (e >= 1 && e <= 8) {
                    round = (e * 9);
                }
                if (e >= 9 && e <= 17) {
                    round = ((e - 9) * 9) + 1;
                }
                if (e >= 18 && e <= 26) {
                    round = ((e - 18) * 9) + 2;
                }
                if (e >= 27 && e <= 35) {
                    round = ((e - 27) * 9) + 3;
                }
                if (e >= 36 && e <= 44) {
                    round = ((e - 36) * 9) + 4;
                }
                if (e >= 45 && e <= 53) {
                    round = ((e - 45) * 9) + 5;
                }
                if (e >= 54 && e <= 62) {
                    round = ((e - 54) * 9) + 6;
                }
                if (e >= 63 && e <= 71) {
                    round = ((e - 63) * 9) + 7;
                }
                if (e >= 72 && e <= 80) {
                    round = ((e - 72) * 9) + 8;
                }
            }
        }
        return round;
    }
}