import javax.swing.*;


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


        JTextField text = new JTextField("testo di prova");
        text.setBounds(500,200,200,80);


        JButton btn = new JButton("button");
        btn.setBounds(550,300,100,40);
        btn.addActionListener(e-> {  //add event action listener, creating nested functions? idk lmao
            text.setText("IL BOTTONE FUNZIONA");
        });


        f.add(text);//add elements
        f.add(btn);

        f.render();//show the frame
    }
}