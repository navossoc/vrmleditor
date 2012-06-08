package gui.export;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import shape.Shape;

public class ExportVrml {

    public static boolean save(String filename, DefaultListModel listModel) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8"));
            outputStreamWriter.write("#VRML V2.0 utf8\r\n");
            outputStreamWriter.write("# Centro Universitário Fundação Santo André\r\n");
            outputStreamWriter.write(viewPoint());
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

    private static String viewPoint() {
        return "# Cameras\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Front (x/y)\"\r\n"
                + "\tfieldOfView 0.01\r\n"
                + "\torientation 0.00 1.00 0.00 0.000\r\n"
                + "\tposition 0.00 0.00 500.00\r\n"
                + "},\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Back (x/y)\"\r\n"
                + "\tfieldOfView 0.01\r\n"
                + "\torientation 0.00 -1.00 0.00 3.142\r\n"
                + "\tposition 0.00 0.00 -500.00\r\n"
                + "},\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Left (z/y)\"\r\n"
                + "\tfieldOfView 0.01\r\n"
                + "\torientation 0.00 -1.00 0.00 1.571\r\n"
                + "\tposition -500.00 0.00 0.00\r\n"
                + "},\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Right (z/y)\"\r\n"
                + "\tfieldOfView 0.01\r\n"
                + "\torientation 0.00 1.00 0.00 1.571\r\n"
                + "\tposition 500.00 0.00 0.00\r\n"
                + "},\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Top (x/z)\"\r\n"
                + "\tfieldOfView 0.01\r\n"
                + "\torientation -1.00 0.00 0.00 1.571\r\n"
                + "\tposition 0.00 500.00 0.00\r\n"
                + "},\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Bottom (x/z)\"\r\n"
                + "\tfieldOfView 0.01\r\n"
                + "\torientation 1.00 0.00 0.00 1.571\r\n"
                + "\tposition 0.00 -500.00 0.00\r\n"
                + "},\r\n"
                + "Viewpoint {\r\n"
                + "\tdescription \"Free\"\r\n"
                + "\torientation -0.815 0.534 0.226 0.957\r\n"
                + "\tposition 3.00 6.00 5.00\r\n"
                + "}\r\n";
    }
}
