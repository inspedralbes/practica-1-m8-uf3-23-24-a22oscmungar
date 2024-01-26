package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import AssetManager.AssetManager;

public class GameScreen implements Screen {
    final MyGdxGame game;
    Texture ovniImg;
    Rectangle ovni;
    Texture meteoroImg;
    Music musica;
    int vidas = 3;
    int puntuacion = 0;

    OrthographicCamera camera;
    long lastMeteorTime;
    Array<Rectangle> meteors;
    public static AssetManager assets = new AssetManager();
    Texture fondo;
    TextureRegion region;
    public GameScreen(final MyGdxGame game) {
    this.game = game;

    ovniImg = new Texture(assets.loadOvni());
    ovni = new Rectangle();
    ovni.x = 800 / 2 - 64 / 2;
    ovni.y = 175;
    ovni.width = 64;
    ovni.height = 64;




    meteoroImg = new Texture(assets.Meteoro());
    musica = assets.MUSICA();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);

    meteors = new Array<Rectangle>();
    spawnMeteor();

    }

    private void spawnMeteor() {
        Rectangle meteor = new Rectangle();
        meteor.x = MathUtils.random(0, 800 - 64);
        meteor.y = 480;
        meteor.width = 150;
        meteor.height = 150;
        meteors.add(meteor);
        lastMeteorTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
        musica.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.getData().setScale(2.0f);

        game.batch.draw(region,0,0);
        game.font.draw(game.batch, "Te quedan  " + vidas +" vidas", 20, 440);
        game.font.draw(game.batch, "Puntuacion: " + puntuacion, 20, 400);
        game.batch.draw(ovniImg, ovni.x, ovni.y, ovni.width, ovni.height);

        for (Rectangle meteor : meteors) {
            game.batch.draw(meteoroImg, meteor.x, meteor.y);
        }

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            ovni.x = touchPos.x - 64 / 2;
        }

        if (TimeUtils.nanoTime() - lastMeteorTime > 2000000000)
            spawnMeteor();

        game.batch.end();

        Iterator<Rectangle> iter = meteors.iterator();
        while (iter.hasNext()) {
            Rectangle meteor = iter.next();
            meteor.y -= 200 * Gdx.graphics.getDeltaTime();
            if (meteor.y + 64 < 0){
                puntuacion += 100;
                iter.remove();
            }

            if (meteor.overlaps(ovni)) {
                vidas--;
                iter.remove();
            }

        }

        if (vidas == 0) {
            musica.stop();
            game.setScreen(new GameOverScreen(game, puntuacion));
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
