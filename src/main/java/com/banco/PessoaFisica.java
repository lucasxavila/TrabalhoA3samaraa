package com.banco;

public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(String nome, String cpf){
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
}