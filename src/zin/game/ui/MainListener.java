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
 * MainListener Ŭ����
 * 
 * ���� ���� �� �������� ������
 * 
 */

public class MainListener {
	private HashSet<KeyCode> pressed; // ���� ���� Ű ����
	private static Scene scene; // ����ȭ��
	private Player player; // �÷��̾�
	private Aim aim; // ũ�ν����

	public MainListener(Scene _scene) {
		Log.print(this);
		pressed = new HashSet<KeyCode>();
		scene = _scene;
		player = Player.getInstance();
		aim = Aim.getInstance();
		setListener();
	}

	// Ű�� ���� �� Ű �������� �߰��� �ȴ�.
	private synchronized void KeyPressed(KeyEvent event) {
		pressed.add(event.getCode());
	}

	// �ݴ�� Ű�� �� �� Ű �������� ���Ű� �ȴ�.
	private synchronized void KeyReleased(KeyEvent event) {
		pressed.remove(event.getCode());
	}

	// ũ�ν������ ������
	private synchronized void MouseMoved(MouseEvent event) {
		aim.moveMouse(event);
	}

	private synchronized void MousePressed(MouseEvent event) {
		if(event.getButton() == MouseButton.PRIMARY) {
			aim.setPoint(event);
		}
	}

	// ���� ������ ����
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
	
	// Ű �Է¿� ���� ���ۺκ�
	public void activate() {
		legActivate();

		// ������
		if (pressed.contains(KeyCode.SHIFT) && player.getStatus().getStemina() > 0) {
			if(move(1.2)) {
				player.addStemina(1);
			}
		}else if(pressed.contains(KeyCode.SHIFT) && player.getStatus().getStemina() == 0){
			move(1);
		}else {
			move(1);
		}
		
		// �߻�ӵ� ������
		if (aim.isFire() && player.isReady()) {
			player.attack(aim);
		}
		if (pressed.contains(KeyCode.F)) { // ��ȣ�ۿ�
			player.activate();
			pressed.remove(KeyCode.F);
			aim.disableFire();
		}
		if (pressed.contains(KeyCode.E)) { // �κ��丮
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
