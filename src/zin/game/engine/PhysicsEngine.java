package zin.game.engine;

import javafx.scene.shape.Rectangle;
import zin.game.character.Player;
import zin.game.enemy.Enemy;
import zin.game.map.Map;

/*
 * PhysicsEngineŬ����
 * 
 * �ܼ��� �浹Ȯ�� Ŭ����
 * 
 * �÷��̾ ���� ������ �� �浹������ ������Ʈ�� ������
 * �ش�������� �������� ���� ����
 */

public class PhysicsEngine {
	private Enemy enemy;
	private double left, right, top, buttom;
	
	public PhysicsEngine(double _left, double _right, double _top, double _buttom) {
		left = _left;
		right = _right;
		top = _top;
		buttom = _buttom;
	}
	
	public PhysicsEngine(Enemy _enemy, double _left, double _right, double _top, double _buttom) {
		enemy = _enemy;
		left = _left;
		right = _right;
		top = _top;
		buttom = _buttom;
	}

	public void setHitBox(double _left, double _right, double _top, double _buttom) {
		left = _left;
		right = _right;
		top = _top;
		buttom = _buttom;
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

	
	// �Ѿ��� ������ �ε�����?
	public Enemy isHit(double x, double y) {
		for(Enemy enemy : Map.getEnemys()) {
			double enemyLeft = enemy.getLeft();
			double enemyRight = enemy.getRight();
			double enemyTop = enemy.getTop();
			double enemyButtom = enemy.getButtom();

			if ((right - x > enemyLeft) && (left - x < enemyRight) && (top - y < enemyButtom)
					&& (buttom - y > enemyTop)) {
				return enemy;
			}
		}
		return null;
	}
	
	// �Ѿ��� ������ �ε�����?
	public Boolean isPlayerHit(double x, double y) {
		Player player = Player.getInstance();
		PhysicsEngine engine = player.getEngine();
		double playerLeft, playerRight, playerTop, playerButtom;
		playerLeft = engine.getLeft();
		playerRight = engine.getRight();
		playerTop = engine.getTop();
		playerButtom = engine.getButtom();
		
		if ((right - x > playerLeft) && (left - x < playerRight) && (top - y < playerButtom)
				&& (buttom - y > playerTop)) {
			return true;
		}
		return false;
	}
	
	// ������ �浹 true�� �浹, false�� ����
	public Boolean isBlocked(double x, double y, BlockCheckType type) {
		switch(type) {
		case Tile:
			for (Rectangle tile : Map.getTiles()) {
				if (tile.getId() == "chest" || tile.getId() == "opened_chest")
					continue;
				double tileLeft = tile.getX();
				double tileRight = tile.getX() + tile.getWidth();
				double tileTop = tile.getY();
				double tileButtom = tile.getY() + tile.getHeight();
				if ((right + x > tileLeft) && (left + x < tileRight) && (top + y < tileButtom) && (buttom + y > tileTop)) {
					return true;
				}
			}
			break;
			
		case Enemy:
			for (Enemy enemy : Map.getEnemys()) {
				if (this.enemy.equals(enemy)) {
					continue;
				}
				double enemyLeft = enemy.getLeft();
				double enemyRight = enemy.getRight();
				double enemyTop = enemy.getTop();
				double enemyButtom = enemy.getButtom();
				if ((right + x > enemyLeft) && (left + x < enemyRight) && (top + y < enemyButtom)
						&& (buttom + y > enemyTop)) {
					return true;
				}
			}
			break;
		}
		return false;
	}
	

}
