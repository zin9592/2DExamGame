package zin.game.enemy.ai;

import zin.game.character.Player;
import zin.game.enemy.Enemy;

/*
 * AI 클래스
 * 
 * 적들의 인공지능을 표현하는 부모 클래스
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
	
	// 업데이트
	public abstract void update();
	
	// 상태변경
	protected abstract void changeState(StateType state);
	
	// 행동
	protected abstract void patrol();
	
	protected abstract void move();
	
	protected abstract void attack();
	
	// 적 감지
	protected void isDetected(double distance) {
		if(distance <= enemy.getStatus().getRange() && !enemy.isDetected()) {
			// 특정거리에 들어오면 감지모드
			enemy.setDetected(true);
			changeState(StateType.Move);
		}else if(distance <= enemy.getStatus().getRange() * enemy.getDetectedRange() && enemy.isDetected() ) {
			// 감지될시 감지거리 증가
			changeState(StateType.Move);	
		}
		else if(distance > enemy.getStatus().getRange() * enemy.getDetectedRange() && enemy.isDetected() ) {
			// 거리에서 멀어지면 미감지
			enemy.setDetected(false);
			changeState(StateType.Patrol);
		}
	}
}
