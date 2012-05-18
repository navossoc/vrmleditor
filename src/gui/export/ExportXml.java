package gui.export;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class ExportXml {

    private final String filename;
    private final DefaultListModel listModel;

    public ExportXml(String file, DefaultListModel listModel) {
        this.filename = file;
        this.listModel = listModel;
    }

    public List<Shape> open() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filename);
            Element elementRoot = doc.getDocumentElement();
            NodeList nodeList = elementRoot.getChildNodes();
            List<Shape> shapes = new ArrayList<Shape>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    Shape shape = null;
                    String name = item.getNodeName();
                    if (name.equals("box")) {
                        shape = new Box(1, 1, 1);
                    } else if (name.equals("cone")) {
                        shape = new Cone(1, 1);
                    } else if (name.equals("cylinder")) {
                        shape = new Cylinder(1, 1);
                    } else if (name.equals("sphere")) {
                        shape = new Sphere(1);
                    }
                    NodeList properties = item.getChildNodes();
                    for (int j = 0; j < properties.getLength(); j++) {
                        Node itemProperty = properties.item(j);
                        String pName = itemProperty.getNodeName();
                        if (name.equals("box")) {
                            Box b = (Box) shape;
                            if (pName.equals("width")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setWidth(Float.valueOf(value));
                            } else if (pName.equals("height")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setHeight(Float.valueOf(value));
                            } else if (pName.equals("depth")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setDepth(Float.valueOf(value));
                            }
                        } else if (name.equals("cone")) {
                            Cone b = (Cone) shape;
                            if (pName.equals("bottomRadius")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setBottomRadius(Float.valueOf(value));
                            } else if (pName.equals("height")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setHeight(Float.valueOf(value));
                            }
                        } else if (name.equals("cylinder")) {
                            Cylinder b = (Cylinder) shape;
                            if (pName.equals("radius")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setRadius(Float.valueOf(value));
                            } else if (pName.equals("height")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setHeight(Float.valueOf(value));
                            }
                        } else if (name.equals("sphere")) {
                            Sphere b = (Sphere) shape;
                            if (pName.equals("radius")) {
                                String value = itemProperty.getFirstChild().getNodeValue();
                                b.setRadius(Float.valueOf(value));
                            }
                        }

                        if (pName.equals("scale")) {
                            NodeList list = itemProperty.getChildNodes();
                            String value = list.item(1).getFirstChild().getNodeValue();
                            shape.getScale().x = Float.valueOf(value);
                            value = list.item(3).getFirstChild().getNodeValue();
                            shape.getScale().y = Float.valueOf(value);
                            value = list.item(5).getFirstChild().getNodeValue();
                            shape.getScale().z = Float.valueOf(value);
                        } else if (pName.equals("translation")) {
                            NodeList list = itemProperty.getChildNodes();
                            String value = list.item(1).getFirstChild().getNodeValue();
                            shape.getTranslation().x = Float.valueOf(value);
                            value = list.item(3).getFirstChild().getNodeValue();
                            shape.getTranslation().y = Float.valueOf(value);
                            value = list.item(5).getFirstChild().getNodeValue();
                            shape.getTranslation().z = Float.valueOf(value);
                        } else if (pName.equals("rotation")) {
                            NodeList list = itemProperty.getChildNodes();
                            String value = list.item(1).getFirstChild().getNodeValue();
                            shape.getRotation().w = Float.valueOf(value);
                            value = list.item(3).getFirstChild().getNodeValue();
                            shape.getRotation().x = Float.valueOf(value);
                            value = list.item(5).getFirstChild().getNodeValue();
                            shape.getRotation().y = Float.valueOf(value);
                            value = list.item(7).getFirstChild().getNodeValue();
                            shape.getRotation().z = Float.valueOf(value);
                        }
                    }
                    shapes.add(shape);
                }
            }
            return shapes;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8"));
            outputStreamWriter.write("<!-- Centro Universitário Fundação Santo André -->\r\n");
            outputStreamWriter.write("<vrml>\r\n");
            Enumeration e = listModel.elements();
            while (e.hasMoreElements()) {
                Shape s = (Shape) e.nextElement();
                outputStreamWriter.write(s.printXml() + "\r\n");
            }
            outputStreamWriter.write("</vrml>\r\n");
            outputStreamWriter.close();
            fileOutputStream.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
