package history;

import shape.Shape;

public class HistoryInfo {

    protected Shape shape;
    protected Type type;

    public enum Type {

        ADD, EDIT, DELETE
    }

    public HistoryInfo(Shape shape, Type type) {
        this.shape = shape.copy();
        this.type = type;
    }
}