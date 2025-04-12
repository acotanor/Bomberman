package observable;

public class BombaFactory {
    private static BombaFactory miBF;

    private BombaFactory() {}

    public static BombaFactory getBombaFactory() {
        if (miBF == null) {
            miBF = new BombaFactory();
        }
        return miBF;
    }

    public Bomba generarBomba(String tipo, int pI, int pJ) {
        switch (tipo) {
            case "BombaBlanco":
                return new BombaBlanco(pI, pJ);
            case "BombaNegro":
                return new BombaNegro(pI, pJ);
            default:
                throw new IllegalArgumentException("Tipo de bomba desconocido: " + tipo);
        }
    }
}
