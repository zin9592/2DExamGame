package zin.game.character;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.item.ItemSlot;
import zin.game.item.ItemType;

/*
 * InventoryController Ŭ����
 * inventory.fxml ��Ʈ�ѷ��̴�.
 * 
 * �� �̺�Ʈ�� ������ ����
 */

public class InventoryController implements Initializable {
	@FXML
	private ImageView closeBtn;	// â�ݱ�
	@FXML
	private AnchorPane inventoryView;	// �κ��丮 ���� 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		closeBtn.setOnMouseClicked(event -> mouseClicked(event));
		
		// Inventory Slot Draw
		for (ItemSlot slot : Inventory.getInstance().getSlots()) {
			inventoryView.getChildren().add(slot.getItemImage());	
		}
		ItemSlot weapon = Inventory.getInstance().getEquipmentSlot(ItemType.Weapon);
		ItemSlot head = Inventory.getInstance().getEquipmentSlot(ItemType.Head);
		ItemSlot chest = Inventory.getInstance().getEquipmentSlot(ItemType.Chest);
		ItemSlot leggings = Inventory.getInstance().getEquipmentSlot(ItemType.Leggings);
		ItemSlot shoes = Inventory.getInstance().getEquipmentSlot(ItemType.Shoes);
		inventoryView.getChildren().add(weapon.getItemImage());
		inventoryView.getChildren().add(head.getItemImage());
		inventoryView.getChildren().add(chest.getItemImage());
		inventoryView.getChildren().add(leggings.getItemImage());
		inventoryView.getChildren().add(shoes.getItemImage());
	}
	
	private synchronized void mouseClicked(MouseEvent event) {
		Sound.Play(SoundType.Inventory_Open, 0.8f, false);
		Inventory.getInstance().showInventory();
	}
	
	public AnchorPane getPane() {
		return inventoryView;
	}
}
