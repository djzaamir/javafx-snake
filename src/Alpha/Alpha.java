package Alpha;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Alpha extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Setting up basic attributes for Stage
        primaryStage.setTitle("Shaolin Dual Snakes");
        primaryStage.setResizable(false);


        //Setting up very basic window structure for the app
        Group root = new Group();
        Scene scene  =  new Scene(root);
        //region Event Handler's For input

          scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
              @Override
              public void handle(KeyEvent event) {

              }
          });

        //endregion
        primaryStage.setScene(scene);


        //Canvas holder Stack pane ,  this is being  used here primarily to change background color
        StackPane holder =  new StackPane();
        holder.setStyle("-fx-background-color: green");



        //Setting up canvas
        Canvas canvas =  new Canvas(1200 ,600);
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);


        //region Game-Loop

        new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        }.start();

        //endregion


        primaryStage.show(); //Showing up the application
    }

    public static void main(String[] args) throws Exception {launch(args);}
}
