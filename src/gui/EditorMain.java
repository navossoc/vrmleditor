package gui;

import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas2;
import gui.menu.MenuEdit;
import gui.menu.MenuFile;
import gui.menu.MenuInsert;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import shape.Shape;

/**
 *
 * @author Rafael
 */
public class EditorMain extends javax.swing.JFrame {

    private LwjglCanvas canvas;
    private DefaultListModel listModel = new DefaultListModel();
    public static EditorMain instance;

    /**
     * Creates new form EditorMain
     */
    public EditorMain() {
        initComponents();
        //setExtendedState(MAXIMIZED_BOTH);

        canvas = new LwjglCanvas2(new EditorRender(listModel), false);
        jPanelRender.add(canvas.getCanvas());
        jPanelRender.validate();
    }

    /*
     * Public Methods
     */
    public void addShape(Shape shape) {
        listModel.addElement(shape);
    }

    public void stopCanvas() {
        canvas.stop();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonBox = new javax.swing.JButton();
        jButtonCylinder = new javax.swing.JButton();
        jButtonCone = new javax.swing.JButton();
        jButtonSphere = new javax.swing.JButton();
        jScrollPaneList = new javax.swing.JScrollPane();
        jListShapes = new javax.swing.JList();
        jPanelRender = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuFileNew = new javax.swing.JMenuItem();
        jMenuFileOpen = new javax.swing.JMenuItem();
        jMenuFileSave = new javax.swing.JMenuItem();
        jMenuFileExport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuFileExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuEditUndo = new javax.swing.JMenuItem();
        jMenuEditRedo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuEditDelete = new javax.swing.JMenuItem();
        jMenuEditScale = new javax.swing.JMenuItem();
        jMenuEditRotate = new javax.swing.JMenuItem();
        jMenuEditTranslate = new javax.swing.JMenuItem();
        jMenuInsert = new javax.swing.JMenu();
        jMenuInsertBox = new javax.swing.JMenuItem();
        jMenuInsertCylinder = new javax.swing.JMenuItem();
        jMenuInsertCone = new javax.swing.JMenuItem();
        jMenuInsertSphere = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VRML Editor");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/box-64.png"))); // NOI18N
        jButtonBox.setToolTipText("Caixa");
        jButtonBox.addActionListener(new MenuInsert.ItemBox());

        jButtonCylinder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cylinder-64.png"))); // NOI18N
        jButtonCylinder.setToolTipText("Cilindro");
        jButtonCylinder.addActionListener(new MenuInsert.ItemCylinder());

        jButtonCone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/cone-64.png"))); // NOI18N
        jButtonCone.setToolTipText("Cilindro");
        jButtonCone.addActionListener(new MenuInsert.ItemCone());

        jButtonSphere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/sphere-64.png"))); // NOI18N
        jButtonSphere.setToolTipText("Esfera");
        jButtonSphere.addActionListener(new MenuInsert.ItemSphere());

        jListShapes.setModel(listModel);
        jListShapes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListShapes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListShapesMousePressed(evt);
            }
        });
        jListShapes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListShapesKeyPressed(evt);
            }
        });
        jScrollPaneList.setViewportView(jListShapes);

        jPanelRender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelRender.setLayout(new java.awt.GridLayout(1, 0));

        jMenuFile.setMnemonic('a');
        jMenuFile.setText("Arquivo");

        jMenuFileNew.setMnemonic('n');
        jMenuFileNew.setText("Novo");
        jMenuFileNew.addActionListener(new MenuFile.ItemNew());
        jMenuFile.add(jMenuFileNew);

        jMenuFileOpen.setMnemonic('a');
        jMenuFileOpen.setText("Abrir");
        jMenuFileOpen.addActionListener(new MenuFile.ItemOpen());
        jMenuFile.add(jMenuFileOpen);

        jMenuFileSave.setMnemonic('s');
        jMenuFileSave.setText("Salvar");
        jMenuFileSave.addActionListener(new MenuFile.ItemSave());
        jMenuFile.add(jMenuFileSave);

        jMenuFileExport.setMnemonic('e');
        jMenuFileExport.setText("Exportar");
        jMenuFileExport.addActionListener(new MenuFile.ItemExport());
        jMenuFile.add(jMenuFileExport);
        jMenuFile.add(jSeparator1);

        jMenuFileExit.setMnemonic('i');
        jMenuFileExit.setText("Sair");
        jMenuFileExit.addActionListener(new MenuFile.ItemExit());
        jMenuFile.add(jMenuFileExit);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setMnemonic('e');
        jMenuEdit.setText("Editar");

        jMenuEditUndo.setMnemonic('d');
        jMenuEditUndo.setText("Desfazer");
        jMenuEditUndo.addActionListener(new MenuEdit.ItemUndo());
        jMenuEdit.add(jMenuEditUndo);

        jMenuEditRedo.setMnemonic('r');
        jMenuEditRedo.setText("Refazer");
        jMenuEditRedo.addActionListener(new MenuEdit.ItemRedo());
        jMenuEdit.add(jMenuEditRedo);
        jMenuEdit.add(jSeparator2);

        jMenuEditDelete.setMnemonic('x');
        jMenuEditDelete.setText("Excluir");
        jMenuEditDelete.addActionListener(new MenuEdit.ItemDelete());
        jMenuEdit.add(jMenuEditDelete);

        jMenuEditScale.setMnemonic('e');
        jMenuEditScale.setText("Escala");
        jMenuEditScale.addActionListener(new MenuEdit.ItemScale());
        jMenuEdit.add(jMenuEditScale);

        jMenuEditRotate.setMnemonic('r');
        jMenuEditRotate.setText("Girar");
        jMenuEditRotate.addActionListener(new MenuEdit.ItemRotate());
        jMenuEdit.add(jMenuEditRotate);

        jMenuEditTranslate.setMnemonic('m');
        jMenuEditTranslate.setText("Mover");
        jMenuEditTranslate.addActionListener(new MenuEdit.ItemTranslate());
        jMenuEdit.add(jMenuEditTranslate);

        jMenuBar.add(jMenuEdit);

        jMenuInsert.setMnemonic('i');
        jMenuInsert.setText("Inserir");

        jMenuInsertBox.setMnemonic('a');
        jMenuInsertBox.setText("Caixa");
        jMenuInsertBox.addActionListener(new MenuInsert.ItemBox());
        jMenuInsert.add(jMenuInsertBox);

        jMenuInsertCylinder.setMnemonic('i');
        jMenuInsertCylinder.setText("Cilindro");
        jMenuInsertCylinder.addActionListener(new MenuInsert.ItemCylinder());
        jMenuInsert.add(jMenuInsertCylinder);

        jMenuInsertCone.setMnemonic('o');
        jMenuInsertCone.setText("Cone");
        jMenuInsertCone.addActionListener(new MenuInsert.ItemCone());
        jMenuInsert.add(jMenuInsertCone);

        jMenuInsertSphere.setMnemonic('e');
        jMenuInsertSphere.setText("Esfera");
        jMenuInsertSphere.addActionListener(new MenuInsert.ItemSphere());
        jMenuInsert.add(jMenuInsertSphere);

        jMenuBar.add(jMenuInsert);

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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPaneList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonBox, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonCylinder, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelRender, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
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
                        .addComponent(jScrollPaneList, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        stopCanvas();
    }//GEN-LAST:event_formWindowClosing

    private void jListShapesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListShapesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            Shape shape = (Shape) jListShapes.getSelectedValue();
            if (JOptionPane.showConfirmDialog(this,
                    "Deseja remover o objeto?",
                    shape.toString(),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                listModel.removeElement(jListShapes.getSelectedValue());
            }
        }
    }//GEN-LAST:event_jListShapesKeyPressed

    private void jListShapesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListShapesMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() == 2) {
                Shape shape = (Shape) jListShapes.getSelectedValue();
                if (shape != null) {
                    new ShapeProperty(shape).setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jListShapesMousePressed

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

            @Override
            public void run() {
                // fix the problem with the menu items appearing behind drawing canvas
                JPopupMenu.setDefaultLightWeightPopupEnabled(false);
                instance = new EditorMain();
                instance.setVisible(true);
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
    private javax.swing.JMenuItem jMenuEditRotate;
    private javax.swing.JMenuItem jMenuEditScale;
    private javax.swing.JMenuItem jMenuEditTranslate;
    private javax.swing.JMenuItem jMenuEditUndo;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuFileExit;
    private javax.swing.JMenuItem jMenuFileExport;
    private javax.swing.JMenuItem jMenuFileNew;
    private javax.swing.JMenuItem jMenuFileOpen;
    private javax.swing.JMenuItem jMenuFileSave;
    private javax.swing.JMenu jMenuInsert;
    private javax.swing.JMenuItem jMenuInsertBox;
    private javax.swing.JMenuItem jMenuInsertCone;
    private javax.swing.JMenuItem jMenuInsertCylinder;
    private javax.swing.JMenuItem jMenuInsertSphere;
    private javax.swing.JPanel jPanelRender;
    private javax.swing.JScrollPane jScrollPaneList;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
