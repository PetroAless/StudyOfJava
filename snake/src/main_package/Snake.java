package main_package;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Snake {
    JLabel head, body[] = new JLabel[900];
    int hx,hy,bx,by;//head x, head y, body x, body y
    Snake(){
        head = new JLabel(new ImageIcon("../src/resources/head.png"));
        body[0] = new JLabel(new ImageIcon("../src/resources/dot.png"));
    }
    public void setBounds(){
        head.setBounds(0,0,10,10);
        body[0].setBounds(10,0,10,10);
    }
    public void setXY(int x, int y){
        head.setLocation(x,y);
        body[0].setLocation(head.getX()-10,head.getY());
    }
    public void startGame(){

    }
}