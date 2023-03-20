import javax.swing.*;
public class Frame extends JFrame{
    JFrame frame;
    Frame(int width,int height){
        frame = new JFrame("Frame Demo");
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void add(JComponent c){
        frame.add(c);
    }
    public static void main(String[] args) {
        Frame f = new Frame(1200,900);


        JTextField text = new JTextField("testo di prova");
        text.setBounds(500,200,200,80);

        JButton btn = new JButton("button");
        btn.setBounds(550,300,100,40);

        f.add(text);
        f.add(btn);
        f.frame.setLayout(null);
        f.frame.setVisible(true);
    }
}