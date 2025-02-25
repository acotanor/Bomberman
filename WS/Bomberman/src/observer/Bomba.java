package bbr;
import java.util.Observable;
import java.util.Observer;

public class Bomba extends Observable
{
	int[] coordenadas;
	
	public Bomba()
	{
		coordenadas = new int[2];
	}
}
