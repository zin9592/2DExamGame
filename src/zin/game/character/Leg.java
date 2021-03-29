package zin.game.character;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.effect.image.ImageType;
import zin.game.effect.sound.Sound;
import zin.game.effect.sound.SoundType;

/*
 * Leg Ŭ����
 * 
 * Player Ŭ���� ���� ����
 * 
 * ���� �������� ǥ��
 * 
 */

public class Leg {
	private static final Leg leg = new Leg();
	private Rectangle shape;
	private ArrayList<ImageType> legList;
	private int index;

	private Leg() {
	}

	// ���� ��ǥ �� ũ��
	public void createLeg(double x, double y, double size) {
		shape = new Rectangle(x, y, size, size);
		shape.setFill(new ImagePattern(ImageType.Human_Leg_1.getImage()));
		initLegList();
	}
	
	public static Leg getInstance() {
		return leg;
	}

	// ���� ������
	private void initLegList() {
		legList = new ArrayList<ImageType>();
		legList.add(ImageType.Human_Leg_1);
		legList.add(ImageType.Human_Leg_2);
		legList.add(ImageType.Human_Leg_3);
		legList.add(ImageType.Human_Leg_4);
		legList.add(ImageType.Human_Leg_5);
		legList.add(ImageType.Human_Leg_6);
		legList.add(ImageType.Human_Leg_5);
		legList.add(ImageType.Human_Leg_4);
		legList.add(ImageType.Human_Leg_3);
		legList.add(ImageType.Human_Leg_2);
		legList.add(ImageType.Human_Leg_1);
		legList.add(ImageType.Human_Leg_7);
		legList.add(ImageType.Human_Leg_8);
		legList.add(ImageType.Human_Leg_9);
		legList.add(ImageType.Human_Leg_10);
		legList.add(ImageType.Human_Leg_11);
		legList.add(ImageType.Human_Leg_10);
		legList.add(ImageType.Human_Leg_9);
		legList.add(ImageType.Human_Leg_8);
		legList.add(ImageType.Human_Leg_7);
		index = 0;
	}

	public Rectangle getShape() {
		return shape;
	}

	public void setDegree(double degree) {
		shape.setRotate(degree);
	}

	// ���� ������ ������ �Ҹ� ���
	public void legMove() {
		if (index == 1) {
			Random rand = new Random();
			switch (rand.nextInt(3)) {
			case 0:
				Sound.Play(SoundType.Walk1, 0.8f, false);
				break;
			case 1:
				Sound.Play(SoundType.Walk2, 0.8f, false);
				break;
			case 2:
				Sound.Play(SoundType.Walk3, 0.8f, false);
				break;
			}
		}
		if (index < legList.size() - 1) {
			++index;
		} else {
			index = 0;
		}
		shape.setFill(new ImagePattern(legList.get(index).getImage()));
	}

	// �������� ���߾��� �� ������ ����� ó������ �ʱ�ȭ
	public void legStop() {
		index = 0;
		shape.setFill(new ImagePattern(legList.get(index).getImage()));
	}
}
