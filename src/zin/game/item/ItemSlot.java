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
 * ItemSlot 클래스
 * 인벤토리의 아이템 슬롯을 나타내는 클래스
 * 
 * 기능
 * 1. 아이템 이동
 * 2. 아이템 장착
 * 3. 아이템 사용
 * 4. 아이템 상세설명 확인
 * 
 * 필요한 기능
 * 1. 아이템 버리는 기능
 */

public class ItemSlot {
	// 인벤토리 슬롯 >>> 10 x 4
	public static final int WIDTH = 10;
	public static final int HEIGHT = 4;

	private Rectangle itemImage; // 인벤토리에 표시될 이미지
	private Label itemStatus; // 아이템 능력치
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

	// 아이템 스테이터스 표시
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
		// 선택시 아이템 강조
		itemImage.setOnMouseEntered(event -> {
			drawItemStatus(true);
			itemImage.setWidth(Item.SIZE + 4);
			itemImage.setHeight(Item.SIZE + 4);
			itemImage.setTranslateX(-2);
			itemImage.setTranslateY(-2);
		});
		// 미선택시 아이템 원상복구
		itemImage.setOnMouseExited(event -> {
			drawItemStatus(false);
			itemImage.setWidth(Item.SIZE);
			itemImage.setHeight(Item.SIZE);
			itemImage.setTranslateX(0);
			itemImage.setTranslateY(0);
		});
		// 아이템 클릭시 소리재생
		itemImage.setOnMousePressed(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				drawItemStatus(false);
				pickupSound();
			}else if(event.getButton() == MouseButton.SECONDARY &&
					item.getItemType() == ItemType.Consumable) {
				// 아이템 사용
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
				
				// 임시 : 머리 아이템 다른것도 만들어야 하기 때문에
				if(head.getItem() != null) {
					Body body = Body.getInstance();
					body.equipHelmet();
				}else{
					Body body = Body.getInstance();
					body.unEquipHelmet();
				}
				// 손에 들고 있는 무기 그리기
				Hand hand = Hand.getInstance();
				hand.drawEquipment();
			}
		});
	}

	// 인벤토리 아이템 위치 변경
	private void swapSlot(ItemSlot slot, MouseEvent event) {
		if (slot.getX() <= event.getX() && event.getX() <= slot.getX() + Item.SIZE) {
			if (slot.getY() <= event.getY() && event.getY() <= slot.getY() + Item.SIZE) {
				swapItem(slot);
				return;
			}
		}
	}

	// 장착 위치변경
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

	// 인벤토리에서 마우스 드래그를 통해 교환
	private void swapItem(ItemSlot slot) {
		Item temp = item;
		setItem(slot.getItem());
		slot.setItem(temp);
	}

	// Item 관련 메소드
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
