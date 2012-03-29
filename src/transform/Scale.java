package transform;

public class Scale extends Transform {

    public Scale() {
        this(0, 0, 0);
    }

    public Scale(float x, float y) {
        this(x, y, 0);
    }

    public Scale(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
