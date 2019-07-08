/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.ProjetoFinal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.dao.CompraVendaDao;
import br.edu.utfpr.pb.ProjetoFinal.dao.PessoaDao;
import br.edu.utfpr.pb.ProjetoFinal.dao.ProdutoDao;
import br.edu.utfpr.pb.ProjetoFinal.model.CompraVenda;
import br.edu.utfpr.pb.ProjetoFinal.model.CompraVendaProduto;
import br.edu.utfpr.pb.ProjetoFinal.model.Pessoa;
import br.edu.utfpr.pb.ProjetoFinal.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T-Gamer
 */
public class FXMLCompraVendaCadastroController implements Initializable {

    @FXML
    private TextField textNumdoc;
    @FXML
    private TextField textQuantidade;
    @FXML
    private TextField textUnitario;
    @FXML
    private TextField textTotal;
    @FXML
    private ComboBox<Produto> comboProduto;
    @FXML
    private TextArea textDescricao;
    @FXML
    private DatePicker dateData;
    @FXML
    private ComboBox<Pessoa> comboPessoa;

    private List<CompraVendaProduto> listaProdutos;

    @FXML
    private TableView<CompraVendaProduto> tableData;
    @FXML
    private TableColumn<CompraVendaProduto, Long> columnId;
    @FXML
    private TableColumn<CompraVendaProduto, String> columnProduto;
    @FXML
    private TableColumn<CompraVendaProduto, Long> columnQtde;
    @FXML
    private TableColumn<CompraVendaProduto, BigDecimal> columnValor;

    private ObservableList<CompraVendaProduto> list =
            FXCollections.observableArrayList();



    private Stage dialogStage;
    private CompraVendaDao compraVendaDao;
    private CompraVenda compravenda;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(listaProdutos == null){
            listaProdutos = new ArrayList<>();
        }
        ProdutoDao produtoDao;
        PessoaDao pessoaDao;

        this.compraVendaDao = new CompraVendaDao();
        this.compravenda = new CompraVenda();

        pessoaDao = new PessoaDao();
        produtoDao = new ProdutoDao();

        ObservableList<Pessoa> pessoas =
                FXCollections.observableArrayList(
                        pessoaDao.getAll()
                );

        ObservableList<Produto> produtos =
                FXCollections.observableArrayList(
                        produtoDao.getAll()
                );
        this.comboProduto.setItems(produtos);
        this.comboPessoa.setItems(pessoas);


        setColumnProperties();
        loadData();
    }


    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }


    private void setColumnProperties() {
        columnId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        columnProduto.setCellValueFactory(
                new PropertyValueFactory<>("produto")
        );
        columnQtde.setCellValueFactory(
                new PropertyValueFactory<>("quantidade")
        );
        columnValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );
    }

    public void setCompraVenda(CompraVenda compravenda) {
        this.compravenda = compravenda;
        this.listaProdutos = compravenda.getCompraVendaProdutos();
        if(this.listaProdutos != null && this.list != null)
            this.loadData();
        if (compravenda.getId() != null) {
            this.textNumdoc.setText(compravenda.getNumeroDocumento());
            this.textDescricao.setText(compravenda.getDescricao());
            this.dateData.setValue(compravenda.getData());
            this.comboPessoa.setValue(compravenda.getPessoa());

            this.listaProdutos = compraVendaDao.getOne(compravenda.getId()).getCompraVendaProdutos();
            Double b = 0.00;
            for(CompraVendaProduto a : this.listaProdutos){
              b =+  a.getValor()*a.getQuantidade();
            }
            this.textTotal.setText(b.toString());
        }
    }


    @FXML
    private void add(ActionEvent event) {
        if(listaProdutos == null){
            listaProdutos = new ArrayList<>();
        }
        CompraVendaProduto a = new CompraVendaProduto();
        a.setValor(Double.parseDouble(this.textUnitario.getText()));
        a.setProduto((Produto) this.comboProduto.getSelectionModel().getSelectedItem());
        a.setQuantidade(Integer.parseInt(this.textQuantidade.getText()));
        listaProdutos.add(a);

        loadData();
        setColumnProperties();
    }

    private void loadData() {
        list.clear();

        list.addAll(listaProdutos);
        reCalc();

        tableData.setItems(list);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >= 0) {
            try {
                CompraVendaProduto produto = tableData
                        .getSelectionModel().getSelectedItem();

                listaProdutos.remove(produto);

                tableData.getItems().remove(
                        tableData.getSelectionModel()
                                .getSelectedIndex());


                Double b = 0.00;
                for(CompraVendaProduto c : this.list){
                    b =+  c.getValor()*c.getQuantidade();
                }

                this.textTotal.setText(b.toString());

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro "
                        + " ao remover o produto!");
                alert.setContentText("Por favor, tente realizar "
                        + "a operação novamente!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum produto "
                    + "selecionado");
            alert.setContentText("Por favor, "
                    + "selecione um registro "
                    + "na tabela!");
            alert.showAndWait();
        }
    }

    private void reCalc(){
        Double b = 0.00;
        for(CompraVendaProduto c : this.listaProdutos){
            b +=  c.getValor()*c.getQuantidade();
        }

        this.textTotal.setText(b.toString());
    }

    @FXML
    private void cancel() {
        this.dialogStage.close();
    }

    @FXML
    private void save() {

        try{
        compravenda.setNumeroDocumento(textNumdoc.getText());
      //  compravenda.setParcelas(text.getText());
        compravenda.setData(this.dateData.getValue());
        compravenda.setPessoa((Pessoa) this.comboPessoa.getSelectionModel()
                .getSelectedItem());
        compravenda.setDescricao(textDescricao.getText());
        for (CompraVendaProduto a : this.listaProdutos){
            a.setCompravenda(compravenda);
        }
        compravenda.setCompraVendaProdutos(this.listaProdutos);

        if (this.compraVendaDao.isValid(compravenda)) {
            this.compraVendaDao.save(compravenda);
            this.compraVendaDao.save(compravenda);
            this.dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText(
                    this.compraVendaDao.getErrors(compravenda)
            );
            alert.showAndWait();
        }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Feche o formulario e abra novamente");
            alert.setContentText(
                    this.compraVendaDao.getErrors(compravenda)
            );
            alert.showAndWait();
        }
    }
}


