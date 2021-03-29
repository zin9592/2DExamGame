package zin.game.effect.object;

import java.util.HashSet;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import zin.game.character.Player;
import zin.game.effect.image.ImageType;
import zin.game.enemy.Enemy;
import zin.game.engine.BlockCheckType;
import zin.game.engine.PhysicsEngine;
import zin.game.window.MainWindow;

/*
 * Bullet Ŭ����
 * 
 * �÷��̾� �Ѿ��� �����ϴ� Ŭ����
 * �Ѿ��� �߻�Ǵ� ��ġ���� �ӵ� ������� �����Ѵ�.
 * 
 */

public class Bullet {
	private static HashSet<Bullet> ammo = new HashSet<Bullet>(); // Bullets

	private BulletType type; // ���� �Ѿ��� �߻��ߴ°�?
	private double x, y; // Shooting Point
	private double aimX, aimY; // Arrival Point
	private double damage, critical;

	private double speedX, speedY; // Bullet Speed
	private int speed_balance; // Bullet Speed Balance
	private final int DIVISION_SPEED = 15; // Bullet Speed Division

	private double degree; // Move Bullet Degree

	// Bullet Image
	private ImageView bullet;

	// Bullet HitBox
	private final int SIZE = 10; // Bullet Size
	private final int HIT_BOX = 7; // Bullet Hit Box
	private PhysicsEngine engine;
	private double left, right, top, buttom; // Bullet Hit Box Point

	public Bullet(BulletType _type, double _x, double _y, double _aimX, double _aimY, double _damage,
			double _critical) {
		type = _type;
		x = _x;
		y = _y;
		aimX = _aimX;
		aimY = _aimY;
		damage = _damage;
		critical = _critical;
		left = x - SIZE;
		right = x + SIZE;
		top = y - SIZE + HIT_BOX;
		buttom = y + SIZE - HIT_BOX;
		engine = new PhysicsEngine(left, right, top, buttom);
		degree = getDegree();
		int biggerPoint = getBiggerPoint();
		speed_balance = (biggerPoint > DIVISION_SPEED) ? biggerPoint / DIVISION_SPEED : 1;
		speedX = (aimX - x) / speed_balance;
		speedY = (aimY - y) / speed_balance;
		bullet = new ImageView(ImageType.Bullet.getSRC());
		bullet.setFitHeight(2 * SIZE);
		bullet.setFitWidth(2 * SIZE);
		bullet.setRotate(degree);
		bullet.setX(x - SIZE);
		bullet.setY(y - SIZE);

	}

	public static HashSet<Bullet> getAmmo() {
		return ammo;
	}

	// �Ѿ��� ���ư��� ����
	private double getDegree() {
		return Math.atan2(aimY - y, aimX - x) * 180 / Math.PI;
	}

	// �Ѿ� �ӵ������� �� ū ���� �������� ����
	private int getBiggerPoint() {
		return (int) ((Math.abs(aimX - x) > Math.abs(aimY - y)) ? Math.abs(aimX - x) : Math.abs(aimY - y));
	}

	// �Ѿ� �̵�
	private void moveBullet() {
		x += speedX;
		y += speedY;
		bullet.setX(x - SIZE);
		bullet.setY(y - SIZE);
		left = x - SIZE;
		right = x + SIZE;
		top = y - SIZE + HIT_BOX;
		buttom = y + SIZE - HIT_BOX;
		engine.setHitBox(left, right, top, buttom);
	}

	// ĳ���� �̵��� �Ѿ��̵�
	public void moveBullet(double _x, double _y) {
		x += _x;
		y += _y;
		bullet.setX(x - SIZE);
		bullet.setY(y - SIZE);
		aimX += _x;
		aimY += _y;
		left = x - SIZE;
		right = x + SIZE;
		top = y - SIZE + HIT_BOX;
		buttom = y + SIZE - HIT_BOX;
		engine.setHitBox(left, right, top, buttom);
	}

	// �Ѿ��� ��ǥ��ġ�� �����ߴ°�?
	public boolean isBulletArrival() {
		if (speedX > 0 && x > aimX) {
			x = aimX;
			return true;
		} else if (speedX < 0 && x < aimX) {
			x = aimX;
			return true;
		}
		if (speedY > 0 && y > aimY) {
			y = aimY;
			return true;
		} else if (speedY < 0 && y < aimY) {
			y = aimY;
			return true;
		}
		return false;
	}

	// �Ѿ� �׸���
	public Boolean drawBullet() {
		Pane root = MainWindow.getPane();
		moveBullet();
		if (type == BulletType.Player) {
			Enemy enemy = engine.isHit(speedX, speedY);
			if (enemy != null) {
				root.getChildren().remove(bullet);
				enemy.getDamege(damage, critical);
				return true;
			}
		}else if(type == BulletType.Enemy) {
			if(engine.isPlayerHit(speedX, speedY)) {
				Player player = Player.getInstance();
				root.getChildren().remove(bullet);
				player.getDamege(damage);
				return true;
			}
		}
		if (engine.isBlocked(speedX, speedY, BlockCheckType.Tile)) { // �Ѿ��� ���� ������ ��
			root.getChildren().remove(bullet);
			return true;
		}
		if (isBulletArrival()) { // �Ѿ��� �������� �����Ͽ��� ��
			root.getChildren().remove(bullet);
			return true;
		}
		if (speedX == 0 || speedY == 0) { // �Ѿ��� �ڽſ��� �� �Ȼ������ ����
			root.getChildren().remove(bullet);
			return true;
		}
		return false;
	}

	public ImageView getBullet() {
		return bullet;
	}
}
