package com.badlogic.animacions;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    final int height = 480, width = 800;
    Screen currentScreen;
    SpriteBatch batch;
    BitmapFont font;
    Texture img;

    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        img = new Texture("badlogic.jpg");

        currentScreen = new GameScreen(this);
        this.setScreen(currentScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
