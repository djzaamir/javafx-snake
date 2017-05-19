package Alpha;

import Necklace.Necklace;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import Necklace.*;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Alpha extends Application{

    private Necklace necklace;
    private MenuBar menubar;
    private Menu file ,  view , exit;



    @Override
    public void start(Stage primaryStage) throws Exception {

        //initializing vars
        necklace =  new Necklace();
        menubar  = new MenuBar();
        file = new Menu("File");
        view = new Menu("View");
        exit = new Menu("Exit");
        menubar.getMenus().addAll(file , view , exit);


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
        for (Bail b:
             necklace.getBails()) {

            //Now append each bail to the canvas
            root.getChildren().add(b.getBail());

        }
        //endregion

        //region Game-Loop

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                  //Remove all bails from the root object
                for (Bail b:
                     necklace.getBails()) {
                    root.getChildren().remove(b.getBail());

                }


                //perform update function on all bails , to change their color
                necklace.updateBails();


                //Repopulate canvas with updated bails
                for (Bail b:
                        necklace.getBails()) {
                    root.getChildren().add(b.getBail());

                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();

        //endregion


        primaryStage.show(); //Showing up the application
    }

    public static void main(String[] args) throws Exception {launch(args);}
}
