package launcher;

import model.Bomberman;
import model.MatrizBloques;
import view.MainFrame;
import controller.GameController;

public class Main {
    public static void main(String[] args) {
    	
    	// Primero guardamos referencias a todas las clases relevantes.
    	// Luego empezamos a "cablear" lo necesario: por ejemplo, conectar el controlador con el modelo,
    	// y el modelo con la vista a través del patrón Observer.

    	
    	// La idea es que esta clase actúe como un "launcher". Solo inicializa y
    	// conecta los componentes. Después de esto, el juego funciona por sí solo
    	// y se interviene desde aqui para cosas como salir al menu etc..
    	
        Bomberman bombMan = Bomberman.getBom();
        MatrizBloques board = MatrizBloques.getMB();
        MainFrame view = new MainFrame();
        GameController controller = new GameController(bombMan);
        view.addKeyListener(controller);
        view.addWindowListener(controller);

        bombMan.addObserver(view);
        board.addObserver(view);
        //model.addObserver(controller);  

        view.inicializarVista();
        view.setVisible(true);
    }
}
