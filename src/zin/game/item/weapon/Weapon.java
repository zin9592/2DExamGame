package zin.game.item.weapon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.paint.ImagePattern;
import zin.game.character.Hand;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Weapon Ŭ����
 * 
 * Item�� ����Ŭ����
 * 
 * �÷��̾ ���� ����� �� �ִ� �����̴�.
 * 
 */

public abstract class Weapon extends Item {
	protected int damage, critical, fireRate, ammo, magazine, reloadingTime;
	protected Boolean reload; // ��������?
	protected WeaponType weaponType; // ����Ÿ�� (�ڵ��, ������ ���)
	protected SoundType fireSound; // �ѼҸ�

	// ���� �Ѿ˹߻��Ҽ� �ִ°�?
	protected static Timer timer;
	protected static TimerTask fireRateTask, muzzleFlashTask;
	protected static Boolean cooldown;

	public Weapon(double _x, double _y) {
		super(_x, _y);
		itemType = ItemType.Weapon;
		itemSound = SoundType.Weapon;
	}

	// ���� �ʱⰪ ����
	protected abstract void initWeapon();
	
	// ���⼳���
	protected String getExplain() {
		return itemName
				+"\n" + itemType.getString()
				+"\n���� : "+weaponType.getString()
				+"\n���ݷ� : "+damage
				+"\nġ��Ÿ�� : "+critical
				+"\n����� : "+fireRate;
	}
	
	// unused
	public void use() {};

	// �߻� ��ٿ� ����
	public void setCoolDown(Boolean _cooldown) {
		if (!_cooldown) {
			Sound.Play(fireSound, 0.8f, false);
			cooldown = _cooldown;
			timer = new Timer();
			fireRateTask = new TimerTask() {
				public void run() {
					cooldown = true;
				}
			};
			muzzleFlashTask = new TimerTask() {
				public void run() {
					Hand hand = Hand.getInstance();
					hand.getWeaponShape().setFill(new ImagePattern(pickupImage.getImage()));
				}
			};
			Hand hand = Hand.getInstance();
			hand.getWeaponShape().setFill(new ImagePattern(fireImage.getImage()));
			timer.schedule(muzzleFlashTask, 10);
			timer.schedule(fireRateTask, fireRate);
		}
	}

	public Boolean getCoolDown() {
		return cooldown;
	}

	// ���ݷ�
	public int getDamage() {
		return damage;
	}

	// ġ��Ÿ��
	public int getCritical() {
		return critical;
	}
	
	// �����
	public int getFireRate() {
		return fireRate;
	}
	
	public WeaponType getWeaponType() {
		return weaponType;
	}
}
