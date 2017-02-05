package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.model.Item;
import mariaLost.items.model.MovableItem;
import mariaLost.mainApp.model.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by elsacollet on 03/02/2017.
 */
public class Floor<T extends mariaLost.items.model.Item> implements mariaLost.gamePlay.interfaces.Floor {
    private String fileName= "resources/Floor/ground_map.txt";

    private MovableItem littlePlayer;

    protected ArrayList<T> itemList;
    private Dimension2D dimensionFloor;

    /**
     * Create floor with mobile elements
     * @param littlePlayername
     */
    public Floor(String littlePlayername){
        this.littlePlayer = new MovableItem(littlePlayername, "player.png", false );

        this.itemList = new ArrayList<>();
        littlePlayer.setPosition(Parameters.CASE_WIDTH*4, Parameters.CASE_HEIGHT *5);
        add(littlePlayer);
    }

    /**
     * Create Floor from a file;
     */
    public Floor(){
        this.itemList = new ArrayList<>();
        readFromFile(this.fileName);
    }


    public ArrayList<T> getItemList() {
        return itemList;
    }


    public Dimension2D getDimensionFloor() {
        return dimensionFloor;
    }


    @Override
    public Collection<? extends Item> getItemsFromSquare(Rectangle2D square) {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return dimensionFloor;
    }

    @Override
    public void add(Object item) {
        itemList.add((T)item);
    }


    @Override
    public void setLengthX(int x) {
        this.dimensionFloor = new Dimension2D(x, dimensionFloor.getWidth());
    }

    @Override
    public void setLengthY(int y) {
        this.dimensionFloor = new Dimension2D(dimensionFloor.getHeight(), y);


    }

    public void readFromFile(String fileName){

        try{
            File f = new File(fileName);
            FileReader fr=new FileReader(f);
            BufferedReader in= new BufferedReader(fr);
            String line=in.readLine();

            String [] cutLine = line.split(" ");

            this.dimensionFloor = new Dimension2D(Double.parseDouble((cutLine[0])),  Double.parseDouble((cutLine[1])));
            System.out.print(dimensionFloor.toString());
            int x=0;
            int y=0;
            line=in.readLine();
            while(line!=null){
                cutLine=line.split("\\|");
                for(String s: cutLine){
                    int codeItem=Integer.parseInt(s);
                    Item floor = new mariaLost.items.model.Item(codeItem, x* Parameters.CASE_WIDTH, y* Parameters.CASE_HEIGHT);
                    add(floor);
                    y++;
                }
                x++;
                y=0;
                line=in.readLine();
            }

            in.close();
            fr.close();
        }catch (Exception e){
            System.out.println("ground floor error : " + e);
        }
    }

    public MovableItem getLittlePlayer() {
        return littlePlayer;
    }
}
