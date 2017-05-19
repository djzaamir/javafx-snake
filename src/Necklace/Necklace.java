package Necklace;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

/**
 * Created by Aamir on 5/19/2017.
 */
public class Necklace {



    //region VARS
    private ArrayList<Bail> bails;
    private int bail_len = 8;
    //endregion



    public Necklace(){
        bails =  new ArrayList<>();
        //Calling internal method to setup bails
        this.initBails();
    }



    //region Functional Section

    private void initBails(){
        int change_y = 50;
        for (int i = 0 ; i < bail_len ; i++){

            //Creating and managing new bail for the necklace
           Bail new_bail =  new Bail();

            Circle c = new Circle(change_y , 40 , 40);
              c.setLayoutX(200);
              c.setLayoutY(change_y);

           new_bail.setBail(c);
           change_y += 40;



            //Add it to the list
           bails.add(new_bail);
        }
    }

    public void updateBails(){
        for (Bail bail:
             bails) {
            //And now for each bail , call internal update method for bail
            bail.updateBail();

        }
    }
    //endregion



    //Auto generated code
    public ArrayList<Bail> getBails() {
        return bails;
    }

}
