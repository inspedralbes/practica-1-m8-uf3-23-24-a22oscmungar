package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import AssetManager.AssetManager;

public class GameOverScreen implements Screen {
    final MyGdxGame game;
    OrthographicCamera camera;
    int puntuacion;
    Texture fondo;
    TextureRegion region;
    final static AssetManager assets = new AssetManager();

    public GameOverScreen(MyGdxGame game, int puntuacion) {
        this.game = game;
        this.puntuacion = puntuacion;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        fondo = new Texture(assets.fondo());
        region = new TextureRegion(fondo, 0, 0, 800, 480);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(region,0,0);
        game.font.getData().setScale(3f);
        game.font.draw(game.batch, "La partida ha acabado! ", 200, 300);
        game.font.draw(game.batch, "Puntuacion: " + puntuacion, 200, 250);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
