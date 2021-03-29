package zin.game.item;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.character.Body;
import zin.game.character.Hand;
import zin.game.character.Inventory;
import zin.game.character.InventoryController;

/*
 * ItemSlot Ŭ����
 * �κ��丮�� ������ ������ ��Ÿ���� Ŭ����
 * 
 * ���
 * 1. ������ �̵�
 * 2. ������ ����
 * 3. ������ ���
 * 4. ������ �󼼼��� Ȯ��
 * 
 * �ʿ��� ���
 * 1. ������ ������ ���
 */

public class ItemSlot {
	// �κ��丮 ���� >>> 10 x 4
	public static final int WIDTH = 10;
	public static final int HEIGHT = 4;

	private Rectangle itemImage; // �κ��丮�� ǥ�õ� �̹���
	private Label itemStatus; // ������ �ɷ�ġ
	private Item item;
	private double x, y;

	public ItemSlot(double _x, double _y) {
		x = _x;
		y = _y;
		itemImage = new Rectangle(_x, _y, Item.SIZE, Item.SIZE);
		itemImage.setFill(null);
		initItemListener();
	}

	public ItemSlot(double _x, double _y, Item _item) {
		x = _x;
		y = _y;
		item = _item;
		itemImage = new Rectangle(_x, _y, Item.SIZE, Item.SIZE);
		itemImage.setFill(new ImagePattern(item.getItemImage()));
		initItemListener();
	}

	// ������ �������ͽ� ǥ��
	private void drawItemStatus(Boolean isStatusDraw) {
		Inventory inv = Inventory.getInstance();
		InventoryController ctrl = inv.getController();
		if (isStatusDraw) {
			itemStatus = new Label(item.getItemStatus());
			itemStatus.setLayoutX(x + 20);
			itemStatus.setLayoutY(y + 10);
			itemStatus.setPrefWidth(200);
			itemStatus.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
			itemStatus.setTextFill(Color.WHITE);
			itemStatus.setOpacity(0.8);
			ctrl.getPane().getChildren().add(itemStatus);
		} else {
			ctrl.getPane().getChildren().remove(itemStatus);
		}
	}

	private void initItemListener() {
		// ���ý� ������ ����
		itemImage.setOnMouseEntered(event -> {
			drawItemStatus(true);
			itemImage.setWidth(Item.SIZE + 4);
			itemImage.setHeight(Item.SIZE + 4);
			itemImage.setTranslateX(-2);
			itemImage.setTranslateY(-2);
		});
		// �̼��ý� ������ ���󺹱�
		itemImage.setOnMouseExited(event -> {
			drawItemStatus(false);
			itemImage.setWidth(Item.SIZE);
			itemImage.setHeight(Item.SIZE);
			itemImage.setTranslateX(0);
			itemImage.setTranslateY(0);
		});
		// ������ Ŭ���� �Ҹ����
		itemImage.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				drawItemStatus(false);
				pickupSound();
			}else if(event.getButton() == MouseButton.SECONDARY &&
					item.getItemType() == ItemType.Consumable) {
				// ������ ���
				item.use();
				itemImage.setFill(null);
				item = null;
			}
		});
		itemImage.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				drawItemStatus(false);
				itemImage.setX(event.getX() - Item.SIZE * 0.5);
				itemImage.setY(event.getY() - Item.SIZE * 0.5);
			}
		});
		itemImage.setOnMouseReleased(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				pickupSound();
				for (ItemSlot slot : Inventory.getInstance().getSlots()) {
					if (itemImage.equals(slot.getItemImage()))
						continue;
					swapSlot(slot, event);
				}
				ItemSlot weapon = Inventory.getInstance().getEquipmentSlot(ItemType.Weapon);
				ItemSlot head = Inventory.getInstance().getEquipmentSlot(ItemType.Head);
				ItemSlot chest = Inventory.getInstance().getEquipmentSlot(ItemType.Chest);
				ItemSlot leggings = Inventory.getInstance().getEquipmentSlot(ItemType.Leggings);
				ItemSlot shoes = Inventory.getInstance().getEquipmentSlot(ItemType.Shoes);
				swapSlot(weapon, event, ItemType.Weapon);
				swapSlot(head, event, ItemType.Head);
				swapSlot(chest, event, ItemType.Chest);
				swapSlot(leggings, event, ItemType.Leggings);
				swapSlot(shoes, event, ItemType.Shoes);
				itemImage.setX(x);
				itemImage.setY(y);
				itemImage.setTranslateX(0);
				itemImage.setTranslateY(0);
				
				// �ӽ� : �Ӹ� ������ �ٸ��͵� ������ �ϱ� ������
				if(head.getItem() != null) {
					Body body = Body.getInstance();
					body.equipHelmet();
				}else{
					Body body = Body.getInstance();
					body.unEquipHelmet();
				}
				// �տ� ��� �ִ� ���� �׸���
				Hand hand = Hand.getInstance();
				hand.drawEquipment();
			}
		});
	}

	// �κ��丮 ������ ��ġ ����
	private void swapSlot(ItemSlot slot, MouseEvent event) {
		if (slot.getX() <= event.getX() && event.getX() <= slot.getX() + Item.SIZE) {
			if (slot.getY() <= event.getY() && event.getY() <= slot.getY() + Item.SIZE) {
				swapItem(slot);
				return;
			}
		}
	}

	// ���� ��ġ����
	private void swapSlot(ItemSlot slot, MouseEvent event, ItemType type) {
		if (slot.getX() <= event.getX() && event.getX() <= slot.getX() + Item.SIZE) {
			if (slot.getY() <= event.getY() && event.getY() <= slot.getY() + Item.SIZE) {
				if (item.getItemType() == type) {
					swapItem(slot);
					return;
				}
			}
		}
	}

	// �κ��丮���� ���콺 �巡�׸� ���� ��ȯ
	private void swapItem(ItemSlot slot) {
		Item temp = item;
		setItem(slot.getItem());
		slot.setItem(temp);
	}

	// Item ���� �޼ҵ�
	public void setItem(Item _item) { // Item Setter
		item = _item;
		itemImage.setX(x);
		itemImage.setY(y);
		if (item != null) {
			itemImage.setFill(new ImagePattern(item.getItemImage()));
		} else {
			itemImage.setFill(null);
		}
	}

	public Item getItem() { // Item Getter
		return item;
	}

	private void pickupSound() {
		if (item == null)
			return;
		item.itemSound();
	}

	public Rectangle getItemImage() {
		return itemImage;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
