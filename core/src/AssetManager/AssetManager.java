package AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    public static final String OVNI = "assets/ovni.png";
    public static final String METEORO = "assets/meteoro.png";
    public static final String MUSICA = "assets/musicajuego.mp4";
    public static FileHandle loadOvni() {

        return Gdx.files.internal("ovni.png");
    }
    public static FileHandle Meteoro() {
        return Gdx.files.internal("meteorito.png");
    }
    public static Music MUSICA() {
        return Gdx.audio.newMusic(Gdx.files.internal("musicajuego.mp4"));
    }
    public static FileHandle fondo(){
        return Gdx.files.internal("fondo.jpg");
    }
    public static void dispose() {
    }


}
