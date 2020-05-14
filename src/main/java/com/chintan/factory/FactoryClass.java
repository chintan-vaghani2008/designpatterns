package com.chintan.factory;

/**
 * Handle instance creation logic in separate class, rather than
 * having it under same class.
 */

public class FactoryClass {

    public static void main(String arg[]) {
        Coordinates cartesianPoint = CoordinateFactory.cartesianCoordinates(10,20);
        Coordinates polarPoint = CoordinateFactory.polarCoordinates(10,20);
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

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class CoordinateFactory {
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
    }
}
