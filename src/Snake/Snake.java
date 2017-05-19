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
    //endregion

    //region constructor
    public  Snake(Color color , int window_width , int window_height ,int initial_len, boolean AI_CONTROL){
        this.scales =  new ArrayList<>();
        this.isAlive = true;
        this.initial_len = initial_len;
        this.color = color;
        this.snake_direction = DIRECTION.RIGHT; //Initially the snake will be moving towards  right
        this.artificial_inteligence = AI_CONTROL;

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
                   new_scale.setLoc_x(last_node.getLoc_x()-this.radius+30); //Adjusting X position
                   new_scale.setLoc_y(h / 2);
                   //Finally add it to the list
                   this.scales.add(new_scale);
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
    //endregion

}
