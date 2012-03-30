package math;

public class Vector2 {

    private float x, y;

    public Vector2() {
        this(0, 0);
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 vec) {
        this.x += vec.x;
        this.y += vec.y;

        return this;
    }

    public Vector2 sub(Vector2 vec) {
        this.x -= vec.x;
        this.y -= vec.y;

        return this;
    }

    public Vector2 mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;

        return this;
    }

    public Vector2 div(float scalar) {
        scalar = 1 / scalar;
        this.x *= scalar;
        this.y *= scalar;

        return this;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
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
}
