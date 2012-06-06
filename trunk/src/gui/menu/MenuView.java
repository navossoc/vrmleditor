package gui.menu;

import gui.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView {

    public static class ItemWireframe implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Settings.setWireframe(!Settings.isWireframe());
        }
    }
}
