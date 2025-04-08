package observable;

import java.util.Timer;
import java.util.TimerTask;

public class BombaNegro extends Bomba{
    private Timer timer = null;
    private BombaNegro b = this;
    public BombaNegro(int pI, int pJ){
        super(pI,pJ);
    
        TimerTask timerTask = new TimerTask(){
			@Override
			public void run(){
				arder(pI, pJ);
				BombermanNegro.getBom().eliminarBomba(b);
				BombermanNegro.getBom().actualizar();
				timer.cancel();
			}		
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 3000, 3000);
    }

	@Override
	public void arder(int pI, int pJ){
		MatrizBloques.getMB().arder(pI, pJ);
		for(int i=0; i<=11; i++){
			if(i!=pI){
				MatrizBloques.getMB().arder(i, pJ);
			}
		}
		for(int j=0; j<=17; j++){
			if(j!=pJ){
				MatrizBloques.getMB().arder(pI, j);
			}
		}
	}
}
