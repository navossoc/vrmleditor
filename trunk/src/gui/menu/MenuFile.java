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
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import shape.Shape;

public class MenuFile {

    private static void newFile() {
        Editor.singleton.getHistory().clear();
        Editor.singleton.getListModel().clear();
        Shape.reset();
    }

    public static class ItemNew implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Editor.singleton.getHistory().isFileDirty()) {
                int option = JOptionPane.showConfirmDialog(Editor.singleton,
                        "Deseja salvar as alterações?", "Novo",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                switch (option) {
                    case JOptionPane.YES_OPTION: {
                        new MenuFile.ItemSave().actionPerformed(null);
                        break;
                    }
                    //case JOptionPane.NO_OPTION:
                    case JOptionPane.CANCEL_OPTION: {
                        return;
                    }
                }
            }

            // create a new file
            newFile();
        }
    }

    public static class ItemOpen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo BIN", "bin"));
            if (fileChooser.showOpenDialog(Editor.singleton) == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                if (!file.endsWith(".bin")) {
                    file += ".bin";
                }

                // erase all shapes
                newFile();

                // insert shapes from file
                List<Shape> shapes = new ArrayList<Shape>();
                if (FormatBin.open(file, shapes)) {
                    for (Shape s : shapes) {
                        Editor.singleton.getListModel().addElement(s);
                    }
                    shapes.clear();
                } else {
                    JOptionPane.showMessageDialog(Editor.singleton, "Erro ao abrir o arquivo BIN", "Abrir", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static class ItemSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo BIN", "bin"));
            if (fileChooser.showSaveDialog(Editor.singleton) == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                if (!file.endsWith(".bin")) {
                    file += ".bin";
                }
                //if (ExportXml.save(file, Editor.singleton.getListModel())) {
                if (FormatBin.save(file, Editor.singleton.getListModel())) {
                    Editor.singleton.getHistory().setFileDirty(false);
                    JOptionPane.showMessageDialog(Editor.singleton, "Arquivo salvo com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Editor.singleton, "Erro ao exportar o arquivo BIN", "Salvar", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static class ItemExport implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo VRML", "wrl"));
            if (fileChooser.showSaveDialog(Editor.singleton) == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                if (!file.endsWith(".wrl")) {
                    file += ".wrl";
                }
                if (ExportVrml.save(file, Editor.singleton.getListModel())) {
                    JOptionPane.showMessageDialog(Editor.singleton, "Arquivo salvo com sucesso!", "Exportar", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Editor.singleton, "Erro ao exportar o arquivo VRML", "Exportar", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static class ItemExit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Editor.singleton.dispose();
            Editor.singleton.exitEditor();
        }
    }
}

/**
 * FileFilter for save file dialog
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