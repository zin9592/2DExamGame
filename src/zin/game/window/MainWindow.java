package zin.game.window;

import java.util.HashSet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import zin.game.character.Player;
import zin.game.effect.object.Bullet;
import zin.game.enemy.Enemy;
import zin.game.log.Log;
import zin.game.map.Map;
import zin.game.ui.MainListener;
import zin.game.ui.PlayerUI;

/*
 * MainWindow Ŭ����
 * 
 * ȭ���� ������ ��Ÿ���� Ŭ�����̴�.
 * 
 * ���⼭ ���� ��ҵ��� ǥ�õȴ�.
 * 
 */

public class MainWindow extends Application {
	private static Pane root;
	private static Scene scene;
	private static Stage stage;
	private MainListener control;
	private Map map;
	@SuppressWarnings("unused")
	private Player player;
	AnimationTimer timer;
	
	@Override
	public void init() {
		Log.print(this);
		root = new Pane();
		scene = new Scene(root);
		player = Player.getInstance();
		map = new Map();
		map.moveCharacter();
		control = new MainListener(scene);
	}

	private void update() {
		// �ൿ
		control.activate();
		//
		for(Enemy e : Map.getEnemys()) {
			e.update();
			PlayerUI UI = PlayerUI.getInstance();
			UI.frontHealthBar();
		}
		// �׸���
		HashSet<Bullet> removeAmmo = new HashSet<Bullet>();
		for (Bullet b : Bullet.getAmmo()) {
			if(b.drawBullet()) {
				removeAmmo.add(b);
			}
			if (b.isBulletArrival()) {
				removeAmmo.add(b);
			}
		}
		Bullet.getAmmo().removeAll(removeAmmo);
	
	}

	@Override
	public void start(Stage _stage) throws Exception {
		stage = _stage;
		timer = new AnimationTimer() { // ȭ���� ��ҵ��� ������
			@Override
			public void handle(long now) {
				update();
				Player player = Player.getInstance();
				if(!player.isAlive()) {
					timer.stop();
				}
			}
		};
		timer.start();
		root.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,CornerRadii.EMPTY,Insets.EMPTY)));
		scene.setCursor(Cursor.NONE);
		stage.setX(0);
		stage.setY(0);
		stage.setScene(scene);
		stage.setTitle("2D Game");
		stage.setResizable(false);
		stage.setMaximized(true);
		stage.show();
	}

	@Override
	public void stop() {
		System.exit(0);
	}

	public static Stage getStage() {
		return stage;
	}

	public static Scene getScene() {
		return scene;
	}

	public static Pane getPane() {
		return root;
	}

	public static void WindowFocused() {
		stage.requestFocus();
	}
}
