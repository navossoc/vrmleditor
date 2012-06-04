package gui;

import gui.menu.MenuEdit;
import gui.menu.MenuFile;
import gui.menu.MenuHelp;
import gui.menu.MenuView;

public class Menu {

    /**
     * Register menu action listeners
     *
     * @param editor
     */
    public static void registerActionListeners() {
        // Instance
        Editor editor = Editor.singleton;

        // File
        editor.jMenuFileNew.addActionListener(new MenuFile.ItemNew());
        editor.jMenuFileOpen.addActionListener(new MenuFile.ItemOpen());
        editor.jMenuFileSave.addActionListener(new MenuFile.ItemSave());
        editor.jMenuFileExport.addActionListener(new MenuFile.ItemExport());
        editor.jMenuFileExit.addActionListener(new MenuFile.ItemExit());

        // Edit
        editor.jMenuEditUndo.addActionListener(new MenuEdit.ItemUndo());
        editor.jMenuEditRedo.addActionListener(new MenuEdit.ItemRedo());
        editor.jMenuEditRemove.addActionListener(new MenuEdit.ItemDelete());

        // View
        editor.jMenuViewWireframe.addActionListener(new MenuView.ItemWireframe());

        // Help
        editor.jMenuHelpAbout.addActionListener(new MenuHelp.ItemAbout());
    }
}
