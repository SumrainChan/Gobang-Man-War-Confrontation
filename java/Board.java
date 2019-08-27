/*
 * Board类：五子棋棋盘
 * 构造器：
 * 		Board() -- 无参数构造器
 *		Board(int[][] intboard) -- 通过传入int数组棋盘构造Board实例
 * 方法：
 * 		boolean isAvail(int x, int y) -- 判断(x, y)是否有棋子
 * 		Board setChess(int chess, int x, int y) -- 在(x, y)坐标上放1/2棋子，1代表人，2代表程序
 * 		String getChess(int x, int y) -- 获得(x, y)坐标上的棋子类型，0代表无棋子，1代表人，2代表程序
 * 		boolean isFive(int x, int y) -- 判断下了(x, y)坐标的棋子后是否达成五子连珠
 * 		String int2String(int i) -- 数字转成棋盘字母
 * 		void showBoard() -- 打印棋盘，+代表无棋子，o代表人，x代表程序
*/

import java.util.HashMap;
import java.util.Map;

public class Board {
	static int Size = 15;
	int[][] intboard;
	public Board(){
		intboard = new int[15][15];
		for(int i=0; i < Size; i++){
			for(int j=0; j < Size; j++){
				intboard[i][j] = 0;
			}
		}
	}
	
	// 通过传入int数组棋盘构造Board实例
	public Board(int[][] intboard){
		int[][] new_intboard = new int[15][15];
		for(int i=0; i < Size; i++){
			for(int j=0; j < Size; j++)new_intboard[i][j] = intboard[i][j];
		}
		this.intboard = new_intboard;
	}
	
	// 判断(x, y)是否有棋子
	public boolean isAvail(int x, int y){
		if(intboard[x][y] == 0)return true;
		else return false;
	}
	
	// 在(x, y)坐标上放1/2棋子，1代表人，2代表程序
	public Board setChess(int chess, int x, int y) {
		if(!isAvail(x, y)){
			System.out.println("当前位置已有棋子，请重新输入！");
			return null;
		}
		intboard[x][y] = chess;
		return this;
	}
	
	// 获得(x, y)坐标上的棋子类型，0代表无棋子，1代表人，2代表程序
	public String getChess(int x, int y){
		return String.valueOf(intboard[x][y]);
	}
	
	// 判断下了(x, y)坐标的棋子后是否达成五子连珠
	public boolean isFive(int x, int y){
		int chess = intboard[x][y];
		int cnt = 0;
		for(int j = Math.max(0, y-4); j <= Math.min(Size-1, y+4); j++) {
			if(intboard[x][j] == chess){
				cnt++;
				if(cnt == 5)return true;
			}
			else {
				cnt = 0;
			}
		}
		
		cnt = 0;
		for(int i = Math.max(0, x-4); i <= Math.min(Size-1, x+4); i++) {
			if(intboard[i][y] == chess){
				cnt++;
				if(cnt == 5)return true;
			}
			else {
				cnt = 0;
			}
		}
		
		cnt = 0;
		for(int offset = -4; offset <= 4; offset++) {
			int i = x + offset;
			int j = y + offset;
			if(i < 0 | i > Size-1 | j < 0 | j > Size-1)continue;
			if(intboard[i][j] == chess){
				cnt++;
				if(cnt == 5)return true;
			}
			else {
				cnt = 0;
			}
		}
		
		cnt = 0;
		for(int offset = -4; offset <= 4; offset++) {
			int i = x - offset;
			int j = y + offset;
			if(i < 0 | i > Size-1 | j < 0 | j > Size-1)continue;
			if(intboard[i][j] == chess){
				cnt++;
				if(cnt == 5)return true;
			}
			else {
				cnt = 0;
			}
		}
		return false;
	}
	
	// 棋盘的字母与数字匹配
	static HashMap<String, Integer> position = new HashMap<>(); 
	static{
		position.put("A", 0);
		position.put("B", 1);
		position.put("C", 2);
		position.put("D", 3);
		position.put("E", 4);
		position.put("F", 5);
		position.put("G", 6);
		position.put("H", 7);
		position.put("I", 8);
		position.put("J", 9);
		position.put("K", 10);
		position.put("L", 11);
		position.put("M", 12);
		position.put("N", 13);
		position.put("O", 14);
	}
	
	// 数字转成棋盘字母
	static String int2String(int i){
		String key = "";
		for(Map.Entry<String, Integer> entry : position.entrySet()){
			if(i == entry.getValue()){key = entry.getKey();break;}
		}
		return key;
	}
	
	// 打印棋盘，+代表无棋子，o代表人，x代表程序
	public void showBoard(){
		System.out.println("  A B C D E F G H I J K L M N O");
		char[] letter = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'}; 
		for(int i=0; i < Size; i++){
			System.out.print(letter[i]);
			for(int j=0; j < Size; j++){
				if(intboard[i][j] == 1)System.out.print(" " + "o");
				else if(intboard[i][j] == 2)System.out.print(" " + "x");
				else System.out.print(" " + "+");
			}
			System.out.println();
		}
	}
}
