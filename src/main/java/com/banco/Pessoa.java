package com.banco;

import java.util.ArrayList;

public abstract class Pessoa {
    private String nome;
    private ArrayList<Conta> listacontas;

    public Pessoa(String nome){
        this.nome = nome;
        this.listacontas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Conta> getListaContas(){
        return this.listacontas;
    }

    public void adicionarConta(Conta conta){
        this.listacontas.add(conta);
    }
}