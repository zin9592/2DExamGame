package zin.game.item.weapon;


/*
 * WeaponType enum
 * 
 * ������ ����
 * 
 */

public enum WeaponType {
	HandGun("����"),
	Rifle("���ݼ���");
	
	private String type;

	private WeaponType(String type) {
		this.type = type;
	}

	public String getString() {
		return type;
	}
}
