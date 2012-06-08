package gui.menu;

import gui.Editor;
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
        JMenu file = editor.getJMenuBar().getMenu(0);

        // New
        file.getItem(0).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionNew();
            }
        });

        // Open
        file.getItem(1).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionOpen();
            }
        });

        // Save
        file.getItem(2).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionSave();
            }
        });

        // Export
        file.getItem(3).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionExport();
            }
        });

        // Separator

        // Exit
        file.getItem(5).addActionListener(new ActionListener() {

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
                    "Deseja salvar as alterações?", "Novo",
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
        fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo BIN", "bin"));
        if (fileChooser.showOpenDialog(editor) == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getAbsolutePath();
            if (!file.endsWith(".bin")) {
                file += ".bin";
            }

            // erase all shapes
            fileNew();

            // insert shapes from file
            List<Shape> shapes = new ArrayList<Shape>();
            if (FormatBin.open(file, shapes)) {
                for (Shape s : shapes) {
                    editor.getListModel().addElement(s);
                }
                shapes.clear();
            } else {
                JOptionPane.showMessageDialog(editor, "Erro ao abrir o arquivo BIN", "Abrir", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Save the project file
     */
    private void actionSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo BIN", "bin"));
        if (fileChooser.showSaveDialog(editor) == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getAbsolutePath();
            if (!file.endsWith(".bin")) {
                file += ".bin";
            }
            if (FormatBin.save(file, editor.getListModel())) {
                editor.getHistory().setFileDirty(false);
                JOptionPane.showMessageDialog(editor, "Arquivo salvo com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(editor, "Erro ao exportar o arquivo BIN", "Salvar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Export project to VRML
     */
    private void actionExport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo VRML", "wrl"));
        if (fileChooser.showSaveDialog(editor) == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getAbsolutePath();
            if (!file.endsWith(".wrl")) {
                file += ".wrl";
            }
            if (ExportVrml.save(file, editor.getListModel())) {
                JOptionPane.showMessageDialog(editor, "Arquivo salvo com sucesso!", "Exportar", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(editor, "Erro ao exportar o arquivo VRML", "Exportar", JOptionPane.ERROR_MESSAGE);
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
        editor.getHistory().clear();
        editor.getListModel().clear();
        Shape.reset();
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