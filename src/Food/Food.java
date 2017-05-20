package Food;

import Snake.Snake;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by Aamir on 5/20/2017.
 */
public class Food {



    //region VARS
     private Rectangle food;
     private  int food_loc_x;
     private  int food_loc_y;
     private  int w_l = 16; //Food Size
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
    public void initFood(Snake[] snakes){
        Cords cords =  getLocationForFood(snakes , window_width , window_height);
        this.food_loc_x = cords.getX();
        this.food_loc_y = cords.getY();
        food.setLayoutX(this.food_loc_x);
        food.setLayoutY(this.food_loc_y);
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

    //This will check if the any part of user snake has hi
    public void handleFoodCollision(Snake[] snakes){

        /*
        * At most two snakes will past into this function
        * The first one will Be User snake
        * And the second one will be Computer controlled snake
        * if any of them hits the food
        * Score card of that snake will be incremented by 1
        * And new Random Location for snake will be choosen
        * */
        Point2D food_vector = new Point2D.Double(food_loc_x , food_loc_y);
        for(Snake snake : snakes){
            for(scale.Scale scale : snake.getScales()){

                //Calculating Euclidean Distance (Pythagoras theorem)
                Point2D scale_vector = new Point2D.Double(scale.getLoc_x() , scale.getLoc_y());
                int dist = (int) food_vector.distance(scale_vector);
                System.out.println(dist);


                if (dist < 17){

                    System.out.println("Inside food collision detection");
                    //This means that the food is being hit
                    snake.setScore(snake.getScore()+1); //update the score of relevant snake
                    //update the location of food
                    initFood(snakes);//display food at new location
                    break;
                }
            }
        }

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
