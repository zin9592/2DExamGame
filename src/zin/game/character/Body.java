package zin.game.character;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.effect.image.ImageType;

/*
 * Body Ŭ����
 * 
 * Player Ŭ������ ���԰���
 * 
 * 1. �÷��̾��� ���� ǥ��
 * 2. �÷��̾��� ���ڸ� ǥ��
 */

public class Body {
	private static final Body body = new Body();
	private Rectangle shape, helmet;
	
	private Body() {}
	
	public static Body getInstance() {
		return body;
	}
	
	// ó�� ���� ���� �� �ʱⰪ�� ����
	public void createBody(double x, double y, double length) {
		shape = new Rectangle(x, y, length, length);
		helmet = new Rectangle(x, y, length, length);
		shape.setFill(new ImagePattern(ImageType.Body.getImage()));
		helmet.setFill(null);
	}
	
	// ĳ���Ͱ� �ٶ󺸴� ���⸸ŭ ȸ��
	public void setDegree(double degree) {
		shape.setRotate(degree);
		helmet.setRotate(degree);
	}

	public Rectangle getShape() {
		return shape;
	}
	
	public void equipHelmet() {
		helmet.setFill(new ImagePattern(ImageType.Body_Helmet.getImage()));
	}
	
	public void unEquipHelmet() {
		helmet.setFill(null);
	}
	
	public Rectangle getHelmet() {
		return helmet;
	}
}
