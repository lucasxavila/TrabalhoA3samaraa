package com.banco;

public class ContaCorrente extends Conta{
    private double salario;

    public ContaCorrente(int numero, double salario, Agencia agencia){
        super(numero, agencia);
        this.salario = salario;
    }

    @Override
    public void imprimir() {
        System.out.println("Conta Corrente | Salário base: "+this.salario);
    }

    public double getSalario(){
        return this.salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }
}