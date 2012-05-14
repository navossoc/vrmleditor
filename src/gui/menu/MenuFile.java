package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFile {

    public static class New implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> new");
        }
    }

    public static class Open implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> open");
        }
    }

    public static class Save implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> save");
        }
    }

    public static class Export implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> export");
        }
    }

    public static class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("file -> exit");
            //canvas.stop();
        }
    }
}
