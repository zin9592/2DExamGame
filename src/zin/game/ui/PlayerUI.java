package zin.game.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import zin.game.character.Body;
import zin.game.character.Status;
import zin.game.effect.image.ImageType;
import zin.game.effect.object.DamageText;
import zin.game.log.Log;
import zin.game.window.MainWindow;

/*
 * PlayerUI Ŭ����
 * 
 * Player Ŭ������ ���� Ŭ�����̴�.
 * 
 * �÷��̾��� ü�»��³� ��Ÿ ���µ��� ȭ�鿡 ǥ���ϴ� Ŭ����
 * 
 */

public class PlayerUI {
	private static final PlayerUI UI = new PlayerUI();
	// health Shape
	private double x1, x2, y;
	private Shape healthShape, steminaShape;
	private Rectangle status, healthBar, maxHealthBar, steminaBar, maxSteminaBar;

	private static final int SIZE = 100;

	// ������
	private PlayerUI() {
		Log.print(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x1 = 10;
		x2 = screenSize.getWidth() - SIZE - x1;
		y = screenSize.getSize().getHeight() - 20 - SIZE;
		status = new Rectangle(0, screenSize.getHeight() - 2 * SIZE, screenSize.getWidth(), 2 * SIZE);
		status.setFill(new ImagePattern(ImageType.Status.getImage()));
		healthBar = new Rectangle(x1, y, SIZE, SIZE);
		maxHealthBar = new Rectangle(x1, y, SIZE, SIZE);
		maxHealthBar.setFill(new ImagePattern(ImageType.Emety.getImage()));
		steminaBar = new Rectangle(x2, y, SIZE, SIZE);
		maxSteminaBar = new Rectangle(x2, y, SIZE, SIZE);
		maxSteminaBar.setFill(new ImagePattern(ImageType.Emety.getImage()));
	}

	// ��üȣ��
	public static PlayerUI getInstance() {
		return UI;
	}

	// UI ȭ�鿡 �׸���
	public void drawUI() {
		Pane root = MainWindow.getPane();
		root.getChildren().add(status);
		root.getChildren().add(maxHealthBar);
		root.getChildren().add(maxSteminaBar);
		drawHealthBar();
		drawSteminaBar();
	}

	// ü�»��� ����
	public void setHealth(Status status, double health) {
		if (status.getHealth() - health > status.getMaxHealth()) {
			status.setHealth(status.getMaxHealth());
		} else {
			status.setHealth(status.getHealth() - health);
		}
		Body body = Body.getInstance();
		DamageText damageText = new DamageText(health, false);
		damageText.show(body.getShape().getX(), body.getShape().getY());
		healthBar.setY(y + (100 - (double) status.getHealth() / (double) status.getMaxHealth() * 100));
		healthBar.setHeight(status.getHealth());
		drawHealthBar();
	}

	// ���׹̳����� ����
	public void setStemina(Status status, double stemina) {
		if (status.getStemina() - stemina > status.getMaxStemina()) {
			status.setStemina(status.getMaxStemina());
		} else {
			status.setStemina(status.getStemina() - stemina);
		}
		steminaBar.setY(y + (100 - (double) status.getStemina() / (double) status.getMaxStemina() * 100));
		steminaBar.setHeight(status.getStemina());
		drawSteminaBar();
	}

	// �ٸ� ������Ʈ(��, �� ���) �ڷ� ���°� ����
	public void frontHealthBar() {
		maxHealthBar.toFront();
		healthBar.toFront();
		healthShape.toFront();
		maxSteminaBar.toFront();
		steminaBar.toFront();
		steminaShape.toFront();
		status.toFront();
	}

	private void drawHealthBar() {
		Pane root = MainWindow.getPane();
		root.getChildren().remove(healthShape);
		healthShape = Shape.intersect(healthBar, maxHealthBar);
		healthShape.setFill(new ImagePattern(ImageType.Health.getImage(), x1, y, SIZE, SIZE, false));
		root.getChildren().add(healthShape);
	}

	private void drawSteminaBar() {
		Pane root = MainWindow.getPane();
		root.getChildren().remove(steminaShape);
		steminaShape = Shape.intersect(steminaBar, maxSteminaBar);
		steminaShape.setFill(new ImagePattern(ImageType.Stemina.getImage(), x2, y, SIZE, SIZE, false));
		root.getChildren().add(steminaShape);
	}
}
