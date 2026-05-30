package model;

import java.time.LocalDate;

public class Despesa extends Transacao {
    public Despesa(String descricao, double valor, LocalDate data, Categoria categoria) {
        super(descricao, valor, data, categoria);
    }

    @Override
    public double getValorParaSaldo() {
        return this.getValor() * -1; 
    }
}