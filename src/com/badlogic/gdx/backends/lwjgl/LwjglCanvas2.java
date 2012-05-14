package com.badlogic.gdx.backends.lwjgl;

import com.badlogic.gdx.ApplicationListener;

/**
 * Class that extends LwjglCanvas to fix a problem with audio.dispose() not being
 * called on stop method
 */
public class LwjglCanvas2 extends LwjglCanvas {

    public LwjglCanvas2(ApplicationListener listener, boolean useGL2) {
        super(listener, useGL2);
    }

    @Override
    public void stop() {
        super.stop();
        this.audio.dispose();
    }
}
