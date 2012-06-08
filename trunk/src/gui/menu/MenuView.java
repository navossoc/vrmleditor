package gui.menu;

import gui.Editor;
import gui.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

public class MenuView {

    private Editor editor;

    public MenuView(Editor instance) {
        this.editor = instance;
    }

    public void addActionListeners() {
        JMenu view = editor.getJMenuBar().getMenu(2);

        // Wireframe
        view.getItem(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionWireframe();
            }
        });
    }

    /**
     * Enable/Disable wireframe render mode
     */
    private void actionWireframe() {
        Settings.setWireframe(!Settings.isWireframe());
    }
}
