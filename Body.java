import java.util.*;
public class Body {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body a) {
        return Math.sqrt(Math.pow(this.xxPos - a.xxPos, 2) + Math.pow(this.yyPos - a.yyPos, 2));
    }

    public double calcForceExertedBy(Body c) {
        return G * this.mass * c.mass / Math.pow(this.calcDistance(c), 2);
    }

    public double calcForceExertedByX(Body c) {
        return Math.sqrt(Math.pow(this.xxPos - c.xxPos, 2) / Math.pow(this.calcDistance(c), 2)) * this.calcForceExertedBy(c);
    }

    public double calcForceExertedByY(Body c) {
        return Math.sqrt(Math.pow(this.yyPos - c.yyPos, 2) / Math.pow(this.calcDistance(c), 2)) * this.calcForceExertedBy(c);
    }

    public double calcNetForceExertedByX(Body[] c) {
        double sum = 0;
        for (Body s : c) {
            if(!this.equals(s)) {
                sum += this.calcForceExertedByX(s);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Body[] c) {
        double sum = 0;
        for (Body s : c) {
            if(!this.equals(s)) {
                sum += this.calcForceExertedByY(s);
            }
        }
        return sum;
    }
    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel = this.xxVel + ax * dt;
        this.yyVel = this.yyVel + ay * dt;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        StdDraw.show();
        StdDraw.pause(2000);
    }

}