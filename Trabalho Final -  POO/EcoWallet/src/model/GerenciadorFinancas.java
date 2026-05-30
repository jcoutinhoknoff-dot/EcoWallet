package model;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinancas {
    private List<Transacao> transacoes;

    public GerenciadorFinancas() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao t) {
        transacoes.add(t);
    }

    public void carregarTransacoes(List<Transacao> carregadas) {
        this.transacoes = carregadas;
    }

    public double calcularSaldoTotal() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            saldo += t.getValorParaSaldo();
        }
        return saldo;
    }
    
    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}