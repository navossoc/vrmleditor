package shape;

import java.util.Comparator;

public class DistanceSorter {

    private static Comparator<Shape> natural;
    private static Comparator<Shape> reverse;
    private float distance;
    private Shape shape;

    public DistanceSorter(Shape shape) {
        this.shape = shape;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float value) {
        this.distance = value;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public static Comparator<Shape> getNaturalComparator() {
        if (natural == null) {
            natural = new NaturalSorter();
        }
        return natural;
    }

    public static Comparator<Shape> getReverseComparator() {
        if (reverse == null) {
            reverse = new ReverseSorter();
        }
        return reverse;
    }

    static class NaturalSorter implements Comparator<Shape> {

        @Override
        public int compare(Shape ss1, Shape ss2) {
            if (ss1.getSorter().distance > ss2.getSorter().distance) {
                return 1;
            } else if (ss1.getSorter().distance == ss2.getSorter().distance) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static class ReverseSorter implements Comparator<Shape> {

        @Override
        public int compare(Shape ss1, Shape ss2) {
            if (ss1.getSorter().distance > ss2.getSorter().distance) {
                return -1;
            } else if (ss1.getSorter().distance == ss2.getSorter().distance) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
