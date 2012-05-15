package com.badlogic.gdx.backends.lwjgl;

import com.badlogic.gdx.ApplicationListener;
import java.awt.EventQueue;
import org.lwjgl.opengl.Display;

/**
 * Class that extends LwjglCanvas to fix a problem with audio.dispose() not
 * being called on stop method
 */
public class LwjglCanvas2 extends LwjglCanvas {

    public LwjglCanvas2(ApplicationListener listener, boolean useGL2) {
        super(listener, useGL2);
    }

    @Override
    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            Display.destroy();
            audio.dispose();
        } catch (Throwable ignored) {
        }
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                listener.pause();
                listener.dispose();
            }
        });
    }
}
