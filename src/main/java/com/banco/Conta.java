package com.banco;

public abstract class Conta implements OperacaoBancaria {
    private int numero;
    private double saldo = 0;
    private Agencia agencia;

    public Conta(int num, Agencia agencia){
        this.numero = num;
        this.agencia = agencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    // CORRIGIDO: Validação para impedir depósito de valor negativo.
    @Override
    public void depositar(double valor){
        if (valor > 0) {
            this.saldo = this.saldo + valor;
        } else {
            System.err.println("Erro: Valor para depósito deve ser positivo.");
        }
    }

    // CORRIGIDO: Validação para impedir saque de valor negativo.
    @Override
    public int sacar(double valor){
        if (valor > 0 && this.saldo >= valor){
            this.saldo = this.saldo - valor;
            return 1; // 1 para sucesso
        }
        return 0; // 0 para falha
    }
}