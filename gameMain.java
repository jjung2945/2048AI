import java.util.ArrayList;
import java.util.Arrays;

public class gameMain {
	
	int [][] initial = new int[4][4];
	int [] findmin = new int[4];
	int [] countmin = new int[4];
	
//	public void moves(char move, State s) {
//		if(move=='w') {
//			pushUp(board);
//			pushUp(board);
//			addUp();
//			pushUp(board);
//			pushUp(board);
//			System.out.println(" ");
//			System.out.println("------");
//			s.randomGen(board);
//			s.print(s.board);
//		}
//		if(move=='s'){
//			pushDown(board);
//			pushDown(board);
//			addDown();
//			pushDown(board);
//			pushDown(board);
//			System.out.println(" ");
//			System.out.println("------");
//			s.randomGen(board);
//			s.print(s.board);
//		}
//		if(move=='d') {
//			pushRight(board);
//			pushRight(board);
//			addRight();
//			pushRight(board);
//			pushRight(board);
//			System.out.println(" ");
//			System.out.println("------");
//			s.randomGen(board);
//			s.print(s.board);
//		}
//		if(move=='a') {
//			pushLeft(board);
//			pushLeft(board);
//			addLeft();
//			pushLeft(board);
//			pushLeft(board);
//			System.out.println(" ");
//			System.out.println("------");
//			s.randomGen(board);
//			s.print(s.board);
//		}
//		
//		if(s.terminalCheck(board)) {
//			int [][] cloneboard = board.clone();
//			pushUp(cloneboard);
//			pushDown(cloneboard);
//			pushLeft(cloneboard);
//			pushRight(cloneboard);
//			if(cloneboard ==board) {
//				
//			}
//		}
//		
//		if(move=='e'){
//			
//			System.out.println("game over thanks for playing");
//			
//		}
//		else {
//			System.err.println("You played wrong move dumb ass");
//		}
//		}
	
	
	
	public State determine(State s) {
		 int min = Arrays.stream(findmin).min().getAsInt();
		 
	}
	public void printarray(int [][] b) {
		for(int i=0; i<4; i++) {
			System.out.println(" ");
			for(int j=0; j<4; j++) {
				System.out.print(" "+ b[i][j]);
			}
		}
	}
	
	public int[][] arrayReset(int [][] b ) {
		int [][] cloneboard = new int [4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				cloneboard[i][j]= b[i][j];
			}
		}
		return cloneboard;
	}
	
	public void tryMoves(State s) {
		int [][] c = arrayReset(s.board);
		int countUp =0;
		int countDown =0;
		int countRight =0; 
		int countLeft =0;
		s.print();
		
		for(int i =0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(tryMovesUp(s).board[i][j]==c[i][j]) {
					countUp++;
					s.board =c;
					printarray(c);
					s.print();
				}
				
				if(tryMovesDown(s).board[i][j]==c[i][j]) {
					countDown++;
					s.board =c;
					System.out.println("down print : ");
					
					printarray(c);
					s.print();
				}
				
				if(tryMovesRight(s).board[i][j]==c[i][j]) {
					countRight++;
					s.board =c;
					s.print();
				}
				
				if(tryMovesLeft(s).board[i][j]==c[i][j]) {
					countLeft++;
					s.board =c;
					s.print();
				}
			}
		}
		
		countmin[0]=countUp;
		countmin[1]=countDown;
		countmin[2]=countRight;
		countmin[3]=countLeft;
	}
	
	public State minChange(State s) {
		int k;
		int min =-1;
		for( k =0; k<4; k++) {
			if(countmin[k]<min) {
				min = countmin[k];
			}
		}
		if(min==countmin[0]) {
			return tryMovesUp(s);
		}
		if(min==countmin[1]) {
			return tryMovesDown(s);
		}
		if(min == countmin[2]) {
			return tryMovesRight(s);
		}
		if(min == countmin[3]) {
			return tryMovesLeft(s);
		}
		else return s;
	}

	
	public State greatestNumber(State s) {
		int max =-1;
		tryMoves(s);
		s.print();
			for(int i=0; i<4; i++) {
				if(max<virChildren.get(i).numberFinder()) {
					max = virChildren.get(i).numberFinder();
					if(virChildren.get(i).numberFinder()<=max) {
						System.out.println(" ");
						virChildren.remove(i);
					}
				}
			}
		if(!virChildren.isEmpty()) {
			virChildren.get(0).print();
		}
		return virChildren.get(0);
	}
	
	
	public void cutBranch(State s) {
		for(State c: s.Children) { //use moves
			minChange(c);
		}
	}
	
	public int count(State s) {
		int answer=0;
		for(int i =0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(s.board[i][j]!=0) {
					answer++;
				}
			}
		}
		return answer;
	}
	
	public void generateTree(State s) { //generate only depth of 3
		s.generateChildren();
		for(State c : s.Children) {
			c.generateChildren();
				for(State x: c.Children) {
					x.generateChildren();
				}			
		}	
	}
	
	
	public static void main(String [] args) {
		gameMain game = new gameMain();
		int [][] initial = new int[4][4];
		initial[0][1] =2;
		initial[1][2]=8;
		initial[1][3]=8;
		State s =new State(initial, null);
		game.generateTree(s);
		game.tryMoves(s);
		System.out.print("countmin 0 : " +game.countmin[0]);
		System.out.print(" countmin 1 : " +game.countmin[1]);	
		System.out.print(" countmin 2 : " +game.countmin[2]);
		System.out.print(" countmin 3 : " +game.countmin[3]);
	}
}
// try moving trymoves UP DOWN RIGHT LEFT to state and use clone boards for the tryMoves method 