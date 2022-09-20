package me.katsumag.A2Coursework;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import me.katsumag.A2Coursework.event_handlers.GateDragMovementHandler;
import me.katsumag.A2Coursework.event_handlers.GateStopDragHandler;
import me.katsumag.A2Coursework.components.*;

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
        ANDGate andGate = new ANDGate();
        ORGate orGate = new ORGate();
        NOTGate notGate = new NOTGate();
        // Cannot be named switch, builtin keyword
        Switch switchh = new Switch();
        Lamp lamp = new Lamp();

        // add elements to left pane
        leftPane.getChildren().addAll(leftTitle, andGate.getImage(), orGate.getImage(), notGate.getImage(), switchh.getImage(), lamp.getImage());
        leftPane.setSpacing(20);
        leftPane.setPadding(new Insets(0, 0, 20, 5));

        borderPane.setLeft(leftPane);

        // construct center panel
        borderPane.setCenter(new Pane());

        borderPane.getCenter().setOnDragOver(new GateDragMovementHandler());
        borderPane.getCenter().setOnDragDropped(new GateStopDragHandler());

        // create and show scene
        Scene scene = new Scene(borderPane, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.setTitle("A2Coursework | Logic Gates");
        primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("images/icon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
