package br.edu.utfpr.pb.ProjetoFinal.controller;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.dao.ContaPagarDao;
import br.edu.utfpr.pb.ProjetoFinal.model.ContaPagar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T-Gamer
 */
public class FXMLContaPagarCadastroController implements Initializable {

    @FXML
    private TextField textDescricao;

    @FXML
    private ComboBox<String> comboTipoPagamento;

    @FXML
    private DatePicker dateDataMovimento;

    @FXML
    private DatePicker dateDataVencimento;

    @FXML
    private TextField textParcelas;

    @FXML
    private TextField textValor;

    private Stage stage;
    private ContaPagarDao contaPagarDao;
    private ContaPagar contaPagar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contaPagarDao = new ContaPagarDao();
        ObservableList<String> tipoPagamento =
               FXCollections.observableArrayList(
                        new ContaPagar().getTiposPagamento()
                );
        this.comboTipoPagamento.setItems(tipoPagamento);

    }

    public void setContaPagar(ContaPagar contaPagar) {
        this.contaPagar = contaPagar;
        if (contaPagar.getId() != null) {
            this.comboTipoPagamento.setValue(contaPagar.getTipoPagamento());
            this.textDescricao.setText(contaPagar.getDescricao());
            this.dateDataMovimento.setValue(contaPagar.getDataMovimento());
            this.dateDataVencimento.setValue(contaPagar.getDataVencimento());
            this.textParcelas.setText(contaPagar.getParcelas().toString());
            this.textValor.setText(contaPagar.getValor().toString());
        }
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void cancel() {
        this.stage.close();
    }

    @FXML
    private void save() {
        if(contaPagar == null)
            contaPagar = new ContaPagar();
        contaPagar.setTipoPagamento(this.comboTipoPagamento.getSelectionModel().getSelectedItem());
        contaPagar.setDescricao(this.textDescricao.getText());
        contaPagar.setDataMovimento(this.dateDataMovimento.getValue());
        contaPagar.setDataVencimento(this.dateDataVencimento.getValue());
        contaPagar.setParcelas(Integer.parseInt(this.textParcelas.getText()));
        contaPagar.setValor(Double.parseDouble(this.textValor.getText()));
        this.contaPagarDao.save(contaPagar);
        this.stage.close();
    }

}

