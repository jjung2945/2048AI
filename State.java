import java.util.ArrayList;
import java.util.Random;

public class State {
	
	 public ArrayList<State> Children = new ArrayList<State>();
	 public int [][] board;
	 public State Parent;
	
	 
	 public State (int[][] board, State Parent) {
		 this.board = board;
		 this.Parent = Parent;
	 }
	 
	 public State tryMovesUp(State s) {
			int [][] clone = s.board.clone();
			State c = new State(clone, s);
			c.pushUp();
			c.pushUp();
			c.addUp();
			c.pushUp();
			c.pushUp();
			virChildren.add(c);
			findmin[0]=count(s);
			return c;
		}
		
		public State tryMovesDown(State s) {
			int [][] clone = s.board.clone();
			State c = new State(clone, s);
			c.pushDown();
			c.pushDown();
			c.addDown();
			c.pushDown();
			c.pushDown();
			virChildren.add(c);
			findmin[1]=count(s);
			return c;
		}
		
		public State tryMovesRight(State s) {
			int [][] clone = s.board.clone();
			State c = new State(clone, s);
			c.pushRight();
			c.pushRight();
			c.addRight();
			c.pushRight();
			c.pushRight();
			virChildren.add(c);
			findmin[2]=count(s);
			return c;
		}
		
		public State tryMovesLeft(State s) {
			int [][] clone = s.board.clone();
			State c = new State(clone, s);
			c.pushLeft();
			c.pushLeft();
			c.addLeft();
			c.pushLeft();
			c.pushLeft();
			virChildren.add(c);
			findmin[3]=count(s);
			return c;
		}
	 
	 
	 public int randGen() {
		 Random rand = new Random();
		 int i = (1+rand.nextInt(2))*2;
		 return i;
	 }
	 
	 public int numberFinder() {
			int g = -1;
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(board[i][j]>g) {
						g=board[i][j];
					}
				}
			}
			return g;
		}
	 
	 public boolean terminalCheck(int [][] b) {
		 int count=0;
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(filledCheck(i,j,b)) {
							count ++;
					}
				}
			}
			if(count==16) {
				return true;
			}
			else return false;
		}
	 
	public void randomGen(int [][] b) {
			Random rand = new Random();
			int x = rand.nextInt(4);
			int y= rand.nextInt(4);
			int z= (rand.nextInt(2)+1)*2;
			
			if(!filledCheck(x,y,b)) {
				b[x][y]=z;
			}
			else randomGen(b);
		}
	 
	 public boolean filledCheck(int i, int j, int [][] b) {
			if((b[i][j])!= 0) {
				return true;
			}
			else return false;
		}
	 
	  public ArrayList<State> getChildren(){
	        return Children;
	    }

	 public void generateChildren() {
		
		
		
		 if(terminalCheck(board)) {
			 return;
		 } Children.clear();
			for( int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(board[i][j]==0) {
						int[][] carr = copyArr(board);
						carr[i][j]=randGen();
						addChildren(new State(carr, this));
					}
				}
				}
		
	 }
	 
	 public void voidArray(int [][] b) {
		 for(int i =0; i<4; i++) {
			 for(int j=0; j<4; j++) {
				 b[i][j]=0;
			 }
		 }
	 }
	 
	 private static int[][] copyArr(int[][] arr){
	        int[][] out = new int[4][4];
	        for (int x = 0; x < 4; x++){
	            for (int y = 0; y < 4; y++){
	                out[x][y] = arr[x][y];
	            }
	        }
	        return out;
	    }
	 
	 public void addUp(){
			for(int i=0; i<3; i++) {
				for(int j= 0; j<4; j++) {
					if(board[i][j]== board[i+1][j]) {
						board[i+1][j]=0; 
						board[i][j]*=2;
					}
				}
			}
		}
		
		public void pushUp(){
			for(int i=0; i<3; i++) {
				for(int j= 0; j<4; j++) {
					if(board[i+1][j]!=0) {
						if(board[i][j]==0) {
							board[i][j]=board[i+1][j];
							board[i+1][j]=0;
						}
					}
					
				}
			}
		}
		
		public void addLeft() {
			for(int i=0; i<4; i++) {
				for(int j= 0; j<3; j++) {
					if(board[i][j]== board[i][j+1]) {
						board[i][j+1]=0; 
						board[i][j]*=2;
					}
				}
			}
		}
		
		public void pushLeft() {
			for(int i=0; i<4; i++) {
				for(int j= 0; j<3; j++) {
					if(board[i][j+1]!=0) {
						if(board[i][j]==0) {
							board[i][j]=board[i][j+1];
							board[i][j+1]=0;
						}
					}
					
		}
	}
		}
		
		public void pushRight() {
			for(int i=0; i<4; i++) {
				for(int j= 3; j>0; j--) {
					if(board[i][j-1]!=0) {
						if(board[i][j]==0) {
							board[i][j]=board[i][j-1];
							board[i][j-1]=0;
						}
					}
				}
			}
		}
		
		public void addRight() {
			for(int i=0; i<4; i++) {
				for(int j= 3; j>0; j--) {
						if(board[i][j]== board[i][j-1]) {
							board[i][j-1]=0; 
							board[i][j]*=2;
						}
					}
					
			}
		}
		
		public void pushDown() {
			for(int i=3; i>0; i--) {
				for(int j= 0; j<4; j++) {
					if(board[i-1][j]!=0) {
						if(board[i][j]==0) {
							board[i][j]=board[i-1][j];
							board[i-1][j]=0;
						}
					}
				}
			}
		}
		public void addDown() {
			for(int i=3; i>0; i--) {
				for(int j= 0; j<4; j++) {
					if(board[i][j]== board[i-1][j]) {
						board[i-1][j]=0; 
						board[i][j]*=2;
					}
				}
			}
		}

	 
	 public void print() {
			for(int i=0; i<4; i++) {
				System.out.println(" ");
				for(int j= 0; j<4; j++) {
					System.out.print(board[i][j] + " ");
				}
			}
		}
		
		public void print(int [][] b) {
			for(int i=0; i<4; i++) {
				System.out.println(" ");
				for(int j= 0; j<4; j++) {
					System.out.print(b[i][j] + " ");
				}
			}

		}
		
	 
	 public State findChildren(int i, int j){
	        if (board[i][j]==0) return null; //efficiency
	        for (State s : getChildren()){
	            if (board[i][j]!=0){
	                return s;
	            }
	        }
	        return null;
	 }
	 
	 public void addChildren(State s){
	        Children.add(s);
	 }
	 
	 public void reset() {
		 
	 }


	 public static void main (String[] args) {
		 gameMain game = new gameMain();
		
		 int [][] example = new int [4][4];
		 game.initial= example;
		 
		 State s = new State(game.initial, null);
		 s.generateChildren();
	 }
	 
}
