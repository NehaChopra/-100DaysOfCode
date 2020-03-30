//https://practice.geeksforgeeks.org/problems/n-queen-problem/0
package Backtracking;

import java.util.Scanner;
import java.util.Stack;

class NQueenProblem {
	public static int num;
	public static int rowTrip = 0;
	public static int lastCol = 0;
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int testCases = input.nextInt();
		for(int index=0; index<testCases; index++){
			num = input.nextInt();
			nQueenProblem();
		}
	}
	public static void nQueenProblem(){
		Stack<Integer> stack = new Stack<Integer>();
		boolean[][] board = new boolean[num][num];
		if(DFSUtil(0, 0, board, stack)){
			printBoard(board,stack);
			System.out.println();
		}
	}
	public static void printBoard(boolean[][] board,Stack<Integer> stack){
		System.out.print("[");
		while(stack.size() > 0){
			System.out.print(stack.pop() + " ");
		}
		System.out.print("]");
	}
	public static void printSolution(boolean[][] board){
		for(int index=0; index<num;index++) {
			for(int jindex=0; jindex<num;jindex++) {
				System.out.print(board[index][jindex] + "       ");
			}
			System.out.println();
		}
	}
	public static boolean DFSUtil(int col, int rowTrip, boolean[][] board, Stack<Integer> stack){
		if(col>=num){
			printSolution(board);
			return true;
		}
		for(int rowIndex=0; rowIndex<num; rowIndex++){	
			if(isSafe(rowIndex, col, board)){
				board[rowIndex][col] = true;
				stack.push(rowIndex + 1);
				if(DFSUtil(col+1,rowTrip, board, stack)){
					return true;
				}
				board[rowIndex][col] = false;
				stack.pop();
			}
		}
		return false;
	}
	public static boolean isSafe(int row, int col, boolean[][] board){
		for(int colIndex=0; colIndex<col; colIndex++){
			if(board[row][colIndex]){
				return false;
			}
		}
		for(int rowIndex=row-1, colIndex=col-1; 
				rowIndex>=0 && colIndex>=0; 
				rowIndex--,colIndex--
			){
				if(board[rowIndex][colIndex]){
					return false;
				}
		}
		for(int rowIndex=row+1, colIndex=col-1; 
				rowIndex<num && colIndex>=0; 
				rowIndex++, colIndex--){
			if(board[rowIndex][colIndex]){
				return false;
			}
		}
		return true;
	}
}
