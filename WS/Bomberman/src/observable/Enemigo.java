package observable;

import java.util.ArrayList;
import java.util.Random;

public class Enemigo 
{
	private int[] coordenadas;
	private String tipo;
	
	public Enemigo(int i, int j, String pTipo)
	{
		coordenadas = new int[2];
		coordenadas[0] = i;
		coordenadas[1] = j;
		tipo = pTipo;
	}
	
	public void mover()
	{
		String dir = direccionAleatoria();
		boolean arde = false;
		
		if(dir.equals("Izquierda"))
		{
			coordenadas[1]--;
			arde = verificarCasilla();
			if(arde)
			{
				coordenadas[1]++;
				ListaEnemigos.getLE().eliminarEnemigo(coordenadas[0],coordenadas[1]);
			}
		}
		else if(dir.equals("Derecha"))
		{
			coordenadas[1]++;
			arde = verificarCasilla();
			if(arde)
			{
				coordenadas[1]--;
				ListaEnemigos.getLE().eliminarEnemigo(coordenadas[0],coordenadas[1]);
			}
		}
		else if(dir.equals("Arriba"))
		{
			coordenadas[0]--;
			arde = verificarCasilla();
			if(arde)
			{
				coordenadas[0]++;
				ListaEnemigos.getLE().eliminarEnemigo(coordenadas[0],coordenadas[1]);
			}
		}
		else if(dir.equals("Abajo"))
		{
			coordenadas[0]++;
			arde = verificarCasilla();
			if(arde)
			{
				coordenadas[0]--;
				ListaEnemigos.getLE().eliminarEnemigo(coordenadas[0],coordenadas[1]);
			}
		}
		
		if(!dir.equals("") && !arde)
		{
			Facade.getFacade().comprobarPosicion(coordenadas[0],coordenadas[1]);
		}
	}
	
	//Obtiene una direccion aleatoriamente entre todas las posibles, o un String vacio en su defecto
	private String direccionAleatoria()
	{
		String dir = "";
		ArrayList<String> direcciones = new ArrayList<String>();
		
		if(coordenadas[0]>0 && !Facade.getFacade().hayObstaculo(coordenadas[0]-1, coordenadas[1]))
		{
			direcciones.add("Arriba");
		}
		if(coordenadas[0]<10 && !Facade.getFacade().hayObstaculo(coordenadas[0]+1, coordenadas[1]))
		{
			direcciones.add("Abajo");
		}
		if(coordenadas[1]>0 && !Facade.getFacade().hayObstaculo(coordenadas[0], coordenadas[1]-1))
		{
			direcciones.add("Izquierda");
		}
		if(coordenadas[1]<16 && !Facade.getFacade().hayObstaculo(coordenadas[0], coordenadas[1]+1))
		{
			direcciones.add("Derecha");
		}
		
		if(!direcciones.isEmpty())
		{
			Random r = new Random();
			dir = direcciones.get(r.nextInt(direcciones.size()));
		}
		
		return dir;
	}
	
	//Devuelve un booleano que indica si se encuentra en la fila pI y la columna pJ
	public boolean estaEn(int pI, int pJ)
	{
		return(pI == coordenadas[0] && pJ == coordenadas[1]);
	}
	
	//Verifica si el enemigo esta en una casilla ardiendo
	public boolean verificarCasilla()
	{
		return (Facade.getFacade().estaArdiendo(coordenadas[0],coordenadas[1]));
	}
	
	//Si el enemigo esta en una casilla ardiendo, este se elimina
	public void comprobarExplosion()
	{
		if(verificarCasilla())
		{
			ListaEnemigos.getLE().eliminarEnemigo(coordenadas[0],coordenadas[1]);
		}
	}

	public String getTipo()
	{
		return tipo;
	}
}
