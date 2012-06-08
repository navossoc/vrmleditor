package gui.menu;

import gui.About;
import gui.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JMenu;

public class MenuHelp {

    private Editor editor;

    public MenuHelp(Editor instance) {
        this.editor = instance;
    }

    public void addActionListeners() {
        JMenu help = editor.getJMenuBar().getMenu(3);

        // About
        help.getItem(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionAbout();
            }
        });
    }

    /**
     * Show about dialog
     */
    private void actionAbout() {
        JDialog about = new About(editor, true);
        about.setLocationRelativeTo(editor);
        about.setVisible(true);
    }
}
