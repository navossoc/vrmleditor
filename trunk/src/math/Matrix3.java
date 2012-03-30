package math;

public class Matrix3 {

    private float[] values;

    public Matrix3() {
        values = new float[9];
    }

    public Matrix3(float[] values) {
        this.values = values;
    }

    public void add(Matrix3 m) {
        values[0] += m.values[0];
        values[1] += m.values[1];
        values[2] += m.values[2];
        values[3] += m.values[3];
        values[4] += m.values[4];
        values[5] += m.values[5];
        values[6] += m.values[6];
        values[7] += m.values[7];
        values[8] += m.values[8];
    }

    public void sub(Matrix3 m) {
        values[0] -= m.values[0];
        values[1] -= m.values[1];
        values[2] -= m.values[2];
        values[3] -= m.values[3];
        values[4] -= m.values[4];
        values[5] -= m.values[5];
        values[6] -= m.values[6];
        values[7] -= m.values[7];
        values[8] -= m.values[8];
    }

    public void mul(Matrix3 m) {
        final float[] temp = new float[9];
        temp[0] = values[0] * m.values[0] + values[1] * m.values[3] + values[2] * m.values[6];
        temp[1] = values[0] * m.values[1] + values[1] * m.values[4] + values[2] * m.values[7];
        temp[2] = values[0] * m.values[2] + values[1] * m.values[5] + values[2] * m.values[8];
        temp[3] = values[3] * m.values[0] + values[4] * m.values[3] + values[5] * m.values[6];
        temp[4] = values[3] * m.values[1] + values[4] * m.values[4] + values[5] * m.values[7];
        temp[5] = values[3] * m.values[2] + values[4] * m.values[5] + values[5] * m.values[8];
        temp[6] = values[6] * m.values[0] + values[7] * m.values[3] + values[8] * m.values[6];
        temp[7] = values[6] * m.values[1] + values[7] * m.values[4] + values[8] * m.values[7];
        temp[8] = values[6] * m.values[2] + values[7] * m.values[5] + values[8] * m.values[8];
        values = temp;
    }

    public void div(Matrix3 m) {
        // TODO: in the future...
    }

    public static Matrix3 identity() {
        return new Matrix3(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
    }

    public float[] getValues() {
        return values;
    }
}
