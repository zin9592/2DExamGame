package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Leggings Ŭ����
 * 
 * ���Ǹ� ǥ��
 * 
 * �κ��丮���� ������ ����
 * ĳ���Ϳ��� ǥ�ð� �ȵ�
 * 
 */

public class Leggings extends Item {
	public Leggings(double _x, double _y){
		super(_x, _y);
		initItemImage(ImageType.Leggings);
		initItemTitle("Leggings");
		itemType = ItemType.Leggings;
		itemSound = SoundType.Pickup;
		itemUseSound = SoundType.Pickup;
		itemExplain = getExplain("����� õ ���� �Դϴ�.");
	}
	
	public void use() {}
}