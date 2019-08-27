/*
 * Board�ࣺ����������
 * ��������
 * 		Board() -- �޲���������
 *		Board(int[][] intboard) -- ͨ������int�������̹���Boardʵ��
 * ������
 * 		boolean isAvail(int x, int y) -- �ж�(x, y)�Ƿ�������
 * 		Board setChess(int chess, int x, int y) -- ��(x, y)�����Ϸ�1/2���ӣ�1�����ˣ�2�������
 * 		String getChess(int x, int y) -- ���(x, y)�����ϵ��������ͣ�0���������ӣ�1�����ˣ�2�������
 * 		boolean isFive(int x, int y) -- �ж�����(x, y)��������Ӻ��Ƿ�����������
 * 		String int2String(int i) -- ����ת��������ĸ
 * 		void showBoard() -- ��ӡ���̣�+���������ӣ�o�����ˣ�x�������
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
	
	// ͨ������int�������̹���Boardʵ��
	public Board(int[][] intboard){
		int[][] new_intboard = new int[15][15];
		for(int i=0; i < Size; i++){
			for(int j=0; j < Size; j++)new_intboard[i][j] = intboard[i][j];
		}
		this.intboard = new_intboard;
	}
	
	// �ж�(x, y)�Ƿ�������
	public boolean isAvail(int x, int y){
		if(intboard[x][y] == 0)return true;
		else return false;
	}
	
	// ��(x, y)�����Ϸ�1/2���ӣ�1�����ˣ�2�������
	public Board setChess(int chess, int x, int y) {
		if(!isAvail(x, y)){
			System.out.println("��ǰλ���������ӣ����������룡");
			return null;
		}
		intboard[x][y] = chess;
		return this;
	}
	
	// ���(x, y)�����ϵ��������ͣ�0���������ӣ�1�����ˣ�2�������
	public String getChess(int x, int y){
		return String.valueOf(intboard[x][y]);
	}
	
	// �ж�����(x, y)��������Ӻ��Ƿ�����������
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
	
	// ���̵���ĸ������ƥ��
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
	
	// ����ת��������ĸ
	static String int2String(int i){
		String key = "";
		for(Map.Entry<String, Integer> entry : position.entrySet()){
			if(i == entry.getValue()){key = entry.getKey();break;}
		}
		return key;
	}
	
	// ��ӡ���̣�+���������ӣ�o�����ˣ�x�������
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
