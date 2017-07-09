package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import component.bird.Bird;
import component.bird.BirdLeft;
import component.bird.BirdType;
import component.bullet.Bullet;
import component.bullet.BulletRight;
import component.bullet.EnemyBullet;
import music.Level1Music;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lucas on 08/07/2017.
 */
public class NestWorldManager extends WorldManager{

    int lives = 50;
    float time = 0;
    List<Bullet> bullets;
    Bird b1;
    Bird b2;
    float timeSinceLastBullet = 0;
    int p1Kills = 0;
    int p2Kills = 0;
    public NestWorldManager(OrthographicCamera cam, String p1Name, String p2Name, BirdType p1Bird, BirdType p2Bird){
        super(cam);
        bullets = new ArrayList<>();
        b1 = createBird(p1Name,p1Bird,true);
        b2 = createBird(p2Name,p2Bird,true);
    }
    @Override
    protected boolean hasEnded() {
        return lives <= 0;
    }

    @Override
    protected void endGame() {
        contPlay = false;
    }

    @Override
    public void update(float dt,float pos, float width){
        fps.log();
        g.update(pos, width);

        b1.update(dt);
        b2.update(dt);

        b1.updateTimers();
        b2.updateTimers();

        for(Bullet bu1: b1.getBullets()){
            bu1.update(dt);
        }
        for(Bullet bu2: b2.getBullets()){
            bu2.update(dt);
        }

        Iterator<Bullet> itr = bullets.iterator();
        boolean removed = false;
        while(itr.hasNext()){
            Bullet b = itr.next();
            Iterator<Bullet> itr2 = b1.getBullets().iterator();
            while(itr2.hasNext() && !removed){
                if(b.crash(itr2.next())){
                    p1Kills++;
                    itr2.remove();
                    removed = true;
                    itr.remove();
                }
            }
            itr2 = b2.getBullets().iterator();
            while(itr2.hasNext() && !removed){
                if(b.crash(itr2.next())){
                    p2Kills++;
                    itr2.remove();
                    removed = true;
                    itr.remove();
                }
            }
            if(!removed){
                b.update(dt);
                if(b.getPosition().x < pos- width/2){
                    lives--;
                    Level1Music.getInstance().setPitch(1 + (1 - lives/50));
                    itr.remove();
                }
            }
        }

        generateBullets(dt,pos);
        if(hasEnded()){
            endGame();
        }

        time+=dt;
    }

    private void generateBullets(float dt,float pos) {
        timeSinceLastBullet+= dt;

        if(Math.random()*10 < timeSinceLastBullet){
            bullets.add(new EnemyBullet(700 + pos,(int)(Math.random()*350 + 100),-100));
            timeSinceLastBullet = 0;
        }
    }
    public Bird getBLeft() {
        return b1;
    }

    public Bird getBRight() {
        return b2;
    }
}
