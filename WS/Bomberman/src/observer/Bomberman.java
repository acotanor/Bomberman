package observer;
import java.util.Observable;
import java.util.Observer;

public class Bomberman extends Observable
{
	private static Bomberman miBom = new Bomberman();
	
	private int[] coordenadas;
	private boolean blanco;
	private boolean vivo;
	private Bomba bomba;
	
	private Bomberman()
	{
		coordenadas = new int[2];
		coordenadas[0] = 0;// coordenada x en la que esta bomberman, siempre empieza en la 0
		coordenadas[1] = 0;// coordenada Y en la que esta bomberman, siempre empieza en la 0
	}
	
	public static Bomberman getBom()
	{
		return miBom;
	}
	
	private void actualizarPos() {// Manda a Partida la informacion sobre la posicion del bomberman
		//falta
		
	}
	
	public void actualizar() {//Metodo que busca si la posicion actual de bomberman le puede hacer daño
		if (observer.MatrizBloques.getMB().estaArdiendo(coordenadas[0],coordenadas[1])) {
			
			haMuerto();
		}
	}
		
	private void haMuerto() {// Manda a Partida la informacion de que el bomberman ha muerto
		vivo = false;
		//falta
	}
		
	public void soltarBomba() {//llama a la clase Bomba con las coordenadas actuales del bomberman 
		bomba = new Bomba(coordenadas[0],coordenadas[1]);
	}
	
	public void moverArriba() {//llama a la clase esta disponible de Partida para comprobar si la celda de arriba esta disponible 
		
		if (coordenadas[0] > 0 && !MatrizBloques.getMB().hayBloque(coordenadas[0]-1,coordenadas[1])) 
		{
			coordenadas[0]--;
			actualizar();
		}
	}
	
	public void moverAbajo() {//llama a la clase esta disponible de Partida para comprobar si la celda de abajo esta disponible 
		if (coordenadas[0] < 10 && !MatrizBloques.getMB().hayBloque(coordenadas[0]+1,coordenadas[1])) 
		{
			coordenadas[0]++;
			actualizar();
		}
	}
		
	public void moverIzquierda() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de arriba esta disponible 
		
		if (coordenadas[1] > 0 && !MatrizBloques.getMB().hayBloque(coordenadas[0],coordenadas[1]-1)) 
		{
			coordenadas[1]--;
			actualizar();
		}
	}
		
	public void moverDerecha() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de abajo esta disponible 
		
		if (coordenadas[1] < 16 && !MatrizBloques.getMB().hayBloque(coordenadas[0],coordenadas[1]+1)) 
		{
			coordenadas[1]++;
			actualizar();
		}

	}
}