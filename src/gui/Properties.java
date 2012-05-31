package gui;

import com.l2fprod.common.propertysheet.DefaultProperty;
import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.propertysheet.PropertySheetTable;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class Properties {

    private PropertySheetPanel panel;

    public Properties(PropertySheetPanel panel) {
        this.panel = panel;
    }

    public void addProperties(Shape shape) {
        clear();

        // properties
        addCommonProperties(shape);
        panel.addPropertySheetChangeListener(new PropertyListener(shape));
        if (shape instanceof Box) {
            addBoxProperties((Box) shape);
        } else if (shape instanceof Cone) {
            addConeProperties((Cone) shape);
        } else if (shape instanceof Cylinder) {
            addCylinderProperties((Cylinder) shape);
        } else if (shape instanceof Sphere) {
            addSphereProperties((Sphere) shape);
        }
    }

    public void clear() {
        // clear all properties and listeners
        panel.setTable(new PropertySheetTable());
        //panel.getEditorRegistry().registerEditor(Float.class, FloatPropertyEditor2.class);

        // settings
        panel.setMode(PropertySheetPanel.VIEW_AS_CATEGORIES);
        panel.setToolBarVisible(false);
        panel.setDescriptionVisible(false);
    }

    private void addCommonProperties(Shape shape) {
        // Id
        DefaultProperty id = new DefaultProperty();
        id.setCategory("Geral");
        id.setDisplayName("ID");
        id.setEditable(false);
        id.setName("id");
        id.setType(Long.class);
        id.setValue(shape.getID());
        panel.addProperty(id);

        // Name
        DefaultProperty name = new DefaultProperty();
        name.setCategory("Geral");
        name.setDisplayName("Nome");
        name.setEditable(false);
        name.setName("name");
        name.setType(String.class);
        name.setValue(shape.toString());
        panel.addProperty(name);

        // Color
        DefaultProperty color = new DefaultProperty();
        color.setCategory("Cor");
        color.setDisplayName("Paleta");
        color.setName("color");
        color.setType(Color.class);
        color.setValue(shape.getColorAWT());
        panel.addProperty(color);

        // Transparency
        DefaultProperty transparency = new DefaultProperty();
        transparency.setCategory("Cor");
        transparency.setDisplayName("Transparência");
        transparency.setName("transparency");
        transparency.setType(Float.class);
        transparency.setValue(shape.getColor().a);
        panel.addProperty(transparency);

        // Scale
        DefaultProperty scaleX = new DefaultProperty();
        scaleX.setCategory("Escala");
        scaleX.setDisplayName("X");
        scaleX.setName("scaleX");
        scaleX.setType(Float.class);
        scaleX.setValue(shape.getScale().x);
        panel.addProperty(scaleX);
        DefaultProperty scaleY = new DefaultProperty();
        scaleY.setCategory("Escala");
        scaleY.setDisplayName("Y");
        scaleY.setName("scaleY");
        scaleY.setType(Float.class);
        scaleY.setValue(shape.getScale().y);
        panel.addProperty(scaleY);
        DefaultProperty scaleZ = new DefaultProperty();
        scaleZ.setCategory("Escala");
        scaleZ.setDisplayName("Z");
        scaleZ.setName("scaleZ");
        scaleZ.setType(Float.class);
        scaleZ.setValue(shape.getScale().z);
        panel.addProperty(scaleZ);

        // Rotation
        DefaultProperty rotationW = new DefaultProperty();
        rotationW.setCategory("Rotação");
        rotationW.setDisplayName("W");
        rotationW.setName("rotationW");
        rotationW.setType(Float.class);
        rotationW.setValue(shape.getRotation().w);
        panel.addProperty(rotationW);
        DefaultProperty rotationX = new DefaultProperty();
        rotationX.setCategory("Rotação");
        rotationX.setDisplayName("X");
        rotationX.setName("rotationX");
        rotationX.setType(Float.class);
        rotationX.setValue(shape.getRotation().x);
        panel.addProperty(rotationX);
        DefaultProperty rotationY = new DefaultProperty();
        rotationY.setCategory("Rotação");
        rotationY.setDisplayName("Y");
        rotationY.setName("rotationY");
        rotationY.setType(Float.class);
        rotationY.setValue(shape.getRotation().y);
        panel.addProperty(rotationY);
        DefaultProperty rotationZ = new DefaultProperty();
        rotationZ.setCategory("Rotação");
        rotationZ.setDisplayName("Z");
        rotationZ.setName("rotationZ");
        rotationZ.setType(Float.class);
        rotationZ.setValue(shape.getRotation().z);
        panel.addProperty(rotationZ);

        // Translation
        DefaultProperty translationX = new DefaultProperty();
        translationX.setCategory("Translação");
        translationX.setDisplayName("X");
        translationX.setName("translationX");
        translationX.setType(Float.class);
        translationX.setValue(shape.getTranslation().x);
        panel.addProperty(translationX);
        DefaultProperty translationY = new DefaultProperty();
        translationY.setCategory("Translação");
        translationY.setDisplayName("Y");
        translationY.setName("translationY");
        translationY.setType(Float.class);
        translationY.setValue(shape.getTranslation().y);
        panel.addProperty(translationY);
        DefaultProperty translationZ = new DefaultProperty();
        translationZ.setCategory("Translação");
        translationZ.setDisplayName("Z");
        translationZ.setName("translationZ");
        translationZ.setType(Float.class);
        translationZ.setValue(shape.getTranslation().z);
        panel.addProperty(translationZ);
    }

    private void addBoxProperties(Box shape) {
        // Width
        DefaultProperty width = new DefaultProperty();
        width.setCategory("Caixa");
        width.setDisplayName("Largura");
        width.setName("width");
        width.setType(Float.class);
        width.setValue(shape.getWidth());
        panel.addProperty(width);
        // Height
        DefaultProperty height = new DefaultProperty();
        height.setCategory("Caixa");
        height.setDisplayName("Altura");
        height.setName("height");
        height.setType(Float.class);
        height.setValue(shape.getHeight());
        panel.addProperty(height);
        // Depth
        DefaultProperty depth = new DefaultProperty();
        depth.setCategory("Caixa");
        depth.setDisplayName("Profundidade");
        depth.setName("depth");
        depth.setType(Float.class);
        depth.setValue(shape.getDepth());
        panel.addProperty(depth);
    }

    private void addConeProperties(Cone shape) {
        // Bottom Radius
        DefaultProperty radius = new DefaultProperty();
        radius.setCategory("Cone");
        radius.setDisplayName("Raio");
        radius.setName("radius");
        radius.setType(Float.class);
        radius.setValue(shape.getBottomRadius());
        panel.addProperty(radius);
        // Height
        DefaultProperty height = new DefaultProperty();
        height.setCategory("Cone");
        height.setDisplayName("Altura");
        height.setName("height");
        height.setType(Float.class);
        height.setValue(shape.getHeight());
        panel.addProperty(height);
    }

    private void addCylinderProperties(Cylinder shape) {
        // Radius
        DefaultProperty radius = new DefaultProperty();
        radius.setCategory("Cilindro");
        radius.setDisplayName("Raio");
        radius.setName("radius");
        radius.setType(Float.class);
        radius.setValue(shape.getRadius());
        panel.addProperty(radius);
        // Height
        DefaultProperty height = new DefaultProperty();
        height.setCategory("Cilindro");
        height.setDisplayName("Altura");
        height.setName("height");
        height.setType(Float.class);
        height.setValue(shape.getHeight());
        panel.addProperty(height);
    }

    private void addSphereProperties(Sphere shape) {
        // Radius
        DefaultProperty radius = new DefaultProperty();
        radius.setCategory("Esfera");
        radius.setDisplayName("Raio");
        radius.setName("radius");
        radius.setType(Float.class);
        radius.setValue(shape.getRadius());
        panel.addProperty(radius);
    }
}

class PropertyListener implements PropertyChangeListener {

    private Shape shape;

    public PropertyListener(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object obj = evt.getSource();

        if (obj instanceof Property) {
            Property property = (Property) obj;
            String name = property.getName();

            // color
            if (name.equals("color")) {
                Color color = (Color) property.getValue();
                shape.setColorRGB(color);
            } else {
                float value = (Float) property.getValue();

                // transparency
                if (name.equals("transparency")) {
                    shape.setColorA(value);
                } // scale
                else if (name.equals("scaleX")) {
                    shape.setScaleX(value);
                } else if (name.equals("scaleY")) {
                    shape.setScaleY(value);
                } else if (name.equals("scaleZ")) {
                    shape.setScaleZ(value);
                } // rotation
                else if (name.equals("rotationW")) {
                    shape.setRotationW(value);
                } else if (name.equals("rotationX")) {
                    shape.setRotationX(value);
                } else if (name.equals("rotationY")) {
                    shape.setRotationY(value);
                } else if (name.equals("rotationZ")) {
                    shape.setRotationZ(value);
                } // translation
                else if (name.equals("translationX")) {
                    shape.setTranslationX(value);
                } else if (name.equals("translationY")) {
                    shape.setTranslationY(value);
                } else if (name.equals("translationZ")) {
                    shape.setTranslationZ(value);
                }

                // box
                if (shape instanceof Box) {
                    Box box = (Box) shape;
                    if (name.equals("width")) {
                        box.setWidth(value);
                    } else if (name.equals("height")) {
                        box.setHeight(value);
                    } else if (name.equals("depth")) {
                        box.setDepth(value);
                    }
                    // cone
                } else if (shape instanceof Cone) {
                    Cone cone = (Cone) shape;
                    if (name.equals("radius")) {
                        cone.setBottomRadius(value);
                    } else if (name.equals("height")) {
                        cone.setHeight(value);
                    }
                    // cylinder
                } else if (shape instanceof Cylinder) {
                    Cylinder cylinder = (Cylinder) shape;
                    if (name.equals("radius")) {
                        cylinder.setRadius(value);
                    } else if (name.equals("height")) {
                        cylinder.setHeight(value);
                    }
                    // sphere
                } else if (shape instanceof Sphere) {
                    Sphere sphere = (Sphere) shape;
                    if (name.equals("radius")) {
                        sphere.setRadius(value);
                    }
                }
            }
        }
    }
}
