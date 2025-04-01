package observable;

public class BombermanNegro extends Bomberman{
    private static BombermanNegro miBom = new BombermanNegro();
    private BombermanNegro(){
        super();
    }
    public static BombermanNegro getBom(){
        return miBom;
    }
}
