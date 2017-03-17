package mariaLost.gamePlay.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.model.AbstractItem;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 28/01/2017.
 */
public class MoteurPhysique {

    /**
     * Deplace les objet de items en prenant en compte les collisions
     *
     * @param items Items du monde
     */
    public static void move(Collection<? extends AbstractItem> items) {

        for (Iterator<AbstractItem> iterator = (Iterator<AbstractItem>) items.iterator(); iterator.hasNext(); ) {
            AbstractItem next = iterator.next();
            move(next, items);
        }
    }

    private static void move(AbstractItem mobileItem, Collection<? extends AbstractItem> items) {
        Point2D vitesse = mobileItem.getSpeed();
        double vx = vitesse.getX();
        double vy = vitesse.getY();
        if (vx != 0 || vy != 0) {
            if (!mobileItem.isOnDestination()) {
                move(mobileItem, items, vx, vy);
            } else {
                mobileItem.setDestination(null);
            }
        }
    }

    private static boolean move(AbstractItem mobileItem, Collection<? extends AbstractItem> items, double vx, double vy) {
        Rectangle2D rect = null;
        Point2D point2D = mobileItem.getPosition();
        Rectangle2D bounds = mobileItem.getBounds();

        //défini la zone dans laquel mobileItem peux entre en collision avec d'autre objet
        if (vx >= 0 && vy >= 0) { //Vers le bas à droite
            rect = new Rectangle2D(point2D.getX(), point2D.getY(), bounds.getWidth() + vx, vy + bounds.getHeight());
        } else if (vx >= 0 && vy <= 0) { //Vers le haut à droite
            rect = new Rectangle2D(point2D.getX(), point2D.getY() + vy, bounds.getWidth() + vx, -vy + bounds.getHeight());
        } else if (vx <= 0 && vy <= 0) {//Vers le haut à gauche
            rect = new Rectangle2D(point2D.getX() + vx, vy + point2D.getY(), bounds.getWidth() - vx, -vy + bounds.getHeight());
        } else {//Vers le bas à gauche
            rect = new Rectangle2D(vx + point2D.getX(), point2D.getY(), bounds.getWidth() - vx, vy + bounds.getHeight());
        }

        LinkedList<AbstractItem> items1 = new LinkedList<>();
        double distanceMin = Math.min(bounds.getHeight(), bounds.getWidth());

        //récuppère les Items de la zone et cherche les dimmentions du plus petit
        for (Iterator<AbstractItem> iterator = (Iterator<AbstractItem>) items.iterator(); iterator.hasNext(); ) {
            AbstractItem next = iterator.next();
            Rectangle2D limite1 = next.getBounds();

            if (mobileItem != next && rect.intersects(limite1)) {
                distanceMin = Math.min(distanceMin, limite1.getHeight());
                distanceMin = Math.min(distanceMin, limite1.getWidth());
                items1.add(next);
            }
        }

        // Si la vitesse est suppérieur aux dimensions de l'objet le plus petit on découpe le déplacement pour ne pas passe a travers l'objet
        if (Math.abs(vx) >= distanceMin || Math.abs(vy) >= distanceMin) {
            //effectue la premiere partie du déplacement puis la deuxieme
            return move(mobileItem, items1, (int) vx / 2, (int) vy / 2) &&
                    move(mobileItem, items1, vx - (int) vx / 2, vy - (int) vy / 2);
        } else {
            //effectue le deplacement
            return refine(mobileItem, items1, vx, vy);
        }
    }

    /**
     * Effectue le déplacement a proprement parler
     *
     * @param mobileItem
     * @param items
     * @param vx
     * @param vy
     * @return vrai si aucun objet n'as été percute
     */
    private static boolean refine(AbstractItem mobileItem, Collection<? extends AbstractItem> items, double vx, double vy) {
        boolean retour = true;

        Rectangle2D collision = Rectangle2D.EMPTY;

        Point2D position = mobileItem.getPosition();
        double xMin = position.getX();
        double yMin = position.getY();
        double xMax = xMin + vx;
        double yMax = yMin + vy;

        if (vx != 0) {
            // while (collision!= null) {

            mobileItem.setPosition(xMax, yMin);
            //essaye de se rendre a la position d'arrivée si il y a une collision alors place l'item avant l'objet percute
            if ((collision = collision(mobileItem, items)) != null) {
                if (vx > 0) {
                    xMax = collision.getMinX() - mobileItem.getBounds().getWidth();
                } else {
                    xMax = collision.getMaxX();
                }
                retour = false;
            }
            // }
        }
        mobileItem.setPosition(xMax, yMax);
        if (vy != 0) {
            //collision = Rectangle2D.EMPTY;
            //while (collision != null) {
            //essaye de se rendre a la position d'arrivée si il y a une collision alors place l'item avant l'objet percute
            if ((collision = collision(mobileItem, items)) != null) {
                if (vy > 0) {
                    yMax = collision.getMinY() - mobileItem.getBounds().getHeight();
                } else {
                    yMax = collision.getMaxY();
                }
                retour = false;
                mobileItem.setPosition(xMax, yMax);
            }
            //}
        }
        return retour;
    }

    /**
     * Vérifie si un objet entre en collision avec d'autre si c'est le cas retourne la zone occupe par l'objet percute si cet objet est un ActionableItem il est active avec item en parramettre
     *
     * @param item  objet deplace
     * @param items objet potentiellement percutable
     * @return zone de l'objet percute ou null sinon
     */
    private static Rectangle2D collision(AbstractItem item, Collection<? extends AbstractItem> items) {
        Rectangle2D limite = item.getBounds();
        for (Iterator<AbstractItem> iterator = (Iterator<AbstractItem>) items.iterator(); iterator.hasNext(); ) {
            AbstractItem next = iterator.next();
            if (limite.intersects(next.getBounds())) {
                next.action(item);
                item.action(next);
                if (!next.isPassable() && !item.isPassable())
                    return next.getBounds();
            }
        }
        return null;
    }


}