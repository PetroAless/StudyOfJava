package main_package;
import javax.swing.JLabel;

import java.awt.Point;
import java.util.Random;

import javax.swing.ImageIcon;
public class Snake {
    public enum direction {up,right,down,left}
    JLabel head, body[] = new JLabel[900];
    direction d = direction.down;
    boolean justTurned = false;
    String headSrc = "src/resources/head.png";
    String bodySrc = "src/resources/body.png";
    int measure,frameSizes;

    //head x, head y
    int bodyN = 3;//number of bodies

    Snake(int width_height,int frameSize){
        this.measure = width_height;
        this.frameSizes = frameSize;
        head = new JLabel(new ImageIcon(this.headSrc));
        for (int i = 0; i < bodyN; i++) {
            body[i] = new JLabel(new ImageIcon(this.bodySrc));
        }
        setBounds();
    }
    public void setBounds(){
        Random r = new Random();
        int x = r.nextInt(frameSizes);
        x = Math.round(x/measure)*measure;
        int y = r.nextInt(frameSizes);
        y = Math.round(y/measure)*measure;
        head.setBounds(x,y,measure,measure);
        for (int i = 0; i < this.bodyN; i++) {
            body[i].setBounds(head.getX(),head.getY(),measure,measure);
        }
    }

    public void move() {
        Point beforeMove,tmp = this.head.getLocation();
        int head_bodyDistance = 0;


        switch (this.d){
            case up -> {
                this.head.setLocation(this.head.getX(),this.head.getY()-measure);
                for(int i = 0; i < this.bodyN; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x,tmp.y+head_bodyDistance);
                    tmp = beforeMove;

                }
            }
            case right -> {
                this.head.setLocation(this.head.getX()+measure,this.head.getY());
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x-head_bodyDistance,tmp.y);
                    tmp = beforeMove;

                }
            }
            case down -> {
                this.head.setLocation(this.head.getX(),this.head.getY()+measure);
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x,tmp.y-head_bodyDistance);
                    tmp = beforeMove;

                }
            }
            case left -> {

                this.head.setLocation(this.head.getX()-measure,this.head.getY());
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x+head_bodyDistance,tmp.y);
                    tmp = beforeMove;

                }
            }
            default -> {
                System.out.println("error, default in move()");
            }
        }


        this.justTurned = false;

    }
    public void initializeNewNode(){
        this.body[bodyN-1] = new JLabel(new ImageIcon(this.bodySrc));
        switch (this.d){
            case up -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX(),this.body[bodyN-2].getY()+measure,measure,measure);
            }
            case right -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX()-measure,this.body[bodyN-2].getY(),measure,measure);
            }
            case down -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX(),this.body[bodyN-2].getY()-measure,measure,measure);
            }
            case left -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX()+measure,this.body[bodyN-2].getY(),measure,measure);
            }
        }
    }
    public void changeDirection(){ //@todo fix change of X IF CHANGING FROM TOP-DOWN TO LEFT-RIGHT (AND CONSEQUENCES)
        this.d = direction.right;

        switch(this.d){

        }
    }
    public void startGame(){

    }
}