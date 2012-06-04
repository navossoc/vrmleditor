package gui.menu;

import gui.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEdit {

    public static class ItemUndo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.singleton.getHistory().undo();
        }
    }

    public static class ItemRedo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.singleton.getHistory().redo();
        }
    }

    public static class ItemDelete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.singleton.removeShape();
        }
    }
}
