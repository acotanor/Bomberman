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
    public void soltarBomba() {
        if (this.vivo && bombas.size() < 1) {
            Bomba bomba = BombaFactory.getBombaFactory().generarBomba("BombaNegro", this.coordenadas[0], this.coordenadas[1]);
            this.bombas.add(bomba);
    
            setChanged();
            notifyObservers("Bomba," + this.coordenadas[0] + "," + this.coordenadas[1]);
        }
    }
}
