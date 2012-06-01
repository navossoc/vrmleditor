package gui.menu;

import gui.Editor;
import gui.Render;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView {

    public static class ItemWireframe implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Render render = Editor.singleton.getRenderer();
            render.setWireframe(!render.isWireframe());
        }
    }
}
