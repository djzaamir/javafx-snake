package scale;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Vector;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Scale {

    //region VARS
      private Circle scale;  //The Scale itself
      private  Color color;
      //These are helping attributes , these will be updated externally
      //And the internal update method of each scale will be updated according to these vars
      private int center_x , center_y , radius;  //Attributes for size and radius
      private int loc_x , loc_y;  //Attributes for location of scale
      public static  int window_width ,  window_height; //Making them static because all the Scales will share it
    //endregion


    //region Constructor
    //Here we are only importing color from external enviroment
     public Scale(Color c){
       this.color = c;
     }
     //endregion


    //region Functional Section
     public void initScale(){
         scale =  new Circle(this.center_x , this.center_y ,this.radius); //Set size and radius
         scale.setLayoutX(this.loc_x); //Set x cordinates
         scale.setLayoutY(this.loc_y); //Set y cordinates
         scale.setFill(this.color);    //Set fillColor
     }

     //function to perform internal update
    public void updateScale(){

        //Update location cordinates
        this.scale.setScaleX(this.loc_x);
        this.scale.setScaleY(this.loc_y);
        //We are not going to update the size of scale on runtime
        //Update color property
        this.scale.setFill(this.color);
    }


    //function to check if the head node is hitting any WALL , and take some decision accordingly
    public void isScaleHittingAnyWall(){

        int offet_from_border = 18;

        if (this.loc_x >= this.window_width ){ // Exceeding the right SIDE
           this.loc_x  = 0;

        }else if (this.loc_x <= -5){
            this.loc_x = this.window_width;

        }else if(this.loc_y >= this.window_height - offet_from_border){
            this.loc_y = offet_from_border;

        }else if(this.loc_y <= offet_from_border){
            this.loc_y = this.window_height;
        }
    }

    //endregion


    //Getter and setters
    //region Auto generated code
    public void setScale(Circle scale) {
        this.scale = scale;
    }

    public Circle getScale() {
        return scale;
    }

    public void setCenter_x(int center_x) {
        this.center_x = center_x;
    }

    public void setCenter_y(int center_y) {
        this.center_y = center_y;
    }

    public void setLoc_x(int loc_x) {
        this.loc_x = loc_x;
    }

    public void setLoc_y(int loc_y) {
        this.loc_y = loc_y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCenter_x() {
        return center_x;
    }

    public int getCenter_y() {
        return center_y;
    }

    public int getLoc_x() {
        return loc_x;
    }

    public int getLoc_y() {
        return loc_y;
    }

    public int getRadius() {
        return radius;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    //endregion
}
