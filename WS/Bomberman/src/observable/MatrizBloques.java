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
				else if (matriz[i][j] instanceof BloqueDuro)
				{
					System.out.print("D ");
				}
				else 
				{
					System.out.print("A ");
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
	
	//El bloque de la fila pI y la columna pJ y los de su alrededor pasan a ser un bloque ardiendo
	public void arder(int pI, int pJ)
	{
		//Bloque del centro
		matriz[pI][pJ] = new BloqueArdiendo(pI,pJ);
		notificarBloque(pI,pJ,"BloqueArdiendo");
		
		//Bloque de arriba
		if(pI>0 && !(matriz[pI-1][pJ] instanceof BloqueDuro))
		{
			matriz[pI-1][pJ] = new BloqueArdiendo(pI-1,pJ);
			notificarBloque(pI-1,pJ,"BloqueArdiendo");
		}
		
		//Bloque de debajo
		if(pI<10 && !(matriz[pI+1][pJ] instanceof BloqueDuro))
		{
			matriz[pI+1][pJ] = new BloqueArdiendo(pI+1,pJ);
			notificarBloque(pI+1,pJ,"BloqueArdiendo");
		}
		
		//Bloque de la izquierda
		if(pJ>0 && !(matriz[pI][pJ-1] instanceof BloqueDuro))
		{
			matriz[pI][pJ-1] = new BloqueArdiendo(pI,pJ-1);
			notificarBloque(pI,pJ-1,"BloqueArdiendo");
		}
		
		//Bloque de la derecha
		if(pJ<16 && !(matriz[pI][pJ+1] instanceof BloqueDuro))
		{
			matriz[pI][pJ+1] = new BloqueArdiendo(pI,pJ+1);
			notificarBloque(pI,pJ+1,"BloqueArdiendo");
		}
		
		Bomberman.getBom().actualizar();
	}
	
	//El bloque de la fila pI y la columna pJ pasa de ser un bloque ardiendo a un bloque vacio
	public void dejarDeArder(int pI, int pJ)
	{
		matriz[pI][pJ] = new BloqueVacio();
		notificarBloque(pI,pJ,"BloqueVacio");
	}
	
	//Manda el mensaje a la vista para que cambie el sprite de la explosion
	public void cambiarExplosion(int pI, int pJ, int anim)
	{
		setChanged();
		notifyObservers("BloqueArdiendoA," + String.valueOf(pI) + "," + String.valueOf(pJ) + "," + String.valueOf(anim));
	}
	
	//Notifica el tipo del bloque de la fila y la columna j a la vista
	private void notificarBloque(int i, int j, String tipo)
	{
		setChanged();
		notifyObservers(tipo + "," + String.valueOf(i) + "," + String.valueOf(j));
	}
	
	
}
