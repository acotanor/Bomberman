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

	public void iniciarPartida(String tipo)
	{
		generarEnemigos();
	}
	
	private void generarEnemigos()
	{
		int i = 0;
		int max = MatrizBloques.getMB().cuantosBloquesVacios()/12;
		Random r = new Random();
		
		while(i<max)
		{
			int fila = r.nextInt(11);
			int columna = r.nextInt(17);
			
			if(!MatrizBloques.getMB().hayBloque(fila, columna) && !ListaEnemigos.getLE().hayEnemigo(fila,columna) && fila+columna>1)
			{
				ListaEnemigos.getLE().generarEnemigo(fila, columna);
				i++;
			}
		}
	}

	public boolean estaArdiendo(int i, int j)
	{
		return MatrizBloques.getMB().estaArdiendo(i, j);
	}

	public boolean hayObstaculo(int i, int j)
	{
		return (MatrizBloques.getMB().hayBloque(i, j) || ListaEnemigos.getLE().hayEnemigo(i, j));
	}

	public void comprobarPosicion(int i, int j)
	{
		BombermanBlanco.getBom().comprobarEnemigo(i,j);
	}
}
