package zin.game.enemy.ai;

import zin.game.character.Player;
import zin.game.enemy.Enemy;

/*
 * AI Ŭ����
 * 
 * ������ �ΰ������� ǥ���ϴ� �θ� Ŭ����
 * 
 */

public abstract class AI {
	protected Player player;
	protected Enemy enemy;
	protected StateType e_state;
	protected int moveRemaining;

	public AI(Enemy _enemy) {
		player = Player.getInstance();
		enemy = _enemy;
		e_state = StateType.Patrol;
		moveRemaining = 0;
		changeState(e_state);
	}
	
	// ������Ʈ
	public abstract void update();
	
	// ���º���
	protected abstract void changeState(StateType state);
	
	// �ൿ
	protected abstract void patrol();
	
	protected abstract void move();
	
	protected abstract void attack();
	
	// �� ����
	protected void isDetected(double distance) {
		if(distance <= enemy.getStatus().getRange() && !enemy.isDetected()) {
			// Ư���Ÿ��� ������ �������
			enemy.setDetected(true);
			changeState(StateType.Move);
		}else if(distance <= enemy.getStatus().getRange() * enemy.getDetectedRange() && enemy.isDetected() ) {
			// �����ɽ� �����Ÿ� ����
			changeState(StateType.Move);	
		}
		else if(distance > enemy.getStatus().getRange() * enemy.getDetectedRange() && enemy.isDetected() ) {
			// �Ÿ����� �־����� �̰���
			enemy.setDetected(false);
			changeState(StateType.Patrol);
		}
	}
}
