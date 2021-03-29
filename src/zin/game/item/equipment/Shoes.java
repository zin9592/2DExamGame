package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Shoes 클래스
 * 
 * 신발을 표현
 * 
 * 인벤토리에서 장착은 가능
 * 캐릭터에는 표시가 안됨
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
		itemExplain = getExplain("평범한 신발 입니다.");
	}
	
	public void use() {}
}