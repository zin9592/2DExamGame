package zin.game.enemy.ai;

/*
 * MoveType enum
 * 
 * ���� �̵�����
 * ���¿� ���� �̵��ϴ� ����� �ٸ���.
 * Move : �Ϲ����� �̵�
 * MoveX, MoveY : Y��(Ȥ�� X��)���� �̵��ϴٰ� Y�࿡ ��ֹ��� ���� ��
 *                ��ֹ� ���ĸ� ���ؼ� X�����θ� �̵��� �Ͽ���
 *                ���� �̵��� ������ ������ �̻��¸� ����
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
