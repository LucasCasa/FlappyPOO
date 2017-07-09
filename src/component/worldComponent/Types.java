package component.worldComponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.Locale;
import java.util.ResourceBundle;

public class Types {

	public static final String BLUE_BIRD = "bird_blue.png";
	public static final String GREEN_BIRD = "bird_green.png";
	//public static final String ORANGE_BIRD = "bird_orange.png";
	public static final String RED_BIRD = "bird_red.png";
	//public static final String VIOLET_BIRD = "bird_violet.png";
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

	public static final String ENEMY_BULLET = "enemyBullet.png";
	public static final String BOMB = "bomb.png";
	public static final String HEART = "heart.png";
	public static final String BLACK_HEART = "black_heart.png";
	public static final Sound STRIKE = Gdx.audio.newSound(Gdx.files.internal("strike.mp3"));
	public static final Sound LIFE_SOUND = Gdx.audio.newSound(Gdx.files.internal("life.mp3"));
	public static final Sound BOMB_EXPLOSION_SOUND = Gdx.audio.newSound(Gdx.files.internal("bomba.mp3"));
	public static final Sound BULLET_SOUND = Gdx.audio.newSound(Gdx.files.internal("bala.mp3"));
	public static final Sound CANNON_SOUND = Gdx.audio.newSound(Gdx.files.internal("cannon.mp3"));
	public static final Sound FIRE_SOUND = Gdx.audio.newSound(Gdx.files.internal("fuego.mp3"));
	public static final Sound LASER_SOUND = Gdx.audio.newSound(Gdx.files.internal("laser.mp3"));
	public static final Sound CRASH_WOOD_SOUND = Gdx.audio.newSound(Gdx.files.internal("madera.mp3"));
	public static final Sound CRASH_METAL_SOUND = Gdx.audio.newSound(Gdx.files.internal("metal.mp3"));
	public static final Sound CRASH_GRASS_SOUND = Gdx.audio.newSound(Gdx.files.internal("grass.mp3"));
	public static final Sound[] BIRD_SOUNDS = {Gdx.audio.newSound(Gdx.files.internal("ahah.mp3")),Gdx.audio.newSound(Gdx.files.internal("aia.mp3")),Gdx.audio.newSound(Gdx.files.internal("iii.mp3"))};
	public static final Sound JUMP = Gdx.audio.newSound(Gdx.files.internal("salto.mp3"));

	public static final Sound SHRINK_SOUND = Gdx.audio.newSound(Gdx.files.internal("power1.mp3"));
	public static final Sound FREEZE_SOUND = Gdx.audio.newSound(Gdx.files.internal("congela.mp3"));
	public static final Sound SHIELD_SOUND = Gdx.audio.newSound(Gdx.files.internal("escudo.mp3"));

	public static final Sound CANT_SHOOT_SOUND = Gdx.audio.newSound(Gdx.files.internal("nobala.mp3"));
	public static final Sound CANT_POWER_SOUND = Gdx.audio.newSound(Gdx.files.internal("sinpower.mp3"));

	public static final Sound[] ENEMY_HIT = {Gdx.audio.newSound(Gdx.files.internal("pedo1.mp3")),Gdx.audio.newSound(Gdx.files.internal("pedo2.mp3")),Gdx.audio.newSound(Gdx.files.internal("pedo3.mp3"))};

	public static final Sound[] COUNTDOWN = {Gdx.audio.newSound(Gdx.files.internal("countdown_en.mp3")),Gdx.audio.newSound(Gdx.files.internal("countdown_es.mp3"))};
	public static final Sound[] START = {Gdx.audio.newSound(Gdx.files.internal("start_en.mp3")),Gdx.audio.newSound(Gdx.files.internal("start_es.mp3"))};
	public static final Sound[] DRAW = {Gdx.audio.newSound(Gdx.files.internal("draw_en.mp3")),Gdx.audio.newSound(Gdx.files.internal("draw_es.mp3"))};

	
	public static final Sound typing1 =  Gdx.audio.newSound(Gdx.files.internal("typing1.mp3"));
	public static final Sound typing2 =  Gdx.audio.newSound(Gdx.files.internal("typing2.mp3"));
	
	public static final String MAIN_THEME = "main.mp3";
	public static final Sound LEVEL1_THEME = Gdx.audio.newSound(Gdx.files.internal("level1.mp3"));
	public static final Sound LEVEL2_THEME = Gdx.audio.newSound(Gdx.files.internal("level2.mp3"));
	public static final Sound LEVEL3_THEME = Gdx.audio.newSound(Gdx.files.internal("level3.mp3"));
	public static final Sound MODE_2_THEME = Gdx.audio.newSound(Gdx.files.internal("musicaacelerada.mp3"));
	public static final Sound MODE_3_THEME = Gdx.audio.newSound(Gdx.files.internal("musicamodo3.mp3"));
	public static final Sound LEVEL1_VOICE = Gdx.audio.newSound(Gdx.files.internal("level1Voice.mp3"));
	public static final Sound LEVEL2_VOICE = Gdx.audio.newSound(Gdx.files.internal("level2Voice.mp3"));
	public static final Sound LEVEL3_VOICE = Gdx.audio.newSound(Gdx.files.internal("level3Voice.mp3"));
	//public static final Sound GAMEOVER_VOICE = Gdx.audio.newSound(Gdx.files.internal("gameOver.mp3"));
	public static final Sound PLAYER1_VOICE = Gdx.audio.newSound(Gdx.files.internal("player1Wins.mp3"));
	public static final Sound PLAYER2_VOICE = Gdx.audio.newSound(Gdx.files.internal("player2Wins.mp3"));
	
	public static final String LOGO = "logo.png";
	public static final String[] BACKGROUND = {"background_level1.png","background_level2.png","background_level3.png"};
	public static final String[] GROUNDS = {"groundLevel1.png","groundLevel2.png","groundLevel3.png"};
	public static final String TOP_TUBE = "toptube2.png";
	public static final String BOTTOM_TUBE = "bottomtube2.png";
	public static final String METAL_TOP_TUBE = "metaltoptube.png";
	public static final String METAL_BOTTOM_TUBE = "metalbottomtube.png";
	public static final String GAME_OVER = "gameover.png";
	
	public static final int FIRE_BULLET_SPEED = 250;
	public static final int SILVER_BULLET_SPEED = 600;
	public static final int LASER_BULLET_SPEED = 500;
	public static final int CANNON_BULLET_SPEED = 300;


    public static float masterVolume = 1f;


	public static final Texture AMMO_BAR = new Texture("ammoBar.png");
	public static final Texture SHIELD = new Texture("shield.png");
	public static final Texture FREEZE = new Texture("freeze.png");
	public static final Texture AMMO_BACK = new Texture("ammoBack.png");
    public static final ResourceBundle MESSAGES = ResourceBundle.getBundle("text", Locale.forLanguageTag("ES"));

}
