package observable;
import java.util.Observable;
import java.util.Random;

public class MatrizBloques extends Observable
{
	private static MatrizBloques miMB = new MatrizBloques();
	private Bloque[][] matriz;
	
	
	private MatrizBloques() 
	{
		//11x17 (11 filas y 17 columnas)
		matriz = new Bloque[11][17];
	}
	
	public static MatrizBloques getMB()
	{
		return miMB;
	}
	
	public Bloque[][] getMatriz(){
		return matriz;
	}
	
	//Genera una pantalla con bloques duros en casillas impares, y bloques duros y blandos en las demas
	public void inicializarPantallaClasica()
	{
		generarBloquesDuros();
		generarBloquesVaciosYBlandos();
	}
	
	//Genera bloques duros en casillas impares
	private void generarBloquesDuros()
	{
		//Se generan bloques duros en las posiciones impares: (1,1), (1,3), (1,5)...
		for (int i=1;i<11;i+=2)
		{
			for (int j=1;j<17;j+=2)
			{
				matriz[i][j] = new BloqueDuro();
				notificarBloque(i,j,"BloqueDuro");
			}
		}
	}
	
	//Genera bloques blandos y vacios en las casillas no asignadas
	private void generarBloquesVaciosYBlandos()
	{
		//Si no hay una casilla, se genera aleatoriamente un bloque vacio o uno blando, menos en las coords. iniciales, donde se genera uno vacio
		Random r = new Random();
		for (int i=0;i<11;i++)
		{
			for (int j=0;j<17;j++)
			{
				if(matriz[i][j] == null)
				{
					if((i==0 && j==0) || (i==0 && j==1) || (i==1 && j==0))
					{
						matriz[i][j] = new BloqueVacio();
						notificarBloque(i,j,"BloqueVacio");
					}
					else 
					{
						int x = r.nextInt(2);
						
						switch(x) 
						{
							case 0:
								matriz[i][j] = new BloqueVacio();
								notificarBloque(i,j,"BloqueVacio");
								break;
								
							case 1:
								matriz[i][j] = new BloqueBlando();
								notificarBloque(i,j,"BloqueBlando");
								break;
						}
					}
				}
			}
		}
	}

	//Indica cuantos bloques vacios hay en el tablero
	public int cuantosBloquesVacios()
	{
		int x = 0;
		
		for(int i = 0; i < 11 ; i++)
		{
			for (int j = 0; j < 17; j++)
			{
				if(!hayBloque(i,j))
				{
					x++;
				}
			}
		}
		
		return x;
	}
	
	//Indica si en la fila pI y la columna pJ hay un bloque ardiendo
	public boolean estaArdiendo(int pI, int pJ)
	{
		return (matriz[pI][pJ].getType().equals("BloqueArdiendo"));
	}

	//Indica si en la fila pI y la columna pJ hay un bloque que impide el paso
	public boolean hayBloque(int pI, int pJ)
	{
		return (matriz[pI][pJ].getType().equals("BloqueBlando")|| matriz[pI][pJ].getType().equals("BloqueDuro"));
	}
	
	//El bloque de la fila pI y la columna pJ y los de su alrededor pasan a ser un bloque ardiendo
	public void arder(int pI, int pJ)
	{
		
		//Comprueba que el bloque a poner a arder no es duro 
		if(pI>0 && !(matriz[pI][pJ].getType().equals("BloqueDuro")))
		{
			matriz[pI-1][pJ] = new BloqueArdiendo(pI,pJ);
			notificarBloque(pI-1,pJ,"BloqueArdiendo");
		}
	
	}
	
	//El bloque de la fila pI y la columna pJ pasa de ser un bloque ardiendo a un bloque vacio
	public void dejarDeArder(int pI, int pJ)
	{
		matriz[pI][pJ] = new BloqueVacio();
		notificarBloque(pI,pJ,"BloqueVacio");
	}
	
	//Notifica el tipo del bloque de la fila i y la columna j a la vista
	private void notificarBloque(int i, int j, String tipo)
	{
		setChanged();
		notifyObservers(tipo + "," + String.valueOf(i) + "," + String.valueOf(j));
	}
	
}
