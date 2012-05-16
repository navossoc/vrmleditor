package gui.menu;

import gui.EditorMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEdit {

    public static class ItemUndo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> undo");
            EditorMain.instance.history.popUndo();
        }
    }

    public static class ItemRedo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> redo");
            EditorMain.instance.history.popRedo();
        }
    }

    public static class ItemDelete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> delete");
        }
    }

    public static class ItemScale implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> scale");
        }
    }

    public static class ItemRotate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> rotate");
        }
    }

    public static class ItemTranslate implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("edit -> translate");
        }
    }
}
