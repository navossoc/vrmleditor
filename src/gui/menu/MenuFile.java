package gui.menu;

import gui.Editor;
import gui.ExportVrml;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
            System.out.println("file -> open");
        }
    }

    public static class ItemSave implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> save");
            // TODO: chamar somente se salvar c/ sucesso
            Editor.singleton.getHistory().setFileDirty(false);
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