package Vue;

import java.util.List;

import DAO.FonDAO;
import Models.Vente;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class StatsView{
	
	private HBox root;
	
	public StatsView() {
		
		PieChart pieChart = new PieChart();
		List<Vente> list = FonDAO.GetSellByDay();
		
		for(Vente v : list) {
			PieChart.Data s = new PieChart.Data(v.getDate(), v.getNum());
			pieChart.getData().add(s);
		}
        
        pieChart.setPrefSize(400, 300);
 
        pieChart.setLegendSide(Side.BOTTOM);
        pieChart.setStartAngle(0);
        pieChart.setTitle("Représentation Du Nombre De Ventes Par Jour");
 
        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");
        caption.setStyle("-fx-text-fill: red;");
 
        for(final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY() - 50);
                    caption.setText(String.valueOf(data.getPieValue()) + " Ventes");
                }
            });
        }
        AnchorPane r = new AnchorPane();
        r.getChildren().addAll(pieChart, caption);
        r.setStyle("-fx-background-color : rgba(42, 143, 4, 0.7);");
        
        root = new HBox();
        root.getChildren().add(r);
	}
	
	public Pane getRoot() {
		return this.root;
	}

}
