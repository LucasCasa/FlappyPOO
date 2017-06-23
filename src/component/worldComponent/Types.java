package component.worldComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Types {

	public static final String BLUE_BIRD = "bird_blue.png";
	public static final String GREEN_BIRD = "bird_green.png";
	public static final String ORANGE_BIRD = "bird_orange.png";
	public static final String RED_BIRD = "bird_red.png";
	public static final String VIOLET_BIRD = "bird_violet.png";
	public static final String SILVER_BIRD = "bird.png";
	
	public static final String SILVER_BIRD_INV = "bird_inv.png";
	public static final String BLUE_BIRD_INV = "bird_blue_inv.png";
	public static final String GREEN_BIRD_INV = "bird_green_inv.png";
	public static final String RED_BIRD_INV = "bird_red_inv.png";
	
	public static final String SILVER_BULLET = "bullet1.png";
	public static final String LASER_BULLET = "bullet2.png";
	public static final String CANNON_BULLET = "bullet3.png";
	public static final String FIRE_BULLET = "bullet4.png";
	
	public static final String SILVER_BULLET_INV = "bullet1_inv.png";
	public static final String LASER_BULLET_INV = "bullet2_inv.png";
	public static final String CANNON_BULLET_INV = "bullet3_inv.png";
	public static final String FIRE_BULLET_INV = "bullet4_inv.png";
	public static final String BOMB = "bomb.png";
	public static final String HEART = "heart.png";
	public static final String BLACK_HEART = "black_heart.png";
	public static final String CRASH = "crash.mp3";
	public static final String SHOOT = "jump2.wav";

	public static final Sound typing1 =  Gdx.audio.newSound(Gdx.files.internal("typing1.mp3"));
	public static final Sound typing2 =  Gdx.audio.newSound(Gdx.files.internal("typing2.mp3"));
	
	public static final String MAIN_THEME = "main.wav";
	
	public static final String LOGO = "logo.png";
	public static final String[] BACKGROUND = {"background_level1.png","background_level2.png","background_level1.png"};
	public static final String PLAYBTN = "playbtn.png";
	public static final String GROUND = "ground2.png";
	public static final String TOP_TUBE = "toptube.png";
	public static final String BOTTOM_TUBE = "bottomtube.png";
	public static final String METAL_TOP_TUBE = "metaltoptube.png";
	public static final String METAL_BOTTOM_TUBE = "metalbottomtube.png";
	public static final String GAME_OVER = "gameover.png";
	
	public static final int FIRE_BULLET_SPEED = 250;
	public static final int SILVER_BULLET_SPEED = 600;
	public static final int LASER_BULLET_SPEED = 500;
	public static final int CANNON_BULLET_SPEED = 300;
	
}
