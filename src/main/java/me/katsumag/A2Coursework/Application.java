package me.katsumag.A2Coursework;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.girod.javafx.svgimage.SVGImage;
import org.girod.javafx.svgimage.SVGLoader;

public class Application extends javafx.application.Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        // construct GUI
        BorderPane borderPane = new BorderPane();

        // construct left panel
        VBox leftPane = new VBox();

        // left pane title
        Label leftTitle = new Label("Logic Components");
        leftTitle.setFont(new Font(15));
        leftTitle.setUnderline(true);

        // include logic gate images
        SVGHelper svgHelper = new SVGHelper();
        SVGImage andGateImage = SVGLoader.load(svgHelper.getURLOf("./images/AND gate.svg"));
        SVGImage orGateImage = SVGLoader.load(svgHelper.getURLOf("./images/OR gate.svg"));
        SVGImage notGateImage = SVGLoader.load(svgHelper.getURLOf("./images/NOT gate.svg"));

        // add elements to left pane
        leftPane.getChildren().addAll(leftTitle, andGateImage, orGateImage, notGateImage);
        leftPane.setSpacing(20);
        leftPane.setPadding(new Insets(0, 0, 20, 5));

        borderPane.setLeft(leftPane);

        // construct center panel
        Label label = new Label("Hello, world!");
        borderPane.setCenter(label);

        // create and show scene
        Scene scene = new Scene(borderPane, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
