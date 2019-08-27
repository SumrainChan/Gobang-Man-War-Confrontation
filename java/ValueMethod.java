/*
 * ValueMethod类：下子分数评估
 * 方法：
 * 		int AlphaBetaValue(Board board, int x, int y, int depth, int alpha, int beta) -- Alpha-Beta剪枝算法评估
 * 		int BoardValue(Board board, int x, int y) -- 棋子在(x, y)位置的分数评估（用于alpha-beta剪枝的叶子分数评估）
 */

import java.util.HashMap;

public class ValueMethod {
	// Alpha-Beta剪枝算法评估
	/* 
	 * 参数说明：
	 * 		Board board -- 上一层的棋盘
	 * 		int x -- 这层要下子的横坐标
	 * 		int y -- 这层要下子的纵坐标
	 * 		int depth -- 本层的深度
	 * 		int alpha -- Alpha值
	 * 		int beta -- Beta值
	*/
	public static int AlphaBetaValue(Board board, int x, int y, int depth, int alpha, int beta) {
		// 1--人 下的棋         2--程序下的棋
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
				// 取最小层
				if(depth%2 == 0){
					if(next_value < Beta){
						Beta = next_value;
					}
				}
				// 取最大层
				else{
					if(next_value > Alpha){
						Alpha = next_value;
					}
				}
				if(Alpha > Beta){cut=true;break;}
			}
			if(cut)break;
		}
		// 取最小层
		if(depth%2 == 0){
//			System.out.print(depth);System.out.print("  ");System.out.println(Beta);
			return Beta;
		}
		// 取最大层
		else{
			if(depth == 1){
//				System.out.print(depth);System.out.print("  ");System.out.print(Alpha);System.out.print("  ");
//				System.out.println(BoardValue(board, x, y));
				return Alpha + BoardValue(board, x, y);
			}
			return Alpha;
		}
	}
	
	// 棋型分数
	public static HashMap<String,Integer> map = new HashMap<String,Integer>();//设置不同落子情况和相应权值的数组
	static {
		
		//被堵住
		map.put("01", 25);//眠1连
		map.put("02", 22);//眠1连
		map.put("001", 17);//眠1连
		map.put("002", 12);//眠1连
		map.put("0001", 17);//眠1连
		map.put("0002", 12);//眠1连
		
		map.put("0102",25);//眠1连，15
		map.put("0201",22);//眠1连，10
		map.put("0012",15);//眠1连，15
		map.put("0021",10);//眠1连，10
		map.put("01002",25);//眠1连，15
		map.put("02001",22);//眠1连，10
		map.put("00102",17);//眠1连，15
		map.put("00201",12);//眠1连，10
		map.put("00012",15);//眠1连，15
		map.put("00021",10);//眠1连，10

		map.put("01000",25);//活1连，15
		map.put("02000",22);//活1连，10
		map.put("00100",19);//活1连，15
		map.put("00200",14);//活1连，10
		map.put("00010",17);//活1连，15
		map.put("00020",12);//活1连，10
		map.put("00001",15);//活1连，15
		map.put("00002",10);//活1连，10

		//被墙堵住
		map.put("0101",65);//眠2连，40
		map.put("0202",60);//眠2连，30
		map.put("0110",80);//眠2连，40
		map.put("0220",76);//眠2连，30
		map.put("011",80);//眠2连，40
		map.put("022",76);//眠2连，30
		map.put("0011",65);//眠2连，40
		map.put("0022",60);//眠2连，30
		
		map.put("01012",65);//眠2连，40
		map.put("02021",60);//眠2连，30
		map.put("01102",80);//眠2连，40
		map.put("02201",76);//眠2连，30
		map.put("01120",80);//眠2连，40
		map.put("02210",76);//眠2连，30
		map.put("00112",65);//眠2连，40
		map.put("00221",60);//眠2连，30

		map.put("01100",80);//活2连，40
		map.put("02200",76);//活2连，30
		map.put("01010",75);//活2连，40
		map.put("02020",70);//活2连，30
		map.put("00110",75);//活2连，40
		map.put("00220",70);//活2连，30
		map.put("00011",75);//活2连，40
		map.put("00022",70);//活2连，30
		
		//被堵住
		map.put("0111",150);//眠3连，100
		map.put("0222",140);//眠3连，80
		
		map.put("01112",150);//眠3连，100
		map.put("02221",140);//眠3连，80
	
		map.put("01110", 1000);//活3连
		map.put("02220", 1050);//活3连
		map.put("01101",1000);//活3连，130
		map.put("02202",800);//活3连，110
		map.put("01011",1000);//活3连，130
		map.put("02022",800);//活3连，110
		
		map.put("01111",3000);//4连，300
		map.put("02222",4000);//4连，280
		
	}
	
	// 各位置分数
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
	
	// 棋子在(x, y)位置的分数评估（用于alpha-beta剪枝的叶子分数评估）
	public static int BoardValue(Board board, int x, int y){
		int i = x;
		int j = y;
		int score = position_score[x][y];
		//往左延伸
		String ConnectType = "0";
		int jmin = Math.max(0, j-4);
		for(int positionj = j-1; positionj >= jmin; positionj--) {
			//依次加上前面的棋子
			ConnectType = ConnectType + board.getChess(i, positionj);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置中
		Integer valueleft = map.get(ConnectType);
		if(valueleft != null) score += valueleft;
		  
		//往右延伸
		ConnectType = "0";
		int jmax = Math.min(14, j+4);
		for(int positionj = j+1; positionj <= jmax; positionj++) {
			//依次加上前面的棋子
			ConnectType = ConnectType + board.getChess(i, positionj);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置中
		if(ConnectType == "01110")System.out.println(ConnectType);
		Integer valueright = map.get(ConnectType);
		if(valueright != null)score += valueright;
		  
		//联合判断，判断行
		score += unionWeight(valueleft, valueright);
		  
		//往上延伸
		ConnectType = "0";
		int imin = Math.max(0, i-4);
		for(int positioni = i-1; positioni >= imin; positioni--) {
			//依次加上前面的棋子
			ConnectType = ConnectType + board.getChess(positioni, j);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置中
		Integer valueup = map.get(ConnectType);
		if(valueup!=null)score += valueup;
		  
		//往下延伸
		ConnectType = "0";
		int imax=Math.min(14, i+4);
		for(int positioni = i+1; positioni <= imax; positioni++) {
			//依次加上前面的棋子
			ConnectType=ConnectType + board.getChess(positioni, j);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置中
		Integer valuedown = map.get(ConnectType);
		if(valuedown!=null)score += valuedown;
		  
		//联合判断，判断列
		score += unionWeight(valueleft, valueright);
		  
		//往左上方延伸,i,j,都减去相同的数
		ConnectType = "0";
		for(int position = -1; position >= -4; position--) {
			if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
			ConnectType = ConnectType + board.getChess(i+position, j+position);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置
		Integer valueLeftUp = map.get(ConnectType);
		if(valueLeftUp!=null) score += valueLeftUp;
		  
		//往右下方延伸,i,j,都加上相同的数
		ConnectType = "0";
		for(int position=1;position<=4;position++) {
			if((i+position>=0)&&(i+position<=14)&&(j+position>=0)&&(j+position<=14))
			ConnectType = ConnectType + board.getChess(i+position, j+position);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置
		Integer valueRightDown = map.get(ConnectType);
		if(valueRightDown!=null)score += valueRightDown;
		  
		//联合判断，判断行
		score += unionWeight(valueLeftUp,valueRightDown);
		  
		//往左下方延伸,i加,j减
		ConnectType = "0";
		for(int position = 1; position <= 4; position++) {
			if((i+position>=0)&&(i+position<=14)&&(j-position>=0)&&(j-position<=14))
			ConnectType = ConnectType + board.getChess(i+position, j-position);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置
		Integer valueLeftDown = map.get(ConnectType);
		if(valueLeftDown!=null) score += valueLeftDown;
		  
		//往右上方延伸,i减,j加
		ConnectType = "0";
		for(int position = 1; position <= 4; position++) {
			if((i-position>=0)&&(i-position<=14)&&(j+position>=0)&&(j+position<=14))
			ConnectType=ConnectType + board.getChess(i-position, j+position);
		}
		//从数组中取出相应的权值，加到权值数组的当前位置
		Integer valueRightUp = map.get(ConnectType);
		if(valueRightUp!=null) score += valueRightUp;
		  
		//联合判断，判断行
		score += unionWeight(valueLeftDown,valueRightUp);
		
		
		return score;
	}
	// 左右两方向分值综合
	static Integer unionWeight(Integer a,Integer b ) {
		//必须要先判断a,b两个数值是不是null
		if((a==null)||(b==null)) return 0;
		//一一:101/202
	    else if((a>=22)&&(a<=25)&&(b>=22)&&(b<=25)) return 60;
		//一二、二一:1011/2022
		else if(((a>=22)&&(a<=25)&&(b>=76)&&(b<=80))||((a>=76)&&(a<=80)&&(b>=22)&&(b<=25))) return 800;
		//一三、三一、二二:10111/20222
		else if(((a>=10)&&(a<=25)&&(b>=1050)&&(b<=1100))||((a>=1050)&&(a<=1100)&&(b>=10)&&(b<=25))||((a>=76)&&(a<=80)&&(b>=76)&&(b<=80)))
			return 3000;
		//眠三连和眠一连。一三、三一
		else if(((a>=22)&&(a<=25)&&(b>=140)&&(b<=150))||((a>=140)&&(a<=150)&&(b>=22)&&(b<=25))) return 3000;
		//二三、三二:110111
		else if(((a>=76)&&(a<=80)&&(b>=1050)&&(b<=1100))||((a>=1050)&&(a<=1100)&&(b>=76)&&(b<=80))) return 3000;
		else return 0;
	}
}

