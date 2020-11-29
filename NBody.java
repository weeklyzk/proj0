import java.util.*;
public class NBody {
    public static int N;
    public static double R;
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        N = in.readInt();
        R = in.readDouble();
        return R;
    }
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        N = in.readInt();
        R = in.readDouble();
        Body[] bodies = new Body[N];
        for (int i = 0; i < N; i++) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return bodies;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        R = readRadius(filename);
        String imageToDraw = "images/starfield.jpg";
        Body[] bodies = readBodies(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();
        StdDraw.pause(1);
        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for(int i = 0; i < N; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i = 0; i <N; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            for(Body s : bodies) {
                s.draw();
            }
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
