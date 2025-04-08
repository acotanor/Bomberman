package observable;

public abstract class Bomba {
	private int[] coordenadas;
	
	public Bomba(int pI,int pJ)
	{	
		coordenadas = new int[2];
		coordenadas[0] = pI;
		coordenadas[1] = pJ;
	}
	
	public boolean tienePosicion(int pI,int pJ)
	{
		return (coordenadas[0] == pI && coordenadas[1] == pJ);
	}

	public abstract void arder(int pI, int pJ);
}
