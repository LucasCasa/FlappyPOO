package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import component.bird.Bird;
import component.bird.RedBird;
import component.bomb.Bomb;
import component.bullet.BlueBulletR;
import component.bullet.Bullet;
import component.ground.Grounds;

public class AuraAndScoreTest {

	Bird a;

	@Before
	public void init() {
		a = new RedBird(1, 100, 200);
	}
	
	@Test
	public void reduceScoreBullet(){
		Bullet b = new BlueBulletR(100, 200);
		a.crash(b); 
		Assert.assertEquals(0, a.getScore());
		Assert.assertTrue(a.getAuraState());
	}
	
	@Test
	public void noSetAuraBullet(){
		Bullet b = new BlueBulletR(200, 200);
		a.crash(b);
		Assert.assertFalse(a.getAuraState());
	}
	
	@Test
	public void reduceScoreBomb(){
		Bomb b = new Bomb(100, 200);
		a.crash(b); 
		Assert.assertEquals(0, a.getScore());
		Assert.assertTrue(a.getAuraState());
	}
	
	@Test
	public void noSetAuraBomb(){
		Bomb b = new Bomb(200, 200);
		a.crash(b);
		Assert.assertFalse(a.getAuraState());
	}
	
	/*
	@Test
	public void reduceScoreTube(){
		Tubes t = new Tubes(100, 10, 10);
		a.crash(t.getTop()); 
		Assert.assertEquals(0, a.getScore());
		Assert.assertTrue(a.getAuraState());
	}
	
	@Test
	public void noSetAuraTube(){
		Tubes t = new Tubes(100, 10, 10);
		a.crash(t.getBottom());
		Assert.assertFalse(a.getAuraState());
	}
	*/
	
	@Test
	public void reduceScoreGround(){
		a.setPosition(100, 0);
		Grounds g = new Grounds(100, 100);
		g.crash(a);
		Assert.assertEquals(0, a.getScore());
		Assert.assertTrue(a.getAuraState());
	}
	
	@Test
	public void noSetAuraGround(){
		a.setPosition(500, 120);
		Grounds g = new Grounds(100, 100);
		g.crash(a);
		Assert.assertFalse(a.getAuraState());
	}
	
}
