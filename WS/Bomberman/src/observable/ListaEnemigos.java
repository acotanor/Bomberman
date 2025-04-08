package observable;
import java.util.ArrayList;

public class ListaEnemigos 
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
		Enemigo e = new Enemigo(i,j);
		lista.add(e);
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
}
