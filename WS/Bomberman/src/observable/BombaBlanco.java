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

	@Override
	public void arder(int pI,int pJ){
		MatrizBloques.getMB().arder(pI, pJ);
		if(pI-1>=0){
			MatrizBloques.getMB().arder(pI-1, pJ);
		}
		else if(pI+1<=11){
			MatrizBloques.getMB().arder(pI+1, pJ);
		}
		else if(pJ-1>=0){
			MatrizBloques.getMB().arder(pI, pJ-1);
		}
		else if(pJ+1<=17){
			MatrizBloques.getMB().arder(pI, pJ+1);
		}
	}

}
