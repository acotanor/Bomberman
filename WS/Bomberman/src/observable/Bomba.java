package observable;
import java.util.Timer;
import java.util.TimerTask;

public class Bomba 
{
	private Timer timer = null;
	private int[] coordenadas;
	
	public Bomba(int pI,int pJ)
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() 
			{
				MatrizBloques.getMB().arder(pI, pJ);
				Bomberman.getBom().eliminarBomba();
				timer.cancel();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 3000, 3000);
		
		coordenadas = new int[2];
		coordenadas[0] = pI;
		coordenadas[1] = pJ;
	}
	
	public boolean tienePosicion(int pI,int pJ)
	{
		return (coordenadas[0] == pI && coordenadas[1] == pJ);
	}
}
