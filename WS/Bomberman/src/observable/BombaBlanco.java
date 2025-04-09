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
				arder(pI, pJ);
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
		if(pI-1>=0){
			MatrizBloques.getMB().arder(pI-1, pJ);
		}
		if(pI+1<=10){
			MatrizBloques.getMB().arder(pI+1, pJ);
		}
		if(pJ-1>=0){
			MatrizBloques.getMB().arder(pI, pJ-1);
		}
		if(pJ+1<=16){
			MatrizBloques.getMB().arder(pI, pJ+1);
		}
		MatrizBloques.getMB().arder(pI, pJ);
	}

}