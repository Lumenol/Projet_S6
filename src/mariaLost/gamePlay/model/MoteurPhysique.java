package mariaLost.gamePlay.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.ActionableItem;
import mariaLost.items.interfaces.Item;
import mariaLost.items.interfaces.MobileItem;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 28/01/2017.
 */
public class MoteurPhysique {
    public static void move(Collection<? extends Item> items, Collection<? extends MobileItem> mobileItems) {
        LinkedList<Item> allItems = new LinkedList<>();
        allItems.addAll(items);
        allItems.addAll(mobileItems);

        for (Iterator<MobileItem> iterator = (Iterator<MobileItem>) mobileItems.iterator(); iterator.hasNext(); ) {
            MobileItem next = iterator.next();
            move(next, allItems);
        }
    }

    private static void move(MobileItem mobileItem, Collection<? extends Item> items) {
        Point2D vitesse = mobileItem.getSpeed();
        double vx = vitesse.getX();
        double vy = vitesse.getY();
        if (vx != 0 || vy != 0) {
            move(mobileItem, items, vx, vy);
        }
    }

    private static boolean move(MobileItem mobileItem, Collection<? extends Item> items, double vx, double vy) {
        Rectangle2D rect = null;
        Point2D point2D = mobileItem.getPosition();
        Rectangle2D bounds = mobileItem.getBounds();

        if (vx >= 0 && vy >= 0) {
            rect = new Rectangle2D(point2D.getX(), point2D.getY(), bounds.getWidth() + vx, vy + bounds.getHeight());
        } else if (vx >= 0 && vy <= 0) {
            rect = new Rectangle2D(point2D.getX(), point2D.getY() + vy, bounds.getWidth() + vx, -vy + bounds.getHeight());
        } else if (vx <= 0 && vy <= 0) {
            rect = new Rectangle2D(point2D.getX() + vx, vy + point2D.getY(), bounds.getWidth() - vx, -vy + bounds.getHeight());
        } else {
            rect = new Rectangle2D(vx + point2D.getX(), point2D.getY(), bounds.getWidth() - vx, vy + bounds.getHeight());
        }

        LinkedList<Item> items1 = new LinkedList<>();
        double hauteurMin = bounds.getHeight();
        double largeurMin = bounds.getWidth();

        for (Iterator<Item> iterator = (Iterator<Item>) items.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            Rectangle2D limite1 = next.getBounds();

            if (mobileItem != next && rect.intersects(limite1)) {
                hauteurMin = Math.min(hauteurMin, limite1.getHeight());
                largeurMin = Math.min(largeurMin, limite1.getWidth());
                items1.add(next);
            }
        }

        if (Math.abs(vx) >= largeurMin || Math.abs(vy) >= hauteurMin) {
            return move(mobileItem, items1, (int) vx / 2, (int) vy / 2) &&
                    move(mobileItem, items1, vx - (int) vx / 2, vy - (int) vy / 2);
        } else {
            return refine(mobileItem, items1, vx, vy);
        }
    }

    private static boolean refine(MobileItem mobileItem, Collection<? extends Item> items, double vx, double vy) {
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

    private static Rectangle2D collision(Item item, Collection<? extends Item> items) {
        Rectangle2D limite = item.getBounds();
        for (Iterator<Item> iterator = (Iterator<Item>) items.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            if (limite.intersects(next.getBounds())) {
                if (next instanceof ActionableItem) {
                    ((ActionableItem) next).action(item);
                }
                if (!next.isPassable())
                    return next.getBounds();
            }
        }
        return null;
    }


}