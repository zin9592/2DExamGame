package zin.game.enemy;

/*
 * Status 클래스
 * 
 * 각종 능력치들을 표시한다.
 * 
 */

public class Status {
	private double health, maxHealth, stemina, maxStemina;
	private double power, range, attackRange; // 공격력, 감지거리, 사정거리
	private double attackSpeed, moveSpeed;

	public Status(double _health, double _power, double _range, double _attackRange, double _attackSpeed,
			double _moveSpeed) {
		health = _health;
		maxHealth = health;
		power = _power;
		range = _range;
		attackRange = _attackRange;
		attackSpeed = _attackSpeed;
		moveSpeed = _moveSpeed;
	}

	public void setHealth(double _health) {
		health = _health;
	}

	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setStemina(double _stemina) {
		stemina = _stemina;
	}

	public double getStemina() {
		return stemina;
	}

	public double getMaxStemina() {
		return maxStemina;
	}
	
	public double getPower() {
		return power;
	}

	public double getRange() {
		return range;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public double getAttackRange() {
		return attackRange;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}
}
