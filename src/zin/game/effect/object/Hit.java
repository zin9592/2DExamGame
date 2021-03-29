package zin.game.effect.object;

import java.util.LinkedList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import zin.game.effect.image.ImageType;
import zin.game.window.MainWindow;

/*
 * Hit 클래스
 * 
 * 플레이어나 적이 피격받았을 때 히트 이펙트를 출력한다.
 */

public class Hit {
	private static LinkedList<Hit> hits = new LinkedList<Hit>();
	private Rectangle shape;
	// 이미지 자르기
	private static final double CUT_SIZE = 64;
	private static final double INDEX = 4;
	private int i, j;

	public Hit(double x, double y) {
		shape = new Rectangle(x, y, CUT_SIZE, CUT_SIZE);
		i = 0;
		j = 0;
	}

	public void show() {
		Pane root = MainWindow.getPane();
		double animatedDuration = 30;
		int animatedFrame = 16;
		
		shape.setFill(new ImagePattern(ImageType.Hit.getImage(), shape.getX(), shape.getY(), CUT_SIZE * INDEX, CUT_SIZE * INDEX, false));
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(animatedDuration), ev -> {
			shape.setFill(new ImagePattern(ImageType.Hit.getImage(), -CUT_SIZE * i + shape.getX(),
					-CUT_SIZE * j + shape.getY(), CUT_SIZE * INDEX, CUT_SIZE * INDEX, false));
			i++;
			if (i == INDEX) {
				i = 0;
				j++;
			}
		}));
		timeline.setCycleCount(animatedFrame);
		timeline.play();

		Timeline delTimeline = new Timeline(new KeyFrame(Duration.millis(animatedDuration * animatedFrame), ev -> {
			root.getChildren().remove(shape);
			hits.remove(this);
		}));
		delTimeline.play();

		root.getChildren().add(shape);
		hits.add(this);
	}

	public static LinkedList<Hit> getHits() {
		return hits;
	}

	public void setX(double x) {
		shape.setX(shape.getX() + x);
	}

	public void setY(double y) {
		shape.setY(shape.getY() + y);
	}
}
