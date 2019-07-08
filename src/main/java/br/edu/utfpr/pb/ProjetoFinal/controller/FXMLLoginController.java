package br.edu.utfpr.pb.ProjetoFinal.controller;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.dao.UsuarioDao;
import br.edu.utfpr.pb.ProjetoFinal.model.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField textUsuario;
    @FXML
    private TextField textSenha;
    @FXML 
    private Button buttonEntrar;
    
    private UsuarioDao usuarioDao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarioDao = new UsuarioDao();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textUsuario.requestFocus();
            }
        });
        buttonEntrar.setDefaultButton(true);
    }    


    @FXML
    private void login() {
            try {
            Usuario usuario = this.usuarioDao.findByEmailAndSenhaNamedQuery(
                    textUsuario.getText(),this.usuarioDao.getMd5(textSenha.getText()));
            if (usuario != null) {
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(
                    this.getClass()
                            .getResource("/fxml/FXMLPrincipal.fxml"));
                VBox root = (VBox) loader.load();
                Scene scene = new Scene(root);
               // scene.getStylesheets().add("/styles/temaDark.css");
                
                Stage stage = new Stage();
                stage.setTitle("Projeto Final - 0024S");
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.setResizable(true);
                
                FXMLPrincipalController controller
                    = loader.getController();
                controller.setUsuarioAutenticado(usuario);
                
                stage.show();
                
                Stage stageLogin = (Stage) buttonEntrar.getScene().getWindow();
                stageLogin.close();
                        
            }
            if(usuario != null && usuario.getIsAdmin()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Bem vindo");
                alert.setHeaderText("Usuario logado como administrador");
                alert.setContentText("Use suas permissões com sabedoria");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Usuário e/ou senha inválidos!");
            alert.setContentText("Por favor, tente novamente!");
            alert.showAndWait();
        }
    }
    
}
