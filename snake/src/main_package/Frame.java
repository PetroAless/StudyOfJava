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
        start(width,height);
    }
    public void start(int width,int height){
        f = new JFrame("Snake");
        f.setSize(width,height);
        f.setLocation(600,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation

        this.setPanel();
        this.randomizePositionOfApple();
        this.render();
        this.listenKeys();
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

    Listener l;
    public void listenKeys(){
        l = new Listener(this.s);

        f.addKeyListener(l);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(checkCollision()){
            this.randomizePositionOfApple();
        }
        if(this.s.collisionWithSelf()){
            this.panel.removeAll();
            JLabel lose = new JLabel("YOU DIED");
            lose.setFont(lose.getFont().deriveFont(50f));
            lose.setBounds(150,0,300,300);
            this.panel.add(lose);
            JButton pressMe = new JButton("replay?");
            pressMe.setFont((lose.getFont().deriveFont(30f)));
            pressMe.setBounds(200,200,150,70);
            this.panel.add(pressMe);


            this.panel.repaint();

            pressMe.addActionListener(e1 -> {
                f.setVisible(false);
                start(600,600);
            });


        }

        s.teleport();
        this.s.move();
        repaint();
    }


    int frame = 100;
    Timer t;
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Frame fr = new Frame(600,600);


            fr.t = new Timer(fr.frame,fr);
            fr.t.start();

        });

    }

}