package Snake;

import javafx.scene.paint.Color;
import scale.Scale;
import  Food.*;
import java.awt.geom.Point2D;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Snake  implements  Runnable{

    //region VARS
      private ArrayList<Scale> scales;
      private boolean isAlive;
      private  int initial_len;
      private Color color;
      private boolean artificial_inteligence;
      private int center_x , center_y , radius;
      private int timerCountToAddScale = 0; //for ai to automatically add new scale
      private int timerCountToChangeDirection = 0; //for ai to automatically pick random direction for snake
      public enum DIRECTION  {LEFT , RIGHT , UP , DOWN };
      private DIRECTION snake_direction;
      private int movement_offet = 10;
      private int score = 0;
      private  int window_width ,  window_height;
      private Snake user_snake;
      private Food food;
      private int snake_speed = 333;

    //endregion

    //region constructor
    public  Snake(Color color , int window_width , int window_height ,int initial_len, boolean AI_CONTROL){
        this.scales =  new ArrayList<>();
        this.isAlive = true;
        this.initial_len = initial_len;
        this.color = color;
        this.snake_direction = DIRECTION.RIGHT; //Initially the snake will be moving towards  right
        this.artificial_inteligence = AI_CONTROL;
        this.window_width   = window_width;
        this.window_height  = window_width;
        Scale.window_width  = window_width;
        Scale.window_height = window_height;
        //setting up size and radius for each scale
        center_x = 10;
        center_y = 10;
        radius = 10;
    }//endregion


    //region Functional Section

    public void initSnake(int w , int h) {

           for (int i =0 ; i < initial_len ; i++){
               if (i == 0) {//if no nodes present

                   //Adding the head of snake
                   Scale new_scale = new Scale(this.color);
                   //Setting up this scale
                   new_scale.setCenter_x(center_x);
                   new_scale.setCenter_y(center_y);
                   new_scale.setRadius(radius);
                   if (!this.artificial_inteligence){
                       new_scale.setLoc_x(w / 2);
                       new_scale.setLoc_y(h / 2);
                   }else{
                       new_scale.setLoc_x(w / 3);
                       new_scale.setLoc_y(h / 3);
                   }
                   //finally add it to the list
                   this.scales.add(new_scale);

               }else{

                   addTrailingScale();
               }
           }
       }

    public void addTrailingScale() {
        Scale last_node  = this.scales.get(this.scales.size()-1); //getting Last node in the list
        Scale new_scale = new Scale(this.color);

        //Setting up this scale
        new_scale.setCenter_x(center_x);
        new_scale.setCenter_y(center_y);
        new_scale.setRadius(radius);
        new_scale.setLoc_x(last_node.getLoc_x()-this.radius-movement_offet); //Adjusting X position here 30 is the additive offset between nodes
        new_scale.setLoc_y(window_height / 2);
        //Finally add it to the list
        this.scales.add(new_scale);
    }

    public void updateSnake() throws InterruptedException {

           /*Algorithm
           * Update all nodes to the cords of next node
           * Finally update the Head node to new Cords
           * */

           int last_X  = this.scales.get(0).getLoc_x(),  last_Y = this.scales.get(0).getLoc_y(); //This will be used during coordinates swapping process
          //Update the rest of the body to new cords
           for (int i =1 ; i < this.getScales().size() ; i++){


               //Store the current in temp vars , because these need to passed to the next node
               int curr_x = this.scales.get(i).getLoc_x(),  curr_y = this.scales.get(i).getLoc_y();


               //Update the current node with previous cords
               this.scales.get(i).setLoc_x(last_X);
               this.scales.get(i).setLoc_y(last_Y);

               //Store the cordinates of the node being updated
               last_X =  curr_x;
               last_Y =  curr_y;

           }

            //Generate New Head node based on user input , and while doing so do handle self collision
            if (!this.artificial_inteligence){  //Works on user input
                generateNewHeadNode();
            }else{
               //Generates snake direction based on some algorithm
              //generateNewNodeArtificialIntelligence();
              generateNewHeadNode();
            }
        //And seperate function call for artifical intelligence  computer snake


       }

    //This function generates new head node , based on user input
    //Handle self collision
    private void generateNewHeadNode() {
        //Now updating head node at the very END of algorithm
        Scale head = this.scales.get(0); //this is the head node index in array
        Scale to_check =  this.scales.get(0);
        boolean  colliding = false;
        switch (snake_direction){
            case LEFT:
                //Decrement X-axis

                colliding =  isCollidingWithItSelf(to_check.getLoc_x()-this.radius-this.movement_offet, to_check.getLoc_y()); //Performing self collision detection
                this.scales.get(0).setLoc_x(head.getLoc_x()-this.radius-this.movement_offet); //Assign new cords

                //Handling self collision based on results
                ifSnakeCollided(colliding);

                this.scales.get(0).isScaleHittingAnyWall();  //Wall hit handling
                break;
            case UP:
                //Decrement Y-axis


                colliding =  isCollidingWithItSelf(to_check.getLoc_x(), to_check.getLoc_y()-this.radius-this.movement_offet); //Performing self collision detection
                this.scales.get(0).setLoc_y(head.getLoc_y()-this.radius-this.movement_offet);

                //Handling collision based on results
                ifSnakeCollided(colliding);
                this.scales.get(0).isScaleHittingAnyWall();
                break;
            case RIGHT:
                //Increment X-axis

                colliding =  isCollidingWithItSelf(to_check.getLoc_x()+this.radius+this.movement_offet, to_check.getLoc_y()); //Performing self collision detection
                this.scales.get(0).setLoc_x(head.getLoc_x()+this.radius+this.movement_offet);

                //Handling self collision based on results
                ifSnakeCollided(colliding);
                this.scales.get(0).isScaleHittingAnyWall();
                break;
            case DOWN:
                //Increment Y-axis
                colliding =  isCollidingWithItSelf(to_check.getLoc_x(), to_check.getLoc_y()+this.radius+this.movement_offet); //Performing self collision detection
                this.scales.get(0).setLoc_y(head.getLoc_y()+this.radius+this.movement_offet);

                //Handling self collision based on results
                ifSnakeCollided(colliding);
                this.scales.get(0).isScaleHittingAnyWall();
                break;
            default:
                //Dont do anything here
                break;
        }
    }

    //Artificial Intelligence function , to control snake automatically
    private void generateNewNodeArtificialIntelligence(){

    }


    //This function is to change all scales color in case of collision ,
    //However its not working properly
    private void ifSnakeCollided(boolean colliding) {
        if (colliding){
            for (Scale scale : this.scales){
                scale.getScale().setFill(Color.RED);
                scale.initScale();
            }
            this.isAlive = false;
        }
    }

    //Self collision detection function
    public boolean isCollidingWithItSelf(int x , int y){

        int hit_proximity = 16;  //This is distance , after which the snake is collided with itself

        //Now scan all the scales of snake and check for Euclidean distance between them
        //if the new incoming cords are too close with any of the scale , snake dead , collided with scale , set isAlive to false
        Point2D new_headNode_vector =new  Point2D.Double(x , y);
        for(Scale scale : this.scales){
            Point2D scale_vector = new Point2D.Double(scale.getLoc_x() , scale.getLoc_y());
            int distance = (int) new_headNode_vector.distance(scale_vector);
            if (distance < hit_proximity){
               return true;
            }
        }
        //Return false , because no scale was too close
        return false;
    }

    //TO detect collision with other snake
    public boolean hitsOtherSnake(Snake snake2) {

        int hit_proximity = 20;
        /*
        * We will check the head node of this snake(User snake) , that weather or not its hitting any part of other snake (Computer-Snake)
        * */
        Point2D user_headnode_vector = new Point2D.Double(this.scales.get(0).getLoc_x() , this.scales.get(0).getLoc_y());
        for(Scale scale : snake2.getScales()){
            Point2D computer_headnode_vector = new Point2D.Double(scale.getLoc_x() ,scale.getLoc_y());
            int dist = (int)user_headnode_vector.distance(computer_headnode_vector);
            if (dist < hit_proximity){
               this.isAlive = false;
                return true;
            }

        }
      return  false;
    }

    private boolean isCollidingWithFood(int  x, int y){
        int hit_proximity = 20;  //Distance to keep from food
        Point2D food_vector =  new Point2D.Double(x , y);
          //Scanning food
        for(Scale scale : this.scales){
            Point2D scale_vector =  new Point2D.Double(scale.getLoc_x() , scale.getLoc_y());
            int dist = (int) food_vector.distance(scale_vector);
            if (dist < hit_proximity){
                return true;
            }
        }

         return false;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.updateSnake();
                this.hitsOtherSnake(user_snake);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.timerCountToAddScale++;
                if (this.timerCountToAddScale == 15){ //then it means Required time 5 seconds have been elplashed add new scale
                    //Scale adding mechanism
                    this.timerCountToAddScale = 0;
                    this.addTrailingScale();
                }
                //Direction change Timer Mechanism
                this.timerCountToChangeDirection++;
                Thread.sleep(snake_speed); //this is basically used to control the speed of snake
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //endregion


    //region Auto generated code

    public ArrayList<Scale> getScales() {
        return scales;
    }

    public void setScales(ArrayList<Scale> scales) {
        this.scales = scales;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public Snake.DIRECTION getSnake_direction() {
        return snake_direction;
    }

    public void setSnake_direction(Snake.DIRECTION snake_direction) {
        this.snake_direction = snake_direction;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    public Snake getUser_snake() {
        return user_snake;
    }

    public void setUser_snake(Snake user_snake) {
        this.user_snake = user_snake;
    }

    public int getSnake_speed() {
        return snake_speed;
    }

    public void setSnake_speed(int snake_speed) {
        this.snake_speed = snake_speed;
    }
    //endregion

}
