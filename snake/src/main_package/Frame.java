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
        s.setBounds();
        panel.add(s.head);
        panel.add(s.body[0]);
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

    public boolean checkCollision(){ //
        int tmp = 0;
        if(s.top)tmp=1;
        if(s.right)tmp=2;
        if(s.bottom)tmp=3;
        if(s.left)tmp=4;
        switch(tmp){
            case 1:
        if(
                ( this.s.hy -5 <= this.apple.getY() +5 && this.s.hy -5 >= this.apple.getY() )
                    &&
                ( this.s.hx == this.apple.getX() )
        )
                return true;
                break;
            case 2:
        if(

                ( this.s.hx +5 <= this.apple.getX() && this.s.hx +5 >= this.apple.getX() -5 )
                    &&
                ( this.s.hy == this.apple.getY() )

        )
                return true;
                break;
            case 3:
        if(
                ( this.s.hy +5 <= this.apple.getY() && this.s.hy +5 >= this.apple.getY() -5 )
                    &&
                ( this.s.hx == this.apple.getX() )
        )
                return true;
                break;
            case 4:
        if(
                ( this.s.hx -5 <= this.apple.getX() +5 && this.s.hx -5 >= this.apple.getX() )
                    &&
                ( this.s.hy == this.apple.getY() )
        )
                return true;
                break;
            default :
                System.out.println("error, collision checking in default mode");
                break;
        }
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        this.s.setXY(this.s.hx,this.s.hy);
        if(checkCollision()){
            this.randomizePositionOfApple();
        }
        repaint();
        this.s.hy+=5;
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