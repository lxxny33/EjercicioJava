package com.example;

// Subclase 1
public class Ahorros extends Cuenta {
    private double tasaInteresAnual;   // porcentaje, por ejemplo 4.5
    private boolean cuentaNomina;

    public Ahorros(String titular, String numero, double saldo, int anioApertura,
                   double tasaInteresAnual, boolean cuentaNomina) {
        super(titular, numero, saldo, anioApertura);
        setTasaInteresAnual(tasaInteresAnual);
        this.cuentaNomina = cuentaNomina;
    }

    public double getTasaInteresAnual() { return tasaInteresAnual; }
    public boolean isCuentaNomina() { return cuentaNomina; }
    public void setCuentaNomina(boolean cuentaNomina) { this.cuentaNomina = cuentaNomina; }

    public void setTasaInteresAnual(double tasaInteresAnual) {
        if (tasaInteresAnual < 0 || tasaInteresAnual > 20) {
            throw new IllegalArgumentException("La tasa de interés anual debe estar entre 0 y 20.");
        }
        this.tasaInteresAnual = tasaInteresAnual;
    }

    /** Lógica de negocio: interés mensual = saldo * (tasa anual / 12). */
    @Override
    public double calcularRendimientoMensual() {
        double base = super.calcularRendimientoMensual();
        return base + this.saldo * (this.tasaInteresAnual / 100) / 12;
    }

    /** Lógica de negocio: las cuentas de nómina no pagan cuota de manejo. */
    @Override
    public double calcularCuotaManejo() {
        if (this.cuentaNomina) {
            return 0;
        }
        return super.calcularCuotaManejo();
    }

    @Override
    public String toString() {
        return super.toString() + ", Tasa anual: " + tasaInteresAnual
                + "%, Nómina: " + (cuentaNomina ? "sí" : "no");
    }
}