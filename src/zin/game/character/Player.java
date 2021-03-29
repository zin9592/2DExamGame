package zin.game.character;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import zin.game.effect.image.ImageType;
import zin.game.effect.object.Bullet;
import zin.game.effect.object.BulletType;
import zin.game.effect.object.Chest;
import zin.game.effect.object.DamageText;
import zin.game.effect.object.Hit;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.enemy.Enemy;
import zin.game.engine.BlockCheckType;
import zin.game.engine.PhysicsEngine;
import zin.game.item.Item;
import zin.game.log.Log;
import zin.game.map.Map;
import zin.game.ui.Aim;
import zin.game.ui.PlayerUI;
import zin.game.window.MainWindow;

/*
 * Player 클래스
 * 
 * 유저가 직접 조종할 캐릭터.
 * 
 */

public class Player {
	private static final Player player = new Player();
	// 캐릭터
	private double x, y, offsetX, offsetY;
	private Body body;
	private Hand hand;
	private Leg leg;
	private double degree;
	private static final int SIZE = 50;
	// 캐릭터 상태
	private Inventory inventory;
	private PlayerUI UI;
	private Status status;
	private Boolean alive;
	// 캐릭터 재생력
	private Timeline regenerationHealth, regenerationStemina;

	// 물리엔진
	private PhysicsEngine engine;

	// 생성자
	private Player() {
		Log.print(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x = screenSize.getWidth() * 0.5;
		y = screenSize.getHeight() * 0.5;
		offsetX = 0;
		offsetY = 0;
		body = Body.getInstance();
		hand = Hand.getInstance();
		leg = Leg.getInstance();
		body.createBody(x - SIZE * 0.5, y - SIZE * 0.5, SIZE);
		hand.createHand(x - SIZE * 0.5, y - SIZE * 0.5, SIZE);
		leg.createLeg(x - SIZE * 0.5, y - SIZE * 0.5, SIZE);
		degree = 0;
		body.setDegree(degree);
		hand.setDegree(degree);
		leg.setDegree(degree);

		inventory = Inventory.getInstance();
		UI = PlayerUI.getInstance();
		status = Status.getInstance();
		alive = true;

		double left, right, top, buttom;
		left = x - SIZE;
		right = x + SIZE;
		top = y - SIZE;
		buttom = y + SIZE;
		engine = new PhysicsEngine(left, right, top, buttom);
		drawPlayer();
		regeneration();
	}

	// 객체 호출
	public static Player getInstance() {
		return player;
	}

	// 캐릭터 이동
	public Boolean moveCharacter(double _x, double _y) {
		if (!engine.isBlocked(-_x, -_y, BlockCheckType.Tile)) {
			for (Rectangle tile : Map.getTiles()) {
				tile.setX(tile.getX() + _x);
				tile.setY(tile.getY() + _y);
			}
			for (Bullet bullet : Bullet.getAmmo()) {
				bullet.moveBullet(_x, _y);
			}
			for (Item item : Map.getItems()) {
				item.movedX(_x);
				item.movedY(_y);
			}
			for (Enemy enemy : Map.getEnemys()) {
				enemy.moveEnemy(_x, _y, true);
			}

			for (DamageText damageText : DamageText.getDamageTexts()) {
				Label label = damageText.getLabel();
				label.setLayoutX(label.getLayoutX() + _x);
				label.setLayoutY(label.getLayoutY() + _y);
			}
			for (Hit hit : Hit.getHits()) {
				hit.setX(_x);
				hit.setY(_y);
			}
			offsetX -= _x;
			offsetY -= _y;
			return true;
		}
		return false;
	}

	// 캐릭터가 바라보는 각도
	public void setDegree(double _x, double _y) {
		degree = Math.atan2(_y - y, _x - x) * 180 / Math.PI;
		body.setDegree(degree);
		hand.setDegree(degree);
		leg.setDegree(degree);
	}

	private void regeneration() {
		regenerationHealth = new Timeline(new KeyFrame(Duration.millis(5000), ev -> {
			if (!alive)
				regenerationHealth.stop();
			addHealth(-status.getHealthRegeneration());
		}));
		regenerationHealth.setCycleCount(Animation.INDEFINITE);
		regenerationHealth.play();

		regenerationStemina = new Timeline(new KeyFrame(Duration.millis(500), ev -> {
			if (!alive)
				regenerationHealth.stop();
			addStemina(-status.getSteminaRegeneration());
		}));
		regenerationStemina.setCycleCount(Animation.INDEFINITE);
		regenerationStemina.play();
	}

	// 상호작용
	public void activate() {
		// 아이템 줍기
		for (Item item : Map.getItems()) {
			double itemX = item.getX();
			double itemY = item.getY();
			double width = Item.SIZE;
			double height = Item.SIZE;
			double size = width < height ? height : width;
			if (itemX - 2 * size < x && x < itemX + 2 * size && itemY - 2 * size < y && y < itemY + 2 * size) {
				Inventory inventory = Inventory.getInstance();
				if (inventory.getItem(item)) {
					Map.getItems().remove(item);
					item.eraseFieldItem();
					Sound.Play(SoundType.Pickup, 0.8f, false);
				} else {
					item.drawFieldItem();
				}
				return;
			}
		}

		// 문열기 및 상자열기
		for (Rectangle tile : Map.getTiles()) {
			if (tile.getId() == "door") {
				double tileX = tile.getX();
				double tileY = tile.getY();
				double width = tile.getWidth();
				double height = tile.getHeight();
				double size = width < height ? height : width;
				if (tileX - size < x && x < tileX + 2 * size && tileY - size < y && y < tileY + 2 * size) {
					Sound.Play(SoundType.Door_Open, 1, false);
					Pane root = MainWindow.getPane();
					root.getChildren().remove(tile);
					Map.getTiles().remove(tile);
					return;
				}
			} else if (tile.getId() == "chest") {
				double tileX = tile.getX();
				double tileY = tile.getY();
				double size = tile.getWidth();
				if (tileX - size * 0.5 < x && x < tileX + 1.5 * size && tileY - size * 0.5 < y
						&& y < tileY + 1.5 * size) {
					Image img = new Image(ImageType.Open_Chest.getSRC());
					tile.setFill(new ImagePattern(img));
					Sound.Play(SoundType.Chest1, 1, false);
					for (Chest chest : Map.getChests()) {
						if (chest.equals(tile)) {
							tile.setId("opened_chest");
							chest.getItemDrop();
							return;
						}
					}
				}
			}
		}
	}

	// 인벤토리 열기
	public void openInventory() {
		inventory.showInventory();
	}

	// 공격
	public void attack(Aim aim) {
		if (hand.getWeapon() == null) {
			return;
		}
		if (hand.getWeapon().getCoolDown()) {
			hand.getWeapon().setCoolDown(false);
			Pane root = MainWindow.getPane();
			Bullet bullet = new Bullet(BulletType.Player, x, y, aim.getAimX(), aim.getAimY(), status.getDamage(),
					status.getCritical());
			Bullet.getAmmo().add(bullet);
			root.getChildren().add(bullet.getBullet());
		}
	}

	// 발사준비
	public Boolean isReady() {
		if (hand.getWeapon() == null) {
			return false;
		}
		return hand.getWeapon().getCoolDown();
	}

	// 플레이어 화면에 그리기
	private void drawPlayer() {
		Pane root = MainWindow.getPane();
		root.getChildren().add(leg.getShape());
		root.getChildren().add(hand.getShape());
		root.getChildren().add(body.getShape());
		root.getChildren().add(body.getHelmet());
		root.getChildren().add(hand.getWeaponShape());
		UI.drawUI();
	}

	// 플레이어 위치
	public double getX() {
		return body.getShape().getX();
	}

	public double getY() {
		return body.getShape().getY();
	}

	public double getShooterX() {
		return body.getShape().getX() + SIZE * 0.5;
	}

	public double getShooterY() {
		return body.getShape().getY() + SIZE * 0.5;
	}

	public double getOffsetX() {
		return offsetX;
	}

	public double getOffsetY() {
		return offsetY;
	}

	public Status getStatus() {
		return status;
	}

	public PhysicsEngine getEngine() {
		return engine;
	}

	// 피격당함
	public void getDamege(double damage) {
		Hit hit = new Hit(body.getShape().getX(), body.getShape().getY());
		hit.show();
		UI.setHealth(status, damage);
		Sound.Play(SoundType.Hit1, 0.8f, false);

		if (status.getHealth() <= 0) {
			// 캐릭터 사망
			alive = false;
			// 캐릭터 사망 시 화면 출력
			Stage dialog = new Stage(StageStyle.UNDECORATED);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(MainWindow.getStage());
			dialog.setTitle("캐릭터 사망 화면");
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../ui/deathUI.fxml"));
				Scene scene = new Scene(parent);
				dialog.setScene(scene);
				dialog.show();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// 회복
	public void addHealth(double health) {
		UI.setHealth(status, health);
	}

	public void addStemina(double stemina) {
		UI.setStemina(status, stemina);
	}

	public Boolean isAlive() {
		return alive;
	}
}
