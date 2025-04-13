package observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import observable.Facade;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane, contentPane2;
	private JLabel labelS,labelB;
	private JButton buttonC, buttonS, buttonE, buttonBB, buttonBN, buttonMain;
	private Controlador controlador;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainMenu() {
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 350);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		
		JLabel title = new JLabel("Selecciona una opcion");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setBorder(new EmptyBorder(10, 0, 20, 0));
		contentPane.add(title);

		controlador = new Controlador();
		
		buttonC = createButton("Pantalla clasica", "CLASICA");
		buttonS = createButton("Pantalla soft", "SOFT");
		buttonE = createButton("Pantalla vacia", "VACIA");
		buttonBB = createButton("Bomberman blanco", "BLANCO");
		buttonBN = createButton("Bomberman negro", "NEGRO");
		buttonMain = createButton("A JUGAR!!!", "MAIN");
		buttonMain.setMaximumSize(new Dimension(400, 80));
		
		labelS = new JLabel("Pantalla seleccionada: CLASICA,");
		labelB = new JLabel("Bomberman seleccionado: BLANCO");
		
		contentPane.add(buttonC);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonS);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonE);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonMain);
		contentPane.add(Box.createVerticalStrut(10));
		
		
		contentPane2 = new JPanel();
		contentPane.add(contentPane2);
		contentPane2.add(buttonBB);
		contentPane2.add(buttonBN);
		contentPane2.add(labelS);
		contentPane2.add(labelB);
	}

	private JButton createButton(String text, String actionCommand) {
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setFont(new Font("Arial", Font.PLAIN, 14));
		button.setActionCommand(actionCommand);
		button.addActionListener(controlador);
		button.setMaximumSize(new Dimension(200, 40));
		return button;
	}

	private class Controlador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand();
			if(comando.equals("MAIN"))
			{
				MainFrame O = new MainFrame();
				O.setVisible(true);
				MainMenu.this.setVisible(false);
			}
			else if(comando.equals("CLASICA") || comando.equals("SOFT") || comando.equals("VACIA"))
			{
				Facade.getFacade().setEscenario(comando);
				MainMenu.this.labelS.setText("Pantalla seleccionada: " + comando + ",");
			}
			else if(comando.equals("BLANCO") || comando.equals("NEGRO"))
			{
				Facade.getFacade().setColor(comando);
				MainMenu.this.labelB.setText("Bomberman seleccionado: " + comando);
			}
		}
	}
}


