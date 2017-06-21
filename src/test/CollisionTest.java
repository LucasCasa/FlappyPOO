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
import component.life.Life;

public class CollisionTest {

	Bird a;

	@Before
	public void init() {
		a = new RedBird(1, 100, 200);
	}
	
	@Test
	public void collisionBulletEnemy(){
		Bullet b = new BlueBulletR(100, 200);
		Assert.assertTrue(a.crash(b));
	}
	
	@Test
	public void collisionLife(){
		Life l = new Life(100, 200);
		Assert.assertTrue(a.crash(l));
	}
	
	@Test
	public void collisionBomb(){
		Bomb l = new Bomb(100, 200);
		Assert.assertTrue(a.crash(l));
	}
	
	@Test
	public void collisionGround(){
		a.setPosition(100, 0);
		Grounds g = new Grounds(100, 100);
		Assert.assertTrue(g.crash(a));
	}
	
	/*
	@Test
	public void collisionTopTube(){
		a.setPosition(100, 0);
		Tubes t = new Tubes(100, 10, 10);
		Assert.assertTrue(t.getTop().crash(a));
	}
	
	@Test
	public void collisionBottomTube(){
		a.setPosition(100, 0);
		Tubes t = new Tubes(100, 10, 10);
		Assert.assertFalse(t.getBottom().crash(a));
	}
	*/


}
