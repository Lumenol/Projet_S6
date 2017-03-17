package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import mariaLost.items.model.*;
import mariaLost.parameters.Parameters_MariaLost;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Created by crede on 06/02/2017.
 */
public class FloorFromFile extends AbstractFloor {


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

        int width, height;

        try {
            width = Integer.parseInt(stringTokenizer.nextToken());
            height = Integer.parseInt(stringTokenizer.nextToken());
        } catch (Exception e) {
            throw new Exception("Erreur ligne 1");
        }
        items = new AbstractItem[height][width];
        dimension = new Dimension2D(width * Parameters_MariaLost.CASE_WIDTH, height * Parameters_MariaLost.CASE_HEIGHT);

        for (int i = 0; i < height; i++) {
            lineRead = br.readLine();

            if (null == lineRead) {
                throw new Exception("Erreur il manque des lignes");
            }

            stringTokenizer = new StringTokenizer(lineRead, "|");

            if (stringTokenizer.countTokens() != width) {
                throw new Exception("Erreur ligne " + (i + 2));
            }

            for (int j = 0; j < width; j++) {
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


}
