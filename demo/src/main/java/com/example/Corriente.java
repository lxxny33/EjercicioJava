package com.example;

// Subclase 2
public class Corriente extends Cuenta {
    private double sobregiroMaximo;
    private int chequesEmitidos;

    public Corriente(String titular, String numero, double saldo, int anioApertura,
                     double sobregiroMaximo, int chequesEmitidos) {
        super(titular, numero, saldo, anioApertura);
        setSobregiroMaximo(sobregiroMaximo);
        setChequesEmitidos(chequesEmitidos);
    }

    public double getSobregiroMaximo() { return sobregiroMaximo; }
    public int getChequesEmitidos() { return chequesEmitidos; }

    public void setSobregiroMaximo(double sobregiroMaximo) {
        if (sobregiroMaximo < 0) {
            throw new IllegalArgumentException("El sobregiro máximo no puede ser negativo.");
        }
        this.sobregiroMaximo = sobregiroMaximo;
    }

    public void setChequesEmitidos(int chequesEmitidos) {
        if (chequesEmitidos < 0) {
            throw new IllegalArgumentException("Los cheques emitidos no pueden ser negativos.");
        }
        this.chequesEmitidos = chequesEmitidos;
    }

    /** Lógica de negocio: además del saldo, se puede usar el cupo de sobregiro. */
    @Override
    public double obtenerFondosDisponibles() {
        return super.obtenerFondosDisponibles() + this.sobregiroMaximo;
    }

    /** Lógica de negocio: cuota estándar + $8.000 de servicio + $1.500 por cheque emitido. */
    @Override
    public double calcularCuotaManejo() {
        return super.calcularCuotaManejo() + 8_000 + this.chequesEmitidos * 1_500;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Sobregiro máx: $%,.2f", sobregiroMaximo)
                + ", Cheques: " + chequesEmitidos;
    }
}
