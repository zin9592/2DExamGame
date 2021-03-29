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
 * Weapon 클래스
 * 
 * Item의 하위클래스
 * 
 * 플레이어가 직접 사용할 수 있는 무기이다.
 * 
 */

public abstract class Weapon extends Item {
	protected int damage, critical, fireRate, ammo, magazine, reloadingTime;
	protected Boolean reload; // 장전가능?
	protected WeaponType weaponType; // 무기타입 (핸드건, 라이플 등등)
	protected SoundType fireSound; // 총소리

	// 현재 총알발사할수 있는가?
	protected static Timer timer;
	protected static TimerTask fireRateTask, muzzleFlashTask;
	protected static Boolean cooldown;

	public Weapon(double _x, double _y) {
		super(_x, _y);
		itemType = ItemType.Weapon;
		itemSound = SoundType.Weapon;
	}

	// 무기 초기값 설정
	protected abstract void initWeapon();
	
	// 무기설명란
	protected String getExplain() {
		return itemName
				+"\n" + itemType.getString()
				+"\n종류 : "+weaponType.getString()
				+"\n공격력 : "+damage
				+"\n치명타율 : "+critical
				+"\n연사력 : "+fireRate;
	}
	
	// unused
	public void use() {};

	// 발사 쿨다운 설정
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

	// 공격력
	public int getDamage() {
		return damage;
	}

	// 치명타율
	public int getCritical() {
		return critical;
	}
	
	// 연사력
	public int getFireRate() {
		return fireRate;
	}
	
	public WeaponType getWeaponType() {
		return weaponType;
	}
}
