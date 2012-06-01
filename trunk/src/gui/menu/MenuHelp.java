package gui.menu;

import gui.About;
import gui.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class MenuHelp {

    public static class ItemAbout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog about = new About(Editor.singleton, true);
            about.setLocationRelativeTo(Editor.singleton);
            about.setVisible(true);
        }
    }
}
