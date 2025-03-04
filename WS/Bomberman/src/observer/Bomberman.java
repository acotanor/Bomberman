package observer;
import java.util.Observable;
import java.util.Observer;

public class Bomberman extends Observable
{
	int[] coordenadas;
	boolean blanco;
	boolean vivo;
	private static Bomberman miBom = new Bomberman();
	
	
	
	
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
	private void actualizarPos() {// Manda a Partida la informacion sobre l posicion del bomberman
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
		
	
	public void soltarBomba() {//llama a la clase crearBomba con las coordenadas actuales del bomberman 
		//colocarBomba(coordenadas[0],coordenadas[1]);
		//falta
		
	}
	
	
	public void moverArriba() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de arriba esta disponible 
		// para que bomberman se mueva arriba, en ese caso sube en 1 el valor de coordenadas[1]
		if (observer.MatrizBloques.getMB().hayBloque(coordenadas[0],coordenadas[1]+1) && coordenadas[1] != 0) {
		coordenadas[1]++;
		}
		
		if (observer.MatrizBloques.getMB().estaArdiendo(coordenadas[0],coordenadas[1]+1)) {
		
		haMuerto();
	
		}
		
		
	}
	public void moverAbajo() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de abajo esta disponible 
		// para que bomberman se mueva abajo, en ese caso decrementa en 1 el valor de coordenadas[1]
		if (!observer.MatrizBloques.getMB().hayBloque(coordenadas[0],coordenadas[1]-1) && coordenadas[1] != 17) {
			coordenadas[1]--;
			
	
		}
		if (observer.MatrizBloques.getMB().estaArdiendo(coordenadas[0],coordenadas[1]-1)) {
		
			haMuerto();
		}
		}
		
	public void moverIzquierda() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de arriba esta disponible 
		// para que bomberman se mueva arriba, en ese caso sube en 1 el valor de coordenadas[1]
		if (!observer.MatrizBloques.getMB().hayBloque(coordenadas[0]-1,coordenadas[1]) && coordenadas[0] != 0) {
		coordenadas[1]--;
		}
		if (observer.MatrizBloques.getMB().estaArdiendo(coordenadas[0],coordenadas[1]+1)) {
			
			haMuerto();
		}
		}
		public void moverDerecha() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de abajo esta disponible 
		// para que bomberman se mueva abajo, en ese caso decrementa en 1 el valor de coordenadas[1]
		if (!observer.MatrizBloques.getMB().hayBloque(coordenadas[0]+1,coordenadas[1]-1) && coordenadas[0] != 11) {
			coordenadas[1]++;
		}	
		if (observer.MatrizBloques.getMB().estaArdiendo(coordenadas[0],coordenadas[1]+1)) {
			
			haMuerto();
		}

		
	
		
		
	}
}
