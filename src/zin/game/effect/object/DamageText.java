package zin.game.effect.object;

import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import zin.game.window.MainWindow;

/*
 * DamageText Ŭ����
 * 
 * ĳ���ͳ� ���� �޴� ������, ũ��Ƽ��, ȸ������
 * �ؽ�Ʈ�� ǥ���Ѵ�.
 */

public class DamageText {
	private static LinkedList<DamageText> damageTexts = new LinkedList<DamageText>();
	private static final int SIZE = 30;
	private Label damageText;
	private double health;
	private Boolean isCritical;
	
	public DamageText(double health, Boolean isCritical){
		this.health = health;
		this.isCritical = isCritical;
	}

	public Label getLabel() {
		return damageText;
	}

	public void show(double x, double y) {
		// TODO Auto-generated method stub
		Pane root = MainWindow.getPane();
		damageText = new Label(String.valueOf(Math.abs(health)));
		if(health > 0) {
			// �ǰݵ����� ǥ�� ���, ũ��Ƽ�� �� ����ٸ�
			if(isCritical) {
				damageText.setTextFill(Color.ORANGE);
			}
		}else if(health < 0) {
			// ȸ�� ǥ�� �ʷϻ�
			damageText.setTextFill(Color.LIGHTGREEN);
		}
		
		damageText.setPrefWidth(2 * SIZE);
		damageText.setPrefHeight(SIZE);
		damageText.setAlignment(Pos.BASELINE_CENTER);
		damageText.setFont(Font.font("HYtbrB",FontWeight.BOLD, 15));
		damageText.setLayoutX(x);
		damageText.setLayoutY(y);
		damageTexts.add(this);
		root.getChildren().add(damageText);
		
		// �ؽ�Ʈ�� �������� ������
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {
			damageText.setLayoutY(damageText.getLayoutY() - 3);
		}));
		timeline.setCycleCount(10);
		timeline.play();
		
		// �ؽ�Ʈ�� �������� �������� ������ ����
		Timeline delTimeline = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
			root.getChildren().remove(damageText);
			damageTexts.remove(this);
		}));
		delTimeline.setCycleCount(1);
		delTimeline.play();
	}
	
	public static LinkedList<DamageText> getDamageTexts() {
		return damageTexts;
	}
}
