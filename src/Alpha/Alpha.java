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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Snake.*;
import scale.Scale;

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
    private final int initial_scale = 5;
    private final int window_width  = 1200;
    private final int window_height = 600;
    //endregion

    //region Constructer
    public Alpha() {
        //initializing vars
        snake =  new Snake(Color.RED,1200 , 600,initial_scale,false);
        snake.initSnake(1200,600);
        initComponents(); //This function will initialize all the local VARS for this Alpha class
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


        //Canvas holder Vbox pane ,  this is being  used here primarily to change background color
        VBox holder =  new VBox();  //We are using Vbox here because we want to place the menu-bar at the very top ,
                                    // and this automatically puts first node at the top
        holder.setStyle("-fx-background-color: green");

        //Adding menu bar
        holder.getChildren().add(menubar);


        //Setting up canvas
        Canvas canvas =  new Canvas(window_width ,window_height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);
        //endregion

        //region Event Handler's For input

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case LEFT:
                        if (snake.getSnake_direction() != Snake.DIRECTION.RIGHT){
                            snake.setSnake_direction(Snake.DIRECTION.LEFT);
                        }
                        break;
                    case UP:
                        if (snake.getSnake_direction() != Snake.DIRECTION.DOWN){
                            snake.setSnake_direction(Snake.DIRECTION.UP);
                        }
                        break;
                    case RIGHT:
                        if (snake.getSnake_direction() != Snake.DIRECTION.LEFT){
                            snake.setSnake_direction(Snake.DIRECTION.RIGHT);
                        }
                        break;
                    case DOWN:
                        if (snake.getSnake_direction() !=  Snake.DIRECTION.UP){
                            snake.setSnake_direction(Snake.DIRECTION.DOWN);
                        }
                        break;
                    default:
                        //do nothing
                        break;

                }
            }
        });

        //endregion

        //region Injecting My Game Look And feel into the Environment

          for(Scale scale : snake.getScales()){
              scale.initScale(); //initialize the scale object , this is necessary because of the internal Implementation of Scale Object
              root.getChildren().add(scale.getScale()); //Adding to the Content-Pane(Group) , to Group and not vbox because vbox doesnt allow
                                                        // location override of elements , on the other hand Group does
              }

        //endregion

        //region Game-Loop
          new AnimationTimer() {
            @Override
            public void handle(long now) {

                //clearing the canvas

                for(Scale scale : snake.getScales()){
                    root.getChildren().remove(scale.getScale());
                }


                gc.clearRect(0 , 0 , window_width , window_height);
               snake.updateSnake();


                //Now readding stuff to the screen

                for(Scale scale : snake.getScales()){
                    scale.initScale(); //initialize the scale object , this is necessary because of the internal Implementation of Scale Object
                    root.getChildren().add(scale.getScale()); //Adding to the Content-Pane(Group) , to Group and not vbox because vbox doesnt allow
                    // location override of elements , on the other hand Group does
                }


                //Pausing the thread here , although we have to change this becuase this is not the proper way to do it , also its causing problems with the main thread
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();
        //endregion


        primaryStage.show(); //Showing up the application
    }



    //Main method , entry point for Java CODE
    public static void main(String[] args) throws Exception {launch(args);}

    //other supporting functions for this class
    //function to init all the vars
    private void initComponents() {
        menubar  = new MenuBar();
        file = new Menu("File");
        view = new Menu("View");
        exit = new Menu("Exit");
        menubar.getMenus().addAll(file , view , exit);
    }
}
