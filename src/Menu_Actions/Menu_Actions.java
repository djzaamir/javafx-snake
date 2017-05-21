package Menu_Actions;

import javax.swing.*;

/**
 * Created by Aamir on 5/20/2017.
 */
public class Menu_Actions implements Runnable {

    //region VARS
    public   enum CALL {ABOUT_DEVELOPER};
    private  CALL func_to_call;
    //endregion


    //region CONSTRUCTOR
    public Menu_Actions(CALL func_to_call){
        this.func_to_call = func_to_call;
    }
    //endregion


    @Override
    public void run() {
        switch (func_to_call){
            case ABOUT_DEVELOPER:
                displayAboutDeveloper();
                break;
            default:
                // do nothing
                break;
        }

    }

    //region FUNCTIONAL SECTION
    public void displayAboutDeveloper(){
        JOptionPane.showMessageDialog(null , "Developed By , Muhammad Aamir \n As a project for java in 6th Semester");
    }
    //endregion
}
