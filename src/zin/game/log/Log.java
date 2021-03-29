package zin.game.log;

/*
 * Log 클래스
 * 
 * 생성, 에러위치 출력
 * 
 */

public class Log {
	public static void print(Object o) {
		System.out.println("create >>> " + o.getClass().getName());
	}

	public static void error(Object o) {
		System.out.println("Error >>> " + o.getClass().getName());
	}
}
