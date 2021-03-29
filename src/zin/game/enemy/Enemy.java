package zin.game.enemy;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import zin.game.effect.image.ImageType;
import zin.game.effect.object.Hit;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.enemy.ai.AI;
import zin.game.engine.BlockCheckType;
import zin.game.engine.PhysicsEngine;
import zin.game.map.Map;
import zin.game.ui.EnemyUI;
import zin.game.window.MainWindow;

/*
 * 
 * Enemy 클래스 
 * 
 * 적 개체를 만드는 부모 클래스
 * 
 */

public abstract class Enemy {
	protected static final double DETECTED_RANGE = 1.5;
	
	// 인공지능
	protected AI AI;

	// 적, 능력치
	protected Rectangle shape;
	protected String enemyName;
	protected Status status;
	protected double size;
	protected ImageType[] attackMotion; 
	protected int index;
	protected Boolean talk, detected;
	
	// 물리엔진
	protected PhysicsEngine engine;
	protected double left, right, top, buttom;

	public Enemy() {
		talk = true;
		detected = false;
		index = 0;
	}

	// 적 그리기
	protected void drawEnemy() {
		Pane root = MainWindow.getPane();
		root.getChildren().add(shape);
	}

	// 적이 바라보는 방향
	public void setDegree(double x, double y) {
		shape.setRotate(Math.atan2(y - shape.getY(), x - shape.getX()) * 180 / Math.PI);
	}
	
	// 적 이동
	public void moveEnemy(double _x, double _y, Boolean playerMoved) {
		if (playerMoved) {
			shape.setX(shape.getX() + _x);
			shape.setY(shape.getY() + _y);
			left += _x;
			right += _x;
			top += _y;
			buttom += _y;
			engine.setHitBox(left, right, top, buttom);
		}else{
			shape.setX(shape.getX() + _x);
			shape.setY(shape.getY() + _y);
			left += _x;
			right += _x;
			top += _y;
			buttom += _y;
			engine.setHitBox(left, right, top, buttom);
		}
	}
	
	// 충돌확인 true면 충돌안함, false면 충돌
	public Boolean isBlocked(double _x, double _y) {
		if(!engine.isBlocked(_x, _y,BlockCheckType.Tile) && !engine.isBlocked(_x, _y,BlockCheckType.Enemy)) {
			return true;
		}else {
			return false;
		}
	}

	public Rectangle getShape() {
		return shape;
	}

	public Status getStatus() {
		return status;
	}

	// 피격당함
	public void getDamege(double damage, double critical) {
		Random rand = new Random();
		double criChance = rand.nextInt(100);
		Boolean isCritical = false;
		if(criChance < critical) {
			Hit hit = new Hit(shape.getX(), shape.getY());
			hit.show();
			damage = damage * 1.5;
			isCritical = true;
			switch(rand.nextInt(3)) {
			case 0:
				Sound.Play(SoundType.Hit1, 1, false);
				break;
			case 1:
				Sound.Play(SoundType.Hit2, 1, false);
				break;
			case 2:
				Sound.Play(SoundType.Hit3, 1, false);
				break;
			}
		}
		
		EnemyUI UI = EnemyUI.getInstance();
		UI.setHealth(this, status, damage, enemyName, isCritical);
		if(!detected) {
			detected = true;
		}
		// 죽음
		if (status.getHealth() <= 0) {
			Pane root = MainWindow.getPane();
			root.getChildren().remove(shape);
			Map.getEnemys().remove(this);
		}
	}

	public void update() {
		AI.update();
	}

	public double getLeft() {
		return left;
	}

	public double getRight() {
		return right;
	}

	public double getTop() {
		return top;
	}

	public double getButtom() {
		return buttom;
	}
	
	public abstract void attack();
	
	public abstract void talk();
	
	public Boolean isDetected() {
		if(detected) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setDetected(Boolean _detected) {
		detected = _detected;
	}
	
	public String getName() {
		return enemyName;
	}
	
	public double getDetectedRange() {
		return DETECTED_RANGE;
	}
	
	// 적과 플레이어 간 거리측정
	public double getDistance(double x1, double x2, double y1, double y2) {
		return Math.pow((Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)), 0.5);
	}
}
