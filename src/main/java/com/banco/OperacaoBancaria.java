package com.banco;

/**
 * Define o contrato para todas as operações bancárias.
 */
public interface OperacaoBancaria {
    public void depositar(double valor);
    public int sacar(double valor);
    public void imprimir();   
}