package zin.game.item.equipment;

import zin.game.effect.image.ImageType;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * ChestPlate 클래스
 * 
 * 상의를 표현
 * 
 * 인벤토리에서 장착은 가능
 * 캐릭터에는 표시가 안됨
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
		itemExplain = getExplain("평범한 천 상의 입니다.");
	}
	
	public void use() {}
}