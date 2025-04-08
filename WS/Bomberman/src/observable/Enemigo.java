package observable;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Random;

public class Enemigo 
{
	private Timer timer;
	private int[] coordenadas;
	private String tipo;
	
	public Enemigo(int i, int j, String pTipo)
	{
		coordenadas = new int[2];
		coordenadas[0] = i;
		coordenadas[1] = j;
		tipo = pTipo;
		
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() 
			{
				mover();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);
	}
	
	private void mover()
	{
		String dir = direccionAleatoria();
		
		if(dir.equals("Izquierda"))
		{
			coordenadas[1]--;
		}
		else if(dir.equals("Derecha"))
		{
			coordenadas[1]++;
		}
		else if(dir.equals("Arriba"))
		{
			coordenadas[0]--;
		}
		else if(dir.equals("Abajo"))
		{
			coordenadas[0]++;
		}
		
		if(!dir.equals(""))
		{
			ListaEnemigos.getLE().notificarEnemigo(coordenadas[0], coordenadas[1], tipo,dir);
		}
	}
	
	//Obtiene una direccion aleatoriamente entre todas las posibles, o un String vacio en su defecto
	private String direccionAleatoria()
	{
		String dir = "";
		ArrayList<String> direcciones = new ArrayList<String>();
		
		if(coordenadas[0]>0 && !MatrizBloques.getMB().hayBloque(coordenadas[0]-1, coordenadas[1]))
		{
			direcciones.add("Arriba");
		}
		if(coordenadas[0]<10 && !MatrizBloques.getMB().hayBloque(coordenadas[0]+1, coordenadas[1]))
		{
			direcciones.add("Abajo");
		}
		if(coordenadas[1]>0 && !MatrizBloques.getMB().hayBloque(coordenadas[0], coordenadas[1]-1))
		{
			direcciones.add("Izquierda");
		}
		if(coordenadas[1]<16 && !MatrizBloques.getMB().hayBloque(coordenadas[0], coordenadas[1]+1))
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

	public boolean estaEn(int pI, int pJ)
	{
		return(pI == coordenadas[0] && pJ == coordenadas[1]);
	}
}
