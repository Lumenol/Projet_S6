package mariaLost.gamePlay.model;

import com.sun.tools.javac.util.Pair;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.model.*;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.*;
import java.util.function.DoubleToIntFunction;

/**
 * Created by elsacollet on 23/02/2017.
 */
public class GeneratedFloor extends AbstractFloor{
    //Nombre de case
    //n est la largeur indexée par i
    int n;
    //m est la hauteur indéxée par j
    int m;
    Case[][] laby;
    Boolean[][] possible;
    private AbstractItem[][] items = null;
    private Rectangle2D beginning = null;
    private Dimension2D dimension;
    private Rectangle2D end = null;
    private Collection<AbstractItem> gettingItemList = new LinkedList<>();

    public GeneratedFloor(int hauteur, int largeur) throws Exception{
        dimension = new Dimension2D(largeur * Parameters_MariaLost.CASE_WIDTH, hauteur * Parameters_MariaLost.CASE_HEIGHT);

        this.n=largeur;
        this.m = hauteur;
        this.laby = new Case[n][m];
        possible = new Boolean[m][n];
        items = new AbstractItem[hauteur][largeur];

        for(int j = 0; j<m; j++){
            for( int i=0; i<n; i++){
                laby[j][i]= new Case(1, false);
                possible[j][i]=true;
                items[j][i] = new Wall(i*Parameters_MariaLost.CASE_WIDTH, j*Parameters_MariaLost.CASE_HEIGHT);
            }
        }
        for(int i=0; i<n; i++){
            possible[0][i]=false;
            possible[m-1][i]=false;
        }
        for(int j=0; j<m; j++){
            possible[j][0]=false;
            possible[j][n-1]=false;
        }



        Random rand = new Random();
        int i= rand.nextInt(largeur-2)+1;
        int j = rand.nextInt(hauteur-2)+1;
        System.out.println("top depart = "+j +","+i);
        Ground begin = new Ground(j*Parameters_MariaLost.CASE_HEIGHT, i*Parameters_MariaLost.CASE_WIDTH);
        this.beginning = begin.getBounds();
        items[j][i]=begin;
        createLaby(j, i);
        if (beginning == null) {
            throw new Exception("Il n'y as pas de départ");
        }
        if (end == null) {

            Random random = new Random();
            i= random.nextInt(largeur-2)+1;
            j = random.nextInt(hauteur-2)+1;
            EndCase endCase = new EndCase(i*Parameters_MariaLost.CASE_WIDTH, j*Parameters_MariaLost.CASE_HEIGHT);
            end = endCase.getBounds();
            items[j][i]=endCase;
            System.out.println("pas de fin alors = "+j +","+i);
          //  throw new Exception("Il n'y as pas d'arrive");
        }
    }
    public boolean finish(){
        boolean finish=true;
        for(int j=0; j<m; j++){
            for(int i=0; i<n; i++){
                if(possible[j][i]){
                    return false;
                }
            }
        }
        return finish;
    }
//Bon pour la gestion. Isoler case depart,
    public void gererPossible(){

        for(int j=1;j<m-1; j++ ) {
            for (int i = 1; i < n - 1; i++) {
                    System.out.println("("+j +", "+ i+")");
                if (laby[j - 1][i].center==0 && laby[j][i].center==0 && laby[j][i - 1].center==0) {
                    System.out.println("unpossible up left");

                    possible[j - 1][i - 1] = false;
                }
                if (laby[j - 1][i].center==0 && laby[j][i].center==0 && laby[j][i + 1].center==0) {
                    System.out.println("unpossible up right");

                    possible[j - 1][i + 1] = false;
                }
                if (laby[j][i - 1].center==0 && laby[j][i].center==0 && laby[j + 1][i].center==0) {
                    System.out.println("unpossible down left");

                    possible[j + 1][i - 1] = false;
                }
                if (laby[j][i + 1].center==0 && laby[j][i].center==0 && laby[j + 1][i].center==0) {
                    System.out.println("unpossible down right");

                    possible[j + 1][i + 1] = false;
                }
            }
        }
        System.out.println(toString());
    }

    public void createLaby(int j, int i){

        ArrayList<Pair<Integer, Integer>> directionPossible= new ArrayList<>();
        laby[j][i].visite=true;
        if(possible[j][i]) {
            items[j][i] = new Ground(i*Parameters_MariaLost.CASE_WIDTH,j*Parameters_MariaLost.CASE_HEIGHT );
            laby[j][i].center = 0;

            possible[j][i]= false;
            gererPossible();
            if(finish()){
                System.out.println("fin : "+ j +", "+i);
                EndCase end = new EndCase(i*Parameters_MariaLost.CASE_WIDTH, j*Parameters_MariaLost.CASE_HEIGHT);
                this.end = end.getBounds();
                items[j][i]=end;
                return;
            }

            do {
                //Au nord
                if (j - 1 > 0 && laby[j - 1][i].visite == false && possible[j - 1][i]) {
                    directionPossible.add(new Pair<>(j - 1, i));
                }
                //Au Sud
                if (j + 1 < m - 1 && laby[j + 1][i].visite == false && possible[j + 1][i]) {
                    directionPossible.add(new Pair<>(j + 1, i));

                }
                //A l'ouest
                if (i - 1 > 0 && laby[j][i - 1].visite == false && possible[j][i - 1]) {
                    directionPossible.add(new Pair<>(j, i - 1));
                }
                //A l'est
                if (i + 1 < n - 1 && laby[j][i + 1].visite == false && possible[j][i + 1]) {
                    directionPossible.add(new Pair<>(j, i + 1));
                }
                if(directionPossible.size()==0){
                    gettingItemList.add(new Money(i*Parameters_MariaLost.CASE_WIDTH, j*Parameters_MariaLost.CASE_HEIGHT));
                    return;
                }
                if(directionPossible.size()>0){
                    Random rand = new Random();
                    int next= rand.nextInt(directionPossible.size());
                    createLaby(directionPossible.get(next).fst, directionPossible.get(next).snd);
                    directionPossible.remove(next);
                }

            }while(!directionPossible.isEmpty());
        }
        return;
    }

    public void exploreLaby(int j, int i){
        ArrayList<Pair<Integer, Integer>> directionPossible= new ArrayList<>();
        this.laby[j][i].visite=true;
        System.out.println(j+", "+i);

      //  do{
            //Au nord
            if(j-1>0 && laby[j-1][i].visite==false){
                directionPossible.add(new Pair<>(j-1, i));
                System.out.print("Nord");
                exploreLaby(j-1, i);
            }
            //Au Sud
            if(j+1<m && laby[j+1][i].visite==false){
                directionPossible.add(new Pair<>(j+1, i));
                System.out.print("Sud");
                exploreLaby(j+1, i);


            }
            //A l'ouest
            if(i-1>0 && laby[j][i-1].visite==false){
                directionPossible.add(new Pair<>(j, i-1));
                System.out.print("Ouest");
                exploreLaby(j, i-1);

            }
            //Au est
            if(i+1<n && laby[j][i+1].visite==false){
                directionPossible.add(new Pair<>(j, i+1));
                System.out.print("Est");
                exploreLaby(j, i+1);

            }

/*            if(directionPossible.size()==0){
                return;
            }
            if(directionPossible.size()>0){
                Random rand = new Random();
                int next= rand.nextInt(directionPossible.size());
                exploreLaby(directionPossible.get(next).fst, directionPossible.get(next).snd);
                directionPossible.remove(next);
              // System.exit(1);
            }*/

      //  }while(! directionPossible.isEmpty());
        return;
    }

    public String toString(){
        String chaine = "possible\n";

        for(int j = 0; j<m; j++){
            for( int i=0; i<n; i++){
                if(possible[j][i]){
                    chaine +="0";
                }else{
                    chaine +="1";
                }
               //chaine +=laby[i][j].toString();
            }
            chaine +="\n";

        }
        chaine+="Laby \n";

        for(int j = 0; j<m; j++){
            for( int i=0; i<n; i++){
                chaine +=laby[j][i].toString();
            }
            chaine +="\n";

        }
        return chaine;
    }

    @Override
    public Collection<? extends AbstractItem> getItemFromSquare(Rectangle2D square) {
        gettingItemList.removeIf(abstractItem -> abstractItem.isFinished());

        int largeurMin = Math.max(0, (int) square.getMinX()) / Parameters_MariaLost.CASE_WIDTH;
        double largeurMax = Math.min(dimension.getWidth(), square.getMaxX()) / Parameters_MariaLost.CASE_WIDTH;

        int hauteurMin = Math.max(0, (int) square.getMinY()) / Parameters_MariaLost.CASE_HEIGHT;
        double hauteurMax = Math.min(dimension.getHeight(), square.getMaxY()) / Parameters_MariaLost.CASE_WIDTH;

        LinkedList<AbstractItem> linkedList = new LinkedList<>();

        for (int i = hauteurMin; i < hauteurMax; i++) {
            for (int j = largeurMin; j < largeurMax; j++) {
                linkedList.add(items[i][j]);
            }
        }

        gettingItemList.forEach(abstractItem -> {
            if (abstractItem.getBounds().intersects(square)) {
                linkedList.addLast(abstractItem);
            }
        });

        return linkedList;
    }

    @Override
    public Dimension2D getDimension() {
        return dimension;
    }

    @Override
    public Rectangle2D getBeginning() {
        return beginning;
    }

    @Override
    public Rectangle2D getEnd() {
        return end;
    }

    @Override
    public Deque<Collection<? extends Drawable>> getDrawableFromSquare(Rectangle2D square) {
        LinkedList<Collection<? extends Drawable>> linkedList = new LinkedList<>();
        linkedList.addLast(getItemFromSquare(square));
        return linkedList;    }
}
