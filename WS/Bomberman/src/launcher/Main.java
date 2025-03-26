package launcher;

import model.Bomberman;
import model.MatrizBloques;
import view.MainFrame;
//import controller.GameController;

public class Main {
    public static void main(String[] args) {
    	
    	Bomberman bbman = Bomberman.getBom();
    	MatrizBloques board = MatrizBloques.getMB();
    	
    	MainFrame mf = new MainFrame(bbman, board);

        //model.addObserver(controller);  
    }
}
