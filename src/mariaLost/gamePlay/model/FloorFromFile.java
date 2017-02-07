package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Item;
import mariaLost.items.model.AbstractItem;
import mariaLost.parameters.Parameters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by crede on 06/02/2017.
 */
public class FloorFromFile extends AbstractFloor {

    private AbstractItem[][] items = null;
    private Dimension2D dimension;

    public FloorFromFile(String fileName) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
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
        dimension = new Dimension2D(largeur * Parameters.CASE_WIDTH, hauteur * Parameters.CASE_HEIGHT);

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
    }

    private static AbstractItem intToItem(int codeItem, int x, int y) throws IllegalArgumentException {
        switch (codeItem) {
            case 0:
                return new Ground(x * Parameters.CASE_WIDTH, y * Parameters.CASE_HEIGHT);
            case 1:
                return new Wall(x * Parameters.CASE_WIDTH, y * Parameters.CASE_HEIGHT);
            default:
                throw new IllegalArgumentException("Pas de correspondence pour codeItem");
        }
    }

    @Override
    public Collection<? extends Item> getItemFromSquare(Rectangle2D square) {
        int largeurMin = Math.max(0, (int) square.getMinX()) / Parameters.CASE_WIDTH;
        int largeurMax = Math.min((int) dimension.getWidth(), (int) square.getMaxX()) / Parameters.CASE_WIDTH;

        int hauteurMin = Math.max(0, (int) square.getMinY()) / Parameters.CASE_HEIGHT;
        int hauteurMax = Math.min((int) dimension.getHeight(), (int) square.getMaxY()) / Parameters.CASE_WIDTH;

        LinkedList<Item> linkedList = new LinkedList<>();

        for (int i = hauteurMin; i < hauteurMax; i++) {
            for (int j = largeurMin; j < largeurMax; j++) {
                linkedList.add(items[i][j]);
            }
        }

        return linkedList;
    }

    @Override
    public Dimension2D getDimension() {
        return dimension;
    }

    @Override
    public Collection<Collection<? extends Drawable>> getDrawableFromSquare(Rectangle2D square) {
        LinkedList<Collection<? extends Drawable>> linkedList = new LinkedList<>();
        linkedList.add((Collection<? extends Drawable>) getItemFromSquare(square));
        return linkedList;
    }
}
