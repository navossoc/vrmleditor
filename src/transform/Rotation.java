package transform;

public class Rotation extends Transform {

    private float angle;

    public Rotation() {
        this(0, 0, 0, 0);
    }

    public Rotation(float x, float y, float angle) {
        this(x, y, 0, angle);
    }

    public Rotation(float x, float y, float z, float angle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.angle = angle;
    }

    @Override
    public void set(float x, float y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void set(float x, float y, float z, float angle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
