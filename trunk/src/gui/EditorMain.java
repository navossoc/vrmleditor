package gui;

import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import javax.swing.DefaultListModel;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

/**
 *
 * @author Rafael
 */
public class EditorMain extends javax.swing.JFrame {

    private LwjglCanvas canvas;
    private DefaultListModel listModel = new DefaultListModel();

    /**
     * Creates new form EditorMain
     */
    public EditorMain() {
        initComponents();
        //editorRender1.setRenderList(listModel);


        canvas = new LwjglCanvas(new EditorRender(listModel), false);
        jPanelRender.add(canvas.getCanvas());
        jPanelRender.validate();

    }

    private void addShape(Shape shape) {
        listModel.addElement(shape);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem10 = new javax.swing.JMenuItem();
        jButtonCone = new javax.swing.JButton();
        jButtonBox = new javax.swing.JButton();
        jButtonCylinder = new javax.swing.JButton();
        jButtonSphere = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListShapes = new javax.swing.JList();
        jPanelRender = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        jMenuItem10.setText("jMenuItem10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(640, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonCone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cone-64.png"))); // NOI18N
        jButtonCone.setToolTipText("Cilindro");
        jButtonCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConeActionPerformed(evt);
            }
        });

        jButtonBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/box-64.png"))); // NOI18N
        jButtonBox.setToolTipText("Caixa");
        jButtonBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBoxActionPerformed(evt);
            }
        });

        jButtonCylinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cylinder-64.png"))); // NOI18N
        jButtonCylinder.setToolTipText("Cilindro");
        jButtonCylinder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCylinderActionPerformed(evt);
            }
        });

        jButtonSphere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/sphere-64.png"))); // NOI18N
        jButtonSphere.setToolTipText("Esfera");
        jButtonSphere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSphereActionPerformed(evt);
            }
        });

        jListShapes.setModel(listModel);
        jListShapes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListShapes);

        jPanelRender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRender.setLayout(new java.awt.GridLayout(1, 0));

        jMenu1.setMnemonic('a');
        jMenu1.setText("Arquivo");

        jMenuItem14.setMnemonic('n');
        jMenuItem14.setText("Novo");
        jMenu1.add(jMenuItem14);

        jMenuItem1.setMnemonic('a');
        jMenuItem1.setText("Abrir");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setMnemonic('s');
        jMenuItem2.setText("Salvar");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setMnemonic('e');
        jMenuItem3.setText("Exportar");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setMnemonic('i');
        jMenuItem4.setText("Sair");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setMnemonic('e');
        jMenu2.setText("Editar");

        jMenuItem5.setMnemonic('d');
        jMenuItem5.setText("Desfazer");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setMnemonic('x');
        jMenuItem6.setText("Excluir");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setMnemonic('m');
        jMenuItem7.setText("Mover");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setMnemonic('r');
        jMenuItem8.setText("Girar");
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setMnemonic('i');
        jMenu3.setText("Inserir");

        jMenuItem9.setMnemonic('a');
        jMenuItem9.setText("Caixa");
        jMenu3.add(jMenuItem9);

        jMenuItem11.setMnemonic('i');
        jMenuItem11.setText("Cilindro");
        jMenu3.add(jMenuItem11);

        jMenuItem12.setMnemonic('o');
        jMenuItem12.setText("Cone");
        jMenu3.add(jMenuItem12);

        jMenuItem13.setMnemonic('e');
        jMenuItem13.setText("Esfera");
        jMenu3.add(jMenuItem13);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCone, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSphere, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonCylinder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelRender, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelRender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCone, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonCylinder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSphere, javax.swing.GroupLayout.DEFAULT_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBoxActionPerformed
        addShape(new Box(100, 100, 100));
    }//GEN-LAST:event_jButtonBoxActionPerformed

    private void jButtonCylinderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCylinderActionPerformed
        addShape(new Cylinder(3, 6));
    }//GEN-LAST:event_jButtonCylinderActionPerformed

    private void jButtonConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConeActionPerformed
        addShape(new Cone(6, 6));
    }//GEN-LAST:event_jButtonConeActionPerformed

    private void jButtonSphereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSphereActionPerformed
        addShape(new Sphere(3));
    }//GEN-LAST:event_jButtonSphereActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO: fix "AL lib: ReleaseALC: 1 device not closed"
        canvas.stop();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new EditorMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBox;
    private javax.swing.JButton jButtonCone;
    private javax.swing.JButton jButtonCylinder;
    private javax.swing.JButton jButtonSphere;
    private javax.swing.JList jListShapes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanelRender;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
