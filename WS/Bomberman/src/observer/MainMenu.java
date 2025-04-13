package observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton buttonC, buttonS, buttonE, buttonMain;
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
		setTitle("Menú Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		
		JLabel title = new JLabel("Selecciona una opción");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setBorder(new EmptyBorder(10, 0, 20, 0));
		contentPane.add(title);

		controlador = new Controlador();
		buttonC = createButton("Pantalla clásica", "CLASICA");
		buttonS = createButton("Pantalla soft", "SOFT");
		buttonE = createButton("Pantalla vacía", "VACIA");
		buttonMain = createButton("A JUGAR!!!", "Main");
		contentPane.add(buttonC);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonS);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonE);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonMain);
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
			switch (comando) {
				case "CLASICA":
					observable.MatrizBloques.getMB().setClassic();
					break;
				case "SOFT":
					observable.MatrizBloques.getMB().setSoft();
					break;
				case "VACIA":
					observable.MatrizBloques.getMB().setEmpty();
					break;
				case "Main":
					MainFrame O = new MainFrame();
					O.setVisible(true);
					break;
			}
		}
	}
}


