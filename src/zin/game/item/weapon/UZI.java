package zin.game.item.weapon;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;

/*
 * UZI Å¬·¡½º
 */

public class UZI extends Weapon {

	public UZI() {
		super(0, 0);
		initWeapon();
	}

	public UZI(double _x, double _y) {
		super(_x, _y);
		initWeapon();
	}

	protected void initWeapon() {
		initItemImage(ImageType.UZI, ImageType.Pickup_UZI, ImageType.Pickup_UZI_Fire);
		initItemTitle("UZI");
		damage = 1;
		critical = 10;
		fireRate = 65;
		cooldown = true;
		fireSound = SoundType.Fire2;
		weaponType = WeaponType.HandGun;
		itemExplain = getExplain();
	}

}
