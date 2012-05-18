package gui.menu;

import gui.Editor;
import gui.export.ExportVrml;
import gui.export.ExportXml;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import shape.Shape;

public class MenuFile {

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
                    }
                    case JOptionPane.NO_OPTION: {
                        Editor.singleton.getHistory().clear();
                        Editor.singleton.getListModel().clear();
                        Shape.reset();
                    }
                }
            } else {
                Editor.singleton.getHistory().clear();
                Editor.singleton.getListModel().clear();
                Shape.reset();
            }
        }
    }

    public static class ItemOpen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo XML", "xml"));
            if (fileChooser.showOpenDialog(Editor.singleton) == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                if (!file.endsWith(".xml")) {
                    file += ".xml";
                }
                List<Shape> shapes = new ExportXml(file, null).open();
                if (shapes != null) {
                    Editor.singleton.getHistory().setFileDirty(false);
                    for (Shape s : shapes) {
                        Editor.singleton.getListModel().addElement(s.copy());
                    }
                    shapes.clear();
                } else {
                    JOptionPane.showMessageDialog(Editor.singleton, "Erro ao abrir o arquivo XML", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static class ItemSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new ExtensionFileFilter("Arquivo XML", "xml"));
            if (fileChooser.showSaveDialog(Editor.singleton) == JFileChooser.APPROVE_OPTION) {
                String file = fileChooser.getSelectedFile().getAbsolutePath();
                if (!file.endsWith(".xml")) {
                    file += ".xml";
                }
                if (new ExportXml(file, Editor.singleton.getListModel()).save()) {
                    Editor.singleton.getHistory().setFileDirty(false);
                    JOptionPane.showMessageDialog(Editor.singleton, "Arquivo salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Editor.singleton, "Erro ao exportar o arquivo XML", "Erro", JOptionPane.ERROR_MESSAGE);
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
                if (new ExportVrml(file, Editor.singleton.getListModel()).save()) {
                    JOptionPane.showMessageDialog(Editor.singleton, "Arquivo salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Editor.singleton, "Erro ao exportar o arquivo VRML", "Erro", JOptionPane.ERROR_MESSAGE);
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