package me.katsumag.A2Coursework;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // construct GUI
        BorderPane borderPane = new BorderPane();

        // construct left panel
        VBox leftPane = new VBox();
        Label leftTitle = new Label("Logic Components");

        ImageView imageView = new ImageView(new SVGLoader().load("./images/AND gate.svg"));
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);

        leftPane.getChildren().addAll(leftTitle, imageView);
        borderPane.setLeft(leftPane);

        // construct center panel
        Label label = new Label("Hello, world!");
        borderPane.setCenter(label);

        Scene scene = new Scene(borderPane, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
