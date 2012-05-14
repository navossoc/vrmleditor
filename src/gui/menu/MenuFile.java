package gui.menu;

import gui.EditorMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFile {

    public static class ItemNew implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> new");
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
            EditorMain.instance.stopCanvas();
        }
    }
}
