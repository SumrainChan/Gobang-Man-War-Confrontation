/*
 * ���������
 */

import java.util.Scanner;

public class Main {
	static int INFINITE = 99999999;
	public static void main(String[] args) {		
		Board board = new Board();
		board.showBoard();
		while(true){
			// �˵Ļغ�
			System.out.print("�����������ӵ����꣺");
			Scanner scanner = new Scanner(System.in);
			String sx, sy;
			int x, y;
			while(true){
				while(true){
					sx = scanner.next();
					if(Board.position.keySet().contains(sx))break;
					else System.out.println("���벻�Ϸ������������룡");
				}
				while(true){
					sy = scanner.next();
					if(Board.position.keySet().contains(sy))break;
					else System.out.println("���벻�Ϸ������������룡");
				}
				x = Board.position.get(sx);
				y = Board.position.get(sy);
				if(board.setChess(1, x, y) == null)continue;
				else {
					break;
				}
			}
			if(board.isFive(x, y)){
				board.showBoard();
				System.out.println("��ϲ������Ӯ�ˣ�");
				break;
			}
			
			// ����Ļغ�
			int best_score = -1;
			int best_x = 0;
			int best_y = 0;
			for(int ioffset=0; ioffset < 8; ioffset++){
				int i = Math.max(x-ioffset, 0);
				for(int joffset=0; joffset < 8; joffset++){
					int j = Math.max(y-joffset, 0);
					if(board.isAvail(i, j)){
//						System.out.print(i);System.out.print(" ");System.out.println(j);
						int score = ValueMethod.AlphaBetaValue(new Board(board.intboard), i, j, 1, -INFINITE, INFINITE);
						if(score > best_score){
							best_score = score;
							best_x = i;
							best_y = j;
						}
					}
					j = Math.min(y+joffset, Board.Size-1);
					if(board.isAvail(i, j)){
//						System.out.print(i);System.out.print(" ");System.out.println(j);
						int score = ValueMethod.AlphaBetaValue(new Board(board.intboard), i, j, 1, -INFINITE, INFINITE);
						if(score > best_score){
							best_score = score;
							best_x = i;
							best_y = j;
						}
					}
				}
				i = Math.min(x+ioffset, Board.Size-1);
				for(int joffset=0; joffset < 8; joffset++){
					int j = Math.max(y-joffset, 0);
					if(board.isAvail(i, j)){
//						System.out.print(i);System.out.print(" ");System.out.println(j);
						int score = ValueMethod.AlphaBetaValue(new Board(board.intboard), i, j, 1, -INFINITE, INFINITE);
						if(score > best_score){
							best_score = score;
							best_x = i;
							best_y = j;
						}
					}
					j = Math.min(y+joffset, Board.Size-1);
					if(board.isAvail(i, j)){
//						System.out.print(i);System.out.print(" ");System.out.println(j);
						int score = ValueMethod.AlphaBetaValue(new Board(board.intboard), i, j, 1, -INFINITE, INFINITE);
						if(score > best_score){
							best_score = score;
							best_x = i;
							best_y = j;
						}
					}
				}
			}
//			System.out.println(ValueMethod.BoardValue(board, best_x, best_y));
			board.setChess(2, best_x, best_y);
			System.out.println("�������ӵ����꣺" + Board.int2String(best_x) + ' ' + Board.int2String(best_y));
			board.showBoard();
			if(board.isFive(best_x, best_y)){
				System.out.println("������˼�������ˣ�");
				break;
			}
		}
		
	}

}
