package main_package;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {
//37 left, 38 up, 39 right, 40 down
    Snake s; //snake to set its direction
    Listener(Snake a){
        this.s = a;
    } //simple constructor
    @Override
    public void keyTyped(KeyEvent e) {

    }


    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    @Override
    public void keyPressed(KeyEvent e) {  //main function to check for input, sets a defined direction only if the opposite direction isn't happening
        switch(e.getKeyCode()){
            case 37-> {
                if(s.d != Snake.direction.right)
                s.d = Snake.direction.left;
            }
            case 38-> {
                if(s.d != Snake.direction.down)
                s.d = Snake.direction.up;
            }
            case 39-> {
                if(s.d != Snake.direction.left)
                s.d = Snake.direction.right;
            }
            case 40-> {
                if(s.d != Snake.direction.up)
                s.d = Snake.direction.down;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
