package observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;
import java.util.Observer;

public class Bomba extends Observable
{
	private Timer timer = null;
	
	public Bomba(int pI,int pJ)
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				MatrizBloques.getMB().arder(pI, pJ);
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 3000);
	}
}
