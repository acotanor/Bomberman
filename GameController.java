package observer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class GameController extends WindowAdapter implements KeyListener 
{
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_UP) 
        {
        	observable.Bomberman.getBom().moverArriba();
        } 
        else if (keyCode == KeyEvent.VK_DOWN) 
        {
        	observable.Bomberman.getBom().moverAbajo();
        } 
        else if (keyCode == KeyEvent.VK_LEFT) 
        {
        	observable.Bomberman.getBom().moverIzquierda();
        } 
        else if (keyCode == KeyEvent.VK_RIGHT) 
        {
        	observable.Bomberman.getBom().moverDerecha();
        }
        else if (keyCode == KeyEvent.VK_B) 
        {
        	observable.Bomberman.getBom().soltarBomba();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
    public void windowOpened(WindowEvent e) 
     {
		observable.MatrizBloques.getMB().inicializarPantallaClasica();
		observable.Bomberman.getBom().notificarPosicion("Inicio",false);
     }
}
