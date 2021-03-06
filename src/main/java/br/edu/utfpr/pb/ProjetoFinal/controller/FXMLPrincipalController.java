package br.edu.utfpr.pb.ProjetoFinal.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.model.Usuario;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private VBox boxPrincipal;

    private Usuario usuarioAutenticado;

    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDataPane(Node node) {
        /* Atualiza o VBox (boxPrincipal) com um 
            novo form (FXML) dependendo do 
            item de menu ou botão acionado
         */
        boxPrincipal.getChildren().setAll(node);
    }

    public VBox openVBox(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(
                this.getClass().getResource(url));
        FadeTransition ft = new FadeTransition(
                Duration.millis(1000));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }

    @FXML
    public void loadCategoria(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLCategoriaLista.fxml"
        ));
    }

    @FXML
    public void loadProduto(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLProdutoLista.fxml"
        ));
    }

    @FXML
    public void loadUsuario(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLUsuarioLista.fxml"
        ));
    }
    
      @FXML
    public void loadPessoa(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLPessoaLista.fxml"
        ));
    }

    @FXML
    public void loadCompra(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLCompraLista.fxml"
        ));
    }

   @FXML
    public void loadVenda(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLVendaLista.fxml"
        ));
    }


    @FXML
    public void loadContaPagar(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLContaPagarLista.fxml"
        ));
    }


    @FXML
    public void loadContaReceber(ActionEvent event)
            throws IOException {
        setDataPane(openVBox(
                "/fxml/FXMLContaPagarLista.fxml"
        ));
    }

/*
    @FXML
    private void showReportProduto(ActionEvent event) {
        GenerateReport generateReport = new GenerateReport();
        InputStream file = this.getClass().getResourceAsStream("/report/produtos.jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "Relatório de Produtos - JavaFx");
        Image imagem = new ImageIcon(
                this.getClass().getResource("/imagens/logoUTFPR.jpg")).getImage();
        parameters.put("LOGO", imagem);

        DatabaseConnection conn = DatabaseConnection.getInstance();
        try {
            JasperViewer viewer = generateReport.getReport(
                    conn.getConnection(), parameters, file);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao exibir relatório!");
            alert.setContentText("Falha ao exibir relatório!");
            alert.showAndWait();
        }
    }*/

    @FXML
    private void showPieChart(ActionEvent event) throws IOException {
        setDataPane(openVBox("/fxml/FXMLPieChart.fxml"));
    }

    @FXML
    private void showBarChart(ActionEvent event) throws IOException {
        setDataPane(openVBox("/fxml/FXMLBarChart.fxml"));
    }
}
