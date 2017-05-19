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
           Bail new_bail =  new Bail();
           new_bail.setBail(new Circle(change_y , 40 , 40));
           change_y += 100;
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
