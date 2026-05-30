package model;

import java.time.LocalDate;

public class Receita extends Transacao {
    public Receita(String descricao, double valor, LocalDate data, Categoria categoria) {
        super(descricao, valor, data, categoria);
    }

    @Override
    public double getValorParaSaldo() {
        return this.getValor(); 
    }
}