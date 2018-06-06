package pantriapp.russellmcdonald.com.pantirmark3.model;

import java.util.ArrayList;

public class PantriList {

    private ArrayList<PantriModel> pantriList = null;


    public PantriList(ArrayList<PantriModel> pantriList) {
        this.pantriList = pantriList;
    }

    public PantriList() {
    }

    public ArrayList<PantriModel> getPantriList() {
        return pantriList;
    }

    public void setPantriList(ArrayList<PantriModel> pantriList) {
        this.pantriList = pantriList;
    }
}
