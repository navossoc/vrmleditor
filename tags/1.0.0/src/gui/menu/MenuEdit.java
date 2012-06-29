package gui.menu;

import gui.Constants;
import gui.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

public class MenuEdit {

    private Editor editor;

    public MenuEdit(Editor instance) {
        this.editor = instance;
    }

    public void addActionListeners() {
        JMenu edit = editor.getJMenuBar().getMenu(Constants.MENU_EDIT);

        // Undo
        edit.getItem(Constants.MENU_EDIT_UNDO).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionUndo();
            }
        });

        // Redo
        edit.getItem(Constants.MENU_EDIT_REDO).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionRedo();
            }
        });

        // Delete
        edit.getItem(Constants.MENU_EDIT_DELETE).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionDelete();
            }
        });
    }

    /**
     * Undo last action
     */
    private void actionUndo() {
        editor.getHistory().undo();
    }

    /**
     * Redo last action
     */
    private void actionRedo() {
        editor.getHistory().redo();
    }

    /**
     * Delete selected shape from list
     */
    private void actionDelete() {
        editor.removeShape();
    }
}
