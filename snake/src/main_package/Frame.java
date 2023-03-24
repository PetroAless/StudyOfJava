package main_package;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;



public class Frame extends JFrame implements ActionListener {//setting up a frame class with my functions to work with jframe and else
    JFrame f;//basic frame
    JPanel panel;
    JLabel apple;String appleSrc = "src/resources/apple.png";
    Snake s;
    Random r = new Random();
    int measureUnit = 30;
    Frame(int width,int height){ //easy constructor with size
        f = new JFrame("Snake");
        f.setSize(width,height);
        f.setLocation(600,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation

        setPanel();


    }

    void prepareSnake(){//for cleaner code, preparing snake's attributes
        s = new Snake(measureUnit,this.f.getWidth());
        panel.add(s.head);
        for (int i = 0; i < s.bodyN; i++) {
            panel.add(s.body[i]);
        }
    }

    void setPanel (){//preparing panel and all parts
        ImageIcon bgImg = new ImageIcon("src/resources/tmp.png");
        panel = new JPanel(null){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(bgImg.getImage(),0,0,null);
            }
        };





        panel.setBounds(0,0,f.getWidth(),f.getHeight());

        prepareComponentsInPanel();

        f.add(panel);
    }

    public void prepareComponentsInPanel(){//for cleaner code, preparing all components like snake and apple
        prepareSnake();
        setApple();
    }

    public void setApple(){//preparing apple and all attributes
        apple = new JLabel(new ImageIcon(this.appleSrc));
        apple.setBounds(0,0,measureUnit,measureUnit);
        panel.add(apple);
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void randomizePositionOfApple(){//randomizing the position, function for later
        int x = r.nextInt(panel.getWidth()-measureUnit);
        x = Math.round(x/measureUnit)*measureUnit;
        int y = r.nextInt(panel.getHeight())-measureUnit;
        y = Math.round(y/measureUnit)*measureUnit;
        this.apple.setLocation(x,y);
    }

    void render() {  //function to just render and other things to do at the end

        f.setVisible(true);
    }

    public boolean checkCollision(){
        boolean res = false;
        int error = measureUnit;
        switch(s.d){
            case up-> {
                if (
                        (this.s.head.getY() - error <= this.apple.getY() + error
                                &&
                        this.s.head.getY() - error >= this.apple.getY())
                                &&
                        (this.s.head.getX() == this.apple.getX())
                )
                    res = true;
            }
            case right-> {
                if (

                        (this.s.head.getX() + error <= this.apple.getX()
                            &&
                        this.s.head.getX() + error >= this.apple.getX() - error)
                                &&
                        (this.s.head.getY() == this.apple.getY())

                )
                    res = true;
            }

            case down-> {
                if (
                        (this.s.head.getY() + error <= this.apple.getY()
                            &&
                        this.s.head.getY() + error >= this.apple.getY() - error)
                                &&
                        (this.s.head.getX() == this.apple.getX())
                )
                    res = true;
            }

            case left-> {
                if (
                        (this.s.head.getX() - error <= this.apple.getX() + error
                            &&
                        this.s.head.getX() - error >= this.apple.getX())
                                &&
                        (this.s.head.getY() == this.apple.getY())
                )
                    res = true;
            }

            default -> System.out.println("error, collision checking in default mode");
        }
        if(res){
            s.bodyN++;
            s.initializeNewNode();
            panel.add(s.body[s.bodyN-1]);
        }

        return res;
    }

    public void listenKeys(){
        Listener l = new Listener(this.s);

        f.addKeyListener(l);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        listenKeys();
        if(checkCollision()){
            this.randomizePositionOfApple();
            if(tmp){
                this.t = new Timer(--this.frame,this);
                tmp=false;
            }else{
                tmp=true;
            }
        }
        if(this.s.collisionWithSelf()){
            JLabel lose = new JLabel("U lost");
            lose.setBounds(100,100,200,200);
            this.panel.add(lose);
            return;
        }
        s.teleport();

        this.s.move();
        repaint();


    }

    boolean tmp = true;
    int frame = 100;
    Timer t;
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Frame fr = new Frame(600,600);
            fr.randomizePositionOfApple();


            fr.render();//show the frame

            fr.t = new Timer(fr.frame,fr);
            fr.t.start();
        });

    }

}