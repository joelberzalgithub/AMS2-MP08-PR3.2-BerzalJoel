package com.badlogic.animacions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {
    private final Animation<TextureRegion> idleFrames, upFrames, leftFrames, downFrames, rightFrames;

    private Player(Texture walkSheet) {
        // Sel·leccionem el fotograma del personatge estant inactiu
        idleFrames = new Animation<>(0.15f,
                new TextureRegion(walkSheet, 0, 128, 64, 64));

        // Sel·leccionem els fotogrames del personatge movent-se cap amunt
        upFrames = new Animation<>(0.15f,
                new TextureRegion(walkSheet, 0, 0, 64, 64),
                new TextureRegion(walkSheet, 64, 0, 64, 64),
                new TextureRegion(walkSheet, 128, 0, 64, 64),
                new TextureRegion(walkSheet, 192, 0, 64, 64));

        // Sel·leccionem els fotogrames del personatge  movent-se cap a l'esquerre
        leftFrames = new Animation<>(0.15f,
                new TextureRegion(walkSheet, 0, 64, 64, 64),
                new TextureRegion(walkSheet, 64, 64, 64, 64),
                new TextureRegion(walkSheet, 128, 64, 64, 64),
                new TextureRegion(walkSheet, 192, 64, 64, 64));

        // Sel·leccionem els fotogrames del personatge movent-se cap a sota
        downFrames = new Animation<>(0.15f,
                new TextureRegion(walkSheet, 0, 128, 64, 64),
                new TextureRegion(walkSheet, 64, 128, 64, 64),
                new TextureRegion(walkSheet, 128, 128, 64, 64),
                new TextureRegion(walkSheet, 192, 128, 64, 64));

        // Sel·leccionem els fotogrames del personatge movent-se cap a la dreta
        rightFrames = new Animation<>(0.15f,
                new TextureRegion(walkSheet, 0, 192, 64, 64),
                new TextureRegion(walkSheet, 64, 192, 64, 64),
                new TextureRegion(walkSheet, 128, 192, 64, 64),
                new TextureRegion(walkSheet, 192, 192, 64, 64));
    }
    public static Player fromTexture(Texture walkSheet) {
        return new Player(walkSheet);
    }

    public Animation<TextureRegion> getIdleFrames() {
        return idleFrames;
    }

    public Animation<TextureRegion> getUpFrames() {
        return upFrames;
    }

    public Animation<TextureRegion> getLeftFrames() {
        return leftFrames;
    }

    public Animation<TextureRegion> getDownFrames() {
        return downFrames;
    }

    public Animation<TextureRegion> getRightFrames() {
        return rightFrames;
    }
}
