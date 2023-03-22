package main_package;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
public class Snake {
    public enum direction {up,right,down,left};
    JLabel head, body[] = new JLabel[900];
    direction d = direction.down;

    int hx,hy;//head x, head y
    int bodyN = 3;//number of bodies

    Snake(){
        head = new JLabel(new ImageIcon("src/resources/head.png"));
        for (int i = 0; i < bodyN; i++) {
            body[i] = new JLabel(new ImageIcon("src/resources/dot.png"));
        }
        setBounds();
    }
    public void setBounds(){
        head.setBounds(0,0,10,10);
        for (int i = 0; i < this.bodyN; i++) {
            body[i].setBounds(0,0,10,10);
        }
    }

    public void move(){
        int beforeMove,tmp;
        switch (this.d){
            case up -> {
                tmp = this.hy+5;
                this.hy-=5;
                this.head.setLocation(this.hx,this.hy);
                for(int i = 0; i < this.bodyN; i++){
                    beforeMove = body[i].getY()+5;
                    body[i].setLocation(body[i].getX(),tmp);
                    tmp = beforeMove;

                }
            }
            case right -> {
                tmp = this.hx-5;
                this.hx+=5;
                this.head.setLocation(this.hx,this.hy);
                for(int i = 0; i < this.bodyN ; i++){
                    System.out.println(i);
                    beforeMove = body[i].getX()-5;
                    body[i].setLocation(tmp,body[i].getY());
                    tmp = beforeMove;

                }
            }
            case down -> {
                tmp = this.hy-5;
                this.hy+=5;
                this.head.setLocation(this.hx,this.hy);
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getY()-5;
                    body[i].setLocation(body[i].getX(),tmp);
                    tmp = beforeMove;

                }
            }
            case left -> {
                tmp = this.hx+5;
                this.hx-=5;
                this.head.setLocation(this.hx,this.hy);
                for(int i = 0; i < this.bodyN ; i++){
                    beforeMove = body[i].getX()+5;
                    body[i].setLocation(tmp,body[i].getY());
                    tmp = beforeMove;

                }
            }
            default -> {
                System.out.println("error, default in move()");
            }
        }
    }
    public void initializeNewNode(){
        this.body[bodyN-1] = new JLabel(new ImageIcon("src/resources/dot.png"));
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
    }
    public void startGame(){

    }
}