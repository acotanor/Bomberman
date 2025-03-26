package view;

import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import controller.GameController;
import model.Bomberman;
import model.MatrizBloques;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel[][] labels = new JLabel[11][17];
    private int labelWidth;
    private int labelHeight;
    
    private String ultimaDir="";
    private int anim=1;
    private boolean finished = false;
    
    Timer timer0 = new Timer(500, e -> resolucionModificada());//Opcional(Reescaling)
    
    public MainFrame(Bomberman bbman, MatrizBloques matriz) {
    	GameController controller = new GameController(bbman);
    	addKeyListener(controller);
        addWindowListener(controller);

        bbman.addObserver(this);
        matriz.addObserver(this);
        inicializarVista();
        setVisible(true);
        
        resolucionModificada();
    }
    	
    public void resolucionModificada() { //Opcional(Reescaling)
    	//Comprobamos si la resolucion ha sido modificada y llamamos o no a reescalar
    	if (!(labels[0][0].getWidth() == labelWidth && labelHeight == labels[0][0].getHeight())) {
    		labelWidth = labels[0][0].getWidth();
    		labelHeight = labels[0][0].getHeight();
    		reescalarTodosLosIconos();  
    	}
    	timer0.start();
    }
    
    private void reescalarTodosLosIconos() {//Opcional(Reescaling)
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                reescalarIcono(labels[i][j]);
            }
        }
    }
    
    private void reescalarIcono(JLabel label) {//Opcional(Reescaling)
        ImageIcon icono = (ImageIcon )label.getIcon();

        if (!(icono == null)) {
            //ImageIcon imageIcon = (ImageIcon) icono;
            Image img = icono.getImage();

            // Avoid invalid dimensions
            if (true) {
                Image scaled = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));
            }
        }
    }
	
	public void inicializarVista()
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
        labelWidth = labels[0][0].getWidth();
        labelHeight = labels[0][0].getHeight();
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
    
    private void setScaledIcon(JLabel label, String resourcePath) {
    	
    	//Es un apaño para que el .png de los bloques este ajustado con respecto a la resolucion de la ventana
    	//System.out.println("resourcePath:");
    	//System.out.println(resourcePath);
        ImageIcon icon = new ImageIcon(MainFrame.class.getResource(resourcePath));
        Image scaled = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
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
			//setScaledIcon(labels[i][j],("/Imgs/whiteleft" + String.valueOf(anim) + ".png"));
		}
		else if(direccion.equals("Arriba"))
		{
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whiteup" + String.valueOf(anim) + ".png")));
			labels[i+1][j].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
			//setScaledIcon(labels[i][j],"/Imgs/whiteup" + String.valueOf(anim) + ".png");
		}
		else if(direccion.equals("Abajo"))
		{
			labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/whitedown" + String.valueOf(anim) + ".png")));
			labels[i-1][j].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
			//setScaledIcon(labels[i][j],"/Imgs/whitedown" + String.valueOf(anim) + ".png");
		}
		else
		{
			if(direccion.equals("Derecha"))
			{
				labels[i][j-1].setIcon(new ImageIcon(MainFrame.class.getResource(icono)));
			}
			//setScaledIcon(labels[i][j],"/Imgs/whiteright" + String.valueOf(anim) + ".png");
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
    private class GameController extends WindowAdapter implements KeyListener {
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
}