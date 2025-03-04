package observable;

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
import observer.Bloque;
import observer.BloqueBlando;	
import observer.BloqueDuro;
import observer.BloqueVacio;
import observer.MatrizBloques;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private MatrizBloques matrizBloques;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        matrizBloques = MatrizBloques.getMB();
        matrizBloques.inicializarPantallaClasica();
        
        contentPane = new JPanel();
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(11, 17));
        contentPane.setBackground(Color.BLACK);
        
        
        setContentPane(contentPane);
        
        Bloque[][] matriz = matrizBloques.getMatriz();
        JLabel[][] labels = new JLabel[11][17];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] instanceof BloqueDuro) {
                	labels[i][j] = new JLabel("");
                	labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/hard5.png")));
                	contentPane.add(labels[i][j]);
                } else if (matriz[i][j] instanceof BloqueBlando) {
                	labels[i][j] = new JLabel("");
                	labels[i][j].setIcon(new ImageIcon(MainFrame.class.getResource("/Imgs/soft1.png")));
                	contentPane.add(labels[i][j]);
                } else if (matriz[i][j] instanceof BloqueVacio) {
                	labels[i][j] = new JLabel("");
                	contentPane.add(labels[i][j]);
                }
            }
        }
    }
}