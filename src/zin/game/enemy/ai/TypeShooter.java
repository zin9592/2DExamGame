package zin.game.enemy.ai;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.enemy.Enemy;

/*
 * TypeShooter 클래스
 * 
 * AI 하위 클래스
 * 
 * 원거리를 하는 적에게 부여되는 인공지능
 * 
 */

public class TypeShooter extends AI {
	MoveType movement;
	double enemyMoveX, enemyMoveY, randMove;
	// randMove는 인공지능의 움직임이 각자 다르도록 설정
	public TypeShooter(Enemy _enemy) {
		super(_enemy);
		Random rand = new Random();
		randMove = rand.nextInt(15) + 5;
				
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		enemy.talk();
		switch (e_state) {
		case Attack:
			attack();
			break;
		case Move:
			move();
			break;
		case Patrol:
			patrol();
			break;
		case Stop:
			break;
		default:
			break;
		}
	}

	@Override
	public void changeState(StateType state) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		switch (state) {
		case Attack:
			e_state = StateType.Attack;
			break;
		case Move:
			e_state = StateType.Move;
			movement = MoveType.Move;
			moveRemaining = rand.nextInt(200) + 100;
			break;
		case Patrol:
			e_state = StateType.Patrol;
			moveRemaining = rand.nextInt(50) + 50;
			break;
		case Stop:
			e_state = StateType.Stop;
			break;
		default:
			break;
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (moveRemaining > 0) {
			double enemyX = enemy.getShape().getX();
			double enemyY = enemy.getShape().getY();
			double playerX = player.getX();
			double playerY = player.getY();
			double enemySpeed;
			moveRemaining--;

			double distance = enemy.getDistance(playerX, enemyX, playerY, enemyY);
			if (distance < enemy.getStatus().getAttackRange()) {
				changeState(StateType.Attack);
				enemy.setDegree(playerX, playerY);
			} else {
				// 적의 발소리
				if (moveRemaining % 50 == 0) {
					float range = 2500;
					if(distance >= range) {
						distance = range - 1;
					}
					float value = (float) (range - distance) / range;
					Random rand = new Random();
					switch (rand.nextInt(3)) {
					case 0:
						Sound.Play(SoundType.Walk1, value, false);
						break;
					case 1:
						Sound.Play(SoundType.Walk2, value, false);
						break;
					case 2:
						Sound.Play(SoundType.Walk3, value, false);
						break;
					}
				}

				enemySpeed = enemy.getStatus().getMoveSpeed();
				// 벽에 막혀있지 않을 때
				if (movement == MoveType.Move) {
					// 속도조절
					if (enemyX < playerX - randMove) {
						enemyMoveX = enemySpeed;
					} else if (enemyX > playerX + randMove) {
						enemyMoveX = -enemySpeed;
					} else {
						enemyMoveX = 0;
					}
					if (enemyY < playerY - randMove) {
						enemyMoveY = enemySpeed;
					} else if (enemyY > playerY + randMove) {
						enemyMoveY = -enemySpeed;
					} else {
						enemyMoveY = 0;
					}
					enemy.setDegree(enemyX + enemyMoveX, enemyY + enemyMoveY);
					// 만약 벽이 있어서 이동을 못했다면 이동형태를 바꾼다.
					if (enemy.isBlocked(enemyMoveX, enemyMoveY)) {
						enemy.moveEnemy(enemyMoveX, enemyMoveY, false);
					} else {
						if (enemy.isBlocked(enemyMoveX, 0) && enemyMoveX != 0) {
							enemy.moveEnemy(enemyMoveX, 0, false);
							movement = MoveType.MoveX;
						} else if (enemy.isBlocked(0, enemyMoveY) && enemyMoveY != 0) {
							enemy.moveEnemy(0, enemyMoveY, false);
							movement = MoveType.MoveY;
						}
					}
				} else if (movement == MoveType.MoveX) {
					double checkMovePoint = playerY > enemyY ? enemySpeed : -enemySpeed;
					enemy.setDegree(enemyX + enemyMoveX, enemyY);
					// 위 아래로 이동이 성공했을 경우
					if (enemy.isBlocked(enemyMoveX, checkMovePoint)) {
						enemy.moveEnemy(enemyMoveX, checkMovePoint, false);
						movement = MoveType.Move;
					} else if (enemy.isBlocked(enemyMoveX, 0)) {
						enemy.moveEnemy(enemyMoveX, 0, false);
					} else if (!enemy.isBlocked(enemyMoveX, 0)) {
						enemyMoveX = -enemyMoveX;
						enemy.moveEnemy(enemyMoveX, 0, false);
					} else if (enemy.isBlocked(0, enemyMoveX)) {
						enemy.moveEnemy(0, enemyMoveX, false);
					}
				} else if (movement == MoveType.MoveY) {
					double checkMovePoint = playerX > enemyX ? enemySpeed : -enemySpeed;
					enemy.setDegree(enemyX, enemyY + enemyMoveY);
					// 좌우로 이동이 성공했을 경우
					if (enemy.isBlocked(checkMovePoint, enemyMoveY)) {
						enemy.moveEnemy(checkMovePoint, enemyMoveY, false);
						movement = MoveType.Move;
					} else if (enemy.isBlocked(0, enemyMoveY)) {
						enemy.moveEnemy(0, enemyMoveY, false);
					} else if (!enemy.isBlocked(0, enemyMoveY)) {
						enemyMoveY = -enemyMoveY;
						enemy.moveEnemy(0, enemyMoveY, false);
					} else if (enemy.isBlocked(enemyMoveY, 0)) {
						enemy.moveEnemy(enemyMoveY, 0, false);
					}
				}
			}
		} else

		{
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Random rand = new Random();
					moveRemaining = rand.nextInt(200) + 100;
				}
			};
			timer.schedule(task, 1500);
		}
	}

	@Override
	public void patrol() {
		// TODO Auto-generated method stub
		double distance = enemy.getDistance(player.getX(), enemy.getShape().getX(), player.getY(),
				enemy.getShape().getY());
		isDetected(distance);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		enemy.attack();
		changeState(StateType.Stop);
		Timer timer = new Timer();
		TimerTask nextState = new TimerTask() {
			public void run() {
				double enemyX = enemy.getShape().getX();
				double enemyY = enemy.getShape().getY();
				double playerX = player.getX();
				double playerY = player.getY();
				double distance = enemy.getDistance(playerX, enemyX, playerY, enemyY);
				isDetected(distance);
			}
		};
		timer.schedule(nextState, (long) (enemy.getStatus().getAttackSpeed() * 1000));
	}
}
