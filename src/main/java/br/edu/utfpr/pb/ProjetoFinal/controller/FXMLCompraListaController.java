/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.ProjetoFinal.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.dao.CompraVendaDao;
import br.edu.utfpr.pb.ProjetoFinal.model.CompraVenda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T-Gamer
 */
public class FXMLCompraListaController implements Initializable {


    @FXML
    private TableView<CompraVenda> tableData;
    @FXML
    private TableColumn<CompraVenda, Long> columnId;
    @FXML
    private TableColumn<CompraVenda, String> columnDescricao;
    @FXML
    private TableColumn<CompraVenda, BigDecimal> columnValor;

    @FXML
    private Button buttonEdit;
    private CompraVendaDao compraVendaDao;
    private ObservableList<CompraVenda> list =
            FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.compraVendaDao = new CompraVendaDao();
        setColumnProperties();
        loadData();
    }

    private void setColumnProperties() {
        columnId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        columnDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );
        columnValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );
    }

    private void loadData() {
        list.clear();
        list.addAll(compraVendaDao.listAllCompra());

        tableData.setItems(list);
    }


    private void openForm(
            CompraVenda compra,
            ActionEvent event) {
        try {
            // Carregar o arquivo fxml e cria um
            //novo stage para a janela Modal
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    this.getClass()
                            .getResource("/fxml/FXMLCompraVendaCadastro.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            //Criando o stage para o modal
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Compra");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(
                    ((Node) event.getSource())
                            .getScene().getWindow());
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);

            FXMLCompraVendaCadastroController controller =
                    loader.getController();
            controller.setCompraVenda(compra);
            controller.setDialogStage(dialogStage);
            // Exibe a janela Modal e espera até o usuário
            //fechar
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro ao abrir "
                    + "a janela de cadastro!");
            alert.setContentText("Por favor, tente realizar "
                    + "a operação novamente!");
            alert.showAndWait();
        }
        loadData();
    }

    @FXML
    private void edit(ActionEvent event) {
        CompraVenda compra =
                tableData.getSelectionModel()
                        .getSelectedItem();
        this.openForm(compra, event);
    }

    @FXML
    private void newRecord(ActionEvent event) {
        CompraVenda a = new CompraVenda();
        a.setIsVenda(false);
        this.openForm(a, event);
    }

    @FXML
    private void delete(ActionEvent event) {
        if (tableData.getSelectionModel()
                .getSelectedIndex() >=0) {
            try {
                CompraVenda compra =  tableData
                        .getSelectionModel().getSelectedItem();
                compraVendaDao.delete(compra.getId());
                tableData.getItems().remove(
                        tableData.getSelectionModel()
                                .getSelectedIndex());



            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um erro "
                        + " ao remover o registro!");
                alert.setContentText("Por favor, tente realizar "
                        + "a operação novamente!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText("Por favor, "
                    + "selecione um registro "
                    + "na tabela!");
            alert.showAndWait();
        }
    }

}
