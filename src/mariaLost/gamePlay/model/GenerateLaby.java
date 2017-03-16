package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import javafx.util.Pair;
import mariaLost.items.model.*;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.*;

/**
 * Created by elsacollet on 16/03/2017.
 */
public class GenerateLaby extends AbstractFloor {

    public int NUMBER_CASE_X = 10;
    public int NUMBER_CASE_Y = 10;
    private int level;
    private boolean possible[][];
    private Pair<Integer, Integer> begin;
    private Pair<Integer, Integer> end;
    private int numberOfMoney;
    private int numberOfEnnemy;

    private HashMap<Pair<Integer, Integer>, Boolean> visited;


    /**
     * Permet d'initialiser
     * @param level permet le calcul de différents paramètres
     */
    public GenerateLaby(int level) {
        this.level = level;
        this.NUMBER_CASE_Y = NUMBER_CASE_Y+level;
        this.NUMBER_CASE_X = NUMBER_CASE_X+level;
        //Calcul hauteur et largeur
        super.dimension = new Dimension2D(
                this.NUMBER_CASE_X * Parameters_MariaLost.CASE_WIDTH
                , this.NUMBER_CASE_Y * Parameters_MariaLost.CASE_HEIGHT);

        super.items = new AbstractItem[NUMBER_CASE_Y][NUMBER_CASE_X];
        this.possible = new boolean[NUMBER_CASE_Y][NUMBER_CASE_X];
        this.visited = new HashMap<>();

        //Initialise visited et items
        for (int j = 0; j < NUMBER_CASE_Y; j++) {
            for (int i = 0; i < NUMBER_CASE_X; i++) {
                super.items[j][i] = new Wall(i * Parameters_MariaLost.CASE_WIDTH, j * Parameters_MariaLost.CASE_HEIGHT);
                visited.put(new Pair<>(j, i), false);
            }
        }
        initialisePossible();

       //Permet de tester qu'il existe bien un chemin de begining à end
        //do {
        createLaby();
        // }while(!isAWay());
    }

    /**
     * Lance les différentes étapes de la création
     */
    private void createLaby() {

        //Point de départ random || Algorithme:
        Random rand = new Random();
        int i = rand.nextInt(NUMBER_CASE_X - 2) + 1;
        int j = rand.nextInt(NUMBER_CASE_Y - 2) + 1;

        generate(j, i);
        lastTest();
        //Determine begining, ending, and items
        initialisePossible();
        determineBegin();
        determineEnd();
        determineMoney();

    }

    /**
     * Pour Tester qu'il existe bien un chemin
     * TODO Y'a un problème ladessus. Il Faut s'en assurer pour que le joueur ne reste pas bloqué dans un "couloir"
     *
     * @return vrai s'il existe un chemin
     */
    private boolean isAWay() {
        Pair<Integer, Integer> player = begin;
        System.out.println("Avancer !");
        avancer(player);
        System.out.println("test");
        if (player == end) {
            System.out.println("C'est bon !");
            return true;
        } else {
            System.out.println("C'est pas bon !");
            return false;
        }
    }

    private void avancer(Pair<Integer, Integer> player) {

        ArrayList<Pair<Integer, Integer>> suiteMovement = suiteMovements(player);
        while (!suiteMovement.isEmpty()) {

            Random rand = new Random();
            int next = rand.nextInt(suiteMovement.size());
            if (player == end) {
                System.out.println("C'est bon !");
                return;
            }
            suiteMovement.remove(next);
            avancer(player);
        }
        //System.out.println("C'est pas bon !");
    }

    /**
     * Recupere les mouvements possible pour le joueur
     *
     * @param player
     * @return la liste des mouvements possibles
     */
    public ArrayList<Pair<Integer, Integer>> suiteMovements(Pair<Integer, Integer> player) {
        ArrayList<Pair<Integer, Integer>> suiteMovement = new ArrayList<>();
        //Au nord
        if (player.getKey() - 1 > 0 && items[player.getKey() - 1][player.getValue()] instanceof Ground) {
            suiteMovement.add(new Pair<>(player.getKey() - 1, player.getValue()));
        }
        //Au Sud
        if (player.getKey() + 1 < NUMBER_CASE_Y - 1 && items[player.getKey() + 1][player.getValue()] instanceof Ground) {
            suiteMovement.add(new Pair<>(player.getKey() + 1, player.getValue()));
        }

        //A l'ouest
        if (player.getValue() - 1 > 0 && items[player.getKey()][player.getValue() - 1] instanceof Ground) {
            suiteMovement.add(new Pair<>(player.getKey(), player.getValue() - 1));
        }
        //A l'est
        if (player.getValue() + 1 < NUMBER_CASE_X - 1 && items[player.getKey()][player.getValue() + 1] instanceof Ground) {
            suiteMovement.add(new Pair<>(player.getKey(), player.getValue() + 1));
        }


        return suiteMovement;
    }


    /**
     * créer un contour d'impossible dans le tableau des possibles et
     * initialise l'interieur à possible
     */
    private void initialisePossible() {
        for (int j = 0; j < NUMBER_CASE_Y; j++) {
            for (int i = 0; i < NUMBER_CASE_X; i++) {
                if (j > 0 && j < NUMBER_CASE_Y - 1 && i > 0 && i < NUMBER_CASE_X - 1)
                    this.possible[j][i] = true;
                else
                    this.possible[j][i] = false;
            }
        }
    }

    /**
     * Calcul à partir du level
     *  - l'argent à disposer
     *  - le nombre d'ennemies
     *  Lance leur placement sur la grille
     */
    private void determineMoney() {
        this.numberOfEnnemy = 0;
        this.numberOfMoney = 0;
        //Calcul de l'argent possible
        for (int y = 0; y < NUMBER_CASE_Y; y++) {
            for (int x = 0; x < NUMBER_CASE_X; x++) {
                if (items[y][x] instanceof Ground && possible[y][x]) {
                    numberOfMoney++;
                    this.possible[y][x] = true;
                } else {
                    this.possible[y][x] = false;
                }
            }
        }

        //A ce niveau on connait le nombre max de money que l'on peut mettre et la table
        //des possibles nous permet de savoir ou.

        numberOfMoney = (int) numberOfMoney * 15 / 100;
        numberOfEnnemy = (int) numberOfMoney * level / 100;

        if (numberOfEnnemy > numberOfMoney) {
            numberOfEnnemy = numberOfMoney;
        }
        //Créer une pièce
        while (numberOfMoney != 0) {
            intToItem(1);
            numberOfMoney--;
        }
        while (numberOfEnnemy != 0) {
            intToItem(2);
            numberOfEnnemy--;
        }
    }

    /**
     * Transforme le code passer en paramètre pour le placer de façon aléatoire sur la grille
     * @param codeItem
     */
    public void intToItem(int codeItem) {
        int y, x;
        do {
            Random random = new Random();
            y = random.nextInt(NUMBER_CASE_Y);
            x = random.nextInt(NUMBER_CASE_X);
        } while (!possible[y][x]);
        if (possible[y][x]) {
            possible[y][x] = false;
            x = x * Parameters_MariaLost.CASE_WIDTH;
            y = y * Parameters_MariaLost.CASE_HEIGHT;
            switch (codeItem) {
                case 1:
                    gettingItemList.add(new Money(x, y, Parameters_MariaLost.QUANTITEE_ARGENT_PIECE));
                    break;
                case 2:
                    gettingItemList.add(new Spider(x, y));
                    break;
                default:
                    throw new IllegalArgumentException("Pas de correspondence pour codeItem");
            }
        }

    }


    /**
     * Prend la premiere case disponible en haut à gauche de la grille.
     * definie super.beginning
     */
    private void determineBegin() {
        for (int y = 0; y < NUMBER_CASE_Y; y++) {
            for (int x = 0; x < NUMBER_CASE_X; x++) {
                if (items[y][x] instanceof Ground) {
                    Ground begin = new Ground(x * Parameters_MariaLost.CASE_WIDTH, y * Parameters_MariaLost.CASE_HEIGHT);
                    super.beginning = begin.getBounds();
                    super.items[y][x] = begin;
                    this.possible[y][x] = false;
                    this.begin = new Pair<>(y, x);
                    return;
                }
            }
        }
    }

    /**
     * Prend la dernière case disponible en bas à droite.
     * definie super.end
     */
    private void determineEnd() {
        for (int y = NUMBER_CASE_Y - 1; y > 0; y--) {
            for (int x = NUMBER_CASE_X - 1; x > 0; x--) {
                if (items[y][x] instanceof Ground) {
                    EndCase end = new EndCase(x * Parameters_MariaLost.CASE_WIDTH, y * Parameters_MariaLost.CASE_HEIGHT);
                    super.end = end.getBounds();
                    super.items[y][x] = end;
                    this.possible[y][x] = false;
                    this.end = new Pair<>(y, x);
                    return;
                }
            }
        }
    }


    /**
     * Méthode utilisée pour la création du tableau des possibles/impossibles.
     * On s'assure que l'on ne peut pas former de carree avec un Ground.
     */
    private void detectUnpossible() {
        for (int j = 1; j < NUMBER_CASE_Y - 1; j++) {
            for (int i = 1; i < NUMBER_CASE_X - 1; i++) {
                if (items[j - 1][i] instanceof Ground
                        && items[j][i] instanceof Ground
                        && items[j][i - 1] instanceof Ground) {
                    possible[j - 1][i - 1] = false;
                    visited.replace(new Pair<>(j - 1, i - 1), true);

                }
                if (items[j - 1][i] instanceof Ground
                        && items[j][i] instanceof Ground
                        && items[j][i + 1] instanceof Ground) {
                    possible[j - 1][i + 1] = false;
                    visited.replace(new Pair<>(j - 1, i + 1), true);
                }
                if (items[j][i - 1] instanceof Ground
                        && items[j][i] instanceof Ground
                        && items[j + 1][i] instanceof Ground) {
                    possible[j + 1][i - 1] = false;
                    visited.replace(new Pair<>(j + 1, i - 1), true);
                }
                if (items[j][i + 1] instanceof Ground
                        && items[j][i] instanceof Ground
                        && items[j + 1][i] instanceof Ground) {
                    possible[j + 1][i + 1] = false;
                    visited.replace(new Pair<>(j + 1, i + 1), true);
                }
            }
        }
    }

    /**
     * Ce teste s'assure qu'il n'existe pas un carre Ground qui serait innacessible.
     */
    private void lastTest() {
        for (int j = 1; j < NUMBER_CASE_Y - 1; j++) {
            for (int i = 1; i < NUMBER_CASE_X - 1; i++) {
                //Un ground to seul entouré de walls se transforme en wall
                if (items[j - 1][i] instanceof Wall
                        && items[j + 1][i] instanceof Wall
                        && items[j][i - 1] instanceof Wall
                        && items[j][i + 1] instanceof Wall
                        && items[j][i] instanceof Ground) {
                    items[j][i] = new Wall(i * Parameters_MariaLost.CASE_WIDTH, j * Parameters_MariaLost.CASE_HEIGHT);
                    possible[j][i] = false;
                    visited.replace(new Pair<>(j, i), true);

                }
            }
        }
    }


    /**
     * Cette méthode créer les items ground à proprement parlé. Pour chaque item on poursuit le chemin en notant les
     * différentes possibilités (N, S, E, O)
     * @param j indice sur la hauteur
     * @param i indice sur la largeur
     */
    private void generate(int j, int i) {
        ArrayList<Pair<Integer, Integer>> possibleWay = new ArrayList<>(4);
        if (possible[j][i]) {
            super.items[j][i] = new Ground(i * Parameters_MariaLost.CASE_WIDTH, j * Parameters_MariaLost.CASE_HEIGHT);
            this.possible[j][i] = false;
        }
        detectUnpossible();
        if (finish()) {
            return;
        }

        //Detection des possibilités
        do {
            //Au nord
            if (j - 1 > 0 && !this.visited.get(new Pair<>(j - 1, i)) && possible[j - 1][i]) {
                possibleWay.add(new Pair<>(j - 1, i));
            }
            //Au Sud

            if (j + 1 < NUMBER_CASE_Y - 1 && !this.visited.get(new Pair<>(j + 1, i)) && possible[j + 1][i]) {
                possibleWay.add(new Pair<>(j + 1, i));

            }
            //A l'ouest
            if (i - 1 > 0 && !this.visited.get(new Pair<>(j, i - 1)) && possible[j][i - 1]) {
                possibleWay.add(new Pair<>(j, i - 1));
            }
            //A l'est
            if (i + 1 < NUMBER_CASE_X - 1 && !this.visited.get(new Pair<>(j, i + 1)) && possible[j][i + 1]) {
                possibleWay.add(new Pair<>(j, i + 1));
            }

            if (possibleWay.size() == 0) {
                gettingItemList.add(new Money(i * Parameters_MariaLost.CASE_WIDTH, j * Parameters_MariaLost.CASE_HEIGHT));
                return;
            } else {
                Random rand = new Random();
                int next = rand.nextInt(possibleWay.size());
                generate(possibleWay.get(next).getKey(), possibleWay.get(next).getValue());
                possibleWay.remove(next);
            }
        } while (!possibleWay.isEmpty() && !finish());

    }


    /**
     * Détermine si toutes les possibilités ont été exploitées
     * @return true si aucune case n'est possible
     */
    private boolean finish() {
        for (int j = 0; j < NUMBER_CASE_Y; j++) {
            for (int i = 0; i < NUMBER_CASE_X; i++) {
                if (possible[j][i])
                    return false;
            }
        }
        return true;
    }


}