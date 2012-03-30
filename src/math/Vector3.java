package math;

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

    public Vector3 mul(Matrix3 matrix) {
        float vx, vy, vz;
        float[] m = matrix.getValues();

        vx = x * m[0] + y * m[3] + z * m[6];
        vy = x * m[1] + y * m[4] + z * m[7];
        vz = x * m[2] + y * m[5] + z * m[8];

        return new Vector3(vx, vy, vz);
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