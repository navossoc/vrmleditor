package gui.menu;

import gui.Constants;
import gui.Editor;
import gui.Settings;
import gui.export.ExportVrml;
import gui.format.FormatBin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import shape.Shape;

public class MenuFile {

    private Editor editor;

    public MenuFile(Editor instance) {
        editor = instance;
    }

    public void addActionListeners() {
        JMenu file = editor.getJMenuBar().getMenu(Constants.MENU_FILE);

        // New
        file.getItem(Constants.MENU_FILE_NEW).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionNew();
            }
        });

        // Open
        file.getItem(Constants.MENU_FILE_OPEN).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionOpen();
            }
        });

        // Save
        file.getItem(Constants.MENU_FILE_SAVE).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionSave();
            }
        });

        // Export
        file.getItem(Constants.MENU_FILE_EXPORT).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionExport();
            }
        });

        // Separator
        //MenuConstants.MENU_FILE_SEPARATOR

        // Exit
        file.getItem(Constants.MENU_FILE_EXIT).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });

    }

    /**
     * Create a new empty project
     */
    private void actionNew() {
        if (editor.getHistory().isFileDirty()) {
            int option = JOptionPane.showConfirmDialog(editor,
                    Settings.getMessage("MsgBox.File.New.Question"), Settings.getMessage("MsgBox.File.New.Title"),
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            switch (option) {
                case JOptionPane.YES_OPTION: {
                    actionSave();
                    break;
                }
                //case JOptionPane.NO_OPTION:
                case JOptionPane.CANCEL_OPTION: {
                    return;
                }
            }
        }

        // create a new file
        fileNew();
    }

    /**
     * Open a saved project file
     */
    private void actionOpen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ExtensionFileFilter(Settings.getMessage("File.Binary.Description"), Settings.getMessage("File.Binary.Extension")));
        if (fileChooser.showOpenDialog(editor) == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getAbsolutePath();
            if (!file.endsWith("." + Settings.getMessage("File.Binary.Extension"))) {
                file += "." + Settings.getMessage("File.Binary.Extension");
            }

            // erase all shapes
            fileNew();

            // insert shapes from file
            List<Shape> shapes = new ArrayList<Shape>();
            if (FormatBin.open(file, shapes)) {
                for (Shape s : shapes) {
                    editor.addShape(s);
                }
                shapes.clear();
            } else {
                JOptionPane.showMessageDialog(editor, Settings.getMessage("MsgBox.File.Open.Failure", Settings.getMessage("File.Binary.Extension")), Settings.getMessage("MsgBox.File.Open.Title"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Save the project file
     */
    private void actionSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ExtensionFileFilter(Settings.getMessage("File.Binary.Description"), Settings.getMessage("File.Binary.Extension")));
        if (fileChooser.showSaveDialog(editor) == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getAbsolutePath();
            if (!file.endsWith("." + Settings.getMessage("File.Binary.Extension"))) {
                file += "." + Settings.getMessage("File.Binary.Extension");
            }
            if (FormatBin.save(file, editor.getListModel())) {
                editor.getHistory().setFileDirty(false);
                JOptionPane.showMessageDialog(editor, Settings.getMessage("MsgBox.File.Save.Success"), Settings.getMessage("MsgBox.File.Save.Title"), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(editor, Settings.getMessage("MsgBox.File.Save.Failure", Settings.getMessage("File.Binary.Extension")), Settings.getMessage("MsgBox.File.Save.Title"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Export project to VRML
     */
    private void actionExport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ExtensionFileFilter(Settings.getMessage("File.VRML.Description"), Settings.getMessage("File.VRML.Extension")));
        if (fileChooser.showSaveDialog(editor) == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getAbsolutePath();
            if (!file.endsWith("." + Settings.getMessage("File.VRML.Extension"))) {
                file += "." + Settings.getMessage("File.VRML.Extension");
            }
            if (ExportVrml.save(file, editor.getListModel())) {
                JOptionPane.showMessageDialog(editor, Settings.getMessage("MsgBox.File.Export.Success"), Settings.getMessage("MsgBox.File.Export.Title"), JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(editor, Settings.getMessage("MsgBox.File.Export.Failure", Settings.getMessage("File.Export.Extension")), Settings.getMessage("MsgBox.File.Export.Title"), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Exit application
     */
    private void actionExit() {
        editor.dispose();
        editor.exitEditor();
    }

    /*
     * Helpers
     */
    protected void fileNew() {
        editor.clearAll();
    }
}

/**
 * FileFilter for file dialog
 * http://www.java2s.com/Code/JavaAPI/javax.swing/JFileChoosersetFileFilterFileFilterfilter.htm
 */
class ExtensionFileFilter extends FileFilter {

    String description;
    String extensions[];

    public ExtensionFileFilter(String description, String extension) {
        this(description, new String[]{extension});
    }

    public ExtensionFileFilter(String description, String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0, n = extensions.length; i < n; i++) {
                String extension = extensions[i];
                if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                    return true;
                }
            }
        }
        return false;
    }
}
