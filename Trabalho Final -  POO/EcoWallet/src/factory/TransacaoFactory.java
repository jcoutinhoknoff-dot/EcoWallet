package factory;

import model.Categoria;
import model.Despesa;
import model.Receita;
import model.Transacao;
import java.time.LocalDate;

public class TransacaoFactory {
    public static Transacao criarTransacao(String tipo, String descricao, double valor, LocalDate data, Categoria categoria) {
        if (tipo.equalsIgnoreCase("Receita")) {
            return new Receita(descricao, valor, data, categoria);
        } else if (tipo.equalsIgnoreCase("Despesa")) {
            return new Despesa(descricao, valor, data, categoria);
        }
        throw new IllegalArgumentException("Tipo de transação inválido");
    }
}