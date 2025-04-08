package observable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class ListaEnemigos extends Observable
{
	private static ListaEnemigos miLE = new ListaEnemigos();
	private ArrayList<Enemigo> lista;
	
	
	private ListaEnemigos()
	{
		lista = new ArrayList<Enemigo>();
	}
	
	public static ListaEnemigos getLE()
	{
		return miLE;
	}

	public void generarEnemigo(int i, int j)
	{
		Random r = new Random();
		int num = r.nextInt(3);
		String tipo = "";
		
		switch(num)
		{
			case 0:
				tipo = "baloon";
				break;
			
			case 1:
				tipo = "doria";
				break;
			
			case 2:
				tipo = "pass";
				break;
		}
		
		Enemigo e = new Enemigo(i,j,tipo);
		lista.add(e);
		
		notificarEnemigo(i,j,tipo,"Inicio");
	}

	public boolean hayEnemigo(int pI, int pJ)
	{
		boolean hay = false;
		int i = 0;
		
		while(!hay && i < lista.size())
		{
			hay = lista.get(i).estaEn(pI,pJ);
			i++;
		}
		
		return hay;
	}

	public void notificarEnemigo(int pI, int pJ, String tipo, String direccion)
	{
		Random r = new Random();
		int anim = r.nextInt(1,3);
		setChanged();
		notifyObservers("Enemigo," + String.valueOf(pI) + "," + String.valueOf(pJ) + "," + tipo + "," + String.valueOf(anim) + "," + direccion);
	}
}
