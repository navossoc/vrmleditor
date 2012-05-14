package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEdit {

    public static class Undo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> undo");
        }
    }

    public static class Redo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> redo");
        }
    }

    public static class Delete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> delete");
        }
    }

    public static class Scale implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> scale");
        }
    }

    public static class Rotate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> rotate");
        }
    }

    public static class Translate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> translate");
        }
    }
}
