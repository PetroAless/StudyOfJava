package main_package;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.ImageIcon;


public class Snake {
    public enum direction {up,right,down,left} //enum for the direction it is going
    JLabel head; //head and body parts ar labels, this is subjective, could be changed i think, but i prefer labels
    JLabel[] body = new JLabel[900]; //length of 900 is a lot i know, but i wasn't sure if a list is implementable
    direction d = direction.right; //initial direction is right
    String headSrc = "src/resources/head.png"; //the sources of both parts
    String bodySrc = "src/resources/body.png";
    int measure,frameSizes; //measure and frameSizes of body parts and the frame
    int bodyN = 3;//number of bodies

    Snake(int width_height,int frameSize){ //a simple constructor for: setting sizes, and the new head and the body, plus setting the locations
        this.measure = width_height;
        this.frameSizes = frameSize;
        head = new JLabel(new ImageIcon(this.headSrc));
        for (int i = 0; i < bodyN; i++) {
            body[i] = new JLabel(new ImageIcon(this.bodySrc));
        }
        setBounds();
    }

    public void setBounds(){ //sets locations and sizes,
        head.setBounds(frameSizes/2,frameSizes/2,measure,measure);
        for (int i = 0; i < this.bodyN; i++) {
            body[i].setBounds(head.getX()-measure,head.getY(),measure,measure);
        }
    }
    public void teleport(){ //"teleport" is just a funny way for the checking of the snake going out of screen and coming out the other size
        if(this.head.getX()<0) { // this is also subjective, some think the snake should die if it goes out of screen, i prefer this way
            this.head.setLocation(frameSizes, this.head.getY());
        }
        if(this.head.getX()>frameSizes){
            this.head.setLocation(0,this.head.getY());
        }
        if(this.head.getY()<0){
            this.head.setLocation(this.head.getX(),frameSizes);
        }
        if(this.head.getY()>frameSizes){
            this.head.setLocation(this.head.getX(),0);
        }
    }
    public void move() { //main function to move the snake, just changes the location of the head, and then changes the location of all body parts, in the spot
        Point beforeMove,tmp = this.head.getLocation(); // of the body part ahead
        int head_bodyDistance = 0;


        switch (this.d){
            case up -> {
                this.head.setLocation(this.head.getX(),this.head.getY()-measure); //i don't think there is a need to explain this, just move the head in the direction
                for(int i = 0; i < this.bodyN; i++){ //         it is going, then save the location of the [i] part and then set it as the [i+1] location
                    beforeMove = body[i].getLocation(); //          and so on and so on
                    body[i].setLocation(tmp.x,tmp.y+head_bodyDistance);
                    tmp = beforeMove;
//                                                                  this for all directions with minor changes
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
            default -> System.out.println("error, default in move()"); //just a small check
        }

    }
    public void initializeNewNode(){ //this is for when the snake hits an apple, and gets a new length
        this.body[bodyN-1] = new JLabel(new ImageIcon(this.bodySrc));
        switch (this.d){
            case up ->      this.body[bodyN-1].setBounds(this.body[bodyN-2].getX(),
                                    this.body[bodyN-2].getY()+measure,measure,measure); //not gonna explain, just a simple setting of bounds based on direction

            case right ->   this.body[bodyN-1].setBounds(this.body[bodyN-2].getX()-measure,
                                        this.body[bodyN-2].getY(),measure,measure);

            case down ->    this.body[bodyN-1].setBounds(this.body[bodyN-2].getX(),
                                    this.body[bodyN-2].getY()-measure,measure,measure);

            case left ->    this.body[bodyN-1].setBounds(this.body[bodyN-2].getX()+measure,
                                        this.body[bodyN-2].getY(),measure,measure);
        }
    }
    public boolean collisionWithSelf(){ // collision with self to check if i lost, or the bug occurs
        for (int i = 0; i < this.bodyN; i++) {
            if(this.body[i].getX() == this.head.getX()
                    &&
                this.body[i].getY() == this.head.getY()
            ){
                return true;
            }
        }
        return false;
    }
}