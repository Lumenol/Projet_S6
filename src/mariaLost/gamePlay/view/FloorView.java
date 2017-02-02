package mariaLost.gamePlay.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mariaLost.gamePlay.model.GroundFloor;
import mariaLost.items.model.Item;

import java.util.ArrayList;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class FloorView {


    private ArrayList<Item> floorList;
    public FloorView(GroundFloor groundFloor){
        floorList = new ArrayList<>();
        readGroundFloor(groundFloor);
    }

    public void readGroundFloor(GroundFloor groundFloor){

        int x = 0;
        int y= 0;
        int pieceSize=groundFloor.getItemFromFloor(0,0).getSize();

        System.out.println(groundFloor.getNbCaseX());
        for(int i=0; i<groundFloor.getNbCaseX(); i++){
            for(int j=0; j<groundFloor.getNbCaseY(); j++){
                Item item= groundFloor.getItemFromFloor(i,j);
                this.floorList.add(item);
                x+=pieceSize;
            }
            x=0;
            y+=pieceSize;
        }
    }

}
