public class Usuario extends Thread {
    private final Tarjeta tarjeta;

    public Usuario(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public void run() {
        // Simula interacciones con la tarjeta
        tarjeta.cargarSaldo(50);
        tarjeta.realizarPago(20);
        System.out.println("Saldo final consultado: " + tarjeta.consultarSaldo());
    }
}
