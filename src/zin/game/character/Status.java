package zin.game.character;

/*
 * Status Ŭ����
 * 
 * �÷��̾� ���� Ŭ����
 * 
 * �÷��̾��� ���� �ɷ�ġ�� ǥ��
 * 
 */

public class Status {
	private static Status status = new Status();
	
	private double health, maxHealth, stemina, maxStemina;
	private double damage, defense, critical;
	private double healthRegeneration, steminaRegeneration, moveSpeed;

	// �������ͽ�
	private Status() {
		health = 100;
		maxHealth = health;
		stemina = 100;
		maxStemina = stemina;
		damage = 0;
		defense = 0;
		critical = 0;
		healthRegeneration = 5;
		steminaRegeneration = 1;
		moveSpeed = 8;
	}
	
	// ��ü ȣ��
	public static Status getInstance() {
		return status;
	}

	// Health
	public void setHealth(double _health) {
		health = _health;
	}

	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	// Stemina
	public void setStemina(double _stemina) {
		stemina = _stemina;
	}

	public double getStemina() {
		return stemina;
	}

	public double getMaxStemina() {
		return maxStemina;
	}

	// MoveSpeed
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	// Damage
	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	// Defense
	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	// Critical
	public double getCritical() {
		return critical;
	}

	public void setCritical(double critical) {
		this.critical = critical;
	}

	// Regeneration
	public double getHealthRegeneration() {
		return healthRegeneration;
	}

	public void setHealthRegeneration(double healthRegeneration) {
		this.healthRegeneration = healthRegeneration;
	}

	public double getSteminaRegeneration() {
		return steminaRegeneration;
	}

	public void setSteminaRegeneration(double steminaRegeneration) {
		this.steminaRegeneration = steminaRegeneration;
	}

}
