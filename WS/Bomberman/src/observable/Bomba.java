package observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;

public class Bomba extends Observable
{
	private Timer timer = null;
	
	public Bomba(int pI,int pJ)
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				MatrizBloques.getMB().arder(pI, pJ);
				timer.cancel();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 3000, 3000);
		
		setChanged();
		notifyObservers("Bomba," + String.valueOf(pI) + "," + String.valueOf(pJ));
	}
}
