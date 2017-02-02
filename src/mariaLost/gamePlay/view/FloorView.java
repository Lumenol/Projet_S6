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

    private Canvas canvas;
    private int lengthX;
    private int lengthY;
    private ArrayList<Item> floorList;
    public FloorView(GroundFloor groundFloor){
        this.lengthX = groundFloor.getLengthX();
        this.lengthY = groundFloor.getLengthY();
        floorList = new ArrayList<>();
        readGroundFloor(groundFloor);
    }

    public void readGroundFloor(GroundFloor groundFloor){

        int x = 0;
        int y= 0;
        int pieceSize=groundFloor.getItemFromFloor(0,0).getSize();
        this.canvas=new Canvas(groundFloor.getLengthX(),groundFloor.getLengthY());
        this.canvas.setTranslateX(0);
        this.canvas.setTranslateY(0);
        canvas.setHeight(groundFloor.getLengthX()*50);
        canvas.setWidth(groundFloor.getLengthX()*50);

        System.out.println(groundFloor.getNbCaseX());
        for(int i=0; i<groundFloor.getNbCaseX(); i++){
            for(int j=0; j<groundFloor.getNbCaseY(); j++){
                Item item= groundFloor.getItemFromFloor(i,j);
                this.floorList.add(item);

          /*      GraphicsContext gc=canvas.getGraphicsContext2D();
                System.out.println("item : "+ item.getSpriteName());
                Image im= new Image("file:resources/Images/"+item.getSpriteName());
                gc.drawImage(im,x,y);
*/
                x+=pieceSize;
            }
            x=0;
            y+=pieceSize;
        }
    }
    public void readGroundFloor2(){

    }

    public Canvas getCanvas() {
        return canvas;
    }

}
