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
 * Medikit Ŭ����
 * 
 * ȸ���� ������
 * ü���� ȸ���Ѵ�.
 * 
 */

public class Medikit extends Item {
	public Medikit(double _x, double _y){
		super(_x, _y);
		initItemImage(ImageType.Medikit);
		initItemTitle("Medikit");
		itemType = ItemType.Consumable;
		itemSound = SoundType.Pickup;
		itemUseSound = SoundType.Medikit;
		itemExplain = getExplain("ü���� 20�� 100 ȸ���Ѵ�.");
	}
	
	public void use() {
		Player player = Player.getInstance();
		Timeline use;
		use = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
			player.addHealth(-20);
			Sound.Play(itemUseSound, 1, false);
		}));
		use.setCycleCount(5);
		use.play();
		
	}
}