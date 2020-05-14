package com.chintan.factory;

import java.awt.*;

/**
 * Methods which can help creating instance of class.
 * Advantage is that we can give meaningful name to these methods
 * rather than having constructor which has same name as class.
 * Also, in case of same type of arguments, we can't overload
 * constructor. In that case, we can make constructor private, and
 * expose these factory methods which can call private constructor.
 * In that case, we can have meaningful name to these methods.
 */
public class FactoryMethod {

    public static void main(String arg[]) {
        Coordinates cartesianPoint = Coordinates.cartesianCoordinates(10,20);
        Coordinates polarPoint = Coordinates.polarCoordinates(10,20);
        System.out.println(cartesianPoint);
        System.out.println(polarPoint);
    }

    static class Coordinates {
        double x;
        double y;

        private Coordinates(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Factory method for cartesian coordinate
         */
        public static Coordinates cartesianCoordinates(double x, double y) {
            return new Coordinates(x, y);
        }

        /**
         * Factory method for polar coordinate
         */
        public static Coordinates polarCoordinates(double rho, double theta) {
            return new Coordinates(rho*Math.cos(theta), rho*Math.sin(theta));
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
