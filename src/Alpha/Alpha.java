package Alpha;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Snake.*;
import scale.Scale;

import java.awt.*;

/**
 * Created by Aamir on 5/19/2017.
 *
 * Each scale will be updated individually
 * Each snake will be updating each scale
 * Each snake will assigning new cords to the head node of snake
 * Then the old head cords will be shifted to one step back to the rest of the body
 * This logic will be taking place inside the update function of each snake
 * Snake will also have an UpdateViaAI() function which will be controlled by computer
 * Each snake will be detecting collision with other snake
 *
 *
 *
 */
public class Alpha extends Application{


    //region VARS
    private Snake snake;
    private MenuBar menubar;
    private Menu file ,  view , exit;
    private final int initial_scale = 4;
    //endregion

    //region Constructer
    public Alpha() {

        //initializing vars
        snake =  new Snake(new Color(0,0,0,1),1200 , 600,initial_scale,false);
        snake.initSnake(1200,600);
        menubar  = new MenuBar();
        file = new Menu("File");
        view = new Menu("View");
        exit = new Menu("Exit");
        menubar.getMenus().addAll(file , view , exit);
    }
    //endregion

    @Override
    //Entry point for javaFx framework
    public void start(Stage primaryStage) throws Exception {



        //region Main Setup , look and feel
        //Setting up basic attributes for Stag
        primaryStage.setTitle("Shaolin Dual Snakes");
        primaryStage.setResizable(false);


        //Setting up very basic window structure for the app
        Group root = new Group();
        Scene scene  =  new Scene(root);
        primaryStage.setScene(scene);


        //Canvas holder Stack pane ,  this is being  used here primarily to change background color
        VBox holder =  new VBox();
        holder.setStyle("-fx-background-color: green");
        //Adding menu bar
        holder.getChildren().add(menubar);


        //Setting up canvas
        Canvas canvas =  new Canvas(1200 ,600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);
        //endregion

        //region Event Handler's For input

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });

        //endregion

        //region Injecting My Game Look And feel into the Environment


          for(Scale scale : snake.getScales()){
              scale.initScale();
              root.getChildren().add(scale.getScale());
          }
        //endregion

        //region Game-Loop
          new AnimationTimer() {
            @Override
            public void handle(long now) {



            }

        }.start();
        //endregion


        primaryStage.show(); //Showing up the application
    }



    //Main method , entry point for Java CODE
    public static void main(String[] args) throws Exception {launch(args);}
}
