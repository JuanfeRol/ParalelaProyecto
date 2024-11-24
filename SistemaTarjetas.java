import java.util.HashMap;
import java.util.Map;

public class SistemaTarjetas {
    private final Map<String, Tarjeta> tarjetas;

    //Constructor
    public SistemaTarjetas(int numeroTarjetas, double saldoInicial) {
        tarjetas = new HashMap<>();
        for (int i = 0; i < numeroTarjetas; i++) {
            String codigo = String.format("%03d", i); // Genera códigos de 3 dígitos (000, 001...)
            tarjetas.put(codigo, new Tarjeta(codigo, saldoInicial));
        }
    }

    //Funcion para crear una tarjeta
    public void crearTarjeta(String codigo, double saldoInicial) {
        if (tarjetas.containsKey(codigo)) {
            throw new IllegalArgumentException("Error: Código de tarjeta ya existe.");
        } else {
            tarjetas.put(codigo, new Tarjeta(codigo, 100));
        }
    }

    public Tarjeta getTarjeta(String codigo) {
        if (tarjetas.containsKey(codigo)) {
            return tarjetas.get(codigo);
        } else {
            throw new IllegalArgumentException("Error: Código de tarjeta no válido.");
        }
    }
}
