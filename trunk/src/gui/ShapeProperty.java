package gui;

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

public class ShapeProperty extends javax.swing.JFrame {
    
    private final Shape shape;

    /**
     * Creates new form ShapeProperty
     */
    public ShapeProperty(Shape shape) {
        initComponents();
        this.shape = shape;
        setTitle(shape.toString());
        
        if (shape instanceof Box) {
            setContent(new BoxPanel((Box) shape));
        } else if (shape instanceof Cone) {
            setContent(new ConePanel((Cone) shape));
        } else if (shape instanceof Cylinder) {
            setContent(new CylinderPanel((Cylinder) shape));
        }
    }
    
    private void setContent(JPanel panel) {
        setContentPane(panel);
        setSize(400, 400);
        validate();
    }
    
    final class BoxPanel extends JPanel implements ActionListener {
        
        Box box;
        JTextField width;
        JTextField height;
        JTextField depth;
        JButton ok;
        JButton cancel;
        
        public BoxPanel(Box b) {
            this.box = b;
            
            width = new JTextField(String.valueOf(b.getWidth()));
            height = new JTextField(String.valueOf(b.getHeight()));
            depth = new JTextField(String.valueOf(b.getDepth()));
            ok = new JButton("OK");
            cancel = new JButton("Aplicar");
            
            setLayout(new GridLayout(4, 2));
            add(new JLabel("Largura"));
            add(width);
            add(new JLabel("Altura"));
            add(height);
            add(new JLabel("Profundidade"));
            add(depth);
            add(ok);
            add(cancel);
            
            ok.addActionListener(this);
            cancel.addActionListener(this);
            
            setSize(400, 400);
            validate();
        }
        
        private void setBox() {
            box.setWidth(Float.valueOf(width.getText()));
            box.setHeight(Float.valueOf(height.getText()));
            box.setDepth(Float.valueOf(depth.getText()));
            box.calculateBox();
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ok) {
                setBox();
                dispose();
            } else {
                setBox();
            }
        }
    }
    
    final class ConePanel extends JPanel implements ActionListener {
        
        Cone cone;
        JTextField bottomRadius;
        JTextField height;
        JButton ok;
        JButton cancel;
        
        public ConePanel(Cone c) {
            this.cone = c;
            
            bottomRadius = new JTextField(String.valueOf(cone.getBottomRadius()));
            height = new JTextField(String.valueOf(cone.getHeight()));
            ok = new JButton("OK");
            cancel = new JButton("Aplicar");
            
            setLayout(new GridLayout(3, 2));
            add(new JLabel("Raio da base"));
            add(bottomRadius);
            add(new JLabel("Altura"));
            add(height);
            add(ok);
            add(cancel);
            
            ok.addActionListener(this);
            cancel.addActionListener(this);
            
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
            if (e.getSource() == ok) {
                setCone();
                dispose();
            } else {
                setCone();
            }
        }
    }
    
    final class CylinderPanel extends JPanel implements ActionListener {
        
        Cylinder cylinder;
        JTextField radius;
        JTextField height;
        JButton ok;
        JButton cancel;
        
        public CylinderPanel(Cylinder c) {
            this.cylinder = c;
            
            radius = new JTextField(String.valueOf(cylinder.getRadius()));
            height = new JTextField(String.valueOf(cylinder.getHeight()));
            ok = new JButton("OK");
            cancel = new JButton("Aplicar");
            
            setLayout(new GridLayout(3, 2));
            add(new JLabel("Raio"));
            add(radius);
            add(new JLabel("Altura"));
            add(height);
            add(ok);
            add(cancel);
            
            ok.addActionListener(this);
            cancel.addActionListener(this);
            
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
            if (e.getSource() == ok) {
                setCylinder();
                dispose();
            } else {
                setCylinder();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(4, 2));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
