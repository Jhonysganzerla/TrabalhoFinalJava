package br.edu.utfpr.pb.ProjetoFinal.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import br.edu.utfpr.pb.ProjetoFinal.dao.CategoriaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class FXMLBarChartController implements Initializable {

    // O gráfico vai exibir o total vendido por categoria por mês
    @FXML //BarChart com a Descricao da Categoria (String) e o valor total vendido (Double)
    private BarChart<String, Double> barChartVendas;

    @FXML // Eixo X no qual serão exibidos os meses
    private CategoryAxis xAxis;
    //Lista com os meses
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    private CategoriaDao categoriaDao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriaDao = new CategoriaDao();
        //Cria o array de meses
        String[] months = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", 
            "Ago", "Set", "Out", "Nov", "Dez"};
        //DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.addAll(Arrays.asList(months));
        // Cria o eixo X com os meses
        xAxis.setCategories(monthNames);
        showChart();
    }

    public void showChart() {/*
        XYChart.Series<String, Double> data = new XYChart.Series<>();
        String descAux = "";
        // Percorre o resultado do Select
        for (Object[] obj : categoriaDao.getCategoriaVendaMes()) {
            // O IF verifica se é uma categoria nova e cria um novo conjunto de dados 
            if (!descAux.equals(obj[1].toString())) {
                data = new XYChart.Series<>();
                data.setName(obj[1].toString());
                descAux = obj[1].toString();
                barChartVendas.getData().add(data); // adiciona a lista no gráfico
            }
            // Para cada mês retornado do BD adiciona o total vendido na lista
            data.getData().add(new XYChart.Data(
                    monthNames.get((int) Math.round(Double.parseDouble(obj[2].toString())) - 1),
                    obj[0])
            );
        }
        barChartVendas.setTitle("Valor total vendido por categoria/mês");*/
    }
}
