package zin.game.engine;


/*
 * BlockCheck enum
 * 
 * 물리엔진이 체크할 블록을 나타냄
 * 
 * Tile일 경우 필드에 상자만을 검색하여서 충돌하는지 아닌지를 판단 
 * 
 * Enemy일 경우 적들에게만 해당하는 체크인데, 
 * 적들끼리 서로 통과해서 지나갈 수 없도록 하기 위함이다.
 */

public enum BlockCheckType {
	Tile,
	Enemy;
}
