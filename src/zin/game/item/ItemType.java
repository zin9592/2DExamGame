package zin.game.item;


/*
 * ItemType enum
 * 
 * 아이템의 종류
 * 
 */

public enum ItemType {
	Weapon("무기"),
	Head("모자"),
	Chest("상의"),
	Leggings("하의"),
	Shoes("신발"),
	Consumable("소모품");
	
	private String type;

	private ItemType(String type) {
		this.type = type;
	}

	public String getString() {
		return type;
	}
}
