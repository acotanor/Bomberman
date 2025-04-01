package observable;

public class BombermanBlanco extends Bomberman{
	private static BombermanBlanco miBom = new BombermanBlanco();
	private BombermanBlanco(){
		super();
	}
	public static BombermanBlanco getBom(){
		return miBom;
	}
}