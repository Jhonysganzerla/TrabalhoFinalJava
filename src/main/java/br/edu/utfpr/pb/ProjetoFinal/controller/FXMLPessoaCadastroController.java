/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.ProjetoFinal.controller;

import br.edu.utfpr.pb.ProjetoFinal.dao.CidadeDao;
import br.edu.utfpr.pb.ProjetoFinal.dao.EstadoDao;
import br.edu.utfpr.pb.ProjetoFinal.dao.PessoaDao;
import br.edu.utfpr.pb.ProjetoFinal.model.Cidade;
import br.edu.utfpr.pb.ProjetoFinal.model.Estado;
import br.edu.utfpr.pb.ProjetoFinal.model.Pessoa;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author T-Gamer
 */
public class FXMLPessoaCadastroController implements Initializable {

    @FXML
    private TextField textId;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textCpfCnpj;
   
    @FXML
    private TextField textRua;
   
    @FXML
    private TextField textBairro;
   
    @FXML
    private TextField textNumero;
       
    @FXML
    private CheckBox checkCliente;
    
    @FXML
    private CheckBox checkFornecedor;
    
    @FXML
    private ComboBox<Estado> comboEstado;
    @FXML
    private ComboBox<Cidade> comboCidade;
    
    private EstadoDao estadoDao;
    
    private CidadeDao cidadeDao;
    
    private Stage dialogStage;
    private Pessoa pessoa;
    private PessoaDao pessoaDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.pessoaDao = new PessoaDao();
        this.pessoa = new Pessoa();

        this.cidadeDao = new CidadeDao();
        this.estadoDao = new EstadoDao();
        ObservableList<Estado> estados =
                FXCollections.observableArrayList(
                        estadoDao.getAll()
                );
        ObservableList<Cidade> cidades =
                FXCollections.observableArrayList(
                        cidadeDao.getAll()
                );

        /*this.comboEstado.setOnAction(event -> {
            ObservableList<Cidade> cidades =
                    FXCollections.observableArrayList(
                            this.cidadeDao.findCidadeByEstado(
                                    (this.comboEstado.getSelectionModel()
                                            .getSelectedItem()))
                    );
            this.comboCidade.setItems(cidades);
        });*/

        this.comboEstado.setItems(estados);
        this.comboCidade.setItems(cidades);
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        if (pessoa.getId() != null) {
            this.textId.setText(pessoa.getId().toString());
            this.textBairro.setText(pessoa.getBairro());


            this.textNome.setText(pessoa.getNome());
            this.textCpfCnpj.setText(pessoa.getCpfcnpj());
            this.comboEstado.setValue(pessoa.getEstado());
            this.comboCidade.setValue(pessoa.getCidade());
            this.textBairro.setText(pessoa.getBairro());
            this.textBairro.setText(pessoa.getBairro());
            this.textNumero.setText(pessoa.getNumero());
            this.textRua.setText(pessoa.getRua());

            this.checkCliente.setSelected(pessoa.getIsCliente());
            this.checkFornecedor.setSelected(pessoa.getIsFornecedor());
        }
    }
    
    
    @FXML
    private void cancel() {
        this.dialogStage.close();
    }

    @FXML
    private void save() {
        
            pessoa.setNome(textNome.getText());
            pessoa.setCpfcnpj(textCpfCnpj.getText());
            pessoa.setEstado(comboEstado.getSelectionModel()
                    .getSelectedItem());
            pessoa.setCidade(comboCidade.getSelectionModel()
                    .getSelectedItem());
            pessoa.setBairro(textBairro.getText());
            pessoa.setRua(textBairro.getText());
            pessoa.setNumero(textNumero.getText());
            pessoa.setRua(textRua.getText());

        pessoa.setIsCliente(checkCliente.isSelected());
            pessoa.setIsFornecedor(checkFornecedor.isSelected());
        if (this.pessoaDao.isValid(pessoa)) {
            this.pessoaDao.save(pessoa);
            this.dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum registro "
                    + "selecionado");
            alert.setContentText(
                    this.pessoaDao.getErrors(pessoa)
            );
            alert.showAndWait();
        }
    }
}
