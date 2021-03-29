package zin.game.map;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import zin.game.character.Player;
import zin.game.effect.image.ImageType;
import zin.game.effect.object.Chest;
import zin.game.enemy.Enemy;
import zin.game.enemy.EnemyA;
import zin.game.enemy.EnemyB;
import zin.game.item.Item;
import zin.game.log.Log;
import zin.game.window.MainWindow;

/*
 * Map 클래스
 * 
 * 필드를 표현하는 클래스
 * 
 */

public class Map {
	// 각종 물건 모음
	private static ArrayList<Rectangle> tiles;
	private static ArrayList<Chest> chests;
	private static LinkedList<Item> items;
	private static LinkedList<Enemy> enemys;
	private static double offsetX, offsetY;
	
	// 맵 블록 사이즈
	public final static int TILE_SIZE = 120;
	
	// 스테이지1
	private final static String[] STAGE1 = new String[] 
	{ 		"1111111111111111111111111111111", 
			"10000000000000AAA00000000000001", 
			"100000000000000B000000000000001",
			"1000000000000000000000000000001", 
			"1000000000000000000000000000001", 
			"1000000000011113111100000000001",
			"1000000000014000004100000000001", 
			"1000000000010000000100000000001", 
			"1A000000000100000001000000000A1",
			"1AB000000002000P000200000000BA1", 
			"1A000000000100000001000000000A1", 
			"1000000000010000000100000000001",
			"1000000000014000004100000000001", 
			"1000000000011113111100000000001", 
			"1000000000000000000000000000001",
			"1000000000000000000000000000001", 
			"100000000000000B000000000000001", 
			"10000000000000AAA00000000000001",
			"1111111111111111111111111111111"};
	
	// 맵 블럭위치
	private static char[][] map;
	
	public Map() {
		Log.print(this);
		tiles = new ArrayList<Rectangle>();
		chests = new ArrayList<Chest>();
		items = new LinkedList<Item>();
		enemys = new LinkedList<Enemy>();
		drawMap();
	}

	public static ArrayList<Rectangle> getTiles() {
		return tiles;
	}

	public static ArrayList<Chest> getChests() {
		return chests;
	}

	public static LinkedList<Item> getItems() {
		return items;
	}

	public static LinkedList<Enemy> getEnemys() {
		return enemys;
	}

	public static char[][] getMap(){
		return map;
	}
	
	public static void addItems(LinkedList<Item> _items) {
		items.addAll(_items);
	}

	private void drawMap() {
		Pane root = MainWindow.getPane();
		map = new char[STAGE1.length][STAGE1[0].length()];
		
		for (int i = 0; i < STAGE1.length; i++) {
			String line = STAGE1[i];
			for (int j = 0; j < line.length(); j++) {
				char stageBlock = line.charAt(j);
				if(stageBlock != '0' && stageBlock != '1') {
					stageBlock = '0';
				}
				map[i][j] = stageBlock; 
				Rectangle block;
				Chest chest;
				switch (line.charAt(j)) {
				case '0': // none
					break;
				case '1': // block
					// 벽 이미지 생성
					block = new Rectangle(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					block.setFill(new ImagePattern(ImageType.Block.getImage()));
					tiles.add(block);
					root.getChildren().add(block);
					break;
				case '2': // door
					// 문 이미지 생성
					block = new Rectangle(j * TILE_SIZE + TILE_SIZE * 0.4, i * TILE_SIZE, TILE_SIZE / 5, TILE_SIZE);
					block.setFill(Color.BROWN);
					block.setStroke(Color.BLACK);
					block.setId("door");
					tiles.add(block);
					root.getChildren().add(block);
					break;
				case '3': // door
					// 문 이미지 생성
					block = new Rectangle(j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE * 0.4, TILE_SIZE, TILE_SIZE / 5);
					block.setFill(Color.BROWN);
					block.setStroke(Color.BLACK);
					block.setId("door");
					tiles.add(block);
					root.getChildren().add(block);
					break;
				case '4': // chest
					// 상자 이미지 생성
					block = new Rectangle(j * TILE_SIZE+0.25*TILE_SIZE, i * TILE_SIZE+0.25*TILE_SIZE, TILE_SIZE*0.5, TILE_SIZE*0.5);
					block.setFill(new ImagePattern(ImageType.Locked_Chest.getImage()));
					block.setId("chest");
					tiles.add(block);
					root.getChildren().add(block);
					// 상자 아이템 생성
					chest = new Chest(block);
					chests.add(chest);
					break;
				case '5': // openedChest
					// 상자 이미지 생성
					block = new Rectangle(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					block.setFill(new ImagePattern(ImageType.Open_Chest.getImage()));
					block.setId("opened_chest");
					tiles.add(block);
					root.getChildren().add(block);
					// 상자 아이템 생성
					chest = new Chest(block);
					chests.add(chest);
					break;
				case 'A': // 적 타입A
					Enemy enemyA = new EnemyA((j + 0.5) * TILE_SIZE, (i + 0.5) * TILE_SIZE);
					enemys.add(enemyA);
					break;	
				case 'B': // 적 타입B
					Enemy enemyB = new EnemyB((j + 0.5) * TILE_SIZE, (i + 0.5) * TILE_SIZE);
					enemys.add(enemyB);
					break;	
				case 'P': // player 위치
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					offsetX = (j + 0.5) * TILE_SIZE - screenSize.getWidth() * 0.5;
					offsetY = (i + 0.5) * TILE_SIZE - screenSize.getHeight() * 0.5;
					break;
				}
			}
		}
	}
	public void moveCharacter() {	// player의 위치를 설정
		Player player = Player.getInstance();
		player.moveCharacter(-offsetX, -offsetY);
	}
}
