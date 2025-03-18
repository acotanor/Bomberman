package observable;
import java.util.Timer;
import java.util.TimerTask;

public class BloqueArdiendo extends Bloque
{
	private Timer timer = null;
	private int anim = 0;
	
	public BloqueArdiendo(int pI, int pJ)
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() 
			{
				anim++;
				if(anim<=5)
				{
					MatrizBloques.getMB().cambiarExplosion(pI, pJ, anim);
				}
				else
				{
					MatrizBloques.getMB().dejarDeArder(pI, pJ);
					timer.cancel();
				}
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 400);
	}
	
	@Override
	public String getType() {
		return "BloqueArdiendo";
	}
}
