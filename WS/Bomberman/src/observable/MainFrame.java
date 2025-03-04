package observable;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
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
        
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Bloque[][] matriz = matrizBloques.getMatriz();
                for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz[i].length; j++) {
                        if (matriz[i][j] instanceof BloqueDuro) {
                            g.drawString("D", j * 20, i * 20);
                        } else if (matriz[i][j] instanceof BloqueBlando) {
                            g.drawString("B", j * 20, i * 20);
                        } else if (matriz[i][j] instanceof BloqueVacio) {
                            g.drawString("V", j * 20, i * 20);
                        } else {
                            g.drawString("E", j * 20, i * 20);
                        }
                    }
                }
            }
        };
        
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(11, 17));

        
        setContentPane(contentPane);
    }
}