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
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel[][] labels = new JLabel[11][17];
    
    private Controlador controlador;
    
    private String ultimaDir="";
    private int anim=1;
    private String ultimaAnimBomber="";
    private boolean finished = false;
    private Timer[][] timers;
    
    
    
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
		timers = new Timer[11][17];
    	controlador = getControlador();
    	addKeyListener(controlador);
    	addWindowListener(controlador);
    	
    	observable.MatrizBloques.getMB().addObserver(this);
    	observable.BombermanBlanco.getBom().addObserver(this);
    	observable.MatrizEnemigos.getME().addObserver(this);
    	
    	inicializarVista();
    }
	
	private void inicializarVista()
	{
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
		else if(msg.startsWith("PeriodoBomber"))
		{
			actualizacionPeriodicaBomber(msg);
		}
		else if(msg.startsWith("Bomba"))
		{
			actualizarBomba(msg);
		}
		else if(msg.startsWith("PeriodoBomba"))
		{
			actualizacionPeriodicaBomba(msg);
		}
		else if(msg.startsWith("Enemigo"))
		{
			actualizarEnemigo(msg);
		}
		else if(msg.startsWith("Dead") && !finished)
		{
			actualizarMuerte(msg);
		} 
		else if(msg.startsWith("Win") && !finished)
		{
			actualizarVictoria();
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
			labels[i][j].setIcon(null);
		}
		else if(msg.startsWith("BloqueArdiendo"))
		{
			int animacion = 1;
			if(msg.startsWith("BloqueArdiendoA"))
			{
				animacion = Integer.valueOf(split[3]);
			} 
			else 
			{
				crearTimer(i,j);
			}
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/kaBomb" + String.valueOf(animacion) +".png")));
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
			ultimaAnimBomber = "/Imgs/whiteleft" + String.valueOf(anim) + ".png";
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteleft" + String.valueOf(anim) + ".png")));
			labels[i][j+1].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
		}
		else if(direccion.equals("Arriba"))
		{
			ultimaAnimBomber = "/Imgs/whiteup" + String.valueOf(anim) + ".png";
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteup" + String.valueOf(anim) + ".png")));
			labels[i+1][j].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
		}
		else if(direccion.equals("Abajo"))
		{
			ultimaAnimBomber = "/Imgs/whitedown" + String.valueOf(anim) + ".png";
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitedown" + String.valueOf(anim) + ".png")));
			labels[i-1][j].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
		}
		else
		{
			ultimaAnimBomber = "/Imgs/whiteright" + String.valueOf(anim) + ".png";
			if(direccion.equals("Derecha"))
			{
				labels[i][j-1].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
			}
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteright" + String.valueOf(anim) + ".png")));
		}
    }
    
    private void actualizacionPeriodicaBomber(String msg)
    {
    	String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		
		labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource(ultimaAnimBomber)));
    }
    
    private void actualizarBomba(String msg)
    {
    	String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		
		ultimaAnimBomber = "/Imgs/whitewithbomb1.png";
		labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitewithbomb1.png")));
    }
    
    private void actualizacionPeriodicaBomba(String msg)
    {
    	String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		
		labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/bomb1.png")));
    }
    
    private void actualizarEnemigo(String msg)
    {
    	String[] split = msg.split(",");
		int i = Integer.valueOf(split[1]);
		int j = Integer.valueOf(split[2]);
		String tipo = split[3];
		String animacion = split[4];
		
		labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/" + tipo + String.valueOf(animacion) + ".png")));
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
    
    private void actualizarVictoria()
    {
    	finished = true;
    	
    	Win_Window jf = new Win_Window();
    	jf.setVisible(true);
    }
    
    //Se crea un timer para la animacion del bloque ardiendo de la fila pI y columna pJ
    private void crearTimer(int pI, int pJ)
    {
    	TimerTask timerTask = new TimerTask() {
    		int animacion = 1;
			@Override
			public void run() 
			{
				animacion++;
				if(animacion<=5)
				{
					actualizarBloque("BloqueArdiendoA," + String.valueOf(pI) + "," + String.valueOf(pJ) + "," + String.valueOf(animacion));
				}
				else
				{
					timers[pI][pJ].cancel();
					timers[pI][pJ] = null;
				}
			}		
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 400);
		if(timers[pI][pJ] != null)
		{
			timers[pI][pJ].cancel();
			timers[pI][pJ] = null;
		}
		timers[pI][pJ] = timer;
    }
   
    
    
    private Controlador getControlador() {
		if (controlador == null) 
		{
			controlador = new Controlador();
		}
		return controlador;
	}
    
    private class Controlador extends WindowAdapter implements KeyListener 
    {
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			int keyCode = e.getKeyCode();
            
            if (keyCode == KeyEvent.VK_UP) 
            {
            	observable.BombermanBlanco.getBom().moverArriba();
            } 
            else if (keyCode == KeyEvent.VK_DOWN) 
            {
            	observable.BombermanBlanco.getBom().moverAbajo();
            } 
            else if (keyCode == KeyEvent.VK_LEFT) 
            {
            	observable.BombermanBlanco.getBom().moverIzquierda();
            } 
            else if (keyCode == KeyEvent.VK_RIGHT) 
            {
            	observable.BombermanBlanco.getBom().moverDerecha();
            }
            else if (keyCode == KeyEvent.VK_B) 
            {
            	observable.BombermanBlanco.getBom().soltarBomba();
            }
		}

		@Override
		public void keyReleased(KeyEvent e) {}
	
		@Override
        public void windowOpened(WindowEvent e) 
         {
			observable.Facade.getFacade().iniciarPartida("Classic","Blanco");
         }
    }
}