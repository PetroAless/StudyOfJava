package main_package;
import javax.swing.JLabel;

import java.awt.Point;

import javax.swing.ImageIcon;
public class Snake {
    public enum direction {up,right,down,left}
    JLabel head, body[] = new JLabel[900];
    direction d = direction.down;
    String headSrc = "../resources/head.png";
    String bodySrc = "../resources/dot.png";

    //head x, head y
    int bodyN = 3;//number of bodies

    Snake(){
        head = new JLabel(new ImageIcon(this.headSrc));
        for (int i = 0; i < bodyN; i++) {
            body[i] = new JLabel(new ImageIcon(this.bodySrc));
        }
        setBounds();
    }
    public void setBounds(){
        head.setBounds(100,100,10,10);
        for (int i = 0; i < this.bodyN; i++) {
            body[i].setBounds(head.getX(),head.getY(),10,10);
        }
    }

    public void move(boolean justChangedD) {
        Point beforeMove,tmp = this.head.getLocation();
        int head_bodyDistance = 5;
        if(justChangedD){
            head_bodyDistance = 0;
        }else{
            head_bodyDistance = 5;
        }

        switch (this.d){
            case up -> {
                this.head.setLocation(this.head.getX(),this.head.getY()-5);
                for(int i = 0; i < this.bodyN; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x,tmp.y+head_bodyDistance);
                    tmp = beforeMove;

                }
            }
            case right -> {
                this.head.setLocation(this.head.getX()+5,this.head.getY());
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x-head_bodyDistance,tmp.y);
                    tmp = beforeMove;

                }
            }
            case down -> {
                this.head.setLocation(this.head.getX(),this.head.getY()+5);
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getLocation();
                    body[i].setLocation(tmp.x,tmp.y-head_bodyDistance);
                    tmp = beforeMove;

                }
            }
            case left -> {

                this.head.setLocation(this.head.getX()-5,this.head.getY());
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




    }
    public void initializeNewNode(){
        this.body[bodyN-1] = new JLabel(new ImageIcon(this.bodySrc));
        switch (this.d){
            case up -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX(),this.body[bodyN-2].getY()+10,10,10);
            }
            case right -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX()-10,this.body[bodyN-2].getY(),10,10);
            }
            case down -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX(),this.body[bodyN-2].getY()-10,10,10);
            }
            case left -> {
                this.body[bodyN-1].setBounds(this.body[bodyN-2].getX()+10,this.body[bodyN-2].getY(),10,10);
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