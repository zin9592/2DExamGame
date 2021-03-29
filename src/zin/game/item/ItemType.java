package zin.game.item;


/*
 * ItemType enum
 * 
 * �������� ����
 * 
 */

public enum ItemType {
	Weapon("����"),
	Head("����"),
	Chest("����"),
	Leggings("����"),
	Shoes("�Ź�"),
	Consumable("�Ҹ�ǰ");
	
	private String type;

	private ItemType(String type) {
		this.type = type;
	}

	public String getString() {
		return type;
	}
}
