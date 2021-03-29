package zin.game.item.weapon;


/*
 * WeaponType enum
 * 
 * 무기의 종류
 * 
 */

public enum WeaponType {
	HandGun("권총"),
	Rifle("돌격소총");
	
	private String type;

	private WeaponType(String type) {
		this.type = type;
	}

	public String getString() {
		return type;
	}
}
