package observable;

import java.util.Timer;
import java.util.TimerTask;

public class BombaBlanco extends Bomba{
    private Timer timer = null;
    private BombaBlanco b = this;
    public BombaBlanco(int pI, int pJ){
        super(pI,pJ);
    
        TimerTask timerTask = new TimerTask(){
			@Override
			public void run(){
				MatrizBloques.getMB().arder(pI, pJ);
				BombermanBlanco.getBom().eliminarBomba(b);
				BombermanBlanco.getBom().actualizar();
				timer.cancel();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 3000, 3000);
    }
}
