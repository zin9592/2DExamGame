package zin.game.effect.object;

import java.util.LinkedList;
import java.util.Random;

import javafx.scene.shape.Rectangle;
import zin.game.item.Item;
import zin.game.item.consumable.Medikit;
import zin.game.item.consumable.Water;
import zin.game.item.equipment.ChestPlate;
import zin.game.item.equipment.Helmet;
import zin.game.item.equipment.Leggings;
import zin.game.item.equipment.Shoes;
import zin.game.item.weapon.AK47;
import zin.game.item.weapon.Glock18;
import zin.game.item.weapon.UZI;
import zin.game.map.Map;

/*
 * Chest 클래스
 * 
 * 필드에 생성되는 상자를 표현하는 클래스
 * 
 * 각종 아이템을 상호작용을 통하여서 획득할 수 있다.
 * 
 */

public class Chest {
	LinkedList<Item> items;
	Rectangle block;

	public Chest(Rectangle _block) {
		// 상자 생성 시 랜덤으로 아이템 생성
		block = _block;
		items = new LinkedList<Item>();
	}

	public Boolean equals(Rectangle _block) {
		if (block == _block)
			return true;
		return false;
	}

	public void getItemDrop() {
		setItems();
		for (Item item : items) {
			item.drawFieldItem();
		}
		Map.addItems(items);
		items.clear();
	}

	private void setItems() {
		long seed = System.currentTimeMillis();
		Random rand = new Random(seed);
		int itemCount = rand.nextInt(3)+3;
		for (int i = 0; i < itemCount; ++i) {
			int randItemID = rand.nextInt(9);
			double randX = rand.nextDouble() * block.getWidth() - Item.SIZE * 0.5;
			double randY = rand.nextDouble() * block.getWidth() - Item.SIZE * 0.5;
			double blockX = block.getX() + randX;
			double blockY = block.getY() + randY;
			switch (randItemID) {
			case 0:
				Item randItem = new Water(blockX, blockY);
				items.add(randItem);
				break;
			case 1:
				randItem = new Glock18(blockX, blockY);
				items.add(randItem);
				break;
			case 2:
				randItem = new AK47(blockX, blockY);
				items.add(randItem);
				break;
			case 3:
				randItem = new UZI(blockX, blockY);
				items.add(randItem);
				break;
			case 4:
				randItem = new Medikit(blockX, blockY);
				items.add(randItem);
				break;
			case 5:
				randItem = new Helmet(blockX, blockY);
				items.add(randItem);
				break;
			case 6:
				randItem = new ChestPlate(blockX, blockY);
				items.add(randItem);
				break;
			case 7:
				randItem = new Leggings(blockX, blockY);
				items.add(randItem);
				break;
			case 8:
				randItem = new Shoes(blockX, blockY);
				items.add(randItem);
				break;
			}
		}
	}
}
