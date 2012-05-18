package gui.menu;

import gui.Editor;
import history.HistoryInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class MenuInsert {

    private static void addShape(Shape shape) {
        Editor.singleton.addShape(shape);
        Editor.singleton.getHistory().insertUndo(new HistoryInfo(shape, HistoryInfo.Type.ADD));
    }

    public static class ItemBox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addShape(new Box(1.0f, 1.0f, 1.0f));
        }
    }

    public static class ItemCone implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addShape(new Cone(0.5f, 1.0f));
        }
    }

    public static class ItemCylinder implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addShape(new Cylinder(0.5f, 1.0f));
        }
    }

    public static class ItemSphere implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addShape(new Sphere(0.5f));
        }
    }
}
