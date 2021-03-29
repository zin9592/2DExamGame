package zin.game.item.consumable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import zin.game.character.Player;
import zin.game.effect.image.ImageType;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemType;

/*
 * Water Ŭ����
 * 
 * ȸ���� ������
 * ���׹̳��� ȸ���Ѵ�.
 * 
 */

public class Water extends Item {
	public Water(double _x, double _y){
		super(_x, _y);
		initItemImage(ImageType.Water);
		initItemTitle("Water");
		itemType = ItemType.Consumable;
		itemSound = SoundType.Portion;
		itemUseSound = SoundType.Drink;
		itemExplain = getExplain("���׹̳��� 20�� 100 ȸ���Ѵ�.");
	}
	
	public void use() {
		Player player = Player.getInstance();
		Timeline use;
		use = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
			player.addStemina(-20);
		}));
		use.setCycleCount(5);
		use.play();
		Sound.Play(itemUseSound, 1, false);
	}
}