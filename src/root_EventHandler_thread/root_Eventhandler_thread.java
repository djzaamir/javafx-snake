package root_EventHandler_thread;

import Menu_Actions.Menu_Actions;
import Snake.Snake;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;

/**
 * Created by Aamir on 5/20/2017.
 */
public class root_Eventhandler_thread  implements  Runnable{


    //region VARS
    private  Scene scene;
    private Snake snake;
    private MenuItem About;
    //endregion


    //region CONSTRUCTOR
    public root_Eventhandler_thread(Snake usr_snake , Scene scene , MenuItem about){
        this.scene = scene;
        this.snake  = usr_snake;
        this.About = about;
    }
    //endregion

    @Override
    public void run() {

        //Event Handler to take input from the keyboard from snake movement
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



        //Event handler for About Menu
        About.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Thread thread = new Thread(new Menu_Actions(Menu_Actions.CALL.ABOUT_DEVELOPER));
                thread.start();
            }
        });


    }//End of overrided run method




}
