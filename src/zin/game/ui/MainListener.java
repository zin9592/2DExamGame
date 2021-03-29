package zin.game.ui;

import java.util.HashSet;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import zin.game.character.Leg;
import zin.game.character.Player;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.log.Log;

/*
 * MainListener 클래스
 * 
 * 각종 조작 및 움직임의 리스너
 * 
 */

public class MainListener {
	private HashSet<KeyCode> pressed; // 현재 누른 키 모음
	private static Scene scene; // 메인화면
	private Player player; // 플레이어
	private Aim aim; // 크로스헤드

	public MainListener(Scene _scene) {
		Log.print(this);
		pressed = new HashSet<KeyCode>();
		scene = _scene;
		player = Player.getInstance();
		aim = Aim.getInstance();
		setListener();
	}

	// 키를 누를 시 키 모음집에 추가가 된다.
	private synchronized void KeyPressed(KeyEvent event) {
		pressed.add(event.getCode());
	}

	// 반대로 키를 뗄 시 키 모음집에 제거가 된다.
	private synchronized void KeyReleased(KeyEvent event) {
		pressed.remove(event.getCode());
	}

	// 크로스헤어의 움직임
	private synchronized void MouseMoved(MouseEvent event) {
		aim.moveMouse(event);
	}

	private synchronized void MousePressed(MouseEvent event) {
		if(event.getButton() == MouseButton.PRIMARY) {
			aim.setPoint(event);
		}
	}

	// 각종 리스너 장착
	private void setListener() {
		scene.setOnKeyPressed(event -> {
			KeyPressed(event);
		});
		scene.setOnKeyReleased(event -> {
			KeyReleased(event);
		});
		scene.setOnMouseMoved(event -> {
			MouseMoved(event);
		});
		scene.setOnMouseDragged(event -> {
			MousePressed(event);
			MouseMoved(event);
		});
		scene.setOnMousePressed(event -> {
			MousePressed(event);
		});
		scene.setOnMouseReleased(event -> {
			aim.disableFire();
		});
	}

	private Boolean move(double run) {
		Boolean isMoved = false;
		double playerSpeed = player.getStatus().getMoveSpeed() * run;
		if (pressed.contains(KeyCode.W)) {
			for (double i = playerSpeed; i > 0; i--) {
				if (player.moveCharacter(0, i)) {
					isMoved = true;
					break;
				}
			}
		}
		if (pressed.contains(KeyCode.S)) {
			for (double i = playerSpeed; i > 0; i--) {
				if (player.moveCharacter(0, -i)) {
					isMoved = true;
					break;
				}
			}
		}
		if (pressed.contains(KeyCode.A)) {
			for (double i = playerSpeed; i > 0; i--) {
				if (player.moveCharacter(i, 0)) {
					isMoved = true;
					break;
				}
			}
		}
		if (pressed.contains(KeyCode.D)) {
			for (double i = playerSpeed; i > 0; i--) {
				if (player.moveCharacter(-i, 0)) {
					isMoved = true;
					break;
				}
			}
		}
		return isMoved;
	}
	
	// 키 입력에 따른 동작부분
	public void activate() {
		legActivate();

		// 움직임
		if (pressed.contains(KeyCode.SHIFT) && player.getStatus().getStemina() > 0) {
			if(move(1.2)) {
				player.addStemina(1);
			}
		}else if(pressed.contains(KeyCode.SHIFT) && player.getStatus().getStemina() == 0){
			move(1);
		}else {
			move(1);
		}
		
		// 발사속도 딜레이
		if (aim.isFire() && player.isReady()) {
			player.attack(aim);
		}
		if (pressed.contains(KeyCode.F)) { // 상호작용
			player.activate();
			pressed.remove(KeyCode.F);
			aim.disableFire();
		}
		if (pressed.contains(KeyCode.E)) { // 인벤토리
			player.openInventory();
			Sound.Play(SoundType.Inventory_Open, 0.8f, false);
			pressed.remove(KeyCode.E);
			aim.disableFire();
		}
		if (pressed.contains(KeyCode.ESCAPE)) {
			System.out.println("Exit >>> Game Exited");
			System.exit(0);
		}
	}

	private void legActivate() {
		Leg leg = Leg.getInstance();
		if (!pressed.contains(KeyCode.W) && !pressed.contains(KeyCode.S) && !pressed.contains(KeyCode.A)
				&& !pressed.contains(KeyCode.D)) {
			leg.legStop();
		} else {
			leg.legMove();
		}
	}
}
