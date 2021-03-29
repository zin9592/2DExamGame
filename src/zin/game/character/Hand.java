package zin.game.character;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.effect.image.ImageType;
import zin.game.item.ItemSlot;
import zin.game.item.ItemType;
import zin.game.item.weapon.AK47;
import zin.game.item.weapon.Weapon;

/*
 * Hand Ŭ����
 * 
 * Player Ŭ���� ���� ����
 * 
 * 1. �÷��̾��� ���� ǥ��
 * 2. �÷��̾ ������ ���� ǥ��
 * 
 */

public class Hand {
	private static final Hand hand = new Hand();

	private Rectangle shape, weaponShape;
	private Weapon weapon;

	private Hand() {
	}

	// ���� ��ǥ �� ũ��
	public void createHand(double x, double y, double size) {
		shape = new Rectangle(x, y, size, size);
		shape.setFill(new ImagePattern(ImageType.Hand_1.getImage()));
		weapon = new AK47();
		weaponShape = new Rectangle(x, y, size, size);
		weaponShape.setFill(new ImagePattern(weapon.getPickupImage()));
		Status status = Status.getInstance();
		status.setDamage(weapon.getDamage());
		status.setCritical(weapon.getCritical());
	}

	// ��ü ȣ��
	public static Hand getInstance() {
		return hand;
	}

	public Rectangle getShape() {
		return shape;
	}

	public Rectangle getWeaponShape() {
		return weaponShape;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setDegree(double degree) {
		shape.setRotate(degree);
		weaponShape.setRotate(degree);
	}

	public void drawEquipment() {
		ItemSlot weaponSlot = Inventory.getInstance().getEquipmentSlot(ItemType.Weapon);
		Weapon item = (Weapon) weaponSlot.getItem();

		// hand changed
		if (item != null) {
			Status status = Status.getInstance();
			
			switch (item.getWeaponType()) {
			case HandGun:
				shape.setFill(new ImagePattern(ImageType.Hand_2.getImage()));
				break;
			case Rifle:
				shape.setFill(new ImagePattern(ImageType.Hand_1.getImage()));
				break;
			}
			weapon = item;
			status.setDamage(weapon.getDamage());
			status.setCritical(weapon.getCritical());
			weaponShape.setFill(new ImagePattern(weapon.getPickupImage()));
		} else {
			shape.setFill(null);
			weapon = null;
			weaponShape.setFill(null);
			Status status = Status.getInstance();
			status.setDamage(0);
			status.setCritical(0);
		}
	}
}
