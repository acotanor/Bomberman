package observable;

public class BombermanBlanco extends Bomberman{
	private static BombermanBlanco miBom = new BombermanBlanco();
	private BombermanBlanco(){
		super();
	}
	public static BombermanBlanco getBom(){
		return miBom;
	}

	@Override
	public void soltarBomba(){
		if(this.vivo)
		{
			this.bombas.add(new BombaBlanco(this.coordenadas[0], this.coordenadas[1]));
				
			setChanged();
			notifyObservers("Bomba," + String.valueOf(this.coordenadas[0]) + "," + String.valueOf(this.coordenadas[1]));
		}
	}
}