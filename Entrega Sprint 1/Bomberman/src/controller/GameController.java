package controller;

import model.Bomberman;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameController extends WindowAdapter implements KeyListener {
	Bomberman bombman;
	public GameController(Bomberman player) {
		bombman = player;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_UP) 
        {
        	model.Bomberman.getBom().moverArriba();
        } 
        else if (keyCode == KeyEvent.VK_DOWN) 
        {
        	model.Bomberman.getBom().moverAbajo();
        } 
        else if (keyCode == KeyEvent.VK_LEFT) 
        {
        	model.Bomberman.getBom().moverIzquierda();
        } 
        else if (keyCode == KeyEvent.VK_RIGHT) 
        {
        	model.Bomberman.getBom().moverDerecha();
        }
        else if (keyCode == KeyEvent.VK_B) 
        {
        	model.Bomberman.getBom().soltarBomba();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
    public void windowOpened(WindowEvent e) 
     {
		model.MatrizBloques.getMB().inicializarPantallaClasica();
		model.Bomberman.getBom().notificarPosicion("Inicio",false);
     }
}
