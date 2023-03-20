import javax.swing.*;
import java.awt.*;


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
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Frame f = new Frame(1200,900);


        JLabel label = new JLabel("testo di prova");
        label.setBounds(500,200,200,80);
        JLabel img = new JLabel(new ImageIcon("heart.png"));





        JButton btn = new JButton("button");
        btn.setBounds(550,300,100,40);
        btn.addActionListener(e-> {  //add event action listener, creating nested functions? idk lmao
            label.setText("Giorgia ti amo tanto tanto");
            label.setBackground(Color.PINK);
            label.setOpaque(true);

        });


        f.add(label);//add elements
        f.add(btn);
        f.frame.add(img);
        f.pack();
        f.render();//show the frame
    }
}