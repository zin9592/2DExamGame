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
 * Item 클래스
 * 
 * 필드에 드롭되는 아이템들의 부모 클래스
 * 
 */

public abstract class Item {
	// 아이템 상태
	public static final int SIZE = 25; // 아이템 크기
	protected double x, y; // 필드에 드랍되는 위치
	protected Rectangle item; // 도화지
	protected ImageType itemImage, pickupImage, fireImage; // 아이템 이미지
	protected Label itemTitle; // 드롭 아이템 타이틀
	protected String itemName, itemExplain; // 아이템 이름, 아이템 설명
	protected ItemType itemType; // 아이템 종류
	protected SoundType itemSound, itemUseSound; // 아이템 소리 ( 드롭, 선택 / 사용)

	// 아이템의 역동적인 움직임
	protected AnimationTimer itemDropTimer; // 타이머
	protected int translateY; // y축으로 이동하는 정도

	// 생성자
	public Item(double _x, double _y) {
		x = _x - SIZE * 0.5;
		y = _y - SIZE * 0.5;
		item = new Rectangle(x, y, SIZE, SIZE);
		translateY = -10;

		// 아이템 생성 시 역동적인 움직임
		itemDropTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				dropItemDraw();
			}
		};
	}

	// 설명란
	protected String getExplain(String subExplain) {
		return itemName
				+"\n" + itemType.getString()
				+"\n" + subExplain;
	}
	
	// 아이템 이미지 초기화
	protected void initItemImage(ImageType _itemImage) {
		itemImage = _itemImage;
	}

	protected void initItemImage(ImageType _itemImage, ImageType _pickUpImage, ImageType _fireImage) {
		itemImage = _itemImage;
		pickupImage = _pickUpImage;
		fireImage = _fireImage;
	}

	// 아이템 사용
	public abstract void use();
	
	// 아이템 이미지 가져옴
	public Image getItemImage() {
		return itemImage.getImage();
	}

	public Image getPickupImage() {
		return pickupImage.getImage();
	}
	
	public Image getFireImage() {
		return fireImage.getImage();
	}
	
	// 아이템 종류를 반환
	public ItemType getItemType() {
		return itemType;
	}

	public String getItemStatus() {
		return itemExplain;
	}
	
	// 필드바닥에 드롭된 아이템 그리기
	public void drawFieldItem() {
		Pane root = MainWindow.getPane();

		if (!root.getChildren().contains(item)) {
			item.setFill(new ImagePattern(itemImage.getImage()));
			root.getChildren().add(item);
			root.getChildren().add(itemTitle);
		}

		itemDropTimer.start();
	}

	// 드롭 아이템에 보여질 이름
	protected void initItemTitle(String _itemName) {
		int NameCount = _itemName.length(); // 글자수에 맞춰서 라벨의 크기조절
		itemName = _itemName;
		itemTitle = new Label(itemName);
		itemTitle.setAlignment(Pos.BASELINE_CENTER);
		itemTitle.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		itemTitle.setPrefWidth(NameCount * 8);
		itemTitle.setLayoutX(x - itemTitle.getPrefWidth() * 0.5 + SIZE * 0.5);
		itemTitle.setLayoutY(y - 20);
	}
	
	// 필드바닥에 드롭된 아이템 지우기
	public void eraseFieldItem() {
		Pane root = MainWindow.getPane();
		root.getChildren().remove(item);
		root.getChildren().remove(itemTitle);
	}
	
	

	// 아이템 소리 재생
	public void itemSound() {
		Sound.Play(itemSound, 0.8f, false);
	}

	// 역동적인 움직임
	private void dropItemDraw() {
		movedY(translateY++);
		if (translateY > 10) {
			translateY = -10;
			itemSound();
			itemDropTimer.stop();
		}
	}

	// 캐릭터 이동에 따라서 아이템 위치이동
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

	// 아이템 좌표 가져오기
	public double getX() {
		return item.getX();
	}

	public double getY() {
		return item.getY();
	}

}
