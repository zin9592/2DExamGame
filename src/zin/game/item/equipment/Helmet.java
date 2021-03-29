package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Helmet 클래스
 * 
 * 모자를 표현
 * 
 * 인벤토리에서 장착은 가능
 * 캐릭터에는 표시가 됨
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
		itemExplain = getExplain("평범한 천 모자 입니다.");
	}
	
	public void use() {}
}