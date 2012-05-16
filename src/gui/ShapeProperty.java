package gui;

import history.History;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class ShapeProperty extends javax.swing.JDialog {

    /**
     * Creates new form ShapeProperty
     */
    public ShapeProperty(Shape shape) {
        initComponents();
        setLocationRelativeTo(null);
        setModal(true);
        
        setTitle(shape.toString());
        
        if (shape instanceof Box) {
            setContent(new BoxPanel((Box) shape));
        } else if (shape instanceof Cone) {
            setContent(new ConePanel((Cone) shape));
        } else if (shape instanceof Cylinder) {
            setContent(new CylinderPanel((Cylinder) shape));
        } else if (shape instanceof Sphere) {
            setContent(new SpherePanel((Sphere) shape));
        }
    }
    
    private void setContent(JPanel panel) {
        setContentPane(panel);
        setSize(400, 400);
        validate();
    }
    
    private static void editShape(Shape shape) {
        EditorMain.instance.history.pushUndo(new History(shape.copy(), History.Type.EDIT));
    }
    
    final class BoxPanel extends JPanel implements ActionListener {
        
        Box box;
        JTextField width;
        JTextField height;
        JTextField depth;
        JTextField scalex, scaley, scalez;
        JTextField rotationw, rotationx, rotationy, rotationz;
        JTextField translationx, translationy, translationz;
        JButton ok;
        JButton apply;
        
        public BoxPanel(Box b) {
            this.box = b;
            
            width = new JTextField(String.valueOf(b.getWidth()));
            height = new JTextField(String.valueOf(b.getHeight()));
            depth = new JTextField(String.valueOf(b.getDepth()));
            ok = new JButton("OK");
            apply = new JButton("Aplicar");
            
            setLayout(new GridLayout(14, 2));
            add(new JLabel("Largura"));
            add(width);
            add(new JLabel("Altura"));
            add(height);
            add(new JLabel("Profundidade"));
            add(depth);
            
            scalex = new JTextField(String.valueOf(b.getScale().x));
            scaley = new JTextField(String.valueOf(b.getScale().y));
            scalez = new JTextField(String.valueOf(b.getScale().z));
            add(new JLabel("Escala - X"));
            add(scalex);
            add(new JLabel("Escala - Y"));
            add(scaley);
            add(new JLabel("Escala - Z"));
            add(scalez);
            
            rotationw = new JTextField(String.valueOf(b.getRotation().w));
            rotationx = new JTextField(String.valueOf(b.getRotation().x));
            rotationy = new JTextField(String.valueOf(b.getRotation().y));
            rotationz = new JTextField(String.valueOf(b.getRotation().z));
            add(new JLabel("Rotação - W"));
            add(rotationw);
            add(new JLabel("Rotação - X"));
            add(rotationx);
            add(new JLabel("Rotação - Y"));
            add(rotationy);
            add(new JLabel("Rotação - Z"));
            add(rotationz);
            
            translationx = new JTextField(String.valueOf(b.getTranslation().x));
            translationy = new JTextField(String.valueOf(b.getTranslation().y));
            translationz = new JTextField(String.valueOf(b.getTranslation().z));
            add(new JLabel("Translação - X"));
            add(translationx);
            add(new JLabel("Translação - Y"));
            add(translationy);
            add(new JLabel("Translação - Z"));
            add(translationz);
            
            add(ok);
            add(apply);
            
            ok.addActionListener(this);
            apply.addActionListener(this);
            
            setSize(400, 400);
            validate();
        }
        
        private void setBox() {
            box.setWidth(Float.valueOf(width.getText()));
            box.setHeight(Float.valueOf(height.getText()));
            box.setDepth(Float.valueOf(depth.getText()));
            //
            float w, x, y, z;
            x = Float.valueOf(scalex.getText());
            y = Float.valueOf(scaley.getText());
            z = Float.valueOf(scalez.getText());
            box.setScale(x, y, z);
            
            w = Float.valueOf(rotationw.getText());
            x = Float.valueOf(rotationx.getText());
            y = Float.valueOf(rotationy.getText());
            z = Float.valueOf(rotationz.getText());
            box.setRotation(x, y, z, w);
            
            x = Float.valueOf(translationx.getText());
            y = Float.valueOf(translationy.getText());
            z = Float.valueOf(translationz.getText());
            box.setTranslation(x, y, z);
            //
            box.calculateBox();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            editShape(box);
            setBox();
            if (e.getSource() == ok) {
                dispose();
            }
        }
    }
    
    final class ConePanel extends JPanel implements ActionListener {
        
        Cone cone;
        JTextField bottomRadius;
        JTextField height;
        JButton ok;
        JButton apply;
        
        public ConePanel(Cone c) {
            this.cone = c;
            
            bottomRadius = new JTextField(String.valueOf(cone.getBottomRadius()));
            height = new JTextField(String.valueOf(cone.getHeight()));
            ok = new JButton("OK");
            apply = new JButton("Aplicar");
            
            setLayout(new GridLayout(3, 2));
            add(new JLabel("Raio da base"));
            add(bottomRadius);
            add(new JLabel("Altura"));
            add(height);
            add(ok);
            add(apply);
            
            ok.addActionListener(this);
            apply.addActionListener(this);
            
            setSize(400, 400);
            validate();
            
        }
        
        private void setCone() {
            cone.setBottomRadius(Float.valueOf(bottomRadius.getText()));
            cone.setHeight(Float.valueOf(height.getText()));
            cone.calculateCone();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            editShape(cone);
            setCone();
            if (e.getSource() == ok) {
                dispose();
            }
        }
    }
    
    final class CylinderPanel extends JPanel implements ActionListener {
        
        Cylinder cylinder;
        JTextField radius;
        JTextField height;
        JButton ok;
        JButton apply;
        
        public CylinderPanel(Cylinder c) {
            this.cylinder = c;
            
            radius = new JTextField(String.valueOf(cylinder.getRadius()));
            height = new JTextField(String.valueOf(cylinder.getHeight()));
            ok = new JButton("OK");
            apply = new JButton("Aplicar");
            
            setLayout(new GridLayout(3, 2));
            add(new JLabel("Raio"));
            add(radius);
            add(new JLabel("Altura"));
            add(height);
            add(ok);
            add(apply);
            
            ok.addActionListener(this);
            apply.addActionListener(this);
            
            setSize(400, 400);
            validate();
        }
        
        private void setCylinder() {
            cylinder.setRadius(Float.valueOf(radius.getText()));
            cylinder.setHeight(Float.valueOf(height.getText()));
            cylinder.calculateCylinder();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            editShape(cylinder);
            setCylinder();
            if (e.getSource() == ok) {
                dispose();
            }
        }
    }
    
    final class SpherePanel extends JPanel implements ActionListener {
        
        Sphere sphere;
        JTextField radius;
        JTextField height;
        JButton ok;
        JButton apply;
        
        public SpherePanel(Sphere s) {
            this.sphere = s;
            
            radius = new JTextField(String.valueOf(sphere.getRadius()));
            ok = new JButton("OK");
            apply = new JButton("Aplicar");
            
            setLayout(new GridLayout(2, 2));
            add(new JLabel("Raio"));
            add(radius);
            add(ok);
            add(apply);
            
            ok.addActionListener(this);
            apply.addActionListener(this);
            
            setSize(400, 400);
            validate();
        }
        
        private void setSphere() {
            sphere.setRadius(Float.valueOf(radius.getText()));
            sphere.calculateSphere();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            editShape(sphere);
            setSphere();
            if (e.getSource() == ok) {
                dispose();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this
     * method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(4, 2));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
