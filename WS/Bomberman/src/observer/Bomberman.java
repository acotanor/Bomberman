package bbr;
import java.util.Observable;
import java.util.Observer;

public class Bomberman extends Observable
{
	int[] coordenadas;
	
	public Bomberman()
	{
		coordenadas = new int[2];
	}
}
