package observer;
import java.util.Timer;
import java.util.TimerTask;

public class BloqueArdiendo extends Bloque
{
	private Timer timer = null;
	
	public BloqueArdiendo(int pI, int pJ)
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				MatrizBloques.getMB().dejarDeArder(pI, pJ);
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 2000);
	}
}
