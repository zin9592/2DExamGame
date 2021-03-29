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
 * Inventory Ŭ����
 * 
 * Player�� �κ��丮�� ��Ÿ���� Ŭ���� 
 * 
 * �������� ������ �� �ִ�.
 */

public class Inventory { 
	private static final Inventory inventory = new Inventory();
	private InventoryController ctrl;
	
	// �κ��丮 ��������, ��
	private static Stage stage;
	private static Scene scene;
	
	// �κ��丮 ���ȴ��� ����
	private static Boolean isInventoryOpened;
	
	// �κ��丮 ���Ե�
	private static ArrayList<ItemSlot> slots;
	private static ItemSlot weaponSlot, headSlot, chestSlot, leggingsSlot, shoesSlot; 
	
	// �κ��丮 ȭ��� ��ġ
	private double x, y;
	
	// ������
	private Inventory() {
		Log.print(this);
		isInventoryOpened = false;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x = (int) (screenSize.getWidth() * 0.5) - 200;
		y = (int) (screenSize.getHeight() * 0.5) - 125;
		createSlots();
	}
	
	// ��ü ȣ��
	public static Inventory getInstance() {
		return inventory;
	}

	// �κ��丮 ����
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

	// �κ��丮 ����
	private void createInventory() {
		createStage();
		createScene();
	}

	// �������� ����
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
				if (event.getCode() == KeyCode.E) { // �κ��丮 ����
					Player player = Player.getInstance();
					player.openInventory();
					Sound.Play(SoundType.Inventory_Open, 0.8f, false);
				}
			});
			stage.setScene(scene);
		} catch (IOException e) {
			System.out.println("�κ��丮 ���� ����");
		}
	}
	
	// �κ��丮 ���� �ʱ�ȭ
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

	// ������ �ݱ�
	public Boolean getItem(Item _item) {
		for (ItemSlot slot : slots) {
			if (slot.getItem() == null) {
				slot.setItem(_item);
				return true;
			}
		}
		return false;
	}

	// �κ��丮 ���� ��������
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
