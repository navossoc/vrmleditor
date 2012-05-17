package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import shape.Shape;

/**
 *
 * @author Guilherme
 */
public class ExportVrml {

    private final String file;
    private final DefaultListModel listModel;

    public ExportVrml(String file, DefaultListModel listModel) {
        this.file = file;
        this.listModel = listModel;
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append("#VRML V2.0 utf8\n");
            Enumeration e = listModel.elements();
            while (e.hasMoreElements()) {
                Shape s = (Shape) e.nextElement();
                bufferedWriter.append(s.printVrml() + "\n");
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao exportar o arquivo VRML", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
