package Snake;

import javafx.scene.paint.Color;
import scale.Scale;

import java.security.SecureClassLoader;
import java.util.ArrayList;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Snake {

    //region VARS
      private ArrayList<Scale> scales;
      private boolean isAlive;
      private  int initial_len;
      private Color color;
      private boolean artificial_inteligence;
      private int center_x , center_y , radius;
      public enum DIRECTION  {LEFT , RIGHT , UP , DOWN };
      private DIRECTION snake_direction;
      private int movement_offet = 10;
      private int score = 0;
      private  int window_width ,  window_height;
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
                   new_scale.setLoc_x(w / 2);
                   new_scale.setLoc_y(h / 2);
                   //finally add it to the list
                   this.scales.add(new_scale);

               }else{

                   Scale last_node  = this.scales.get(this.scales.size()-1); //getting Last node in the list
                   Scale new_scale = new Scale(this.color);

                   //Setting up this scale
                   new_scale.setCenter_x(center_x);
                   new_scale.setCenter_y(center_y);
                   new_scale.setRadius(radius);
                   new_scale.setLoc_x(last_node.getLoc_x()-this.radius-movement_offet); //Adjusting X position here 30 is the additive offset between nodes
                   new_scale.setLoc_y(h / 2);
                   //Finally add it to the list
                   this.scales.add(new_scale);
               }
           }
       }

       public void updateSnake(){

           /*AlGARETH
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


           //Now updating head node at the very END of algorithm
           Scale head = this.scales.get(0); //this is the head node index in array
           switch (snake_direction){
               case LEFT:
                   //Decrement X-axis

                   //head.setLoc_x(head.getLoc_x()-this.radius-this.movement_offet);
                   this.scales.get(0).setLoc_x(head.getLoc_x()-this.radius-this.movement_offet);
                   this.scales.get(0).isScaleHittingAnyWall();
                   break;
               case UP:
                   //Decrement Y-axis

                   this.scales.get(0).setLoc_y(head.getLoc_y()-this.radius-this.movement_offet);
                   this.scales.get(0).isScaleHittingAnyWall();
                   break;
               case RIGHT:
                   //Increment X-axis

                   this.scales.get(0).setLoc_x(head.getLoc_x()+this.radius+this.movement_offet);
                   this.scales.get(0).isScaleHittingAnyWall();
                   break;
               case DOWN:
                   //Increment Y-axis

                   this.scales.get(0).setLoc_y(head.getLoc_y()+this.radius+this.movement_offet);
                   this.scales.get(0).isScaleHittingAnyWall();
                   break;
               default:
                   //Dont do anything here
                   break;
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
    //endregion

}
