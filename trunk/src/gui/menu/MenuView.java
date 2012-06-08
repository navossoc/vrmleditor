package gui.menu;

import gui.Constants;
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
        JMenu view = editor.getJMenuBar().getMenu(Constants.MENU_VIEW);

        // Wireframe
        view.getItem(Constants.MENU_VIEW_WIREFRAME).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionWireframe();
            }
        });
        view.getItem(Constants.MENU_VIEW_WIREFRAME).setSelected(Settings.isWireframe());

    }

    /**
     * Enable/Disable wireframe render mode
     */
    private void actionWireframe() {
        Settings.setWireframe(!Settings.isWireframe());
    }
}
