package gui.menu;

import gui.Constants;
import gui.Editor;
import gui.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuView {

    private Editor editor;

    public MenuView(Editor instance) {
        this.editor = instance;
    }

    public void addActionListeners() {
        JMenu view = editor.getJMenuBar().getMenu(Constants.MENU_VIEW);

        // available camera modes
        String[] modes = {"front", "back", "left", "right", "bottom", "top", "free"};

        // Camera 1 - 4
        ButtonGroup[] bgCameras = new ButtonGroup[Constants.CAMERAS_TOTAL];
        for (int cam = 0; cam < Constants.CAMERAS_TOTAL; cam++) {
            // button group
            bgCameras[cam] = new ButtonGroup();

            JMenu menuParent = (JMenu) view.getItem(Constants.MENU_VIEW_CAMERA1 + cam);

            String selected = Settings.getCamera(cam);
            for (int mode = 0; mode < Constants.CAMERAS_MODE; mode++) {
                JMenuItem menuItem = menuParent.getItem(mode);
                menuItem.addActionListener(new CameraActionListener(cam, modes[mode]));

                // select radio button item
                bgCameras[cam].add(menuItem);
                if (selected.equals(modes[mode])) {
                    menuItem.setSelected(true);
                }
            }
        }

        // Wireframe
        view.getItem(Constants.MENU_VIEW_WIREFRAME).addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actionWireframe();
            }
        });
        view.getItem(Constants.MENU_VIEW_WIREFRAME).setSelected(Settings.isWireframe());

    }

    /**
     * Enable/Disable wireframe render mode
     */
    private void actionWireframe() {
        Settings.setWireframe(!Settings.isWireframe());
    }

    /**
     * Action Listener for Camera Modes
     */
    private class CameraActionListener implements ActionListener {

        private int camera;
        private String mode;

        private CameraActionListener(int camera, String mode) {
            this.camera = camera;
            this.mode = mode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView.this.editor.setCamera(camera, mode);
        }
    }
}
