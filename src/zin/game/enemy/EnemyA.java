package zin.game.enemy;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.character.Player;
import zin.game.effect.image.ImageType;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.enemy.ai.TypeWarrior;
import zin.game.engine.PhysicsEngine;

/*
 * 
 * Enemy A 타입 클래스 
 * 
 * A 타입 적
 * AI : 근접
 * 
 */

public class EnemyA extends Enemy {
	public EnemyA(double x, double y){
		enemyName = "좀비";
		size = 50;
		status = new Status(150, 10, 5000, 50, 1, 2);
		shape = new Rectangle(x  - size * 0.5, y  - size * 0.5, size, size);
		shape.setFill(new ImagePattern(ImageType.EnemyA.getImage()));
		left = shape.getX();
		right = shape.getX() + size;
		top = shape.getY();
		buttom = shape.getY() + size;
		engine = new PhysicsEngine(this, left, right, top, buttom);
		attackMotion = new ImageType[] {
				ImageType.EnemyA_Attack_1,
				ImageType.EnemyA_Attack_2,
				ImageType.EnemyA_Attack_3,
				ImageType.EnemyA
		};
		AI = new TypeWarrior(this);
		drawEnemy();
	}
	public void attack() {
		Player player = Player.getInstance();
		player.getDamege(this.getStatus().getPower());
		Sound.Play(SoundType.EnemyA_Attack, 1, false);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(index < attackMotion.length) {
					shape.setFill(new ImagePattern(attackMotion[index++].getImage()));
				}else{
					index = 0;
					timer.cancel();
				}
			}
		};
		timer.schedule(task, 0, 100);
	}
	
	public void talk() {
		if(talk && detected) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					talk = true;
				}
			};
			Player player = Player.getInstance();
			double enemyX = shape.getX();
			double enemyY = shape.getY();
			double playerX = player.getX();
			double playerY = player.getY();
			double distance = getDistance(playerX, enemyX, playerY, enemyY);
			float range = 2500;
			if(distance >= range) {
				distance = range - 1;
			}
			float value = (float) (range - distance) / range;
			
			Random rand = new Random();
			switch(rand.nextInt(3)) {
			case 0:
				Sound.Play(SoundType.EnemyA_Sound1, value, false);
				break;
			case 1:
				Sound.Play(SoundType.EnemyA_Sound2, value, false);
				break;
			case 2:
				Sound.Play(SoundType.EnemyA_Sound3, value, false);
				break;
			}
			timer.schedule(task, rand.nextInt(5000)+5000);
			talk = false;
		}
	}
}
