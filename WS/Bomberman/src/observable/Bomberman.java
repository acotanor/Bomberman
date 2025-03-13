package observable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Bomberman extends Observable
{
	private static Bomberman miBom = new Bomberman();
	
	private int[] coordenadas;
	private boolean vivo;
	private ArrayList<Bomba> bombas;
	
	private Bomberman()
	{
		vivo = true;
		bombas = new ArrayList<Bomba>();
		coordenadas = new int[2];
		coordenadas[0] = 0; //Fila
		coordenadas[1] = 0; //Columna
	}

	public static Bomberman getBom()
	{
		return miBom;
	}
	
	//Crea una Bomba con las coordenadas actuales del bomberman 
	public void soltarBomba() 
	{
		if(vivo)
		{
			bombas.add(new Bomba(coordenadas[0], coordenadas[1]));
				
			setChanged();
			notifyObservers("Bomba," + String.valueOf(coordenadas[0]) + "," + String.valueOf(coordenadas[1]));
		}
	}
		
	//Elimina una bomba de la lista
	public void eliminarBomba(Bomba b)
	{
		bombas.remove(b);
	}
	
	//Verifica si en la casilla actual hay una bomba colocada
	private boolean verificarBomba()
	{
		boolean tiene = false;
		
		if(!bombas.isEmpty())
		{
			Bomba bAct = null;
			Iterator<Bomba> itr = bombas.iterator();
			
			while(itr.hasNext() && !tiene)
			{
				bAct = itr.next();
				tiene = bAct.tienePosicion(coordenadas[0], coordenadas[1]);
			}
		}
		
		return tiene;
	}
	
	//Si la casilla actual del bomberman esta ardiendo, el bomberman muere
	public void actualizar() {
		
		if (MatrizBloques.getMB().estaArdiendo(coordenadas[0],coordenadas[1])) 
		{
			vivo = false;
			setChanged();
			notifyObservers("Dead," + String.valueOf(coordenadas[0]) + "," + String.valueOf(coordenadas[1]));
		}
	}
	
	//Si la celda de arriba esta disponible, se mueve hacia arriba
	public void moverArriba() 
	{ 
		if (coordenadas[0] > 0 && !MatrizBloques.getMB().hayBloque(coordenadas[0]-1,coordenadas[1]) && vivo) 
		{
			boolean b = verificarBomba();
			coordenadas[0]--;
			notificarPosicion("Arriba",b);
			actualizar();
		}
	}
	
	//Si la celda de abajo esta disponible, se mueve hacia abajo
	public void moverAbajo() 
	{
		if (coordenadas[0] < 10 && !MatrizBloques.getMB().hayBloque(coordenadas[0]+1,coordenadas[1]) && vivo) 
		{
			boolean b = verificarBomba();
			coordenadas[0]++;
			notificarPosicion("Abajo", b);
			actualizar();
		}
	}
	
	//Si la celda de la izquierda esta disponible, se mueve hacia la izquierda
	public void moverIzquierda() 
	{ 
		if (coordenadas[1] > 0 && !MatrizBloques.getMB().hayBloque(coordenadas[0],coordenadas[1]-1) && vivo) 
		{
			boolean b = verificarBomba();
			coordenadas[1]--;
			notificarPosicion("Izquierda", b);
			actualizar();
		}
	}
	
	//Si la celda de la derecha esta disponible, se mueve hacia la derecha
	public void moverDerecha() 
	{
		if (coordenadas[1] < 16 && !MatrizBloques.getMB().hayBloque(coordenadas[0],coordenadas[1]+1) && vivo) 
		{
			boolean b = verificarBomba();
			coordenadas[1]++;
			notificarPosicion("Derecha", b);
			actualizar();
		}

	}
	
	//Notifica la posicion y la direccion a la vista
	public void notificarPosicion(String dir,boolean hayBomba)
	{
		setChanged();
		notifyObservers("Bomber," + String.valueOf(coordenadas[0]) + "," + String.valueOf(coordenadas[1]) + "," + dir + "," + String.valueOf(hayBomba));
	}
}