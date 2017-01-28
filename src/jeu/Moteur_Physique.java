package jeu;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 28/01/2017.
 */
public class Moteur_Physique {
    public static void deplacement(Iterable<Objet> objets, Iterable<Objet_Mobile> mobile) {
        LinkedList<Objet> total = new LinkedList<>();
        for (Iterator<Objet> iterator = objets.iterator(); iterator.hasNext(); ) {
            Objet next = iterator.next();
            total.add(next);
        }
        for (Iterator<Objet_Mobile> iterator = mobile.iterator(); iterator.hasNext(); ) {
            Objet next = iterator.next();
            total.add(next);
        }

        for (Iterator<Objet_Mobile> iterator = mobile.iterator(); iterator.hasNext(); ) {
            Objet_Mobile next = iterator.next();
            deplace(next, total);
        }
    }

    private static void deplace(Objet_Mobile mobile, Iterable<Objet> objets) {
        Point2D vitesse = mobile.getVitesse();
        double vx = vitesse.getX();
        double vy = vitesse.getY();
        if (vx != 0 || vy != 0) {
            deplace(mobile, objets, vx, vy);
        }
    }

    private static void deplace(Objet_Mobile mobile, Iterable<Objet> objets, double vx, double vy) {
        Bounds rect = null;
        Bounds limite = mobile.getLimite();

        if (vx >= 0 && vy >= 0) {
            rect = new BoundingBox(limite.getMinX(), limite.getMinY(), limite.getWidth() + vx, vy + limite.getHeight());
        } else if (vx >= 0 && vy <= 0) {
            rect = new BoundingBox(limite.getMinX(), limite.getMinY() + vy, limite.getWidth() + vx, vy + limite.getHeight());
        } else if (vx <= 0 && vy <= 0) {
            rect = new BoundingBox(limite.getMinX() + vx, vy + limite.getMinY(), limite.getWidth() + vx, vy + limite.getHeight());
        } else {
            rect = new BoundingBox(vx + limite.getMinX(), limite.getMinY(), limite.getWidth() + vx, vy + limite.getHeight());
        }

        LinkedList<Objet> objs = new LinkedList<>();
        double hauteurMin = Double.POSITIVE_INFINITY;
        double largeurMin = Double.POSITIVE_INFINITY;

        for (Iterator<Objet> iterator = objets.iterator(); iterator.hasNext(); ) {
            Objet next = iterator.next();
            Bounds limite1 = next.getLimite();

            if (rect.intersects(limite1)) {
                hauteurMin = Math.min(hauteurMin, limite1.getHeight());
                largeurMin = Math.min(largeurMin, limite1.getWidth());
                objs.add(next);
            }
        }

        if (vx >= largeurMin || vy >= hauteurMin) {
            deplace(mobile, objs, vx / 2, vy / 2);
            deplace(mobile, objs, vx / 2, vy / 2);
        } else {
            affine(mobile, objs, vx, vy);
        }
    }

    private static void affine(Objet_Mobile mobile, LinkedList<Objet> objs, double vx, double vy) {
        Point2D position = mobile.getPosition();
        double xMin = position.getX();
        double yMin = position.getY();
        double xMax = xMin + vx;
        double yMax = yMin + vy;

        while (xMax > xMin) {
            double x = (xMax + xMin) / 2;
            mobile.setPosition(x, yMin);
            if (collision(mobile, objs)) {
                xMax = x;
            } else {
                xMin = x;
            }
        }

        while (yMax > yMin) {
            double y = (yMax + yMin) / 2;
            mobile.setPosition(xMin, y);
            if (collision(mobile, objs)) {
                yMax = y;
            } else {
                yMin = y;
            }
        }
    }

    private static boolean collision(Objet obj, Iterable<Objet> objs) {
        Bounds limite = obj.getLimite();
        for (Iterator<Objet> iterator = objs.iterator(); iterator.hasNext(); ) {
            Objet next = iterator.next();
            if (limite.intersects(next.getLimite())) {
                return true;
            }
        }
        return false;
    }


}
