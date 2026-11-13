package com.example;

// Clase Padre
public class Cuenta {
    protected String titular;
    protected String numero;
    protected double saldo;
    protected int anioApertura;

    public Cuenta(String titular, String numero, double saldo, int anioApertura) {
        setTitular(titular);
        setNumero(numero);
        setSaldo(saldo);
        setAnioApertura(anioApertura);
    }

    /** Lógica de negocio: suma un monto positivo al saldo. */
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero.");
        }
        this.saldo += monto;
    }

    /** Lógica de negocio: resta un monto del saldo respetando los fondos disponibles. */
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor que cero.");
        }
        if (monto > obtenerFondosDisponibles()) {
            throw new IllegalArgumentException("Fondos insuficientes para realizar el retiro.");
        }
        this.saldo -= monto;
    }

    /** Fondos que se pueden usar. Las subclases lo sobrescriben (por ejemplo, con sobregiro). */
    public double obtenerFondosDisponibles() {
        return this.saldo;
    }

    /** Rendimiento (intereses) que genera la cuenta en un mes. Por defecto no genera. */
    public double calcularRendimientoMensual() {
        return 0.0;
    }

    /** Cuota de manejo mensual estándar: $12.000. */
    public double calcularCuotaManejo() {
        return 12_000;
    }

    /** Años completos desde la apertura de la cuenta. */
    public int calcularAntiguedad(int anioActual) {
        int antiguedad = anioActual - this.anioApertura;
        if (antiguedad < 0) {
            throw new IllegalArgumentException("El año actual no puede ser anterior al año de apertura.");
        }
        return antiguedad;
    }

    public String getTitular() { return titular; }
    public String getNumero() { return numero; }
    public double getSaldo() { return saldo; }
    public int getAnioApertura() { return anioApertura; }

    public void setTitular(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular no puede estar vacío.");
        }
        this.titular = titular;
    }

    public void setNumero(String numero) {
        if (numero == null || !numero.matches("\\d{8,12}")) {
            throw new IllegalArgumentException("El número de cuenta debe tener entre 8 y 12 dígitos.");
        }
        this.numero = numero;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.saldo = saldo;
    }

    public void setAnioApertura(int anioApertura) {
        if (anioApertura < 1950 || anioApertura > 2100) {
            throw new IllegalArgumentException("Año de apertura fuera del rango válido (1950-2100).");
        }
        this.anioApertura = anioApertura;
    }

    @Override
    public String toString() {
        return "Titular: " + titular + ", Cuenta: " + numero
                + String.format(", Saldo: $%,.2f", saldo) + ", Apertura: " + anioApertura;
    }
}