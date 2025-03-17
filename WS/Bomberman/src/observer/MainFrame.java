package observer;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel[][] labels = new JLabel[11][17];
    
    private Controlador controlador;
    
    private String ultimaDir="";
    private int anim=1;
    
    private boolean finished = false;
    
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
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        
        contentPane = new JPanel(){
            
			private static final long serialVersionUID = 1L;
			private Image imagen = new ImageIcon(MainFrame.class.getResource("/Imgs/stageBack1.png")).getImage(); // Carga la imagen

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(11, 17));
        
        setContentPane(contentPane);
        
        for (int i = 0; i < 11; i++) 
        {
            for (int j = 0; j < 17; j++) 
            {
            	labels[i][j] = new JLabel("");
            	contentPane.add(labels[i][j]);
            }
        }
        
    }
	
	//Se llama a un metodo que actualiza la vista en funcion del argumento recibido
    @Override
	public void update(Observable o, Object arg) 
	{
    	String msg = arg.toString();
    	
		if(msg.startsWith("Bloque"))
		{
			actualizarBloque(msg);
		}
		else if(msg.startsWith("Bomber"))
		{
			actualizarBomber(msg);
		}
		else if(msg.startsWith("Bomba"))
		{
			actualizarBomba(msg);
		}
		else if(msg.startsWith("Dead") && !finished)
		{
			actualizarMuerte(msg);
		}
	}
    
    private void actualizarBloque(String msg)
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
			String animacion = "1";
			
			if(msg.startsWith("BloqueArdiendoA"))
			{
				animacion = split[3];
			}
			
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/kaBomb" + animacion + ".png")));
		}
    }
    
    private void actualizarBomber(String msg)
    {
    	String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		String direccion = split[3];
		boolean hayBomba = Boolean.valueOf(split[4]);
		
		if(ultimaDir.equals(direccion))
		{
			anim++;
			if((direccion.equals("Abajo") && anim>4) || anim>5)
			{
				anim = 1;
			}
		}
		else 
		{
			anim = 1;
			ultimaDir = direccion;
		}
		
		String icono ="";
		if(hayBomba) 
		{
			icono = "/Imgs/bomb1.png";
		}
		
		if(direccion.equals("Izquierda"))
		{
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteleft" + String.valueOf(anim) + ".png")));
			labels[i][j+1].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
		}
		else if(direccion.equals("Arriba"))
		{
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteup" + String.valueOf(anim) + ".png")));
			labels[i+1][j].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
		}
		else if(direccion.equals("Abajo"))
		{
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitedown" + String.valueOf(anim) + ".png")));
			labels[i-1][j].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
		}
		else
		{
			if(direccion.equals("Derecha"))
			{
				labels[i][j-1].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
			}
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteright" + String.valueOf(anim) + ".png")));
		}
    }
    
    private void actualizarBomba(String msg)
    {
    	String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		
		labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitewithbomb1.png")));
    }
    
    private void actualizarMuerte(String msg)
    {
    	finished = true;
		String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		
		labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/onFire1.png")));
		
		Dead_Window jf = new Dead_Window();
		jf.setVisible(true);
    }
    
    
    private class Controlador 
    {
    	public Controlador()
    	{
    		addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    inicializarListener();
                    iniciar();
                }
            });
    	}
    	
		public void inicializarListener()
	    {
	    	addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	            	
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
	        });
	    }
		
		public void iniciar()
		{
			observable.MatrizBloques.getMB().inicializarPantallaClasica();
			observable.Bomberman.getBom().notificarPosicion("Inicio",false);
		}
	}
}