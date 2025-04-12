package observable;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MatrizEnemigos extends Observable
{
	private static MatrizEnemigos miME = new MatrizEnemigos();
	private Enemigo[][] matrizEnemigos;
	private Timer timer;
	
	private MatrizEnemigos()
	{
		matrizEnemigos = new Enemigo[11][17];
		
		TimerTask timerTask = new TimerTask() 
		{
			@Override
			public void run() 
			{
				limpiarEnemigos();
				moverEnemigos();
				notificarYReiniciarEnemigos();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);
	}
	
	public static MatrizEnemigos getME()
	{
		return miME;
	}

	public void generarEnemigo(int i, int j)
	{
		Random r = new Random();
		int num = r.nextInt(3);
		String tipo = "";
		
		switch(num)
		{
			case 0:
				tipo = "baloon";
				break;
			
			case 1:
				tipo = "doria";
				break;
			
			case 2:
				tipo = "pass";
				break;
		}
		
		Enemigo e = new Enemigo(i,j,tipo);
		matrizEnemigos[i][j] = e;
		
		notificarEnemigo(i,j,tipo);
	}
	
	//Indica  si en la fila pI y en la columna pJ hay un enemigo
	public boolean hayEnemigo(int pI, int pJ)
	{
		return (matrizEnemigos[pI][pJ]!= null);
	}
	
	//Notifica a la vista el enemigo de la fila pI y la columna pJ
	public void notificarEnemigo(int pI, int pJ, String tipo)
	{
		Random r = new Random();
		
		final int anim = r.nextInt(2) + 1;
		setChanged();
		notifyObservers("Enemigo," + String.valueOf(pI) + "," + String.valueOf(pJ) + "," + tipo + "," + String.valueOf(anim));
	}
	
	//Elimina el enemigo de la fila pI y la columna pJ
	public void eliminarEnemigo(int pI, int pJ)
	{
		matrizEnemigos[pI][pJ] = null;
		comprobarWin();
	}
	
	//Hace que todos los enemigos comprueben si estan en una explosion
	public void comprobarExplosiones()
	{
		for(int i = 0; i < 11;i++)
		{
			for(int j = 0; j < 17; j++)
			{
				if(matrizEnemigos[i][j] != null)
				{
					matrizEnemigos[i][j].comprobarExplosion();
				}
			}
		}
	}

	//Se comprueba si no quedan enemigos. En ese caso se manda la señal a la vista.
	private void comprobarWin()
	{
		boolean hayEnemigos = false;
		int i = 0;
		
		while (i<11 && !hayEnemigos)
		{
			int j = 0;
			while(j<17 && !hayEnemigos)
			{
				hayEnemigos = (matrizEnemigos[i][j] != null);
				j++;
			}
			i++;
		}
		
		if(!hayEnemigos)
		{
			setChanged();
			notifyObservers("Win");
		}
	}
	
	//Limpia todos los enemigos de la vista
	private void limpiarEnemigos()
	{
		for(int i = 0; i < 11;i++)
		{
			for(int j = 0; j < 17; j++)
			{
				Enemigo e = matrizEnemigos[i][j];
				if(e != null)
				{
					setChanged();
					notifyObservers("BloqueVacio," + String.valueOf(i) + "," + String.valueOf(j));
				}
			}
		}
	}
	
	//Mueve a todos los enemigos
	private void moverEnemigos()
	{
		for(int i = 0; i < 11;i++)
		{
			for(int j = 0; j < 17; j++)
			{
				if(matrizEnemigos[i][j] != null && !matrizEnemigos[i][j].seHaMovido())
				{
					int[] coordsNuevas = matrizEnemigos[i][j].mover();
					int newI = coordsNuevas[0];
					int newJ = coordsNuevas[1];
					
					if(newI != i || newJ != j)
					{
						matrizEnemigos[newI][newJ] = matrizEnemigos[i][j];
						matrizEnemigos[i][j] = null;
					}
				}
			}
		}
	}
	
	//Notifica las posiciones de todos los enemigos y reinicia a false la variable "movido" 
	private void notificarYReiniciarEnemigos()
	{
		for(int i = 0; i < 11;i++)
		{
			for(int j = 0; j < 17; j++)
			{
				Enemigo e = matrizEnemigos[i][j];
				if(e != null)
				{
					notificarEnemigo(i,j,e.getTipo());
					e.reiniciarMov();
				}
			}
		}
	}

}
