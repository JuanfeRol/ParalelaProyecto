import java.util.concurrent.locks.ReentrantLock;

public class Tarjeta {
    private final String codigo; // Código único de 3 dígitos
    private double saldo;
    private final ReentrantLock lock = new ReentrantLock(); // Bloqueo para exclusión mutua

    public Tarjeta(String codigo, double saldoInicial) {
        this.codigo = codigo;
        this.saldo = saldoInicial;
    }

    public String getCodigo() {
        return codigo;
    }

    public void cargarSaldo(double monto) {
        lock.lock();
        try {
            if (monto > 0) {
                saldo += monto;
                System.out.println("Saldo cargado: " + monto + " a la tarjeta " + codigo);
            } else {
                System.out.println("Error: Monto inválido para cargar.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void realizarPago(double monto) {
        lock.lock();
        try {
            if (monto > 0 && monto <= saldo) {
                saldo -= monto;
                System.out.println("Pago realizado: " + monto + " de la tarjeta " + codigo);
            } else {
                System.out.println("Error: Saldo insuficiente o monto inválido.");
            }
        } finally {
            lock.unlock();
        }
    }

    public double consultarSaldo() {
        lock.lock();
        try {
            return saldo;
        } finally {
            lock.unlock();
        }
    }
}
