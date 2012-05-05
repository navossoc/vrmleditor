package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import shape.Shape;

/**
 *
 * @author Rafael
 */
public class EditorRender extends javax.swing.JPanel {

    private int w, h;
    DefaultListModel shapes;

    /**
     * Creates new form EditorRender
     */
    public EditorRender() {
        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("paint(" + w + "," + h + ")");

        // adjust "camera"
        w = getWidth() / 2;
        h = getHeight() / 2;
        g.translate(w, h);

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(10, -10);

        Enumeration<Shape> e = (Enumeration<Shape>) shapes.elements();
        while (e.hasMoreElements()) {
            e.nextElement().draw(g);
        }

    }

    public void setRenderList(DefaultListModel shapes) {
        this.shapes = shapes;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
