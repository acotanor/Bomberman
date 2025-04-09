package observable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Bomberman extends Observable{
   	
	protected int[] coordenadas;
	protected boolean vivo;
	protected ArrayList<Bomba> bombas;
	private Timer timer;
   
    public Bomberman(){
        vivo = true;
        bombas = new ArrayList<Bomba>();
        coordenadas = new int[2];
        coordenadas[0] = 0;
        coordenadas[1] = 0;
        
        TimerTask timerTask = new TimerTask() {
			@Override
			public void run() 
			{
				notificacionPeriodica();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 1000, 50);
    }

    
    
    //Crea una Bomba con las coordenadas actuales del bomberman, lo implementan los hijos 
    public void soltarBomba(){
        
    }

    //Elimina una bomba de la lista
	public void eliminarBomba(Bomba b)
	{
		this.bombas.remove(b);
	}

    //Verifica si en la casilla actual hay una bomba colocada
	private boolean verificarBomba()
	{
		boolean tiene = false;
		
		if(!this.bombas.isEmpty())
		{
			Bomba bAct = null;
			Iterator<Bomba> itr = this.bombas.iterator();
			
			while(itr.hasNext() && !tiene)
			{
				bAct = itr.next();
				tiene = bAct.tienePosicion(this.coordenadas[0], this.coordenadas[1]);
			}
		}
		
		return tiene;
	}

	
	//Comprueba si en la posicion actual hay un enemigo
	private boolean hayEnemigo()
	{
		return ListaEnemigos.getLE().hayEnemigo(coordenadas[0], coordenadas[1]);
	}
	
    //Si la casilla actual del bomberman esta ardiendo, el bomberman muere
	public void actualizar() {
		
		if (MatrizBloques.getMB().estaArdiendo(this.coordenadas[0],this.coordenadas[1])) 
		{
			morir();
		}
	}
	
	//Si bomberman esta en la fila pI y la columna pJ, pierde la partida
	public void comprobarCasilla(int pI, int pJ)
	{
		if(coordenadas[0] == pI && coordenadas[1] == pJ)
		{
			this.vivo = false;
			setChanged();
			notifyObservers("DeadEnemy," + String.valueOf(this.coordenadas[0]) + "," + String.valueOf(this.coordenadas[1]));
		}
	}
		
	//Pierde la partida
	private void morir()
	{
		this.vivo = false;
		setChanged();
		notifyObservers("DeadBomb," + String.valueOf(this.coordenadas[0]) + "," + String.valueOf(this.coordenadas[1]));
	}
	
	
	
	//Si la celda de arriba esta disponible, se mueve hacia arriba
	public void moverArriba() 
	{ 
		if (this.coordenadas[0] > 0 && !MatrizBloques.getMB().hayBloque(this.coordenadas[0]-1,this.coordenadas[1]) && this.vivo) 
		{
			boolean b = verificarBomba();
			this.coordenadas[0]--;
			notificarPosicion("Arriba",b);
			actualizar();
			
			if(hayEnemigo())
			{
				morir();
			}
		}
	}
	
	//Si la celda de abajo esta disponible, se mueve hacia abajo
	public void moverAbajo() 
	{
		if (this.coordenadas[0] < 10 && !MatrizBloques.getMB().hayBloque(this.coordenadas[0]+1,this.coordenadas[1]) && this.vivo) 
		{
			boolean b = verificarBomba();
			this.coordenadas[0]++;
			notificarPosicion("Abajo", b);
			actualizar();
			
			if(hayEnemigo()) 
			{
				morir();
			}
		}
	}
	
	//Si la celda de la izquierda esta disponible, se mueve hacia la izquierda
	public void moverIzquierda() 
	{ 
		if (this.coordenadas[1] > 0 && !MatrizBloques.getMB().hayBloque(this.coordenadas[0],this.coordenadas[1]-1) && this.vivo) 
		{
			boolean b = verificarBomba();
			this.coordenadas[1]--;
			notificarPosicion("Izquierda", b);
			actualizar();
			
			if(hayEnemigo())
			{
				morir();
			}
		}
	}
	
	//Si la celda de la derecha esta disponible, se mueve hacia la derecha
	public void moverDerecha() 
	{
		if (this.coordenadas[1] < 16 && !MatrizBloques.getMB().hayBloque(this.coordenadas[0],this.coordenadas[1]+1) && this.vivo) 
		{
			boolean b = verificarBomba();
			this.coordenadas[1]++;
			notificarPosicion("Derecha", b);
			actualizar();
			
			if(hayEnemigo())
			{
				morir();
			}
		}

	}
	
	
	
	//Notifica la posicion y la direccion a la vista
	public void notificarPosicion(String dir,boolean hayBomba)
	{
		setChanged();
		notifyObservers("Bomber," + String.valueOf(this.coordenadas[0]) + "," + String.valueOf(this.coordenadas[1]) + "," + dir + "," + String.valueOf(hayBomba));
	}
	
	public void notificacionPeriodica()
	{
		setChanged();
		notifyObservers("PeriodoBomber," + String.valueOf(this.coordenadas[0]) + "," + String.valueOf(this.coordenadas[1]));
		
		if(bombas.size()>0)
		{
			for(int i = 0; i < bombas.size(); i++) 
			{
				Bomba b = bombas.get(i);
				if(b.getFila() != coordenadas[0] && b.getColumna() != coordenadas[1])
				{
					setChanged();
					notifyObservers("PeriodoBomba," + String.valueOf(b.getFila()) + "," + String.valueOf(b.getColumna()));
				}
			}
		}
	}

}
