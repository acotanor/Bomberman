package observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;

public class Dead_Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Dead_Window() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocation(800,100);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(237, 51, 59), 20, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HAS MUERTO");
		lblNewLabel.setBounds(180, 35, 200, 200);
		contentPane.add(lblNewLabel);
	}
}
