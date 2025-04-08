package observable;
import java.util.Random;

public class Facade 
{
	private static Facade miFacade = new Facade();
	
	private Facade()
	{
		
	}
	
	public static Facade getFacade()
	{
		return miFacade;
	}

	public void iniciarPartida()
	{
		generarEnemigos();
	}
	
	private void generarEnemigos()
	{
		int i = 0;
		int max = MatrizBloques.getMB().cuantosBloquesVacios()/6;
		System.out.println(String.valueOf(max));
		Random r = new Random();
		
		while(i<max)
		{
			int fila = r.nextInt(11);
			int columna = r.nextInt(17);
			
			if(!MatrizBloques.getMB().hayBloque(fila, columna) && !ListaEnemigos.getLE().hayEnemigo(fila,columna))
			{
				ListaEnemigos.getLE().generarEnemigo(fila, columna);
				i++;
				System.out.println("Fila: " + String.valueOf(fila) + " Columna: " + String.valueOf(columna));
			}
		}
	}
}
