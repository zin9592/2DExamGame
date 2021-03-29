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
 * Bullet 클래스
 * 
 * 플레이어 총알을 관리하는 클래스
 * 총알의 발사되는 위치부터 속도 방향등을 조절한다.
 * 
 */

public class Bullet {
	private static HashSet<Bullet> ammo = new HashSet<Bullet>(); // Bullets

	private BulletType type; // 누가 총알을 발사했는가?
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

	// 총알의 날아가는 각도
	private double getDegree() {
		return Math.atan2(aimY - y, aimX - x) * 180 / Math.PI;
	}

	// 총알 속도조정할 때 큰 수를 기준으로 조정
	private int getBiggerPoint() {
		return (int) ((Math.abs(aimX - x) > Math.abs(aimY - y)) ? Math.abs(aimX - x) : Math.abs(aimY - y));
	}

	// 총알 이동
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

	// 캐릭터 이동시 총알이동
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

	// 총알이 목표위치에 도착했는가?
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

	// 총알 그리기
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
		if (engine.isBlocked(speedX, speedY, BlockCheckType.Tile)) { // 총알이 벽에 막혔을 때
			root.getChildren().remove(bullet);
			return true;
		}
		if (isBulletArrival()) { // 총알이 목적지에 도착하였을 때
			root.getChildren().remove(bullet);
			return true;
		}
		if (speedX == 0 || speedY == 0) { // 총알이 자신에게 쏠때 안사라지는 버그
			root.getChildren().remove(bullet);
			return true;
		}
		return false;
	}

	public ImageView getBullet() {
		return bullet;
	}
}
