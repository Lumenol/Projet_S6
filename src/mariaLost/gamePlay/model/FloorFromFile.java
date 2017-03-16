package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.model.*;
import mariaLost.parameters.Parameters_MariaLost;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by crede on 06/02/2017.
 */
public class FloorFromFile extends AbstractFloor {

    private AbstractItem[][] items = null;
    private Dimension2D dimension;
    private Rectangle2D beginning = null;
    private Rectangle2D end = null;
    private Collection<AbstractItem> gettingItemList = new LinkedList<>();

    public FloorFromFile(String fileName) throws Exception {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            br = new BufferedReader(new FileReader(Parameters_MariaLost.FILEPATH_DEFAUTL_MAP));
        }
        String lineRead = br.readLine();

        if (null == lineRead) {
            throw new Exception("Le fichier est vide");
        }
        StringTokenizer stringTokenizer = new StringTokenizer(lineRead, " ");
        if (stringTokenizer.countTokens() != 2) {
            throw new Exception("Erreur ligne 1");
        }

        int largeur, hauteur;

        try {
            largeur = Integer.parseInt(stringTokenizer.nextToken());
            hauteur = Integer.parseInt(stringTokenizer.nextToken());
        } catch (Exception e) {
            throw new Exception("Erreur ligne 1");
        }
        items = new AbstractItem[hauteur][largeur];
        dimension = new Dimension2D(largeur * Parameters_MariaLost.CASE_WIDTH, hauteur * Parameters_MariaLost.CASE_HEIGHT);

        for (int i = 0; i < hauteur; i++) {
            lineRead = br.readLine();

            if (null == lineRead) {
                throw new Exception("Erreur il manque des lignes");
            }

            stringTokenizer = new StringTokenizer(lineRead, "|");

            if (stringTokenizer.countTokens() != largeur) {
                throw new Exception("Erreur ligne " + (i + 2));
            }

            for (int j = 0; j < largeur; j++) {
                int codeItem;
                try {
                    codeItem = Integer.parseInt(stringTokenizer.nextToken());
                } catch (Exception e) {
                    throw new Exception("Erreur ligne " + (i + 2));
                }

                items[i][j] = intToItem(codeItem, j, i);
            }
        }
        br.close();

        if (beginning == null) {
            throw new Exception("Il n'y as pas de départ");
        }
        if (end == null) {
            throw new Exception("Il n'y as pas d'arrive");
        }
    }


    private AbstractItem intToItem(int codeItem, int x, int y) throws IllegalArgumentException {
        x = x * Parameters_MariaLost.CASE_WIDTH;
        y = y * Parameters_MariaLost.CASE_HEIGHT;
        switch (codeItem) {
            case 5:
                gettingItemList.add(new Money(x, y, Parameters_MariaLost.QUANTITEE_ARGENT_PIECE));
            case 0:
                return new Ground(x, y);
            case 1:
                return new Wall(x, y);
            case 2:
                Ground beginning = new Ground(x, y);
                this.beginning = beginning.getBounds();
                return beginning;
            case 3:
                EndCase end = new EndCase(x, y);
                this.end = end.getBounds();
                return end;
            case 4:
                return new BreakableWall(x, y);
            case 6:
                gettingItemList.add(new Spider(x, y));
                return new Ground(x, y);
            default:
                throw new IllegalArgumentException("Pas de correspondence pour codeItem");
        }
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
    public Collection<AbstractItem> getItems() {
        return gettingItemList;
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
        return linkedList;
    }
}
