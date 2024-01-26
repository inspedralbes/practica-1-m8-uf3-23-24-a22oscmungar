package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import AssetManager.AssetManager;

public class MainMenuScreen implements Screen {
    final MyGdxGame game;

    OrthographicCamera camera;
    Texture fondo;
    TextureRegion region;
    final static AssetManager assets = new AssetManager();

    public MainMenuScreen(final MyGdxGame game) {
        this.game = game;

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
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "Bienvenido a la practica 1! ", 100, 150);
        game.font.draw(game.batch, "Pulsa la pantalla para continuar", 100, 100);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
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
