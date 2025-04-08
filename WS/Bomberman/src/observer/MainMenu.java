package observer;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JButton buttonC,buttonS,buttonE;
	private Controlador controlador;

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenu frame = new MainMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonC = getButtonC();
		buttonS = getButtonS();
		buttonE = getButtonE();

		controlador = getControlador();
		setContentPane(contentPane);
		contentPane.add(buttonC);
		contentPane.add(buttonS);
		contentPane.add(buttonE);
		MainFrame s = new MainFrame();
	
		
	
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		  
		    	MainFrame s = new MainFrame();
				s.setVisible(true);
		    }
		});
		cerrar();
	}
	
	private JButton getButtonC()
	{
		JButton b = new JButton("Pantalla clasica");
		//b.addActionListener(getControlador());
		return b;
	}
	
	private JButton getButtonS()
	{
		JButton b = new JButton("Pantalla soft");
	//	b.addActionListener(getControlador());
		return b;
	}
	
	private JButton getButtonE()
	{
		JButton b = new JButton("Pantalla vacia");
		//b.addActionListener(getControlador());
		return b;
	}
	private void cerrar() {
		this.setVisible(false);
	}
 	private Controlador getControlador()
	{
 		if(controlador == null)
 		{
 			controlador = new Controlador();
 		}
		return controlador;
	}

 	 private class Controlador extends WindowAdapter implements KeyListener 
	{
		@Override
		public void windowClosing(WindowEvent e) {
			MainFrame s = new MainFrame();
			s.setVisible(true);
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
