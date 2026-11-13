package com.example;

// Subclase 3
public class Inversion extends Cuenta {
    private double tasaInteresAnual;   // porcentaje, por ejemplo 9.0
    private int plazoMeses;

    public Inversion(String titular, String numero, double saldo, int anioApertura,
                     double tasaInteresAnual, int plazoMeses) {
        super(titular, numero, saldo, anioApertura);
        setTasaInteresAnual(tasaInteresAnual);
        setPlazoMeses(plazoMeses);
    }

    public double getTasaInteresAnual() { return tasaInteresAnual; }
    public int getPlazoMeses() { return plazoMeses; }

    public void setTasaInteresAnual(double tasaInteresAnual) {
        if (tasaInteresAnual <= 0 || tasaInteresAnual > 30) {
            throw new IllegalArgumentException("La tasa de interés anual debe estar entre 0 y 30.");
        }
        this.tasaInteresAnual = tasaInteresAnual;
    }

    public void setPlazoMeses(int plazoMeses) {
        if (plazoMeses < 1 || plazoMeses > 120) {
            throw new IllegalArgumentException("El plazo debe estar entre 1 y 120 meses.");
        }
        this.plazoMeses = plazoMeses;
    }

    /** Lógica de negocio: interés mensual con 10% extra si el plazo es de 12 meses o más. */
    @Override
    public double calcularRendimientoMensual() {
        double interes = super.calcularRendimientoMensual()
                + this.saldo * (this.tasaInteresAnual / 100) / 12;
        if (this.plazoMeses >= 12) {
            interes = interes * 1.10;
        }
        return interes;
    }

    /** Lógica de negocio: retirar antes del vencimiento cobra una penalización del 2% del monto. */
    @Override
    public void retirar(double monto) {
        double penalizacion = monto * 0.02;
        super.retirar(monto + penalizacion);
    }

    @Override
    public String toString() {
        return super.toString() + ", Tasa anual: " + tasaInteresAnual
                + "%, Plazo: " + plazoMeses + " meses";
    }
}