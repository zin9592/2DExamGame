package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * ChestPlate Ŭ����
 * 
 * ���Ǹ� ǥ��
 * 
 * �κ��丮���� ������ ����
 * ĳ���Ϳ��� ǥ�ð� �ȵ�
 * 
 */

public class ChestPlate extends Item {
	public ChestPlate(double _x, double _y){
		super(_x, _y);
		initItemImage(ImageType.Chest);
		initItemTitle("ChestPlate");
		itemType = ItemType.Chest;
		itemSound = SoundType.Pickup;
		itemUseSound = SoundType.Pickup;
		itemExplain = getExplain("����� õ ���� �Դϴ�.");
	}
	
	public void use() {}
}