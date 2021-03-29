package zin.game.effect.sound;


/*
 * Soundtype enum
 * 
 * 사운드의 경로를 여기에 입력을 하면 단어를 입력하여서 출력가능
 * 
 */

public enum SoundType {
	//player
	Inventory_Open("src/zin/game/effect/sound/inventory_open.wav"),
	Walk1("src/zin/game/effect/sound/walk1.wav"),
	Walk2("src/zin/game/effect/sound/walk2.wav"),
	Walk3("src/zin/game/effect/sound/walk3.wav"),
	//item
	Pickup("src/zin/game/effect/sound/pickup.wav"),
	Weapon("src/zin/game/effect/sound/weapon.wav"),
	Portion("src/zin/game/effect/sound/portion.wav"), 
	Drink("src/zin/game/effect/sound/drink.wav"),
	Medikit("src/zin/game/effect/sound/medikit.wav"),
	//activate
	Door_Open("src/zin/game/effect/sound/door_open.wav"), 
	Chest1("src/zin/game/effect/sound/chest1.wav"),
	//fire
	Fire0("src/zin/game/effect/sound/fire0.wav"),
	Fire1("src/zin/game/effect/sound/fire1.wav"),
	Fire2("src/zin/game/effect/sound/fire2.wav"),
	//hit
	Hit1("src/zin/game/effect/sound/hit1.wav"),
	Hit2("src/zin/game/effect/sound/hit2.wav"),
	Hit3("src/zin/game/effect/sound/hit3.wav"),
	//enemy
	EnemyA_Sound1("src/zin/game/effect/sound/enemy/enemyA/enemyA_sound1.wav"),
	EnemyA_Sound2("src/zin/game/effect/sound/enemy/enemyA/enemyA_sound2.wav"),
	EnemyA_Sound3("src/zin/game/effect/sound/enemy/enemyA/enemyA_sound3.wav"),
	EnemyA_Attack("src/zin/game/effect/sound/enemy/enemyA/enemyA_attack.wav");

	private String src;

	private SoundType(String _src) {
		src = _src;
	}

	public String getSRC() {
		return src;
	}
}
