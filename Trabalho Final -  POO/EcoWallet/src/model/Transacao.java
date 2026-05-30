package model;

import java.time.LocalDate;

public abstract class Transacao {
    private String descricao;
    private double valor;
    private LocalDate data;
    private Categoria categoria;

    public Transacao(String descricao, double valor, LocalDate data, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    // Método abstrato para garantir o Polimorfismo
    public abstract double getValorParaSaldo();

    // Getters
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public LocalDate getData() { return data; }
    public Categoria getCategoria() { return categoria; }
}