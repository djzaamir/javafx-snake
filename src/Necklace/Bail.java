package Necklace;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Bail {

    //region Vars
    private Circle bail;
    //endregion


    //change color function for bail

    public void updateBail(){

        //Now internally perform the required functions here
        this.changeColorOnRandom();

    }


    private void changeColorOnRandom(){

         //Changing random color for this bail
        this.bail.setFill(new Color(new Random().nextFloat(),new Random().nextFloat(),new Random().nextFloat(),1));

    }

    //Auto generated code
    public Circle getBail() {
        return bail;
    }

    public void setBail(Circle bail) {
        this.bail = bail;
    }
}
