package observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel[][] labels = new JLabel[11][17];
    
    private Controlador controlador;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public MainFrame() 
    {
    	controlador = new Controlador();
    	
    	observable.MatrizBloques.getMB().addObserver(this);
    	observable.Bomberman.getBom().addObserver(this);
    	
    	inicializarListener();
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        
        contentPane = new JPanel();
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(11, 17));
        contentPane.setBackground(Color.BLUE);
        
        setContentPane(contentPane);
        
        for (int i = 0; i < 11; i++) 
        {
            for (int j = 0; j < 17; j++) 
            {
            	labels[i][j] = new JLabel("");
            	contentPane.add(labels[i][j]);
            }
        }
        
        controlador.iniciar();
    }

    private void inicializarListener()
    {
    	addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                // Detectar las teclas de flechas y pasar al controlador
                if (keyCode == KeyEvent.VK_UP) {
                    controlador.moverArriba();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    controlador.moverAbajo();
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    controlador.moverIzquierda();
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    controlador.moverDerecha();
                }
                else if (keyCode == KeyEvent.VK_B) {
                    controlador.ponerBomba();
                }
            }
        });
    }
    
    @Override
	public void update(Observable o, Object arg) 
	{
    	String msg = arg.toString();
    	
		if(msg.startsWith("Bloque"))
		{
			String[] split = msg.split(",");
			int i = Integer.valueOf(split[1]);
			int j = Integer.valueOf(split[2]);
			
			if(msg.startsWith("BloqueDuro"))
			{
            	labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/hard5.png")));
			}
			else if(msg.startsWith("BloqueBlando"))
			{
            	labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/soft1.png")));
			}
			else if(msg.startsWith("BloqueVacio"))
			{
				labels[i][j].setIcon(new ImageIcon());
			}
			else if(msg.startsWith("BloqueArdiendo"))
			{
				labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/kaBomb0.png")));
			}
		}
		else if(msg.startsWith("Bomber"))
		{
			String[] split = msg.split(",");
			int i = Integer.valueOf(split[1]);
			int j = Integer.valueOf(split[2]);
			String direccion = split[3];
			
			if(direccion.equals("Izquierda"))
			{
				labels[i][j+1].setIcon(new ImageIcon());
				labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteleft1.png")));
			}
			else if(direccion.equals("Arriba"))
			{
				labels[i+1][j].setIcon(new ImageIcon());
				labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteup1.png")));
			}
			else if(direccion.equals("Abajo"))
			{
				labels[i-1][j].setIcon(new ImageIcon());
				labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitedown1.png")));
			}
			else
			{
				if(direccion.equals("Derecha"))
				{
					labels[i][j-1].setIcon(new ImageIcon());
				}
				labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteright1.png")));
			}
		}
		else if(msg.startsWith("Bomba"))
		{
			String[] split = msg.split(",");
			int i = Integer.valueOf(split[1]);
			int j = Integer.valueOf(split[2]);
			
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitewithbomb1.png")));
		}
	}

    private Controlador getControlador() {
		if (controlador == null) 
		{
			controlador = new Controlador();
		}
		return controlador;
	}
    
    private class Controlador implements ActionListener 
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
				
		}
		
		public void iniciar()
		{
			observable.MatrizBloques.getMB().inicializarPantallaClasica();
			observable.Bomberman.getBom().notificarPosicion("Inicio");
		}
		
		public void moverIzquierda()
		{
			observable.Bomberman.getBom().moverIzquierda();
		}
		
		public void moverDerecha()
		{
			observable.Bomberman.getBom().moverDerecha();
		}
		
		public void moverArriba()
		{
			observable.Bomberman.getBom().moverArriba();
		}
		
		public void moverAbajo()
		{
			observable.Bomberman.getBom().moverAbajo();
		}
		
		public void ponerBomba()
		{
			observable.Bomberman.getBom().soltarBomba();
		}
	}
}