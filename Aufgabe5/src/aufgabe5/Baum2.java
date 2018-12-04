package aufgabe5;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Baum2 {

    static double h = Math.random();

    public static class Pkt{
        double x, y;

        public Pkt(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(final String[] args) {
        StdDraw.show(0);
        draw(0.4, 0, 0.1, 0);
        StdDraw.show(0);
    }

    private static void draw(double x, double y,
                             double w, double phi) {
        if(w < 0.002) {
            return;
        }

        double delta = Math.random();
        double s = w * sin(phi);
        double c = w * cos(phi);
        double u = w * cos(delta);
        double v = w * sin(delta);
        Pkt A = new Pkt(x, y);
        Pkt B = new Pkt(A.x + c, A.y + s);
        Pkt C = new Pkt(B.x - s, B.y + c / h);
        Pkt D = new Pkt(A.x - s, A.y + c / h);
        Pkt E = new Pkt(D.x + u * cos(delta + phi),
                D.y + u * sin(delta + phi));

        StdDraw.setPenRadius(0.005);
        /*StdDraw.point(A.x, A.y);
        StdDraw.point(B.x, B.y);
        StdDraw.point(C.x, C.y);
        StdDraw.point(D.x, D.y);
        StdDraw.point(E.x, E.y);*/
        phi += delta;
        draw(D.x, D.y, u, phi);
        draw(E.x, E.y, v, phi - Math.PI / 2);

        if (w < 0.01) {
            StdDraw.setPenColor(StdDraw.GREEN);
        } else{
            StdDraw.setPenColor(StdDraw.BOOK_RED);
        }
        StdDraw.line(A.x, A.y, D.x, D.y);
        StdDraw.line(B.x, B.y, C.x, C.y);
    }
}