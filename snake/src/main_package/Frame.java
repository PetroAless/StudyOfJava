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
    Random r = new Random();
    Frame(int width,int height,Color c){ //easy constructor with size
        f = new JFrame("Snake");
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
        f.add(panel);
    }

    public void setApple(){
        apple = new JLabel(new ImageIcon("src/resources/apple.png"));
        apple.setBounds(0,0,10,10);
    }


    public void randomizePositionOfApple(){
        int x = r.nextInt(f.getWidth())-10;
        int y = r.nextInt(f.getHeight())-10;
        this.apple.setLocation(x,y);
        System.out.println("x="+x+";y="+y);
    }

    public void addAllComponents(){
        panel.add(apple);
    }

    void render() {  //function to just render and other things to do at the end

        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        randomizePositionOfApple();

        repaint();
    }
    public static void main(String[] args) {




        java.awt.EventQueue.invokeLater(() -> {
            Frame fr = new Frame(800,600,null);
            fr.randomizePositionOfApple();
            fr.render();//show the frame
            Listener l = new Listener();


            Timer t = new Timer(100,fr);
            t.start();
        });




    }


}