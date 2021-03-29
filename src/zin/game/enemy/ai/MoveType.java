package zin.game.enemy.ai;

/*
 * MoveType enum
 * 
 * 적의 이동종류
 * 상태에 따라서 이동하는 방법이 다르다.
 * Move : 일반적인 이동
 * MoveX, MoveY : Y축(혹은 X축)으로 이동하다가 Y축에 장애물이 있을 때
 *                장애물 돌파를 위해서 X축으로만 이동을 하여서
 *                위로 이동이 가능할 때까지 이상태를 유지
 *        
 */

public enum MoveType {
		Move("Move"),
		MoveX("MoveX"),
		MoveY("MoveY");
		
		private String type;
		
		private MoveType(String _type) {
			type = _type;
		}
		public String getString() {
			return type;
		}
}
