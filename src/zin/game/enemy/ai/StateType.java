package zin.game.enemy.ai;

/*
 * StateType enum
 * 
 * 적의 현재상태
 * 상태에 따라서 행동이 다르다.
 * Patrol 정찰 : 미구현 이유 >>> 디펜스로 만들어서
 * 				 개인행동을 하다가 특정거리에 적이 들어오면 감지한다.
 * 
 * Move 이동 : 적이 플레이어를 감지하면 플레이어한테로 이동
 * 			   벽이 있을때 최소한으로 벽을 지나쳐온다.
 * 
 * Attack 공격 : 적이 플레이어가 공격사거리에 들어오면 공격모션과 함께
 * 				 공격
 * 
 * Stop 정지 : 공격 후 잠시 멈춘다.
 */

public enum StateType {
	Move,
	Attack,
	Patrol,
	Stop;
}
