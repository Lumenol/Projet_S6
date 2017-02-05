package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Item;
import mariaLost.items.interfaces.Movable;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by elsacollet on 02/02/2017.
 */
public class Motor {

    public static void mooving(Iterable<? extends mariaLost.items.interfaces.Item> objets, Iterable<? extends mariaLost.items.interfaces.Movable> mobile) {
        LinkedList<mariaLost.items.interfaces.Item> total = new LinkedList<>();
        for (Iterator<? extends Item> iterator = objets.iterator(); iterator.hasNext(); ) {
            mariaLost.items.interfaces.Item next = iterator.next();
            total.add(next);
        }
        for (Iterator<? extends Movable> iterator = mobile.iterator(); iterator.hasNext(); ) {
            Movable next = iterator.next();
            total.add(next);
        }

        for (Iterator<? extends Movable> iterator = mobile.iterator(); iterator.hasNext(); ) {
            Movable next = (MovableItem) iterator.next();
            deplace(next, total);
        }
    }

    private static void deplace(Movable mobile, Iterable<mariaLost.items.interfaces.Item> items) {
        Point2D speed = mobile.getSpeed();
        double vx = speed.getX();
        double vy = speed.getY();
        if (vx != 0 || vy != 0) {
            deplace(mobile, items, vx, vy);
        }
    }

    private static void deplace(Movable movableItem, Iterable<mariaLost.items.interfaces.Item> items, double vx, double vy) {
        Rectangle2D rect = null;
        Rectangle2D bounds = movableItem.getBounds();

        if (vx >= 0 && vy >= 0) {
            rect = new Rectangle2D(bounds.getMinX(), bounds.getMinY(), bounds.getWidth() + vx, vy + bounds.getHeight());
        } else if (vx >= 0 && vy <= 0) {
            rect = new Rectangle2D(bounds.getMinX(), bounds.getMinY() + vy, bounds.getWidth() + vx, -vy + bounds.getHeight());
        } else if (vx <= 0 && vy <= 0) {
            rect = new Rectangle2D(bounds.getMinX() + vx, vy + bounds.getMinY(), bounds.getWidth() - vx, -vy + bounds.getHeight());
        } else {
            rect = new Rectangle2D(vx + bounds.getMinX(), bounds.getMinY(), bounds.getWidth() - vx, vy + bounds.getHeight());
        }

        LinkedList<mariaLost.items.interfaces.Item> listItem = new LinkedList<>();
        double minHeight = movableItem.getBounds().getHeight();
        double minWidth = movableItem.getBounds().getWidth();

        for (Iterator<mariaLost.items.interfaces.Item> iterator = items.iterator(); iterator.hasNext(); ) {
            mariaLost.items.interfaces.Item next = iterator.next();
            Rectangle2D bounds1 = next.getBounds();

            if (movableItem != next && rect.intersects(bounds1)) {
                minHeight = Math.min(minHeight, bounds1.getHeight());
                minWidth = Math.min(minWidth, bounds1.getWidth());
                listItem.add(next);
            }
        }

        if (Math.abs(vx) >= minWidth || Math.abs(vy) >= minHeight) {
            deplace(movableItem, listItem, (int) vx / 2, (int) vy / 2);
            deplace(movableItem, listItem, vx - (int) vx / 2, vy - (int) vy / 2);
        } else {
            affine(movableItem, listItem, (int) vx, (int) vy);
        }
    }

    private static void affine(Movable movableItem, LinkedList<mariaLost.items.interfaces.Item> listItem, int vx, int vy) {
        System.err.println("vx= " + vx + "\tvy= " + vy);
        Point2D position = movableItem.getPosition();
        double xMin = position.getX();
        double yMin = position.getY();
        double xMax = xMin + vx;
        double yMax = yMin + vy;

        vx = (int) Math.signum(vx);
        vy = (int) Math.signum(vy);

        while (xMax != xMin) {
            double x = xMin;
            movableItem.setPosition(x += vx, yMin);
            if (collision(movableItem, listItem)) {
                movableItem.setPosition(xMin, yMin);
                break;
            } else {
                xMin = x;
            }
        }

        while (yMax != yMin) {
            double y = yMin;
            movableItem.setPosition(xMin, y += vy);
            if (collision(movableItem, listItem)) {
                movableItem.setPosition(xMin, yMin);
                break;
            } else {
                yMin = y;
            }
        }

    }

    private static boolean collision(mariaLost.items.interfaces.Item item, Iterable<mariaLost.items.interfaces.Item> listItem) {
        Rectangle2D bounds = item.getBounds();
        for (Iterator<mariaLost.items.interfaces.Item> iterator = listItem.iterator(); iterator.hasNext(); ) {
            mariaLost.items.interfaces.Item next = iterator.next();
            if (!next.isPassable() && bounds.intersects(next.getBounds())) {
                return true;
            }
        }
        return false;
    }

}
