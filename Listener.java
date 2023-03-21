package javatries;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class Listener implements KeyListener{
	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e){
		System.out.println(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e){
		System.out.println(e.getKeyCode());
	}
}