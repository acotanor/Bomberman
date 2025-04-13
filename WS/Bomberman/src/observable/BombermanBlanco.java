package observable;

public class BombermanBlanco extends Bomberman{
	private static BombermanBlanco miBom = new BombermanBlanco();
	private BombermanBlanco(){
		super();
		this.color = "white";
	}
	public static BombermanBlanco getBom(){
		return miBom;
	}

	@Override
	public void soltarBomba() {
		if (this.vivo && bombas.size() < 10) {
			Bomba bomba = BombaFactory.getBombaFactory().generarBomba("BombaBlanco", this.coordenadas[0], this.coordenadas[1]);
			this.bombas.add(bomba);
	
			setChanged();
			notifyObservers("Bomba," + this.coordenadas[0] + "," + this.coordenadas[1] + ",white");
		}
	}
}