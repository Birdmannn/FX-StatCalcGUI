package statcalcgui.statcalcgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class StatCalcGUI extends Application {
    private TextField numInput;
    private Label topMessage, m1, summ, averagem, sdm;
    private StatCalc calc;
    private boolean clear;

    public void start(Stage stage) {
        calc = new StatCalc();
        topMessage = new Label("Enter a number, Click Enter:");
        topMessage.setTextFill(Color.YELLOWGREEN);
        topMessage.setStyle("-fx-font-weight: bold; -fx-background-color: black; -fx-font-size: 17");
        topMessage.setAlignment(Pos.CENTER);
        Button enterButton = new Button("Enter");
        Button clearButton = new Button("Clear");
        enterButton.setDefaultButton(true);

        enterButton.setOnAction(e -> {
            clear = false;
            calculateStats();
        });
        clearButton.setOnAction(e -> {
            clear = true;
            calculateStats();
        });

        numInput = new TextField("0");

        HBox secondRowBar = new HBox(3,numInput,enterButton,clearButton);
        secondRowBar.setStyle("-fx-padding: 0px");
        HBox.setHgrow(enterButton, Priority.ALWAYS);
        enterButton.setMaxWidth(Double.POSITIVE_INFINITY);
        HBox.setHgrow(clearButton, Priority.ALWAYS);
        clearButton.setMaxWidth(Double.POSITIVE_INFINITY);
        HBox.setHgrow(numInput, Priority.ALWAYS);
        numInput.setMaxWidth(Double.POSITIVE_INFINITY);
        TilePane root = new TilePane();

        root.setPrefColumns(1);
        root.setPrefRows(6);
        root.setVgap(3);

        root.setStyle("-fx-border-color: black; -fx-border-width: 3px; -fx-background-color: black");
        root.setPrefTileWidth(400);
        summ = new Label("Sum:                            0");
        summ.setAlignment(Pos.CENTER_LEFT);
        summ.setMaxWidth(Double.POSITIVE_INFINITY);
        summ.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-font-size: 13; -fx-padding: 4px 7px 4px 7px");
        m1 = new Label("Number of Entries:     0");
        m1.setAlignment(Pos.CENTER_LEFT);
        m1.setMaxWidth(Double.POSITIVE_INFINITY);
        m1.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-font-size: 13; -fx-padding: 4px 7px 4px 7px");
        averagem = new Label("Average:                      0");
        averagem.setAlignment(Pos.CENTER_LEFT);
        averagem.setMaxWidth(Double.POSITIVE_INFINITY);
        averagem.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-font-size: 13; -fx-padding: 4px 7px 4px 7px");
        sdm = new Label("Standard Deviation:   0");
        sdm.setAlignment(Pos.CENTER_LEFT);
        sdm.setMaxWidth(Double.POSITIVE_INFINITY);
        sdm.setStyle("-fx-font-weight: bold; -fx-background-color: white; -fx-font-size: 13; -fx-padding: 4px 7px 4px 7px");
        root.getChildren().addAll(topMessage,secondRowBar,m1,summ,averagem,sdm);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("StatCalc GUI");
        stage.setResizable(false);
        stage.show();


    }

    public void calculateStats() {
        if(clear) {
            calc = new StatCalc();
            m1.setText("Number of Entries:     0");
            summ.setText("Sum:                            0");
            averagem.setText("Average:                      0");
            sdm.setText("Standard Deviation:   0");
            numInput.requestFocus();
            numInput.setText("0");
            numInput.selectAll();
        }
        else {
            double num;
            try {
                num = Double.parseDouble(numInput.getText());
            }
            catch (NumberFormatException e) {
                numInput.requestFocus();
                numInput.selectAll();
                return;
            }



            calc.enter(num);
            m1.setText("Number of Entries:     " + calc.getCount());
            summ.setText("Sum:                            " + calc.getSum());
            averagem.setText("Average:                      " + calc.getMean());
            sdm.setText("Standard Deviation:   " + calc.getStandardDeviation());


            numInput.requestFocus();
            numInput.selectAll();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}