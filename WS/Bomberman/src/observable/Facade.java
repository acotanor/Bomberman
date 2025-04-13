package observable;
import java.util.Random;

public class Facade 
{
	private static Facade miFacade = new Facade();
	private Bomberman bomber;
	
	private String escenario;
	private String color;
	
	private Facade()
	{
		bomber = null;
		escenario = "CLASICA";
		color = "BLANCO";
	}
	
	public void setEscenario(String pEscenario) 
	{
		escenario = pEscenario;
	}
	
	public void setColor(String pColor)
	{
		color = pColor;
	}
	
	public static Facade getFacade()
	{
		return miFacade;
	}

	public void iniciarPartida()
	{
		inicializarPantalla();
		generarEnemigos();
		inicializarBomberman();
	}
	
	private void inicializarPantalla()
	{
		MatrizBloques.getMB().inicializarPantalla(escenario);
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
			
			if(!MatrizBloques.getMB().hayBloque(fila, columna) && !MatrizEnemigos.getME().hayEnemigo(fila,columna) && fila+columna>1)
			{
				MatrizEnemigos.getME().generarEnemigo(fila, columna);
				i++;
			}
		}
	}

	private void inicializarBomberman()
	{
		if(color.equals("BLANCO"))
		{
			bomber = observable.BombermanBlanco.getBom();
		} 
		else if(color.equals("NEGRO"))
		{
			bomber = observable.BombermanNegro.getBom();
		}
		bomber.notificarPosicion("Inicio",false);
	}
	
	
	public boolean estaArdiendo(int i, int j)
	{
		return MatrizBloques.getMB().estaArdiendo(i, j);
	}

	public boolean hayObstaculo(int i, int j)
	{
		return (MatrizBloques.getMB().hayBloque(i, j) || MatrizEnemigos.getME().hayEnemigo(i, j));
	}

	public void comprobarPosicion(int i, int j)
	{
		bomber.comprobarCasilla(i,j);
	}

	public void accionBomber(String tecla)
	{
		if (tecla.equals("Arriba")) 
        {
        	bomber.moverArriba();
        } 
        else if (tecla.equals("Abajo")) 
        {
        	bomber.moverAbajo();
        } 
        else if (tecla.equals("Izquierda")) 
        {
        	bomber.moverIzquierda();
        } 
        else if (tecla.equals("Derecha")) 
        {
        	bomber.moverDerecha();
        }
        else if (tecla.equals("B")) 
        {
        	bomber.soltarBomba();
        }
	}
}
