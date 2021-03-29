package zin.game.item.weapon;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;

/*
 * AK47 Å¬·¡½º
 */

public class AK47 extends Weapon {

	public AK47() {
		super(0, 0);
		initWeapon();
	}

	public AK47(double _x, double _y) {
		super(_x, _y);
		initWeapon();
	}

	protected void initWeapon() {
		initItemImage(ImageType.AK47, ImageType.Pickup_AK47, ImageType.Pickup_AK47_Fire);
		initItemTitle("AK47");
		damage = 3;
		critical = 30;
		fireRate = 100;
		cooldown = true;
		fireSound = SoundType.Fire1;
		weaponType = WeaponType.Rifle;
		itemExplain = getExplain();
	}

}
