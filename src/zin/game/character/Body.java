package zin.game.character;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.effect.image.ImageType;

/*
 * Body 클래스
 * 
 * Player 클래스의 포함관계
 * 
 * 1. 플레이어의 몸을 표현
 * 2. 플레이어의 모자를 표현
 */

public class Body {
	private static final Body body = new Body();
	private Rectangle shape, helmet;
	
	private Body() {}
	
	public static Body getInstance() {
		return body;
	}
	
	// 처음 몸을 만들 때 초기값을 저장
	public void createBody(double x, double y, double length) {
		shape = new Rectangle(x, y, length, length);
		helmet = new Rectangle(x, y, length, length);
		shape.setFill(new ImagePattern(ImageType.Body.getImage()));
		helmet.setFill(null);
	}
	
	// 캐릭터가 바라보는 방향만큼 회전
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
