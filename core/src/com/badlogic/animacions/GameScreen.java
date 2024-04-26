package com.badlogic.animacions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final Main game;
	final int IDLE = 0, UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
	Texture walkSheet, background;
	TextureRegion keyFrame, backgroundRegion;
	OrthographicCamera camera;
	Player player;
	float stateTime;
	Rectangle up, down, left, right;
	int posX, posY;

	public GameScreen(Main game) {
		this.game = game;

		background = new Texture(Gdx.files.internal("background.jpg"));
		background.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);

		backgroundRegion = new TextureRegion(background);

		walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));

		player = Player.fromTexture(walkSheet);

		stateTime = 0f;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);

		// Definim les àrees tàctils per controlar el moviment del personatge
		up = new Rectangle(0, (float) (game.height * 2) / 3, game.width, (float) game.height / 3);
		down = new Rectangle(0, 0, game.width, (float) game.height / 3);
		left = new Rectangle(0, 0, (float) game.width / 3, game.height);
		right = new Rectangle((float) (game.width * 2) / 3, 0, (float) game.width / 3, game.height);

		posX = 0;
		posY = 0;
	}

	protected int virtual_joystick_control() {
		for (int i=0; i < 10; i++)
			if (Gdx.input.isTouched(i)) {
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(i), Gdx.input.getY(i), 0);
				camera.unproject(touchPos);

				// Actualitzem la posició del personatge segons quina part de la pantalla s'està prement
				if (up.contains(touchPos.x, touchPos.y)) {
					posY -= 14;
					return UP;
				} else if (down.contains(touchPos.x, touchPos.y)) {
					posY += 14;
					return DOWN;
				} else if (left.contains(touchPos.x, touchPos.y)) {
					posX -= 14;
					return LEFT;
				} else if (right.contains(touchPos.x, touchPos.y)) {
					posX += 14;
					return RIGHT;
				} else {
					return IDLE;
				}
			}
		return IDLE;
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		// Netegem la pantalla amb el color especificat
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// Actualitzem la càmera
		camera.update();

		// Estableim la matriu de projecció per lots a la matriu combinada de la càmera
		game.batch.setProjectionMatrix(camera.combined);

		// Actualitzem el temps d'estat de l'animació
		stateTime += delta;

		// Determinem l'animació del personatge en funció del control del joystick
		switch (virtual_joystick_control()) {
			case 1:
				keyFrame = player.getUpFrames().getKeyFrame(stateTime, true);
				break;
			case 2:
				keyFrame = player.getDownFrames().getKeyFrame(stateTime, true);
				break;
			case 3:
				keyFrame = player.getLeftFrames().getKeyFrame(stateTime, true);
				break;
			case 4:
				keyFrame = player.getRightFrames().getKeyFrame(stateTime, true);
				break;
			default:
				keyFrame = player.getIdleFrames().getKeyFrame(stateTime, true);
				break;
		}

		// Establim la posició de la regió del fons
        backgroundRegion.setRegion(posX, posY, 800, 480);

		game.batch.begin();
		game.batch.draw(backgroundRegion, 0, 0);
		game.batch.draw(keyFrame, 350, 130);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
}
