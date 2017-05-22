package Alpha;
import Food.Food;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Snake.*;
import root_EventHandler_thread.root_Eventhandler_thread;
import scale.Scale;

import javax.swing.*;

/**
 * Created by Aamir on 5/19/2017.
 * Each scale will be updated individually
 * Each snake will be updating each scale
 * Each snake will assigning new cords to the head node of snake
 * Then the old head cords will be shifted to one step back to the rest of the body
 * This logic will be taking place inside the update function of each snake
 * Snake will also have an UpdateViaAI() function which will be controlled by computer
 * Each snake will be detecting collision with other snake
 */

public class Alpha extends Application{


    //region VARS
    private Snake snake ,  snake2;
    private Food food;
    private MenuBar menubar;
    private Menu file ,  view  , Help;
    private MenuItem About , exit;
    private final int initial_scale = 5;
    private final int window_width  = 1250;
    private final int window_height = 600;
    //endregion

    //region Constructer
    public Alpha() {
        //initializing vars
        snake =  new Snake(Color.ORANGE,window_width , window_height,initial_scale,false);
        snake2 =  new Snake(Color.RED , window_width , window_height , initial_scale ,true);
        snake.initSnake(window_width,window_height);
        snake2.initSnake(window_width , window_height);
        food =  new Food(window_width , window_height ,  Color.WHEAT);
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
        Scene scene  =  new Scene(root , window_width  , window_height);
        primaryStage.setScene(scene);


        //Canvas holder Vbox pane ,  this is being  used here primarily to change background color
        VBox holder =  new VBox();  //We are using Vbox here because we want to place the menu-bar at the very top ,
                                    // and this automatically puts first node at the top
        holder.setStyle("-fx-background-color: black");

        //Adding menu bar
        holder.getChildren().add(menubar);


        //Setting up canvas
        Canvas canvas =  new Canvas(window_width + 50 ,window_height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);
        //endregion

        //region Event Handler's  and THREADS For input And Menu items

          //region Handlers for scene and about
        root_Eventhandler_thread Parent_Input_Threaded_EventHandler = new root_Eventhandler_thread(snake,scene,About);//snake is passed in because we need Key ENUMS present inside snake class
        Thread event_handler_thread = new Thread(Parent_Input_Threaded_EventHandler);
        event_handler_thread.start();
        //endregion

          //region Snake-2(Computer Snake) Running in seprate thread  , (its update method)
        //Snake no 2 update method is running is sperate thread
        //Also before starting the thread we are going to pass the food and user snake object as well to computer snake
        snake2.setFood(food);
        snake2.setUser_snake(snake);
        snake2.setSnake_speed(333);
        //Now seperating from Main thread
        Thread snk2 = new Thread(snake2);
         snk2.start();
        //endregion

        //endregion

        //region Injecting My Game Look And feel into the Environment

          //User-Snake
          for(Scale scale : snake.getScales()){
              scale.initScale(); //initialize the scale object , this is necessary because of the internal Implementation of Scale Object
              root.getChildren().add(scale.getScale()); //Adding to the Content-Pane(Group) , to Group and not vbox because vbox doesnt allow
                                                        // location override of elements , on the other hand Group does
          }

          //Snake-2
          for(Scale scale : snake2.getScales()){
            scale.initScale(); //initialize the scale object , this is necessary because of the internal Implementation of Scale Object
            root.getChildren().add(scale.getScale()); //Adding to the Content-Pane(Group) , to Group and not vbox because vbox doesnt allow
            // location override of elements , on the other hand Group does
          }

           //Initiating food here
            Snake[] snakes = {snake ,  snake2};
            food.initFood(snakes);
            root.getChildren().add(food.getFood());

        //endregion

        //region Game-Loop
          new AnimationTimer() {
            @Override
            public void handle(long now) {

                long lastUpdate = 0;
                if (now - lastUpdate >= 28_000_0000) {

                    //clearing the canvas , removing both snakes from screen
                    for(Scale scale : snake.getScales()){
                        root.getChildren().remove(scale.getScale());
                    }
                    for(Scale scale : snake2.getScales()){
                        root.getChildren().remove(scale.getScale());
                    }

                  //Do not update coordinates
                    if (snake.isAlive()){
                        gc.clearRect(0 , 0 , window_width , window_height);

                        try {
                            snake.updateSnake();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        snake.hitsOtherSnake(snake2);
                        food.handleFoodCollision(snakes);
                    }


                    //Now redrawing stuff both snakes to the screen
                    //User-Snake
                    for(Scale scale : snake.getScales()){
                        scale.initScale(); //initialize the scale object , this is necessary because of the internal Implementation of Scale Object
                        root.getChildren().add(scale.getScale()); //Adding to the Content-Pane(Group) , to Group and not vbox because vbox doesnt allow
                        // location override of elements , on the other hand Group does
                    }
                    //Snake-2
                    for(Scale scale : snake2.getScales()){
                        scale.initScale(); //initialize the scale object , this is necessary because of the internal Implementation of Scale Object
                        root.getChildren().add(scale.getScale()); //Adding to the Content-Pane(Group) , to Group and not vbox because vbox doesnt allow
                        // location override of elements , on the other hand Group does
                    }





                    //If user snake is not alive
                    if (!snake.isAlive()){
                        this.stop();
                        JOptionPane.showMessageDialog(null , "Game Over Dude! \n You Have Score :"+snake.getScore() + " Points");
                    }


                    //Pausing the thread here , although we have to change this becuase this is not the proper way to do it , also its causing problems with the main thread
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lastUpdate = now;

                }
            }

        }.start();
        //endregion


        primaryStage.show(); //Showing up the application
        }



    //Main method , entry point for Java CODE
    public static void main(String[] args) throws Exception {launch(args);}

    //region Supporting methods for ALpha
    //other supporting functions for this class
    //function to init all the vars
    private void initComponents() {
        menubar  = new MenuBar();

        //Main menu Options
        file = new Menu("File");
        view = new Menu("View");
        Help = new Menu("Help");

        //Sub menu items inside those menus
        About = new MenuItem("About Developer");
        exit  = new MenuItem("Exit");
        Help.getItems().addAll(About , exit);
        menubar.getMenus().addAll(file , view  , Help);
    }
    //endregion
}
