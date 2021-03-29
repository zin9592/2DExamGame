package zin.game.enemy;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import zin.game.character.Player;
import zin.game.effect.image.ImageType;
import zin.game.effect.object.Bullet;
import zin.game.effect.object.BulletType;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.enemy.ai.TypeShooter;
import zin.game.engine.PhysicsEngine;
import zin.game.window.MainWindow;

/*
 * 
 * Enemy B 타입 클래스 
 * 
 * B 타입 적
 * AI : 원거리
 *   
 */

public class EnemyB extends Enemy {
	public EnemyB(double x, double y){
		enemyName = "좀비";
		size = 50;
		status = new Status(100, 15, 5000, 500, 3, 2);
		shape = new Rectangle(x  - size * 0.5, y  - size * 0.5, size, size);
		shape.setFill(new ImagePattern(ImageType.EnemyB.getImage()));
		left = shape.getX();
		right = shape.getX() + size;
		top = shape.getY();
		buttom = shape.getY() + size;
		engine = new PhysicsEngine(this, left, right, top, buttom);
		attackMotion = new ImageType[] {
				ImageType.EnemyB_Attack_1,
				ImageType.EnemyB_Attack_2,
				ImageType.EnemyB_Attack_3,
				ImageType.EnemyB_Attack_4,
				ImageType.EnemyB_Attack_5,
				ImageType.EnemyB_Attack_6,
				ImageType.EnemyB
		};
		AI = new TypeShooter(this);
		drawEnemy();
	}
	
	public void attack() {
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
		
		Sound.Play(SoundType.EnemyA_Attack, value, false);
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {
			shape.setFill(new ImagePattern(attackMotion[index++].getImage()));
			if(index > attackMotion.length - 1) {
				Pane root = MainWindow.getPane();
				Bullet bullet = new Bullet(BulletType.Enemy, shape.getX()+size * 0.5, shape.getY()+size * 0.5, player.getShooterX(), player.getShooterY(), status.getPower(), 0);
				Bullet.getAmmo().add(bullet);
				root.getChildren().add(bullet.getBullet());
				index = 0;
			}
		}));
		timeline.setCycleCount(attackMotion.length);
		timeline.play();
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
