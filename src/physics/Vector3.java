package physics;

public class Vector3 {

    private float x, y, z;

    public Vector3() {
        this(0, 0, 0);
    }

    public Vector3(float x, float y) {
        this(x, y, 0);
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;

        return this;
    }

    public Vector3 sub(Vector3 vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;

        return this;
    }

    public Vector3 mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public Vector3 div(float scalar) {
        scalar = 1 / scalar;
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}