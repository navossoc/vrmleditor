package gui.format;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class FormatBin {

    public static boolean open(String filename, List<Shape> shapes) {

        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            // header
            byte[] header = new byte[4];
            dataInputStream.read(header, 0, 4);
            if (header.toString().equals("VRML")) {
                throw new Exception("Invalid File Format!");
            }
            // number of objects
            int objects = dataInputStream.readInt();

            for (int i = 0; i < objects; i++) {

                // type
                int len = dataInputStream.readShort() + 1;
                byte[] type = new byte[len];
                dataInputStream.read(type, 0, len);
                String clazz = new String(type).trim();

                // color
                Color color = new Color();
                color.r = dataInputStream.readFloat();
                color.g = dataInputStream.readFloat();
                color.b = dataInputStream.readFloat();
                color.a = dataInputStream.readFloat();

                // scale
                Vector3 scale = new Vector3();
                scale.x = dataInputStream.readFloat();
                scale.y = dataInputStream.readFloat();
                scale.z = dataInputStream.readFloat();

                // rotation
                Quaternion rotation = new Quaternion();
                rotation.w = dataInputStream.readFloat();
                rotation.x = dataInputStream.readFloat();
                rotation.y = dataInputStream.readFloat();
                rotation.z = dataInputStream.readFloat();

                // translation
                Vector3 translation = new Vector3();
                translation.x = dataInputStream.readFloat();
                translation.y = dataInputStream.readFloat();
                translation.z = dataInputStream.readFloat();

                // instance
                Shape shape = null;
                if (clazz.equals("Box")) {
                    float width = dataInputStream.readFloat();
                    float height = dataInputStream.readFloat();
                    float depth = dataInputStream.readFloat();
                    shape = new Box(width, height, depth);
                } else if (clazz.equals("Cone")) {
                    float radius = dataInputStream.readFloat();
                    float height = dataInputStream.readFloat();
                    shape = new Cone(radius, height);
                } else if (clazz.equals("Cylinder")) {
                    float radius = dataInputStream.readFloat();
                    float height = dataInputStream.readFloat();
                    shape = new Cylinder(radius, height);
                } else if (clazz.equals("Sphere")) {
                    float radius = dataInputStream.readFloat();
                    shape = new Sphere(radius);
                } else {
                    throw new Exception("Invalid Shape!");
                }

                // attributes
                shape.setColor(color);
                shape.setScale(scale);
                shape.setRotation(rotation);
                shape.setTranslation(translation);

                // add to list
                shapes.add(shape);
            }

            dataInputStream.close();
            fileInputStream.close();

            return true;

        } catch (Exception ex) {
            return false;
        }

    }

    public static boolean save(String filename, Object[] arrayShapes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            // header
            dataOutputStream.writeBytes("VRML");
            dataOutputStream.writeInt(arrayShapes.length);

            for (Object obj : arrayShapes) {
                Shape shape = (Shape) obj;
                shape.writeBinary(dataOutputStream);
            }

            dataOutputStream.close();
            fileOutputStream.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
