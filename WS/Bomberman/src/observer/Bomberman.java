package observer;
import java.util.Observable;
import java.util.Observer;

public class Bomberman extends Observable
{
	int[] coordenadas;
	boolean blanco;
	boolean vivo;
	
	
	
	
	
	public Bomberman()
	{
		coordenadas = new int[2];
		coordenadas[0] = 0;// coordenada x en la que esta bomberman, siempre empieza en la 0
		coordenadas[1] = 0;// coordenada Y en la que esta bomberman, siempre empieza en la 0
	}
	
	private void actualizarPos() {// Manda a Partida la informacion sobre l posicion del bomberman
		
		
	}
		
	private void haMuerto() {// Manda a Partida la informacion de que el bomberman ha muerto
		
	}
		
	}
	public void soltarBomba() {//llama a la clase crearBomba con las coordenadas actuales del bomberman 
		colocarBomba(coordenadas[0],coordenadas[1]);
		
	}
	
	
	public void moverArriba() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de arriba esta disponible 
		// para que bomberman se mueva arriba, en ese caso sube en 1 el valor de coordenadas[1]
		if (!hayBloque(coordenadas[0],coordenadas[1]+1)) {
		coordenadas[1]++;
		}
		if (estaArdiendo(coordenadas[0],coordenadas[1]+1)) {
		vivo = false;
		haMuerto();
	
		}
		
		
	}
	public void moverAbajo() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de abajo esta disponible 
		// para que bomberman se mueva abajo, en ese caso decrementa en 1 el valor de coordenadas[1]
		if (!hayBloque(coordenadas[0],coordenadas[1]-1)) {
			coordenadas[1]--;
		}
		
	public void moverIzquierda() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de arriba esta disponible 
		// para que bomberman se mueva arriba, en ese caso sube en 1 el valor de coordenadas[1]
		if (!hayBloque(coordenadas[0]-1,coordenadas[1])) {
		coordenadas[1]--;
		}
			
		}
		public void moverDerecha() {//llama a la clase estadisponilbe de Partida para comprobar si la celda de abajo esta disponible 
		// para que bomberman se mueva abajo, en ese caso decrementa en 1 el valor de coordenadas[1]
		if (!hayBloque(coordenadas[0]+1,coordenadas[1]-1)) {
			coordenadas[1]++;
		}	

		
	
		
		
	}
}
