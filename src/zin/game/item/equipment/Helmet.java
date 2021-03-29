package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Helmet Ŭ����
 * 
 * ���ڸ� ǥ��
 * 
 * �κ��丮���� ������ ����
 * ĳ���Ϳ��� ǥ�ð� ��
 * 
 */

public class Helmet extends Item {
	public Helmet(double _x, double _y){
		super(_x, _y);
		initItemImage(ImageType.Helmet);
		initItemTitle("Helmet");
		itemType = ItemType.Head;
		itemSound = SoundType.Pickup;
		itemUseSound = SoundType.Pickup;
		itemExplain = getExplain("����� õ ���� �Դϴ�.");
	}
	
	public void use() {}
}