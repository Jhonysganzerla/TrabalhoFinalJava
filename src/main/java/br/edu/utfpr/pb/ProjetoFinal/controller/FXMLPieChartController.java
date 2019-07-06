package br.edu.utfpr.pb.ProjetoFinal.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.dao.CategoriaDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class FXMLPieChartController implements Initializable {

    @FXML
    private PieChart pieChartCategoria;
    private CategoriaDao categoriaDao;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriaDao = new CategoriaDao();
        showChart();
    }

    public void showChart() {/*
        categoriaDao.getCategoriaProdutos().forEach( obj ->
                pieChartCategoria.getData().add(
                       new PieChart.Data( obj[0].toString()
                        , Integer.parseInt(obj[1].toString())))
        );
        pieChartCategoria.setTitle("Produtos por categoria");*/
    }
}
