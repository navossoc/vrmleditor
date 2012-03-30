package transform;

public class Translation extends Transform {

    public Translation() {
        this(0, 0, 0);
    }

    public Translation(float x, float y) {
        this(x, y, 0);
    }

    public Translation(float x, float y, float z) {
        super(x, y, z);
    }
}
