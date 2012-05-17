package gui;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import shape.Shape;

public class ExportVrml {

    private final String filename;
    private final DefaultListModel listModel;

    public ExportVrml(String file, DefaultListModel listModel) {
        this.filename = file;
        this.listModel = listModel;
    }

    public boolean save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8"));
            outputStreamWriter.write("#VRML V2.0 utf8\r\n");
            outputStreamWriter.write("# Centro Universitário Fundação Santo André\r\n");
            Enumeration e = listModel.elements();
            while (e.hasMoreElements()) {
                Shape s = (Shape) e.nextElement();
                outputStreamWriter.write(s.printVrml() + "\r\n");
            }
            outputStreamWriter.close();
            fileOutputStream.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
