package main_package;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {
//37 left, 38 up, 39 right, 40 down
    Snake s;
    boolean replay = false;
    Listener(Snake a){
        this.s = a;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }


    @SuppressWarnings("SuspiciousIndentAfterControlStatement")
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode()==10){
            replay=true;
            return;
        }
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
