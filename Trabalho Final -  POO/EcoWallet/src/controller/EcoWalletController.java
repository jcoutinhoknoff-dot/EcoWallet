package controller;

import javafx.scene.control.cell.PropertyValueFactory;
import factory.TransacaoFactory;
import model.Categoria;
import model.GerenciadorFinancas;
import model.Transacao;
import data.Persistencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class EcoWalletController {
    @FXML private TextField txtDescricao;
    @FXML private TextField txtValor;
    @FXML private ComboBox<String> cbTipo;
    @FXML private ComboBox<Categoria> cbCategoria;
    @FXML private Label lblSaldo;
    @FXML private Label lblErro;
    @FXML private TableView<Transacao> tabelaTransacoes;
    @FXML private TableColumn<Transacao, String> colDescricao;
    @FXML private TableColumn<Transacao, Double> colValor;
    @FXML private TableColumn<Transacao, java.time.LocalDate> colData;
    @FXML private TableColumn<Transacao, Categoria> colCategoria;

    private GerenciadorFinancas gerenciador = new GerenciadorFinancas();
    private ObservableList<Transacao> transacoesObservable;

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll("Receita", "Despesa");
        cbCategoria.getItems().addAll(Categoria.values());

        // CONFIGURAÇÃO DAS COLUNAS: Diz ao JavaFX qual getter chamar para cada coluna
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        // Carrega dados do TXT ao iniciar
        gerenciador.carregarTransacoes(Persistencia.carregar());
        
        transacoesObservable = FXCollections.observableArrayList(gerenciador.getTransacoes());
        tabelaTransacoes.setItems(transacoesObservable);
        
        atualizarSaldo();
    }

    @FXML
    public void adicionarMovimentacao() {
        lblErro.setText(""); // Limpa erros anteriores
        try {
            String tipo = cbTipo.getValue();
            String desc = txtDescricao.getText();
            double valor = Double.parseDouble(txtValor.getText()); // Tratamento NumberFormatException
            Categoria cat = cbCategoria.getValue();

            if (tipo == null || desc.isEmpty() || cat == null) {
                lblErro.setText("Preencha todos os campos!");
                return;
            }

            Transacao novaTransacao = TransacaoFactory.criarTransacao(tipo, desc, valor, LocalDate.now(), cat);
            
            gerenciador.adicionarTransacao(novaTransacao);
            transacoesObservable.add(novaTransacao);
            
            Persistencia.salvar(gerenciador.getTransacoes()); // Atualiza o TXT
            atualizarSaldo();

            txtDescricao.clear();
            txtValor.clear();
        } catch (NumberFormatException e) {
            lblErro.setText("Erro: O campo valor deve conter apenas números!");
        } catch (Exception e) {
            lblErro.setText("Erro ao adicionar transação: " + e.getMessage());
        }
    }

    private void atualizarSaldo() {
        double saldo = gerenciador.calcularSaldoTotal();
        lblSaldo.setText(String.format("Saldo Total: R$ %.2f", saldo));
    }
}