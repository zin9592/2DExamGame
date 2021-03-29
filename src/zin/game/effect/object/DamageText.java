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
 * DamageText 클래스
 * 
 * 캐릭터나 적이 받는 데미지, 크리티컬, 회복량을
 * 텍스트로 표현한다.
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
			// 피격데미지 표시 흰색, 크리티컬 시 색깔다름
			if(isCritical) {
				damageText.setTextFill(Color.ORANGE);
			}
		}else if(health < 0) {
			// 회복 표시 초록색
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
		
		// 텍스트의 역동적인 움직임
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {
			damageText.setLayoutY(damageText.getLayoutY() - 3);
		}));
		timeline.setCycleCount(10);
		timeline.play();
		
		// 텍스트의 역동적인 움직임이 끝나면 삭제
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
