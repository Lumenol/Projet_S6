package mariaLost.gamePlay.model;

/**
 * Created by elsacollet on 23/02/2017.
 */
public class Case {
    boolean visite;
    int center;
    public Case(int center, boolean v){
        this.visite = v;
        this.center = center;
    }

    public String toString(){
        String chaine="";
        chaine +=this.center;
        return chaine;
    }

}
