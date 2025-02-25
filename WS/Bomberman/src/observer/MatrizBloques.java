package bbr;
import java.util.Observable;
import java.util.Random;
import java.util.Observer;

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
	
	//Genera una pantalla con bloques duros en casillas impares, y bloques duros y blandos en las demas
	public void inicializarPantallaClasica()
	{
		generarBloquesDuros();
		generarBloquesVaciosYBlandos();
		imprimirMatriz();
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
			}
		}
	}
	
	//Genera bloques blandos y vacios en las casillas no asignadas
	private void generarBloquesVaciosYBlandos()
	{
		//Si no hay una casilla, se genera aleatoriamente un bloque vacio o uno blando, menos en las coords. iniciales, donde se genera uno vacío sí o sí
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
					}
					else 
					{
						int x = r.nextInt(2);
						
						switch(x) 
						{
							case 0:
								matriz[i][j] = new BloqueVacio();
								break;
								
							case 1:
								matriz[i][j] = new BloqueBlando();
								break;
						}
					}
				}
			}
		}
	}

	//Metodo para debug: Imprime la matriz de bloques en la consola
	private void imprimirMatriz() 
	{
		//E:Error V:Vacio B:Blando D:Duro
		for(int i=0;i<11;i++)
		{
			for(int j=0;j<17;j++)
			{
				if(matriz[i][j] == null)
				{
					System.out.print("E ");
				}
				else if (matriz[i][j] instanceof BloqueVacio)
				{
					System.out.print("V ");
				}
				else if (matriz[i][j] instanceof BloqueBlando)
				{
					System.out.print("B ");
				}
				else
				{
					System.out.print("D ");
				}
			}
			System.out.println();
		}
	}

	//Indica si en la fila pI y la columna pJ hay un bloque ardiendo
	public boolean estaArdiendo(int pI, int pJ)
	{
		return (matriz[pI][pJ] instanceof BloqueArdiendo);
	}

	//Indica si en la fila pI y la columna pJ hay un bloque que impide el paso
	public boolean hayBloque(int pI, int pJ)
	{
		return (matriz[pI][pJ] instanceof BloqueBlando || matriz[pI][pJ] instanceof BloqueDuro);
	}
}
