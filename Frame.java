package javatries;
import javatries.Listener;
import java.util.Random;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class Frame extends JFrame{
	JFrame f;
	JLabel apple;
	Random r = new Random();
	Frame(int width,int height,Color backColor){
		f = new JFrame("frame demo");
		f.setSize(width,height);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setColor(backColor);

	}
	public void setColor(Color c){
		if(c==null){
			f.getContentPane().setBackground(Color.black);
		}else{
			f.getContentPane().setBackground(c);	
		}
	}
	public void adder(Component c){
		f.add(c);
	}
	public void render(){
		f.setLayout(null);
		
		f.setVisible(true);
	}
	public void addAll(){
		f.add(apple);

	}
	public void setApple(){
		apple = new JLabel(new ImageIcon("apple.png"));
		apple.setBounds(0,0,10,10);

	}
	public void randomizePositionOfApple(){
		int x = r.nextInt(700);
		int y = r.nextInt(500);
		
		
	}
	public void start(){

	}




	

	public static void main(String[] args){
		Frame f = new Frame(700,500,Color.white);
		Listener l = new Listener();
		/*
		Timer t = new Timer(140,null);
		t.start();*/
		JLabel lbl = new JLabel();
		lbl.addKeyListener(l);
		f.add(lbl);
		f.render();
		
	}
}
