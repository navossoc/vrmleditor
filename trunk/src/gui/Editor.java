package gui;

import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import gui.menu.MenuEdit;
import gui.menu.MenuFile;
import gui.menu.MenuHelp;
import gui.menu.MenuView;
import history.History;
import history.HistoryInfo;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListModel;
import shape.Shape;
import shape.geometry.Box;
import shape.geometry.Cone;
import shape.geometry.Cylinder;
import shape.geometry.Sphere;

public class Editor extends javax.swing.JFrame {

    private LwjglCanvas canvas;
    private History history;
    private Properties properties;
    private Renderer renderer;

    /**
     * Creates new form Editor
     */
    public Editor() {
        initComponents();

        // menu action listeners
        new MenuFile(this).addActionListeners();
        new MenuEdit(this).addActionListeners();
        new MenuView(this).addActionListeners();
        new MenuHelp(this).addActionListeners();

        // render
        renderer = new Renderer();
        canvas = new LwjglCanvas(renderer, false);

        // input
        canvas.getInput().setInputProcessor(new Input(this, renderer));

        // editor
        history = new History(this);
        properties = new Properties(this, propertySheetPanel);
        jPanelRender.add(canvas.getCanvas());
    }

    /**
     * Exit application properly
     */
    public void exitEditor() {
        canvas.stop();
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.exit(0);
            }
        });
    }

    /**
     * Add a Shape to JList and render list
     *
     * @param shape
     */
    public void addShape(Shape shape) {
        getListModel().addElement(shape);
        renderer.getListShapes().add(shape);
        // clear properties tab
        clearProperties();
    }

    /**
     * Clear all informations about shape
     */
    public void clearAll() {
        // clear list shapes
        getListModel().clear();
        renderer.getListShapes().clear();
        Shape.reset();
        // clear history
        history.clear();
        // clear properties tab
        clearProperties();
    }

    /**
     * Clear properties tab
     */
    public void clearProperties() {
        properties.clear();
        jTabbedPane.setSelectedIndex(0);
    }

    /**
     * Remove a Shape from JList and render list
     *
     * @param shape
     */
    public void delShape(Shape shape) {
        getListModel().removeElement(shape);
        renderer.getListShapes().removeValue(shape, false);
        if (jListShapes.isSelectionEmpty()) {
            clearProperties();
        }
    }

    /**
     * Edit a Shape from JList
     *
     * @param shape
     * @return
     */
    public Shape editShape(Shape shape) {
        Enumeration e = getListModel().elements();
        while (e.hasMoreElements()) {
            Shape list = (Shape) e.nextElement();
            if (list.equals(shape)) {
                Shape copy = list.copy();
                list.set(shape);
                // update property grid values
                Shape selected = (Shape) jListShapes.getSelectedValue();
                if (shape.equals(selected)) {
                    properties.addProperties(shape);
                }
                // copy for history
                return copy;
            }
        }
        return null;
    }

    /**
     * Insert a Shape, also tracks the history of changes
     *
     * @param shape
     */
    private void insertShape(Shape shape) {
        history.insertUndo(new HistoryInfo(shape, HistoryInfo.Type.ADD));
        addShape(shape);
    }

    /**
     * Remove a Shape, also tracks the history of changes
     */
    public void removeShape() {
        if (jListShapes.isSelectionEmpty()) {
            return;
        }

        Shape shape = (Shape) jListShapes.getSelectedValue();
        if (JOptionPane.showConfirmDialog(this,
                Settings.getMessage("MsgBox.Edit.Delete.Question", shape.toString()),
                Settings.getMessage("MsgBox.Edit.Delete.Title"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            history.insertUndo(new HistoryInfo(shape, HistoryInfo.Type.DELETE));
            delShape(shape);
        }
    }

    /**
     * Select a specific shape
     *
     * @param shape
     */
    public void selectShape(Shape shape) {
        if (shape != null) {
            properties.addProperties(shape);
            jListShapes.setSelectedValue(shape, true);
            jTabbedPane.setSelectedIndex(1);
        } else {
            clearProperties();
        }
    }

    /*
     * Getters
     */
    public final History getHistory() {
        return history;
    }

    public final DefaultListModel getListModel() {
        ListModel list = jListShapes.getModel();
        return (DefaultListModel) list;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonBox = new javax.swing.JButton();
        jButtonCylinder = new javax.swing.JButton();
        jButtonCone = new javax.swing.JButton();
        jButtonSphere = new javax.swing.JButton();
        jPanelRender = new javax.swing.JPanel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jScrollPaneList = new javax.swing.JScrollPane();
        jListShapes = new javax.swing.JList(new DefaultListModel());
        propertySheetPanel = new com.l2fprod.common.propertysheet.PropertySheetPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuFileNew = new javax.swing.JMenuItem();
        jMenuFileOpen = new javax.swing.JMenuItem();
        jMenuFileSave = new javax.swing.JMenuItem();
        jMenuFileExport = new javax.swing.JMenuItem();
        jSeparatorFile = new javax.swing.JPopupMenu.Separator();
        jMenuFileExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuEditUndo = new javax.swing.JMenuItem();
        jMenuEditRedo = new javax.swing.JMenuItem();
        jSeparatorEdit = new javax.swing.JPopupMenu.Separator();
        jMenuEditDelete = new javax.swing.JMenuItem();
        jMenuView = new javax.swing.JMenu();
        jMenuViewWireframe = new javax.swing.JCheckBoxMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Settings.getMessage("Window.Editor")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jButtonBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/box-64.png"))); // NOI18N
        jButtonBox.setToolTipText(Settings.getMessage("Shape.Box")); // NOI18N
        jButtonBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBoxActionPerformed(evt);
            }
        });

        jButtonCylinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cylinder-64.png"))); // NOI18N
        jButtonCylinder.setToolTipText(Settings.getMessage("Shape.Cylinder")); // NOI18N
        jButtonCylinder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCylinderActionPerformed(evt);
            }
        });

        jButtonCone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cone-64.png"))); // NOI18N
        jButtonCone.setToolTipText(Settings.getMessage("Shape.Cone")); // NOI18N
        jButtonCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConeActionPerformed(evt);
            }
        });

        jButtonSphere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/sphere-64.png"))); // NOI18N
        jButtonSphere.setToolTipText(Settings.getMessage("Shape.Sphere")); // NOI18N
        jButtonSphere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSphereActionPerformed(evt);
            }
        });

        jPanelRender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRender.setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneStateChanged(evt);
            }
        });

        jScrollPaneList.setBorder(null);

        jListShapes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListShapes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListShapesMouseClicked(evt);
            }
        });
        jListShapes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListShapesValueChanged(evt);
            }
        });
        jScrollPaneList.setViewportView(jListShapes);

        jTabbedPane.addTab(Settings.getMessage("Tab.Objects"), jScrollPaneList); // NOI18N

        propertySheetPanel.setBorder(null);
        jTabbedPane.addTab(Settings.getMessage("Tab.Properties"), propertySheetPanel); // NOI18N

        jMenuFile.setMnemonic(Settings.getMessage("Menu.File.mnemonic").charAt(0));
        jMenuFile.setText(Settings.getMessage("Menu.File")); // NOI18N

        jMenuFileNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuFileNew.setMnemonic(Settings.getMessage("Menu.File.New.mnemonic").charAt(0));
        jMenuFileNew.setText(Settings.getMessage("Menu.File.New")); // NOI18N
        jMenuFile.add(jMenuFileNew);

        jMenuFileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuFileOpen.setMnemonic(Settings.getMessage("Menu.File.Open.mnemonic").charAt(0));
        jMenuFileOpen.setText(Settings.getMessage("Menu.File.Open")); // NOI18N
        jMenuFile.add(jMenuFileOpen);

        jMenuFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuFileSave.setMnemonic(Settings.getMessage("Menu.File.Save.mnemonic").charAt(0));
        jMenuFileSave.setText(Settings.getMessage("Menu.File.Save")); // NOI18N
        jMenuFile.add(jMenuFileSave);

        jMenuFileExport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuFileExport.setMnemonic(Settings.getMessage("Menu.File.Export.mnemonic").charAt(0));
        jMenuFileExport.setText(Settings.getMessage("Menu.File.Export")); // NOI18N
        jMenuFile.add(jMenuFileExport);
        jMenuFile.add(jSeparatorFile);

        jMenuFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuFileExit.setMnemonic(Settings.getMessage("Menu.File.Exit.mnemonic").charAt(0));
        jMenuFileExit.setText(Settings.getMessage("Menu.File.Exit")); // NOI18N
        jMenuFile.add(jMenuFileExit);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setMnemonic(Settings.getMessage("Menu.Edit.mnemonic").charAt(0));
        jMenuEdit.setText(Settings.getMessage("Menu.Edit")); // NOI18N

        jMenuEditUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuEditUndo.setMnemonic(Settings.getMessage("Menu.Edit.Undo.mnemonic").charAt(0));
        jMenuEditUndo.setText(Settings.getMessage("Menu.Edit.Undo")); // NOI18N
        jMenuEditUndo.setEnabled(false);
        jMenuEdit.add(jMenuEditUndo);

        jMenuEditRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuEditRedo.setMnemonic(Settings.getMessage("Menu.Edit.Redo.mnemonic").charAt(0));
        jMenuEditRedo.setText(Settings.getMessage("Menu.Edit.Redo")); // NOI18N
        jMenuEditRedo.setEnabled(false);
        jMenuEdit.add(jMenuEditRedo);
        jMenuEdit.add(jSeparatorEdit);

        jMenuEditDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuEditDelete.setMnemonic(Settings.getMessage("Menu.Edit.Delete.mnemonic").charAt(0));
        jMenuEditDelete.setText(Settings.getMessage("Menu.Edit.Delete")); // NOI18N
        jMenuEditDelete.setEnabled(false);
        jMenuEdit.add(jMenuEditDelete);

        jMenuBar.add(jMenuEdit);

        jMenuView.setMnemonic(Settings.getMessage("Menu.View.mnemonic").charAt(0));
        jMenuView.setText(Settings.getMessage("Menu.View")); // NOI18N

        jMenuViewWireframe.setMnemonic(Settings.getMessage("Menu.View.Wireframe.mnemonic").charAt(0));
        jMenuViewWireframe.setSelected(true);
        jMenuViewWireframe.setText(Settings.getMessage("Menu.View.Wireframe")); // NOI18N
        jMenuView.add(jMenuViewWireframe);

        jMenuBar.add(jMenuView);

        jMenuHelp.setMnemonic(Settings.getMessage("Menu.Help.mnemonic").charAt(0));
        jMenuHelp.setText(Settings.getMessage("Menu.Help")); // NOI18N

        jMenuHelpAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuHelpAbout.setMnemonic(Settings.getMessage("Menu.Help.About.mnemonic").charAt(0));
        jMenuHelpAbout.setText(Settings.getMessage("Menu.Help.About")); // NOI18N
        jMenuHelp.add(jMenuHelpAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCylinder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelRender, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
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
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        exitEditor();
    }//GEN-LAST:event_formWindowClosed

    /*
     * Buttons
     */
    private void jButtonBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBoxActionPerformed
        insertShape(new Box(1.0f, 1.0f, 1.0f));
    }//GEN-LAST:event_jButtonBoxActionPerformed

    private void jButtonCylinderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCylinderActionPerformed
        insertShape(new Cylinder(0.5f, 1.0f));
    }//GEN-LAST:event_jButtonCylinderActionPerformed

    private void jButtonConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConeActionPerformed
        insertShape(new Cone(0.5f, 1.0f));
    }//GEN-LAST:event_jButtonConeActionPerformed

    private void jButtonSphereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSphereActionPerformed
        insertShape(new Sphere(0.5f));
    }//GEN-LAST:event_jButtonSphereActionPerformed

    /*
     * List
     */
    private void jListShapesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListShapesMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() == 2) {
                if (!jListShapes.isSelectionEmpty()) {
                    jTabbedPane.setSelectedIndex(1);
                }
            }
        }
    }//GEN-LAST:event_jListShapesMouseClicked

    private void jListShapesValueChanged(javax.swing.event.ListSelectionEvent evt) {
        jMenuEditDelete.setEnabled(!jListShapes.isSelectionEmpty());
    }

    /*
     * Tabs
     */
    private void jTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneStateChanged
        // properties tab
        if (jTabbedPane.getSelectedIndex() == 1) {
            // selected shape on list
            Shape shape = (Shape) jListShapes.getSelectedValue();
            if (shape != null) {
                properties.addProperties(shape);
            } else {
                properties.clear();
            }
        }
    }//GEN-LAST:event_jTabbedPaneStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the default look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Settings.setLanguage();
                // fix the problem with the menu items appearing behind drawing canvas
                JPopupMenu.setDefaultLightWeightPopupEnabled(false);
                new Editor().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBox;
    private javax.swing.JButton jButtonCone;
    private javax.swing.JButton jButtonCylinder;
    private javax.swing.JButton jButtonSphere;
    private javax.swing.JList jListShapes;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenuItem jMenuEditDelete;
    private javax.swing.JMenuItem jMenuEditRedo;
    private javax.swing.JMenuItem jMenuEditUndo;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileExit;
    private javax.swing.JMenuItem jMenuFileExport;
    private javax.swing.JMenuItem jMenuFileNew;
    private javax.swing.JMenuItem jMenuFileOpen;
    private javax.swing.JMenuItem jMenuFileSave;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuHelpAbout;
    private javax.swing.JMenu jMenuView;
    private javax.swing.JCheckBoxMenuItem jMenuViewWireframe;
    private javax.swing.JPanel jPanelRender;
    private javax.swing.JScrollPane jScrollPaneList;
    private javax.swing.JPopupMenu.Separator jSeparatorEdit;
    private javax.swing.JPopupMenu.Separator jSeparatorFile;
    private javax.swing.JTabbedPane jTabbedPane;
    private com.l2fprod.common.propertysheet.PropertySheetPanel propertySheetPanel;
    // End of variables declaration//GEN-END:variables
}
