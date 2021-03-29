package zin.game.item;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.effect.image.ImageType;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;
import zin.game.window.MainWindow;

/*
 * 
 * Item Ŭ����
 * 
 * �ʵ忡 ��ӵǴ� �����۵��� �θ� Ŭ����
 * 
 */

public abstract class Item {
	// ������ ����
	public static final int SIZE = 25; // ������ ũ��
	protected double x, y; // �ʵ忡 ����Ǵ� ��ġ
	protected Rectangle item; // ��ȭ��
	protected ImageType itemImage, pickupImage, fireImage; // ������ �̹���
	protected Label itemTitle; // ��� ������ Ÿ��Ʋ
	protected String itemName, itemExplain; // ������ �̸�, ������ ����
	protected ItemType itemType; // ������ ����
	protected SoundType itemSound, itemUseSound; // ������ �Ҹ� ( ���, ���� / ���)

	// �������� �������� ������
	protected AnimationTimer itemDropTimer; // Ÿ�̸�
	protected int translateY; // y������ �̵��ϴ� ����

	// ������
	public Item(double _x, double _y) {
		x = _x - SIZE * 0.5;
		y = _y - SIZE * 0.5;
		item = new Rectangle(x, y, SIZE, SIZE);
		translateY = -10;

		// ������ ���� �� �������� ������
		itemDropTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				dropItemDraw();
			}
		};
	}

	// �����
	protected String getExplain(String subExplain) {
		return itemName
				+"\n" + itemType.getString()
				+"\n" + subExplain;
	}
	
	// ������ �̹��� �ʱ�ȭ
	protected void initItemImage(ImageType _itemImage) {
		itemImage = _itemImage;
	}

	protected void initItemImage(ImageType _itemImage, ImageType _pickUpImage, ImageType _fireImage) {
		itemImage = _itemImage;
		pickupImage = _pickUpImage;
		fireImage = _fireImage;
	}

	// ������ ���
	public abstract void use();
	
	// ������ �̹��� ������
	public Image getItemImage() {
		return itemImage.getImage();
	}

	public Image getPickupImage() {
		return pickupImage.getImage();
	}
	
	public Image getFireImage() {
		return fireImage.getImage();
	}
	
	// ������ ������ ��ȯ
	public ItemType getItemType() {
		return itemType;
	}

	public String getItemStatus() {
		return itemExplain;
	}
	
	// �ʵ�ٴڿ� ��ӵ� ������ �׸���
	public void drawFieldItem() {
		Pane root = MainWindow.getPane();

		if (!root.getChildren().contains(item)) {
			item.setFill(new ImagePattern(itemImage.getImage()));
			root.getChildren().add(item);
			root.getChildren().add(itemTitle);
		}

		itemDropTimer.start();
	}

	// ��� �����ۿ� ������ �̸�
	protected void initItemTitle(String _itemName) {
		int NameCount = _itemName.length(); // ���ڼ��� ���缭 ���� ũ������
		itemName = _itemName;
		itemTitle = new Label(itemName);
		itemTitle.setAlignment(Pos.BASELINE_CENTER);
		itemTitle.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		itemTitle.setPrefWidth(NameCount * 8);
		itemTitle.setLayoutX(x - itemTitle.getPrefWidth() * 0.5 + SIZE * 0.5);
		itemTitle.setLayoutY(y - 20);
	}
	
	// �ʵ�ٴڿ� ��ӵ� ������ �����
	public void eraseFieldItem() {
		Pane root = MainWindow.getPane();
		root.getChildren().remove(item);
		root.getChildren().remove(itemTitle);
	}
	
	

	// ������ �Ҹ� ���
	public void itemSound() {
		Sound.Play(itemSound, 0.8f, false);
	}

	// �������� ������
	private void dropItemDraw() {
		movedY(translateY++);
		if (translateY > 10) {
			translateY = -10;
			itemSound();
			itemDropTimer.stop();
		}
	}

	// ĳ���� �̵��� ���� ������ ��ġ�̵�
	public void movedX(double _x) {
		x += _x;
		item.setX(x);
		itemTitle.setLayoutX(x - itemTitle.getPrefWidth() * 0.5 + SIZE * 0.5);
	}

	public void movedY(double _y) {
		y += _y;
		item.setY(y);
		itemTitle.setLayoutY(y - 20);
	}

	// ������ ��ǥ ��������
	public double getX() {
		return item.getX();
	}

	public double getY() {
		return item.getY();
	}

}
