package observable;

public class BombermanNegro extends Bomberman{
    private static BombermanNegro miBom = new BombermanNegro();
    private BombermanNegro(){
        super();
    }
    public static BombermanNegro getBom(){
        return miBom;
    }

    @Override
	public void soltarBomba(){
		if(this.vivo && bombas.size() < 1)
		{
			this.bombas.add(new BombaNegro(this.coordenadas[0], this.coordenadas[1]));
				
			setChanged();
			notifyObservers("Bomba," + String.valueOf(this.coordenadas[0]) + "," + String.valueOf(this.coordenadas[1]));
		}
	}
}
