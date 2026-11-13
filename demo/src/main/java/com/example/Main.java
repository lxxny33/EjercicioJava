package com.example;

// Clase Principal de Prueba
public class Main {
    public static void main(String[] args) {
        int anioActual = 2026;

        // Polimorfismo: arreglo del tipo padre con objetos de las tres subclases
        Cuenta[] cuentas = {
            new Ahorros("Ana Torres", "10234567890", 4_000_000, 2018, 4.5, false),
            new Corriente("Luis Pérez", "98765432101", 1_500_000, 2020, 2_000_000, 10),
            new Inversion("Marta Gómez", "55443322110", 20_000_000, 2022, 9.0, 18)
        };

        for (Cuenta c : cuentas) {
            System.out.println(c.toString());
            System.out.println("Antigüedad: " + c.calcularAntiguedad(anioActual) + " años");
            System.out.printf("Rendimiento mensual: ", c.calcularRendimientoMensual());
            System.out.printf("Cuota de manejo:    ", c.calcularCuotaManejo());
            System.out.printf("Fondos disponibles:  ", c.obtenerFondosDisponibles());

            c.depositar(500_000);
            c.retirar(1_000_000);   // cada subclase maneja el retiro a su manera
            System.out.printf("Saldo tras depositar 500.000 y retirar 1.000.000: ", c.getSaldo());
            System.out.println("-------------------------------------");
        }

        // Demostración de validaciones
        try {
            cuentas[0].retirar(50_000_000);
        } catch (IllegalArgumentException ex) {
            System.out.println("Validación OK -> " + ex.getMessage());
        }
    }
}