package zin.game.log;

/*
 * Log Ŭ����
 * 
 * ����, ������ġ ���
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
