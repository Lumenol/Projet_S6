package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.interfaces.Floor;
import mariaLost.items.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by elsacollet on 01/02/2017.
 * Permet de lire le fichier de configuration du labyrinthe
 */
public class GroundFloor implements Floor {


    private static String fileName= "resources/Floor/ground_map.txt";
    private static Item[][] tabItem;
    private int lengthX;
    private int lengthY;
    private int nbCaseX;
    private int nbCaseY;
    private ArrayList<Item> floorList;

   /* public GroundFloor(Item [][] tab){
        this.lengthX=tab[0].length*tab[0][0].getSize();
        this.lengthY=tab.length*tab[0][0].getSize();
        this.nbCaseX=tab[0].length;
        this.nbCaseY=tab.length;
        this.tabCase=tab;
        System.out.println("lengthX ="+lengthX +" lengthY = "+ lengthY + "nbCaseX = "+ nbCaseX + "nbCaseY =" + nbCaseY);
    }*/


    /**
     * @author Loic
     * Créer un floor à partir d'un fichier donné en entrée.
     */
    public GroundFloor(){
        floorList = new ArrayList<>();

        try{
            File f = new File(this.fileName);
            FileReader fr=new FileReader(f);
            BufferedReader in= new BufferedReader(fr);
            String line=in.readLine();

            String [] splittedLine=line.split(" ");

            this.lengthX=Integer.parseInt((splittedLine[0]));
            this.lengthY=Integer.parseInt((splittedLine[1]));
            this.nbCaseX=lengthX;
            this.nbCaseY=lengthY;
            System.out.println("nbCaseX "+nbCaseX + " nbCaseY" + nbCaseY);

            this.tabItem = new Item[this.lengthX][this.lengthY];
            int x=0;
            int y=0;
            line=in.readLine();
            while(line!=null){
                splittedLine=line.split("\\|");
                for(String s: splittedLine){
                    int codeItem=Integer.parseInt(s);
//                    tabItem[x][y] = new Item(codeItem, x, y);
                    floorList.add(new Item(codeItem, x*Item.size, y*Item.size));
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

    public Item getItemFromFloor(int i, int j) {
        return tabItem[i][j];
    }


    public int getLengthY() {
        return lengthY;
    }

    public int getLengthX() {
        return lengthX;
    }

    public int getNbCaseX() {
        return nbCaseX;
    }

    public int getNbCaseY() {
        return nbCaseY;
    }

    public ArrayList<Item> getFloorList(){
        return this.floorList;
    }

    @Override
    public Collection<? extends Item> getItemFromSquare(Rectangle2D square) {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return null;
    }




}
