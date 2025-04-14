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
	public void inicializarPantalla(String escenario)
	{
		if(escenario.equals("CLASICA")) {
			generarBloquesDuros();
			generarBloquesVaciosYBlandos();
		}
		if (escenario.equals("SOFT")) {
			generarBloquesVaciosYBlandos();
		}
		if (escenario.equals("VACIA")) {
			generarBloquesVacios();
		}
	}
	private void generarBloquesVacios() {
		//Se generan bloques vacios en todas las posicions para la pantalla soft
				for (int i=0;i<11;i+=1)
				{
					for (int j=0;j<17;j+=1)
					{
						matriz[i][j] = new BloqueVacio();
						notificarBloque(i,j,"BloqueVacio");
					}
				}
	}
	
	//Genera bloques duros en casillas impares
	private void generarBloquesDuros() {
	    BloqueFactory factory = BloqueFactory.getBloqueFactory(); // Obtén la instancia de la fábrica
	    for (int i = 1; i < 11; i += 2) {
	        for (int j = 1; j < 17; j += 2) {
	            matriz[i][j] = factory.generarBloque("BloqueDuro"); // Usa la fábrica para crear el bloque
	            notificarBloque(i, j, "BloqueDuro"); // Notifica el cambio
	        }
	    }
	}
	
	//Genera bloques blandos y vacios en las casillas no asignadas
	private void generarBloquesVaciosYBlandos() {
	    BloqueFactory factory = BloqueFactory.getBloqueFactory(); // Obtén la instancia de la fábrica
	    Random r = new Random();

	    for (int i = 0; i < 11; i++) {
	        for (int j = 0; j < 17; j++) {
	            if (matriz[i][j] == null) {
	                if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
	                    matriz[i][j] = factory.generarBloque("BloqueVacio"); // Usa la fábrica para crear un bloque vacío
	                    notificarBloque(i, j, "BloqueVacio");
	                } else {
	                    String tipoBloque = r.nextInt(2) == 0 ? "BloqueVacio" : "BloqueBlando"; // Decide aleatoriamente el tipo
	                    matriz[i][j] = factory.generarBloque(tipoBloque); // Usa la fábrica para crear el bloque
	                    notificarBloque(i, j, tipoBloque);
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
	
	
	
	//El bloque de la fila pI y la columna pJ pasa a ser un bloque ardiendo
	public void arder(int pI, int pJ) {
		// Comprueba que el bloque a poner a arder no es duro
		if (!(matriz[pI][pJ].getType().equals("BloqueDuro"))) {
			matriz[pI][pJ] = BloqueFactory.getBloqueFactory().generarBloque("BloqueArdiendo", pI, pJ);
			notificarBloque(pI, pJ, "BloqueArdiendo");
		}
	}
	
	public void arderExtendido(int pI, int pJ)
	{
		int i = pI;
		boolean salir = false;
		while (i<11 && !salir)
		{
			salir = matriz[i][pJ].getType().equals("BloqueDuro");
			if(i!=pI)
			{
				arder(i, pJ);
			}
			i++;
		}
		i = pI;
		while (i>=0 && !salir)
		{
			salir = matriz[i][pJ].getType().equals("BloqueDuro");
			if(i!=pI)
			{
				arder(i, pJ);
			}
			i--;
		}
		
		int j = pJ;
		salir = false;
		while(j<17 && !salir)
		{
			salir = matriz[pI][j].getType().equals("BloqueDuro");
			arder(pI, j);
			j++;
		}
		j = pJ;
		while(j>=0 && !salir)
		{
			salir = matriz[pI][j].getType().equals("BloqueDuro");
			arder(pI, j);
			j--;
		}
	}
	
	//El bloque de la fila pI y la columna pJ pasa de ser un bloque ardiendo a un bloque vacio
	public void dejarDeArder(int pI, int pJ)
	{
		matriz[pI][pJ] = new BloqueVacio();
	}
	
	//Notifica el tipo del bloque de la fila i y la columna j a la vista
	private void notificarBloque(int i, int j, String tipo)
	{
		setChanged();
		notifyObservers(tipo + "," + String.valueOf(i) + "," + String.valueOf(j));
	}
	
	
}
