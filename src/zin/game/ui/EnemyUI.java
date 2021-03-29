package zin.game.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import zin.game.effect.image.ImageType;
import zin.game.effect.object.DamageText;
import zin.game.enemy.Enemy;
import zin.game.enemy.Status;
import zin.game.log.Log;
import zin.game.window.MainWindow;

/*
 * EnemyUI Ŭ����
 * 
 * ���� ����â�� ��Ÿ���� Ŭ����
 * 
 * �ַ� ���� Ÿ���Ͽ��� �� ȭ�� �� ������� ��Ÿ����.
 * 
 */

public class EnemyUI {
	private static final EnemyUI UI = new EnemyUI();
	// ȭ�鿡 ǥ�õ� �̹���
	private double x, y;
	private Shape shape;
	private Rectangle healthBar, maxHealthBar;
	private Label enemyTitle;
	
	private static final int SIZE = 30;

	private EnemyUI() {
		Log.print(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x = screenSize.getWidth() * 0.5;
		y = SIZE;
		healthBar = new Rectangle(x - SIZE * 0.5, 2 * y, 5 * SIZE, SIZE);
		maxHealthBar = new Rectangle(x - SIZE * 0.5, 2 * y, 5 * SIZE, SIZE);
		maxHealthBar.setFill(new ImagePattern(ImageType.Enemy_Health_Emety.getImage()));
		enemyTitle = new Label();
		enemyTitle.setPrefWidth(5 * SIZE);
		enemyTitle.setPrefHeight(SIZE);
		enemyTitle.setAlignment(Pos.BASELINE_CENTER);
		enemyTitle.setFont(Font.font("HYtbrB",FontWeight.BOLD, SIZE - 5));
		enemyTitle.setStyle("-fx-font-weight: bold");
		enemyTitle.setLayoutX(x - SIZE * 0.5);
		enemyTitle.setLayoutY(y);
		Pane root = MainWindow.getPane();
		root.getChildren().add(enemyTitle);
	}

	// ��üȣ��
	public static EnemyUI getInstance() {
		return UI;
	}

	// ü�»��� ����
	public void setHealth(Enemy enemy, Status status, double health, String enemyName, Boolean isCritical) {
		DamageText damageText = new DamageText(health, isCritical);
		damageText.show(enemy.getShape().getX(), enemy.getShape().getY());
		status.setHealth(status.getHealth() - health);
		healthBar.setWidth(maxHealthBar.getWidth() * ((double) status.getHealth() / (double) status.getMaxHealth()));
		drawHealthBar(enemyName);
	}

	// �ٸ� ������Ʈ(��, �� ���) �ڷ� ���°� ����
	public void frontHealthBar() {
		healthBar.toFront();
		shape.toFront();
	}

	private void drawHealthBar(String enemyName) {
		Pane root = MainWindow.getPane();
		root.getChildren().remove(shape);
		root.getChildren().remove(maxHealthBar);
		enemyTitle.setText(enemyName);
		root.getChildren().add(maxHealthBar);
		shape = Shape.intersect(healthBar, maxHealthBar);
		shape.setFill(new ImagePattern(ImageType.Enemy_Health.getImage(), x - SIZE * 0.5, 2 * y, 5 * SIZE, SIZE, false));
		root.getChildren().add(shape);
	}

	public static EnemyUI getUi() {
		return UI;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Shape getShape() {
		return shape;
	}

	public Rectangle getHealthBar() {
		return healthBar;
	}

	public Rectangle getMaxHealthBar() {
		return maxHealthBar;
	}

	public Label getEnemyTitle() {
		return enemyTitle;
	}

	public static int getSize() {
		return SIZE;
	}
}
