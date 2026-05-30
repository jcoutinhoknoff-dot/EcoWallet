package data;

import model.Categoria;
import model.Transacao;
import factory.TransacaoFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static final String ARQUIVO = "transacoes.txt";

    public static void salvar(List<Transacao> transacoes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Transacao t : transacoes) {
                String tipo = t.getValorParaSaldo() >= 0 ? "Receita" : "Despesa";
                bw.write(tipo + ";" + t.getDescricao() + ";" + t.getValor() + ";" + t.getData() + ";" + t.getCategoria().name());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static List<Transacao> carregar() {
        List<Transacao> lista = new ArrayList<>();
        File file = new File(ARQUIVO);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Transacao t = TransacaoFactory.criarTransacao(
                    dados[0], dados[1], Double.parseDouble(dados[2]), 
                    LocalDate.parse(dados[3]), Categoria.valueOf(dados[4])
                );
                lista.add(t);
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        return lista;
    }
}