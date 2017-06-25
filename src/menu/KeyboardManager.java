package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import component.worldComponent.Types;
import javafx.scene.input.KeyCode;
import state.GameStateManager;
import world.music.Level1Music;
import world.music.Level2Music;
import world.music.Level3Music;
import world.music.MenuMusic;

import java.awt.*;

public class KeyboardManager implements InputProcessor {
	private static int keycodeBuff;
	
	public static int getKeyDown() {
		return keycodeBuff;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.PLUS){
			if(Types.masterVolume < 1){
				Types.masterVolume+=0.05;
				if(MenuMusic.getInstance().isPlaying()) {
					MenuMusic.getInstance().reduceVolume(Types.masterVolume);
				}else if(Level1Music.getInstance().isPlaying()){
					Level1Music.getInstance().reduceVolume(Types.masterVolume);
				}else if(Level2Music.getInstance().isPlaying()){
					Level2Music.getInstance().reduceVolume(Types.masterVolume);
				}else if(Level3Music.getInstance().isPlaying()){
					Level3Music.getInstance().reduceVolume(Types.masterVolume);
				}
			}
		}else if(keycode == Input.Keys.MINUS){
			if(Types.masterVolume > 0.05){
				Types.masterVolume-=0.05;
				if(MenuMusic.getInstance().isPlaying()) {
					MenuMusic.getInstance().reduceVolume(Types.masterVolume);
				}else if(Level1Music.getInstance().isPlaying()){
					Level1Music.getInstance().reduceVolume(Types.masterVolume);
				}else if(Level2Music.getInstance().isPlaying()){
					Level2Music.getInstance().reduceVolume(Types.masterVolume);
				}else if(Level3Music.getInstance().isPlaying()){
					Level3Music.getInstance().reduceVolume(Types.masterVolume);
				}
			}
		}
		GameStateManager.getInstance().peek().keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
