package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Leggings 클래스
 * 
 * 하의를 표현
 * 
 * 인벤토리에서 장착은 가능
 * 캐릭터에는 표시가 안됨
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
		itemExplain = getExplain("평범한 천 하의 입니다.");
	}
	
	public void use() {}
}