package zin.game.effect.image;

import javafx.scene.image.Image;

/*
 * Imagetype enum
 * 
 * 이미지의 경로를 여기에 입력을 하면
 * 특정 단어를 입력하여서 이미지를 불러올 수 있음
 * 
 */

public enum ImageType {
	Bullet("zin/game/effect/image/object/bullet.png"), 
	Block("zin/game/effect/image/map/block/block.png"),
	Locked_Chest("zin/game/effect/image/map/block/locked_chest.png"),
	Open_Chest("zin/game/effect/image/map/block/open_chest.png"),
	// Hit
	Hit("zin/game/effect/image/object/hit.png"),
	// Weapon
	AK47("zin/game/effect/image/item/weapon/ak47/ak47.png"),
	Pickup_AK47("zin/game/effect/image/item/weapon/ak47/pickup_ak47.png"),
	Pickup_AK47_Fire("zin/game/effect/image/item/weapon/ak47/pickup_ak47_fire.png"),
	Glock18("zin/game/effect/image/item/weapon/glock18/glock18.png"),
	Pickup_Glock18("zin/game/effect/image/item/weapon/glock18/pickup_glock18.png"),
	Pickup_Glock18_Fire("zin/game/effect/image/item/weapon/glock18/pickup_glock18_fire.png"),
	UZI("zin/game/effect/image/item/weapon/uzi/uzi.png"),
	Pickup_UZI("zin/game/effect/image/item/weapon/uzi/pickup_uzi.png"),
	Pickup_UZI_Fire("zin/game/effect/image/item/weapon/uzi/pickup_uzi_fire.png"),
	//equipment
	Helmet("zin/game/effect/image/item/equipment/helmet.png"),
	Chest("zin/game/effect/image/item/equipment/chest.png"),
	Leggings("zin/game/effect/image/item/equipment/leggings.png"),
	Shoes("zin/game/effect/image/item/equipment/shoes.png"),
	// Item
	Water("zin/game/effect/image/item/consumable/water.png"),
	Medikit("zin/game/effect/image/item/consumable/medikit.png"),
	// Player UI
	Health("zin/game/effect/image/character/ui/health.png"), 
	Stemina("zin/game/effect/image/character/ui/stemina.png"),
	Emety("zin/game/effect/image/character/ui/emety.png"), 
	Status("zin/game/effect/image/character/ui/status.png"),
	Crosshair("zin/game/effect/image/character/ui/crosshair.png"),
	// UI
	StartBtn("zin/game/effect/image/character/ui/start_button.png"),
	StartBtnSelect("zin/game/effect/image/character/ui/start_button_select.png"),
	KeyBtn("zin/game/effect/image/character/ui/key_button.png"),
	KeyBtnSelect("zin/game/effect/image/character/ui/key_button_select.png"),
	ExitBtn("zin/game/effect/image/character/ui/exit_button.png"),
	ExitBtnSelect("zin/game/effect/image/character/ui/exit_button_select.png"),
	BackBtn("zin/game/effect/image/character/ui/back_button.png"),
	BackBtnSelect("zin/game/effect/image/character/ui/back_button_select.png"),
	// Body
	Body("zin/game/effect/image/character/body/body.png"), 
	Body_Helmet("zin/game/effect/image/character/body/helmet.png"), 
	Hand_1("zin/game/effect/image/character/hand/hand1.png"),
	Hand_2("zin/game/effect/image/character/hand/hand2.png"),
	Human_Leg_1("zin/game/effect/image/character/leg/human_leg_1.png"),
	Human_Leg_2("zin/game/effect/image/character/leg/human_leg_2.png"),
	Human_Leg_3("zin/game/effect/image/character/leg/human_leg_3.png"),
	Human_Leg_4("zin/game/effect/image/character/leg/human_leg_4.png"),
	Human_Leg_5("zin/game/effect/image/character/leg/human_leg_5.png"),
	Human_Leg_6("zin/game/effect/image/character/leg/human_leg_6.png"),
	Human_Leg_7("zin/game/effect/image/character/leg/human_leg_7.png"),
	Human_Leg_8("zin/game/effect/image/character/leg/human_leg_8.png"),
	Human_Leg_9("zin/game/effect/image/character/leg/human_leg_9.png"),
	Human_Leg_10("zin/game/effect/image/character/leg/human_leg_10.png"),
	Human_Leg_11("zin/game/effect/image/character/leg/human_leg_11.png"),
	Enemy_Health("zin/game/effect/image/enemy/enemy_health_bar.png"),
	Enemy_Health_Emety("zin/game/effect/image/enemy/enemy_health_emety_bar.png"),
	// enemy
	EnemyA("zin/game/effect/image/enemy/enemyA/enemyA.png"),
	EnemyA_Attack_1("zin/game/effect/image/enemy/enemyA/attack/enemyA_attack1.png"),
	EnemyA_Attack_2("zin/game/effect/image/enemy/enemyA/attack/enemyA_attack2.png"),
	EnemyA_Attack_3("zin/game/effect/image/enemy/enemyA/attack/enemyA_attack3.png"),
	EnemyB("zin/game/effect/image/enemy/enemyB/enemyB.png"),
	EnemyB_Attack_1("zin/game/effect/image/enemy/enemyB/attack/enemyB_attack1.png"),
	EnemyB_Attack_2("zin/game/effect/image/enemy/enemyB/attack/enemyB_attack2.png"),
	EnemyB_Attack_3("zin/game/effect/image/enemy/enemyB/attack/enemyB_attack3.png"),
	EnemyB_Attack_4("zin/game/effect/image/enemy/enemyB/attack/enemyB_attack4.png"),
	EnemyB_Attack_5("zin/game/effect/image/enemy/enemyB/attack/enemyB_attack5.png"),
	EnemyB_Attack_6("zin/game/effect/image/enemy/enemyB/attack/enemyB_attack6.png");

	private String src;

	private ImageType(String _src) {
		src = _src;
	}

	public String getSRC() {
		try {
			return "file://" + this.getClass().getResource("/").getPath() + src;
		}catch(Exception e) {
			System.out.println("ImaveType 오류");
		}
		return null;
	}

	public Image getImage() {
		return new Image(getSRC());
	}
}
