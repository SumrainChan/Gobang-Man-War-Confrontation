/*
 * ValueMethod�ࣺ���ӷ�������
 * ������
 * 		int AlphaBetaValue(Board board, int x, int y, int depth, int alpha, int beta) -- Alpha-Beta��֦�㷨����
 * 		int BoardValue(Board board, int x, int y) -- ������(x, y)λ�õķ�������������alpha-beta��֦��Ҷ�ӷ���������
 */

import java.util.HashMap;

public class ValueMethod {
	// Alpha-Beta��֦�㷨����
	/* 
	 * ����˵����
	 * 		Board board -- ��һ�������
	 * 		int x -- ���Ҫ���ӵĺ�����
	 * 		int y -- ���Ҫ���ӵ�������
	 * 		int depth -- ��������
	 * 		int alpha -- Alphaֵ
	 * 		int beta -- Betaֵ
	*/
	public static int AlphaBetaValue(Board board, int x, int y, int depth, int alpha, int beta) {
		// 1--�� �µ���         2--�����µ���
		int chess = depth%2 == 0? 1: 2;
		board.setChess(chess, x, y);
//		System.out.println(depth);
//		System.out.print(x);System.out.print(" ");System.out.println(y);
//		board.showBoard();
		if(depth == 3){
			int score = BoardValue(board, x, y);
//			board.showBoard();
//			System.out.print(depth);System.out.print("  ");System.out.println(score);
			return score;
		}
		int Beta = beta;
		int Alpha = alpha;
		boolean cut = false;
		for(int i=Math.max(x-5, 0); i <= Math.min(x+5, Board.Size-1); i++){
			for(int j=Math.max(y-5, 0); j <= Math.min(y+5, Board.Size-1); j++){
				if(!board.isAvail(i, j))continue;
				int next_value = AlphaBetaValue(new Board(board.intboard), i, j, depth+1, Alpha, Beta);
				// ȡ��С��
				if(depth%2 == 0){
					if(next_value < Beta){
						Beta = next_value;
					}
				}
				// ȡ����
				else{
					if(next_value > Alpha){
						Alpha = next_value;
					}
				}
				if(Alpha > Beta){cut=true;break;}
			}
			if(cut)break;
		}
		// ȡ��С��
		if(depth%2 == 0){
//			System.out.print(depth);System.out.print("  ");System.out.println(Beta);
			return Beta;
		}
		// ȡ����
		else{
			if(depth == 1){
//				System.out.print(depth);System.out.print("  ");System.out.print(Alpha);System.out.print("  ");
//				System.out.println(BoardValue(board, x, y));
				return Alpha + BoardValue(board, x, y);
			}
			return Alpha;
		}
	}
	
	// ���ͷ���
	public static HashMap<String,Integer> map = new HashMap<String,Integer>();//���ò�ͬ�����������ӦȨֵ������
	static {
		
		//����ס
		map.put("01", 25);//��1��
		map.put("02", 22);//��1��
		map.put("001", 17);//��1��
		map.put("002", 12);//��1��
		map.put("0001", 17);//��1��
		map.put("0002", 12);//��1��
		
		map.put("0102",25);//��1����15
		map.put("0201",22);//��1����10
		map.put("0012",15);//��1����15
		map.put("0021",10);//��1����10
		map.put("01002",25);//��1����15
		map.put("02001",22);//��1����10
		map.put("00102",17);//��1����15
		map.put("00201",12);//��1����10
		map.put("00012",15);//��1����15
		map.put("00021",10);//��1����10

		map.put("01000",25);//��1����15
		map.put("02000",22);//��1����10
		map.put("00100",19);//��1����15
		map.put("00200",14);//��1����10
		map.put("00010",17);//��1����15
		map.put("00020",12);//��1����10
		map.put("00001",15);//��1����15
		map.put("00002",10);//��1����10

		//��ǽ��ס
		map.put("0101",65);//��2����40
		map.put("0202",60);//��2����30
		map.put("0110",80);//��2����40
		map.put("0220",76);//��2����30
		map.put("011",80);//��2����40
		map.put("022",76);//��2����30
		map.put("0011",65);//��2����40
		map.put("0022",60);//��2����30
		
		map.put("01012",65);//��2����40
		map.put("02021",60);//��2����30
		map.put("01102",80);//��2����40
		map.put("02201",76);//��2����30
		map.put("01120",80);//��2����40
		map.put("02210",76);//��2����30
		map.put("00112",65);//��2����40
		map.put("00221",60);//��2����30

		map.put("01100",80);//��2����40
		map.put("02200",76);//��2����30
		map.put("01010",75);//��2����40
		map.put("02020",70);//��2����30
		map.put("00110",75);//��2����40
		map.put("00220",70);//��2����30
		map.put("00011",75);//��2����40
		map.put("00022",70);//��2����30
		
		//����ס
		map.put("0111",150);//��3����100
		map.put("0222",140);//��3����80
		
		map.put("01112",150);//��3����100
		map.put("02221",140);//��3����80
	
		map.put("01110", 1000);//��3��
		map.put("02220", 1050);//��3��
		map.put("01101",1000);//��3����130
		map.put("02202",800);//��3����110
		map.put("01011",1000);//��3����130
		map.put("02022",800);//��3����110
		
		map.put("01111",3000);//4����300
		map.put("02222",4000);//4����280
		
	}
	
	// ��λ�÷���
	static int[][] position_score = {

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },

			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },

			{ 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },

			{ 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 5, 6, 6, 6, 5, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 5, 6, 6, 6, 5, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1, 0 },

			{ 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0 },

			{ 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },

			{ 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },

			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	
	// ������(x, y)λ�õķ�������������alpha-beta��֦��Ҷ�ӷ���������
	public static int BoardValue(Board board, int x, int y){
		int i = x;
		int j = y;
		int score = position_score[x][y];
		//��������
		String ConnectType = "0";
		int jmin = Math.max(0, j-4);
		for(int positionj = j-1; positionj >= jmin; positionj--) {
			//���μ���ǰ�������
			ConnectType = ConnectType + board.getChess(i, positionj);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
		Integer valueleft = map.get(ConnectType);
		if(valueleft != null) score += valueleft;
		  
		//��������
		ConnectType = "0";
		int jmax = Math.min(14, j+4);
		for(int positionj = j+1; positionj <= jmax; positionj++) {
			//���μ���ǰ�������
			ConnectType = ConnectType + board.getChess(i, positionj);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
		if(ConnectType == "01110")System.out.println(ConnectType);
		Integer valueright = map.get(ConnectType);
		if(valueright != null)score += valueright;
		  
		//�����жϣ��ж���
		score += unionWeight(valueleft, valueright);
		  
		//��������
		ConnectType = "0";
		int imin = Math.max(0, i-4);
		for(int positioni = i-1; positioni >= imin; positioni--) {
			//���μ���ǰ�������
			ConnectType = ConnectType + board.getChess(positioni, j);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
		Integer valueup = map.get(ConnectType);
		if(valueup!=null)score += valueup;
		  
		//��������
		ConnectType = "0";
		int imax=Math.min(14, i+4);
		for(int positioni = i+1; positioni <= imax; positioni++) {
			//���μ���ǰ�������
			ConnectType=ConnectType + board.getChess(positioni, j);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ����
		Integer valuedown = map.get(ConnectType);
		if(valuedown!=null)score += valuedown;
		  
		//�����жϣ��ж���
		score += unionWeight(valueleft, valueright);
		  
		//�����Ϸ�����,i,j,����ȥ��ͬ����
		ConnectType = "0";
		for(int position = -1; position >= -4; position--) {
			if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
			ConnectType = ConnectType + board.getChess(i+position, j+position);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
		Integer valueLeftUp = map.get(ConnectType);
		if(valueLeftUp!=null) score += valueLeftUp;
		  
		//�����·�����,i,j,��������ͬ����
		ConnectType = "0";
		for(int position=1;position<=4;position++) {
			if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
			ConnectType = ConnectType + board.getChess(i+position, j+position);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
		Integer valueRightDown = map.get(ConnectType);
		if(valueRightDown!=null)score += valueRightDown;
		  
		//�����жϣ��ж���
		score += unionWeight(valueLeftUp,valueRightDown);
		  
		//�����·�����,i��,j��
		ConnectType = "0";
		for(int position = 1; position <= 4; position++) {
			if((i+position>=0)&&(i+position<=14)&&(j-position>=0)&&(j-position<=14))
			ConnectType = ConnectType + board.getChess(i+position, j-position);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
		Integer valueLeftDown = map.get(ConnectType);
		if(valueLeftDown!=null) score += valueLeftDown;
		  
		//�����Ϸ�����,i��,j��
		ConnectType = "0";
		for(int position = 1; position <= 4; position++) {
			if((i-position>=0)&&(i-position<=14)&&(j+position>=0)&&(j+position<=14))
			ConnectType=ConnectType + board.getChess(i-position, j+position);
		}
		//��������ȡ����Ӧ��Ȩֵ���ӵ�Ȩֵ����ĵ�ǰλ��
		Integer valueRightUp = map.get(ConnectType);
		if(valueRightUp!=null) score += valueRightUp;
		  
		//�����жϣ��ж���
		score += unionWeight(valueLeftDown,valueRightUp);
		
		
		return score;
	}
	// �����������ֵ�ۺ�
	static Integer unionWeight(Integer a,Integer b ) {
		//����Ҫ���ж�a,b������ֵ�ǲ���null
		if((a==null)||(b==null)) return 0;
		//һһ:101/202
	    else if((a>=22)&&(a<=25)&&(b>=22)&&(b<=25)) return 60;
		//һ������һ:1011/2022
		else if(((a>=22)&&(a<=25)&&(b>=76)&&(b<=80))||((a>=76)&&(a<=80)&&(b>=22)&&(b<=25))) return 800;
		//һ������һ������:10111/20222
		else if(((a>=10)&&(a<=25)&&(b>=1050)&&(b<=1100))||((a>=1050)&&(a<=1100)&&(b>=10)&&(b<=25))||((a>=76)&&(a<=80)&&(b>=76)&&(b<=80)))
			return 3000;
		//����������һ����һ������һ
		else if(((a>=22)&&(a<=25)&&(b>=140)&&(b<=150))||((a>=140)&&(a<=150)&&(b>=22)&&(b<=25))) return 3000;
		//����������:110111
		else if(((a>=76)&&(a<=80)&&(b>=1050)&&(b<=1100))||((a>=1050)&&(a<=1100)&&(b>=76)&&(b<=80))) return 3000;
		else return 0;
	}
}

