package Food;

import Snake.Snake;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import java.util.Random;

/**
 * Created by Aamir on 5/20/2017.
 */
public class Food {



    //region VARS
     private Rectangle food;
     private  int food_loc_x;
     private  int food_loc_y;
     private  int w_l = 15;
     private  int window_width ,  window_height;
    //endregion



    //region Constructor
     public Food(int window_width , int window_height , Color color){
         food =  new Rectangle(w_l,w_l); //Width and height set to 10,10 Pixels
         food.setFill(color);
         this.window_width  = window_width;
         this.window_height = window_height;
     }
    //endregion



    //region functional section
    public void updateFood(Snake[] snakes){
        Cords cords =  getLocationForFood(snakes , window_width , window_height);
        food.setLayoutX(cords.getX());
        food.setLayoutY(cords.getY());
    }
    private Cords getLocationForFood(Snake[] snakes ,  int window_width , int window_height){
         Cords cords =  null;
         boolean cords_good = true;

            do{
                cords_good = true; //Assuming these are good cords

                //Generate Random new Cords for and x,y cords
                cords =  new Cords(new Random().nextInt(window_width) , new Random().nextInt(window_height));

                //Now checking these cords in snakes
                //If found in any snake , dont use these cords , instead gen more new cordd

                //Traverse entire snakes array
                for (Snake snake : snakes) {

                    //Now for each snake travers all of its scales , and try to match cords with Scales CORDS
                    for(scale.Scale scale: snake.getScales()){
                        if (scale.getLoc_x() == cords.getX()  && scale.getLoc_y() == cords.getY()){
                            //So it means that one of the point is already taken by some scale
                            cords_good = false;
                            break;
                        }
                    }
                }
            }
            while(!cords_good);

         return cords;
    }

    //endregion



    //region Auto-Generated-Code
    public int getFood_loc_y() {
        return food_loc_y;
    }

    public void setFood_loc_y(int food_loc_y) {
        this.food_loc_y = food_loc_y;
    }

    public int getFood_loc_x() {
        return food_loc_x;
    }

    public void setFood_loc_x(int food_loc_x) {
        this.food_loc_x = food_loc_x;
    }
    public Rectangle getFood() {
        return food;
    }

    public void setFood(Rectangle food) {
        this.food = food;
    }
    //endregion
}
