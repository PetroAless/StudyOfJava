package main_package.snake.src;

import javax.swing.*;
import java.awt.*;



public class Frame extends JFrame{//setting up a frame class with my functions to work with jframe and else
    JFrame frame;//basic frame
    Frame(int width,int height){ //easy constructor with size
        frame = new JFrame("Frame Demo");
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation
    }

    void add(JComponent c){  //function to simply add a component easily, without writing f.frame.add(c)
        frame.add(c);
    }
    void render(){  //function to just render and other things to do at the end
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
    void setFrame(){
        frame.getContentPane().setBackground(Color.black);
    }
    public static void main(String[] args) {
        Frame f = new Frame(1200,900);
        f.setFrame();

        f.pack();
        f.render();//show the frame
    }
}