import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Frame extends JFrame{//setting up a frame class with my functions to work with jframe and else
    JFrame frame;//basic frame
    Frame(int width,int height){ //easy constructor with size
        frame = new JFrame("Frame Demo");
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //setting closing operation
    }

    void add(JComponent c){  //function to simply add a component easily, withtout writing f.frame.add(c)
        frame.add(c);
    }
    void render(){  //function to just render and other things to do at the end
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Frame f = new Frame(1200,900);


        JLabel lbl = new JLabel("testo di default");
        lbl.setBounds(0,0,200,80);

        ImageIcon img = new ImageIcon("resources/heart.png");
        Image newImg = img.getImage().getScaledInstance(120,120, Image.SCALE_SMOOTH);
        img = new ImageIcon(newImg);

        JLabel imgLbl = new JLabel(img);

        imgLbl.setBounds(0,0,100,100);


        JButton btn = new JButton("cambia testo");
        btn.setBounds(0,0,100,40);
        btn.addActionListener(e-> {  //add event action listener, creating nested functions? idk lmao
            lbl.setText("il tasto funziona");
            lbl.setBackground(Color.PINK);
            lbl.setOpaque(true);
        });


        f.add(lbl);//add elements
        f.add(btn);
        f.add(imgLbl);
        f.pack();
        f.render();//show the frame
    }
}