package observable;

import java.util.Timer;
import java.util.TimerTask;

public class BombaNegro extends Bomba{
    private Timer timer = null;
    public BombaNegro(int pI, int pJ){
        super(pI,pJ);
    
        TimerTask timerTask = new TimerTask(){
			@Override
			public void run(){
				MatrizBloques.getMB().arder(pI, pJ);
				BombermanNegro.getBom().eliminarBomba(b);
				BombermanNegro.getBom().actualizar();
				timer.cancel();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 3000, 3000);
    }
}
