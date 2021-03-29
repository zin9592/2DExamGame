package zin.game.ui;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import zin.game.character.Player;
import zin.game.effect.image.ImageType;
import zin.game.window.MainWindow;

/*
 * Aim 클래스
 * 
 * Player의 마우스를 표현한다.
 * 
 */

public class Aim { // 에임마우스커서
	private static final Aim aim = new Aim();
	private ImageView image;
	private double aim_x, aim_y; // 사격지점
	private Boolean fire;	//마우스 클릭여부

	private Aim() {
		image = new ImageView(ImageType.Crosshair.getSRC());
		image.setFitHeight(30);
		image.setFitWidth(30);
		fire = false;
		drawAim();
	}

	public void moveMouse(MouseEvent event) {
		Player player = Player.getInstance();
		image.setX(event.getX() - 15);
		image.setY(event.getY() - 15);
		player.setDegree(event.getX(), event.getY());
	}

	public void drawAim() {
		Pane root = MainWindow.getPane();
		root.getChildren().add(image);
	}

	public boolean isFire() {
		return fire;
	}

	public void disableFire() {
		fire = false;
	}	
	
	public double getAimX() {
		return aim_x;
	}

	public double getAimY() {
		return aim_y;
	}
	
	public static Aim getInstance() {
		return aim;
	}
	
	public void setPoint(MouseEvent _point) {
		aim_x = _point.getX();
		aim_y = _point.getY();
		fire = true;
	}
}
