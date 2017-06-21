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

public class LifeTest {

	Bird a;

	@Before
	public void init() {
		a = new RedBird(1, 100, 200);
	}
	
	@Test
	public void life(){
		Assert.assertEquals(3, a.getLife());
	}
	
	@Test
	public void reduceLifeBullet(){
		Bullet b = new BlueBulletR(100, 200);
		a.crash(b); 
		Assert.assertEquals(2, a.getLife());
	}
	
	@Test
	public void noReduceLifeBullet(){
		Bullet b = new BlueBulletR(200, 200);
		a.crash(b);
		Assert.assertEquals(3, a.getLife());
	}
	
	@Test
	public void reduceLifeBomb(){
		Bomb b = new Bomb(100, 200);
		a.crash(b); 
		Assert.assertEquals(2, a.getLife());
	}
	
	@Test
	public void noReduceLifeBomb(){
		Bomb b = new Bomb(200, 200);
		a.crash(b);
		Assert.assertEquals(3, a.getLife());
	}
	
	/*
	@Test
	public void reduceLifeTube(){
		Tubes t = new Tubes(100, 10, 10);
		a.crash(t.getTop()); 
		Assert.assertEquals(2, a.getLife());
	}
	
	@Test
	public void noReduceLifeTube(){
		Tubes t = new Tubes(100, 10, 10);
		a.crash(t.getBottom());
		Assert.assertEquals(3, a.getLife());
	}
	*/
	
	@Test
	public void reduceLifeGround(){
		a.setPosition(100, 0);
		Grounds g = new Grounds(100, 100);
		g.crash(a);
		Assert.assertEquals(2, a.getLife());
	}
	
	@Test
	public void noReduceLifeGround(){
		a.setPosition(500, 120);
		Grounds g = new Grounds(100, 100);
		g.crash(a);
		Assert.assertEquals(3, a.getLife());
	}
	
}
