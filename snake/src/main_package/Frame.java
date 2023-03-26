package main_package;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;



public class Frame implements ActionListener {
    JFrame f;//basic frame that is gonna show all the components and the panel
    JPanel panel; //panel for encapsulation of components
    JLabel apple;String appleSrc = "src/resources/apple.png"; //the apple and it's source position gonna change in zip for usability out of intellij
    Snake s; //the snake that is gonna move, see for reference the class Snake.java
    Random r = new Random(); //a Random for randomizing the position of the apple in the panel
    int measureUnit = 30; //the basic unit size of snake body, head and the apple
    Frame(int width,int height){ //a constructor to be called in main
        start(width,height);
    }
    public void start(int width,int height){ //function that initializes a new JFrame, sets size of it, the location, and how it's gonna close
        this.f = new JFrame("Snake");
        this.f.setSize(width,height);
        this.f.setLocation(600,200);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation

        this.setPanel();  //different calls of functions
        this.randomizePositionOfApple();
        this.f.setVisible(true);
        this.listenKeys();
    }



    void setPanel (){//preparing panel and all parts, adding a bgImg, with paintComponent, kind of redundant, could avoid it, but maybe i could add a grid in the future
        ImageIcon bgImg = new ImageIcon("src/resources/tmp.png");
        panel = new JPanel(null){//no layout cuz in this situation i need pixel perfect situations, I know it's bad practice, ignore this
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(bgImg.getImage(),0,0,null);
            }
        };

        panel.setBounds(0,0,f.getWidth(),f.getHeight());//size and position

        prepareComponentsInPanel();

        f.add(panel);//adding the panel
    }

    public void prepareComponentsInPanel(){//for cleaner code, preparing all components like snake and apple
        prepareSnake();
        setApple();
    }

    void prepareSnake(){//for cleaner code, preparing snake's attributes and adding all parts into the panel
        s = new Snake(measureUnit,this.f.getWidth());
        panel.add(s.head);
        for (int i = 0; i < s.bodyN; i++) {
            panel.add(s.body[i]);
        }
    }
    public void setApple(){//preparing apple and all attributes and adding it to the panel
        apple = new JLabel(new ImageIcon(this.appleSrc));
        apple.setBounds(0,0,measureUnit,measureUnit);
        panel.add(apple);
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void randomizePositionOfApple(){//randomize position of the apple and setting the location, small bug to fix in the future, check it isn't
                                            //                                                        in the same spot of the body of the snake
        int x = r.nextInt(panel.getWidth()-measureUnit);
        x = Math.round(x/measureUnit)*measureUnit;
        int y = r.nextInt(panel.getHeight())-measureUnit;//simple formula is x = Random(max-sizeOfSnakeHead) -> Round(x/sizeOfSnakeHead)*sizeOfSnakeHead
        y = Math.round(y/measureUnit)*measureUnit;      //using a maximum - size to avoid cases of the apple out of screen, and round to be sure that the apple
        this.apple.setLocation(x,y);                    // is in a spot that is perfectly reachable by the snake and can actually work with checkCollision()
    }

    public boolean checkCollision(){ // function to check collision, probably kind of redundant  to check for every situation, but wanted to be extra sure it could work
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
    public void listenKeys(){ //listenKeys() just starts the listener to listen for key input and control the snake
        l = new Listener(this.s);//btw there is a bug as stated in previous commit, but IDK how to fix it yet, may not gonna fix it :(

        f.addKeyListener(l);
    }

    public void showLostGameScreen(){ //function that shows the lost game screen, needs a bit of style, but i won't do it, too boring part
        this.panel.removeAll(); //it removes all components from panel, adds a label and a button and shows em

        JLabel lose = createLostLabel();
        this.panel.add(lose);

        JButton pressMe = createReplayButton(); //then calls this, which is important for the button action see createReplayButton for reference
        this.panel.add(pressMe);

        this.panel.repaint();
    }
    public JLabel createLostLabel(){ //basic JLabel with simple writing on it
        JLabel lose = new JLabel("YOU DIED");
        lose.setFont(lose.getFont().deriveFont(50f));
        lose.setBounds(150,0,300,300);
        return lose;
    }
    public JButton createReplayButton(){  //replay button that when it is called, it starts a new game, set visible false closes previous frame
        JButton pressMe = new JButton("replay?"); //                then start initializes again the frame and starts the game !
        pressMe.setFont((pressMe.getFont().deriveFont(30f)));
        pressMe.setBounds(200,200,150,70);

        pressMe.addActionListener(e1 -> {
            f.setVisible(false);
            start(600,600);
        });

        return pressMe;
    }
    @Override
    public void actionPerformed(ActionEvent e) {  //the main function of the program, calls every thing important, from the move and "teleport" of the snake
            //                                      to the checkCollision that controls if there is a collision with an apple
            //                                         or the collision with self, to control you don't lose
        if(checkCollision()){
            this.randomizePositionOfApple();
        }
        if(this.s.collisionWithSelf()){
            showLostGameScreen();
        }

        s.teleport();
        this.s.move();
        this.f.repaint();  //repaint for obvious reasons
    }
    //int frame = 100;   //could add a new var called frame, to work on the speed of the game, fro now this function is not created
    Timer t; //same for this
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Frame fr = new Frame(600,600);


            fr.t = new Timer(100,fr);
            fr.t.start();

        });

    }

}