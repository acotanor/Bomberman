package observable;

public class BloqueFactory {
    private static BloqueFactory miBF;

    private BloqueFactory() {}

    public static BloqueFactory getBloqueFactory() {
        if (miBF == null) {
            miBF = new BloqueFactory();
        }
        return miBF;
    }

    public Bloque generarBloque(String tipo) {
        switch (tipo) {
            case "BloqueDuro":
                return new BloqueDuro();
            case "BloqueBlando":
                return new BloqueBlando();
            case "BloqueVacio":
                return new BloqueVacio();
            default:
                throw new IllegalArgumentException("Tipo de bloque desconocido: " + tipo);
        }
    }

    // Sobrecarga para crear un BloqueArdiendo con coordenadas
    public Bloque generarBloque(String tipo, int pI, int pJ) {
        if ("BloqueArdiendo".equals(tipo)) {
            return new BloqueArdiendo(pI, pJ);
        }
        throw new IllegalArgumentException("Tipo de bloque desconocido o no soportado con coordenadas: " + tipo);
    }
}