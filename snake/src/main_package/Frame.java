package main_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;



public class Frame extends JFrame implements ActionListener {//setting up a frame class with my functions to work with jframe and else
    JFrame f;//basic frame
    JPanel panel;
    JLabel apple;
    Snake s;
    Random r = new Random();
    Frame(int width,int height,Color c){ //easy constructor with size
        f = new JFrame("Snake");
        f.setSize(width,height);
        f.setLocation(600,250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation

        setPanel(c);


    }
    void prepareSnake(){//for cleaner code, preparing snake's attributes
        s = new Snake();
        panel.add(s.head);
        for (int i = 0; i < s.bodyN; i++) {
            panel.add(s.body[i]);
        }
    }

    void setPanel (Color colorOrNull){//preparing panel and all parts
        panel = new JPanel(null);

        colorOrNull = colorOrNull!=null ? colorOrNull : Color.black;
        panel.setBackground(colorOrNull);
        panel.setBounds(0,0,f.getWidth(),f.getHeight());

        prepareComponentsInPanel();

        f.add(panel);
    }

    public void prepareComponentsInPanel(){//for cleaner code, preparing all components like snake and apple
        prepareSnake();
        setApple();
    }

    public void setApple(){//preparing apple and all attributes
        apple = new JLabel(new ImageIcon("src/resources/apple.png"));
        apple.setBounds(0,0,10,10);
        panel.add(apple);
    }


    public void randomizePositionOfApple(){//randomizing the position, function for later
        int x = r.nextInt(f.getWidth()-10);
        int y = r.nextInt(f.getHeight()-10);
        this.apple.setLocation(x,y);
    }



    void render() {  //function to just render and other things to do at the end

        f.setVisible(true);
    }

    public boolean checkCollision(){
        boolean res = false;
        switch(s.d){
            case up-> {
                if (
                        (this.s.hy - 5 <= this.apple.getY() + 5 && this.s.hy - 5 >= this.apple.getY())
                                &&
                        (this.s.hx == this.apple.getX())
                )
                    res = true;
            }
            case right-> {
                if (

                        (this.s.hx + 5 <= this.apple.getX() && this.s.hx + 5 >= this.apple.getX() - 5)
                                &&
                        (this.s.hy == this.apple.getY())

                )
                res = true;
            }

            case down-> {
                if (
                        (this.s.hy + 5 <= this.apple.getY() && this.s.hy + 5 >= this.apple.getY() - 5)
                                &&
                        (this.s.hx == this.apple.getX())
                )
                    res = true;
            }

            case left-> {
                if (
                        (this.s.hx - 5 <= this.apple.getX() + 5 && this.s.hx - 5 >= this.apple.getX())
                                &&
                        (this.s.hy == this.apple.getY())
                )
                    res = true;
            }

            default -> {
                System.out.println("error, collision checking in default mode");
            }
        }
        if(res){
            System.out.println("COLLISION");
            s.bodyN++;
            s.initializeNewNode();
            panel.add(s.body[s.bodyN-1]);
        }

        return res;
    }
    @Override
    public void actionPerformed(ActionEvent e) {


        this.s.move();
        if(checkCollision()){
            this.randomizePositionOfApple();
            this.s.changeDirection();
        }
        repaint();

    }







    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            Frame fr = new Frame(300,300,null);
            fr.apple.setLocation(0,200);
            fr.render();//show the frame



            Timer t = new Timer(50,fr);
            t.start();
        });

    }

}