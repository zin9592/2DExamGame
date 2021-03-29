package zin.game.item.weapon;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;

/*
 * Glock18 Å¬·¡½º
 */

public class Glock18 extends Weapon {

	public Glock18() {
		super(0, 0);
		initWeapon();
	}

	public Glock18(double _x, double _y) {
		super(_x, _y);
		initWeapon();
	}

	protected void initWeapon() {
		initItemImage(ImageType.Glock18, ImageType.Pickup_Glock18, ImageType.Pickup_Glock18_Fire);
		initItemTitle("Glock18");
		damage = 5;
		critical = 15;
		fireRate = 350;
		cooldown = true;
		fireSound = SoundType.Fire0;
		weaponType = WeaponType.HandGun;
		itemExplain = getExplain();
	}
}
