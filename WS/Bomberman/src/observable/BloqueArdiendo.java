package observable;
import java.util.Timer;
import java.util.TimerTask;

public class BloqueArdiendo extends Bloque
{
	private Timer timer = null;
	
	public BloqueArdiendo(int pI, int pJ)
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() 
			{
				MatrizBloques.getMB().dejarDeArder(pI, pJ);
				timer.cancel();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 2000, 2000);
	}
}
