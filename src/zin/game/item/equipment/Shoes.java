package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Shoes Ŭ����
 * 
 * �Ź��� ǥ��
 * 
 * �κ��丮���� ������ ����
 * ĳ���Ϳ��� ǥ�ð� �ȵ�
 * 
 */

public class Shoes extends Item {
	public Shoes(double _x, double _y){
		super(_x, _y);
		initItemImage(ImageType.Shoes);
		initItemTitle("Shoes");
		itemType = ItemType.Shoes;
		itemSound = SoundType.Pickup;
		itemUseSound = SoundType.Pickup;
		itemExplain = getExplain("����� �Ź� �Դϴ�.");
	}
	
	public void use() {}
}