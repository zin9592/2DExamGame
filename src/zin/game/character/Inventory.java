package zin.game.character;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.item.Item;
import zin.game.item.ItemSlot;
import zin.game.item.ItemType;
import zin.game.log.Log;
import zin.game.window.MainWindow;

/*
 * Inventory 클래스
 * 
 * Player의 인벤토리를 나타내는 클래스 
 * 
 * 아이템을 관리할 수 있다.
 */

public class Inventory { 
	private static final Inventory inventory = new Inventory();
	private InventoryController ctrl;
	
	// 인벤토리 스테이지, 씬
	private static Stage stage;
	private static Scene scene;
	
	// 인벤토리 열렸는지 여부
	private static Boolean isInventoryOpened;
	
	// 인벤토리 슬롯들
	private static ArrayList<ItemSlot> slots;
	private static ItemSlot weaponSlot, headSlot, chestSlot, leggingsSlot, shoesSlot; 
	
	// 인벤토리 화면상 위치
	private double x, y;
	
	// 생성자
	private Inventory() {
		Log.print(this);
		isInventoryOpened = false;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x = (int) (screenSize.getWidth() * 0.5) - 200;
		y = (int) (screenSize.getHeight() * 0.5) - 125;
		createSlots();
	}
	
	// 객체 호출
	public static Inventory getInstance() {
		return inventory;
	}

	// 인벤토리 열기
	public void showInventory() {
		if (!isInventoryOpened) {
			createInventory();
			stage.show();
		} else {
			stage.close();
		}
		MainWindow.WindowFocused();
		isInventoryOpened = !isInventoryOpened;
	}

	// 인벤토리 생성
	private void createInventory() {
		createStage();
		createScene();
	}

	// 스테이지 생성
	private void createStage() {
		stage = new Stage(StageStyle.UNDECORATED);
		stage.setX(x);
		stage.setY(y);
		stage.initOwner(MainWindow.getStage());
		stage.setResizable(false);
	}

	// Init Scene
	private void createScene() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory.fxml"));
			Parent root = loader.load();
			ctrl = loader.getController();
			scene = new Scene(root);
			scene.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.E) { // 인벤토리 끄기
					Player player = Player.getInstance();
					player.openInventory();
					Sound.Play(SoundType.Inventory_Open, 0.8f, false);
				}
			});
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println("인벤토리 열기 오류");
		}
	}
	
	// 인벤토리 슬롯 초기화
	private void createSlots() {
		double x = 30;
		double y = 30;
		Item item = Hand.getInstance().getWeapon();
		weaponSlot = new ItemSlot(x + 0 * (Item.SIZE + 10), 195, item);
		headSlot = new ItemSlot(x + 1 * (Item.SIZE + 10), 195);
		chestSlot = new ItemSlot(x + 2 * (Item.SIZE + 10), 195);
		leggingsSlot = new ItemSlot(x + 3 * (Item.SIZE + 10), 195);
		shoesSlot  = new ItemSlot(x + 4 * (Item.SIZE + 10), 195);
		if (slots == null) {
			slots = new ArrayList<ItemSlot>(ItemSlot.HEIGHT * ItemSlot.WIDTH);
			for (int i = 0; i < ItemSlot.HEIGHT; ++i) {
				for (int j = 0; j < ItemSlot.WIDTH; ++j) {
					ItemSlot slot = new ItemSlot(x + j * (Item.SIZE + 10), y + i * (Item.SIZE + 10));
					slots.add(slot);
				}
			}
		}
	}

	// 아이템 줍기
	public Boolean getItem(Item _item) {
		for (ItemSlot slot : slots) {
			if (slot.getItem() == null) {
				slot.setItem(_item);
				return true;
			}
		}
		return false;
	}

	// 인벤토리 슬롯 가져오기
	public ArrayList<ItemSlot> getSlots() {
		return slots;
	}
	
	public ItemSlot getEquipmentSlot(ItemType type) {
		switch(type) {
		case Weapon:
			return weaponSlot;
		case Head:
			return headSlot;
		case Chest:
			return chestSlot;
		case Leggings:
			return leggingsSlot;
		case Shoes:
			return shoesSlot;
		default:
			return null;
		}
	}
	
	public InventoryController getController() {
		return ctrl;
	}
}
