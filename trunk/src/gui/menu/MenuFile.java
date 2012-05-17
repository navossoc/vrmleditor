package gui.menu;

import gui.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shape.Shape;

public class MenuFile {

    public static class ItemNew implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.singleton.getHistory().clear();
            Editor.singleton.getListModel().clear();
            Shape.reset();
        }
    }

    public static class ItemOpen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> open");
        }
    }

    public static class ItemSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> save");
        }
    }

    public static class ItemExport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> export");
        }
    }

    public static class ItemExit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.singleton.dispose();
            Editor.singleton.exitEditor();
        }
    }
}
