package gui;

import com.l2fprod.common.propertysheet.DefaultProperty;
import com.l2fprod.common.propertysheet.Property;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.propertysheet.PropertySheetTable;
import history.HistoryInfo;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public final class Properties {

    private final Editor editor;
    private final PropertySheetPanel panel;

    public Properties(Editor instance, PropertySheetPanel panel) {
        this.editor = instance;
        this.panel = panel;
    }

    public void addProperties(Shape shape) {
        clear();

        // properties
        addCommonProperties(shape);
        panel.addPropertySheetChangeListener(new PropertyListener(editor, shape));
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

        // settings
        panel.setMode(PropertySheetPanel.VIEW_AS_CATEGORIES);
        panel.setToolBarVisible(false);
        panel.setDescriptionVisible(false);
    }

    private void addCommonProperties(Shape shape) {
        // Id
        DefaultProperty id = new DefaultProperty();
        id.setCategory(Settings.getMessage("Properties.General"));
        id.setDisplayName(Settings.getMessage("Properties.General.Id"));
        id.setEditable(false);
        id.setName("id");
        id.setType(Long.class);
        id.setValue(shape.getID());
        panel.addProperty(id);

        // Name
        DefaultProperty name = new DefaultProperty();
        name.setCategory(Settings.getMessage("Properties.General"));
        name.setDisplayName(Settings.getMessage("Properties.General.Name"));
        name.setEditable(false);
        name.setName("name");
        name.setType(String.class);
        name.setValue(shape.toString());
        panel.addProperty(name);

        // Color
        DefaultProperty color = new DefaultProperty();
        color.setCategory(Settings.getMessage("Properties.Color"));
        color.setDisplayName(Settings.getMessage("Properties.Color.Color"));
        color.setName("color");
        color.setType(Color.class);
        color.setValue(shape.getColorAWT());
        panel.addProperty(color);

        // Transparency
        DefaultProperty transparency = new DefaultProperty();
        transparency.setCategory(Settings.getMessage("Properties.Color"));
        transparency.setDisplayName(Settings.getMessage("Properties.Color.Transparency"));
        transparency.setName("transparency");
        transparency.setType(Float.class);
        transparency.setValue(shape.getColor().a);
        panel.addProperty(transparency);

        // Scale
        DefaultProperty scaleX = new DefaultProperty();
        scaleX.setCategory(Settings.getMessage("Properties.Scale"));
        scaleX.setDisplayName(Settings.getMessage("Properties.Scale.X"));
        scaleX.setName("scaleX");
        scaleX.setType(Float.class);
        scaleX.setValue(shape.getScale().x);
        panel.addProperty(scaleX);
        DefaultProperty scaleY = new DefaultProperty();
        scaleY.setCategory(Settings.getMessage("Properties.Scale"));
        scaleY.setDisplayName(Settings.getMessage("Properties.Scale.Y"));
        scaleY.setName("scaleY");
        scaleY.setType(Float.class);
        scaleY.setValue(shape.getScale().y);
        panel.addProperty(scaleY);
        DefaultProperty scaleZ = new DefaultProperty();
        scaleZ.setCategory(Settings.getMessage("Properties.Scale"));
        scaleZ.setDisplayName(Settings.getMessage("Properties.Scale.Z"));
        scaleZ.setName("scaleZ");
        scaleZ.setType(Float.class);
        scaleZ.setValue(shape.getScale().z);
        panel.addProperty(scaleZ);

        // Rotation
        DefaultProperty rotationW = new DefaultProperty();
        rotationW.setCategory(Settings.getMessage("Properties.Rotation"));
        rotationW.setDisplayName(Settings.getMessage("Properties.Rotation.W"));
        rotationW.setName("rotationW");
        rotationW.setType(Float.class);
        rotationW.setValue(shape.getRotation().w);
        panel.addProperty(rotationW);
        DefaultProperty rotationX = new DefaultProperty();
        rotationX.setCategory(Settings.getMessage("Properties.Rotation"));
        rotationX.setDisplayName(Settings.getMessage("Properties.Rotation.X"));
        rotationX.setName("rotationX");
        rotationX.setType(Float.class);
        rotationX.setValue(shape.getRotation().x);
        panel.addProperty(rotationX);
        DefaultProperty rotationY = new DefaultProperty();
        rotationY.setCategory(Settings.getMessage("Properties.Rotation"));
        rotationY.setDisplayName(Settings.getMessage("Properties.Rotation.Y"));
        rotationY.setName("rotationY");
        rotationY.setType(Float.class);
        rotationY.setValue(shape.getRotation().y);
        panel.addProperty(rotationY);
        DefaultProperty rotationZ = new DefaultProperty();
        rotationZ.setCategory(Settings.getMessage("Properties.Rotation"));
        rotationZ.setDisplayName(Settings.getMessage("Properties.Rotation.Z"));
        rotationZ.setName("rotationZ");
        rotationZ.setType(Float.class);
        rotationZ.setValue(shape.getRotation().z);
        panel.addProperty(rotationZ);

        // Translation
        DefaultProperty translationX = new DefaultProperty();
        translationX.setCategory(Settings.getMessage("Properties.Translation"));
        translationX.setDisplayName(Settings.getMessage("Properties.Translation.X"));
        translationX.setName("translationX");
        translationX.setType(Float.class);
        translationX.setValue(shape.getTranslation().x);
        panel.addProperty(translationX);
        DefaultProperty translationY = new DefaultProperty();
        translationY.setCategory(Settings.getMessage("Properties.Translation"));
        translationY.setDisplayName(Settings.getMessage("Properties.Translation.Y"));
        translationY.setName("translationY");
        translationY.setType(Float.class);
        translationY.setValue(shape.getTranslation().y);
        panel.addProperty(translationY);
        DefaultProperty translationZ = new DefaultProperty();
        translationZ.setCategory(Settings.getMessage("Properties.Translation"));
        translationZ.setDisplayName(Settings.getMessage("Properties.Translation.Z"));
        translationZ.setName("translationZ");
        translationZ.setType(Float.class);
        translationZ.setValue(shape.getTranslation().z);
        panel.addProperty(translationZ);
    }

    private void addBoxProperties(Box shape) {
        // Width
        DefaultProperty width = new DefaultProperty();
        width.setCategory(Settings.getMessage("Properties.Box"));
        width.setDisplayName(Settings.getMessage("Properties.Box.Width"));
        width.setName("width");
        width.setType(Float.class);
        width.setValue(shape.getWidth());
        panel.addProperty(width);
        // Height
        DefaultProperty height = new DefaultProperty();
        height.setCategory(Settings.getMessage("Properties.Box"));
        height.setDisplayName(Settings.getMessage("Properties.Box.Height"));
        height.setName("height");
        height.setType(Float.class);
        height.setValue(shape.getHeight());
        panel.addProperty(height);
        // Depth
        DefaultProperty depth = new DefaultProperty();
        depth.setCategory(Settings.getMessage("Properties.Box"));
        depth.setDisplayName(Settings.getMessage("Properties.Box.Depth"));
        depth.setName("depth");
        depth.setType(Float.class);
        depth.setValue(shape.getDepth());
        panel.addProperty(depth);
    }

    private void addConeProperties(Cone shape) {
        // Bottom Radius
        DefaultProperty radius = new DefaultProperty();
        radius.setCategory(Settings.getMessage("Properties.Cone"));
        radius.setDisplayName(Settings.getMessage("Properties.Cone.Radius"));
        radius.setName("radius");
        radius.setType(Float.class);
        radius.setValue(shape.getBottomRadius());
        panel.addProperty(radius);
        // Height
        DefaultProperty height = new DefaultProperty();
        height.setCategory(Settings.getMessage("Properties.Cone"));
        height.setDisplayName(Settings.getMessage("Properties.Cone.Height"));
        height.setName("height");
        height.setType(Float.class);
        height.setValue(shape.getHeight());
        panel.addProperty(height);
    }

    private void addCylinderProperties(Cylinder shape) {
        // Radius
        DefaultProperty radius = new DefaultProperty();
        radius.setCategory(Settings.getMessage("Properties.Cylinder"));
        radius.setDisplayName(Settings.getMessage("Properties.Cylinder.Radius"));
        radius.setName("radius");
        radius.setType(Float.class);
        radius.setValue(shape.getRadius());
        panel.addProperty(radius);
        // Height
        DefaultProperty height = new DefaultProperty();
        height.setCategory(Settings.getMessage("Properties.Cylinder"));
        height.setDisplayName(Settings.getMessage("Properties.Cylinder.Height"));
        height.setName("height");
        height.setType(Float.class);
        height.setValue(shape.getHeight());
        panel.addProperty(height);
    }

    private void addSphereProperties(Sphere shape) {
        // Radius
        DefaultProperty radius = new DefaultProperty();
        radius.setCategory(Settings.getMessage("Properties.Sphere"));
        radius.setDisplayName(Settings.getMessage("Properties.Sphere.Radius"));
        radius.setName("radius");
        radius.setType(Float.class);
        radius.setValue(shape.getRadius());
        panel.addProperty(radius);
    }
}

final class PropertyListener implements PropertyChangeListener {

    private final Editor editor;
    private final Shape shape;

    public PropertyListener(Editor instance, Shape shape) {
        this.editor = instance;
        this.shape = shape;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object obj = evt.getSource();

        if (obj instanceof Property) {
            Property property = (Property) obj;
            String name = property.getName();

            // keep tracking of modifications
            editor.getHistory().insertUndo(new HistoryInfo(shape, HistoryInfo.Type.EDIT));

            // color
            if (name.equals("color")) {
                Color color = (Color) property.getValue();
                shape.setColorRGB(color);
            } else {
                float value = (Float) property.getValue();

                // transparency
                if (name.equals("transparency")) {
                    if (value < 0.0f) {
                        value = 0.0f;
                    } else if (value > 1.0f) {
                        value = 1.0f;
                    }
                    shape.setColorA(value);
                    property.setValue(value);
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
                    if (value <= -1.0f) {
                        value = -1.0f;
                    } else if (value >= 1.0f) {
                        value = 1.0f;
                    } else {
                        value = 0.0f;
                    }
                    shape.setRotationX(value);
                } else if (name.equals("rotationY")) {
                    if (value <= -1.0f) {
                        value = -1.0f;
                    } else if (value >= 1.0f) {
                        value = 1.0f;
                    } else {
                        value = 0.0f;
                    }
                    shape.setRotationY(value);
                } else if (name.equals("rotationZ")) {
                    if (value <= -1.0f) {
                        value = -1.0f;
                    } else if (value >= 1.0f) {
                        value = 1.0f;
                    } else {
                        value = 0.0f;
                    }
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
