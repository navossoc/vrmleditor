package gui.menu;

import gui.EditorMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class MenuInsert {

    public static class ItemBox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditorMain.instance.addShape(new Box(300, 300, 300));
        }
    }

    public static class ItemCone implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditorMain.instance.addShape(new Cone(150, 300));
        }
    }

    public static class ItemCylinder implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditorMain.instance.addShape(new Cylinder(150, 300));
        }
    }

    public static class ItemSphere implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EditorMain.instance.addShape(new Sphere(150));
        }
    }
}
