package main_package;

import javax.swing.*;
import java.awt.*;
import java.util.Random;



public class Frame extends JFrame{//setting up a frame class with my functions to work with jframe and else
    JFrame f;//basic frame
    JPanel panel;
    JLabel apple;
    Random r = new Random();
    Frame(int width,int height,Color c){ //easy constructor with size
        f = new JFrame("Frame Demo");
        f.setSize(width,height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation

        setPanel(c);
        addAllComponents();

    }
    void setPanel (Color colorOrNull){
        panel = new JPanel(null);

        colorOrNull = colorOrNull!=null ? colorOrNull : Color.black;
        panel.setBackground(colorOrNull);

        panel.setBounds(0,0,f.getWidth(),f.getHeight());

        setApple();
        panel.add(apple);
        add(panel);
    }

    void add(JComponent c){  //function to simply add a component easily, without writing f.frame.add(c)
        f.add(c);
    }

    public void setApple(){
        apple = new JLabel(new ImageIcon("src/resources/apple.png"));
        apple.setBounds(0,0,10,10);
    }


    public void randomizePositionOfApple(){
        int x = r.nextInt(f.getWidth());
        int y = r.nextInt(f.getHeight());
        this.apple.setLocation(x,y);
        System.out.println("x="+x+";y="+y);
    }

    public void addAllComponents(){
        panel.add(apple);
    }

    void render() {  //function to just render and other things to do at the end
        f.setVisible(true);
    }

    public static void main(String[] args) {
        Frame fr = new Frame(800,600,null);
        fr.randomizePositionOfApple();




        Listener l = new Listener();
        fr.pack();
        fr.render();//show the frame
    }
}