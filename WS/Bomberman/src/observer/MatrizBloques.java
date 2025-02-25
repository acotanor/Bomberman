package bbr;
import java.util.Observable;
import java.util.Observer;

public class MatrizBloques extends Observable
{
	private static MatrizBloques miMB = new MatrizBloques();
	private Bloque[][] matriz;
	
	private MatrizBloques() 
	{
		matriz = new Bloque[11][17];
	}
	
	public static MatrizBloques getMB()
	{
		return miMB;
	}
}
