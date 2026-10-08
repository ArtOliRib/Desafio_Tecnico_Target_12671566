package org.arthur.metodo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculadoraJuros {

    public static class Resultado {

        private final double valorInicial;
        private final double taxaDiaria;
        private final long dias;
        private final double valorJuros;
        private final double valorTotal;

        public Resultado(double valorInicial, double taxaDiaria, long dias, double valorJuros, double valorTotal) {
            this.valorInicial = valorInicial;
            this.taxaDiaria = taxaDiaria;
            this.dias = dias;
            this.valorJuros = valorJuros;
            this.valorTotal = valorTotal;
        }

        public double getValorInicial() {
            return valorInicial;
        }

        public double getTaxaDiaria() {
            return taxaDiaria;
        }

        public long getDias() {
            return dias;
        }

        public double getValorJuros() {
            return valorJuros;
        }

        public double getValorTotal() {
            return valorTotal;
        }
    }

    public static Resultado calcular(double valorInicial, double taxaDiaria, LocalDate dataAtual, LocalDate dataVencimento) {
        double valorDoJurosDiario = valorInicial * taxaDiaria;
        long dias = ChronoUnit.DAYS.between(dataAtual, dataVencimento);
        double valorTotal = valorInicial + (valorDoJurosDiario * dias);
        return new Resultado(valorInicial, taxaDiaria, dias, valorDoJurosDiario * dias, valorTotal);
    }
}
