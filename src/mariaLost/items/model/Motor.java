package mariaLost.items.model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by elsacollet on 02/02/2017.
 */
public class Motor {

    public static void mooving(Iterable<Item> objets, Iterable<Item> mobile) {
        LinkedList<Item> total = new LinkedList<>();
        for (Iterator<Item> iterator = objets.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            total.add(next);
        }
        for (Iterator<Item> iterator = mobile.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            total.add(next);
        }

        for (Iterator<Item> iterator = mobile.iterator(); iterator.hasNext(); ) {
            LittlePlayer next = (LittlePlayer) iterator.next();
            deplace(next, total);
        }
    }

    private static void deplace(LittlePlayer mobile, Iterable<Item> items) {
        Point2D speed = mobile.getSpeed();
        double vx = speed.getX();
        double vy = speed.getY();
        if (vx != 0 || vy != 0) {
            deplace(mobile, items, vx, vy);
        }
    }

    private static void deplace(LittlePlayer movableItem, Iterable<Item> items, double vx, double vy) {
        Bounds rect = null;
        Bounds bounds = movableItem.getBoundingBox();

        if (vx >= 0 && vy >= 0) {
            rect = new BoundingBox(bounds.getMinX(), bounds.getMinY(), bounds.getWidth() + vx, vy + bounds.getHeight());
        } else if (vx >= 0 && vy <= 0) {
            rect = new BoundingBox(bounds.getMinX(), bounds.getMinY() + vy, bounds.getWidth() + vx, -vy + bounds.getHeight());
        } else if (vx <= 0 && vy <= 0) {
            rect = new BoundingBox(bounds.getMinX() + vx, vy + bounds.getMinY(), bounds.getWidth() - vx, -vy + bounds.getHeight());
        } else {
            rect = new BoundingBox(vx + bounds.getMinX(), bounds.getMinY(), bounds.getWidth() - vx, vy + bounds.getHeight());
        }

        LinkedList<Item> listItem = new LinkedList<>();
        double minHeight = movableItem.getBoundingBox().getHeight();
        double minWidth = movableItem.getBoundingBox().getWidth();

        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            Bounds bounds1 = next.getBounds();

            if (movableItem != next && rect.intersects(bounds1)) {
                minHeight = Math.min(minHeight, bounds1.getHeight());
                minWidth = Math.min(minWidth, bounds1.getWidth());
                listItem.add(next);
            }
        }

        if (Math.abs(vx) >= minWidth || Math.abs(vy) >= minHeight) {
            deplace(movableItem, listItem, (int)vx / 2, (int)vy / 2);
            deplace(movableItem, listItem, vx-(int)vx / 2, vy-(int)vy / 2);
        } else {
            affine(movableItem, listItem, (int)vx, (int)vy);
        }
    }

    private static void affine(LittlePlayer movableItem, LinkedList<Item> listItem, int vx, int vy) {
        System.err.println("vx= "+vx+"\tvy= "+vy);
        Point2D position = movableItem.getPosition();
        double xMin = position.getX();
        double yMin = position.getY();
        double xMax = xMin + vx;
        double yMax = yMin + vy;


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

    private static boolean collision(Item item, Iterable<Item> listItem) {
        Bounds bounds = item.getBounds();
        for (Iterator<Item> iterator = listItem.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            if (!next.isPassable() && bounds.intersects(next.getBounds())) {
                return true;
            }
        }
        return false;
    }

}
