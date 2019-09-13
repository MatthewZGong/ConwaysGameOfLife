
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.*; 
public class GameOfLife {
    static int length =102; 
    static int width = 102;
    static int pannelHeight = 1000; 
    static int pannelWidth = 1000;
    static int xDilation = pannelWidth/(width-2);
    static int yDilation = pannelHeight/(length-2);
    static BetterDP pannel = new BetterDP(pannelHeight, pannelWidth);
    static Graphics g = pannel.getGraphics();
    static boolean end =  true;
    static boolean stop = false;
    static int[][] board = new int[length][width]; 
    static int[][] nextBoard = new int[length][width];
    static boolean mouseD = false; 
    static int mouseDX;
    static int mouseDY; 
    static int speed = 5;
    static int maxSpeed = 100;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
  

        //fill(board);
        int[] pos = {0,0,1,2,0,0,0,0,0};
        int count = 0;
        
        pannel.setMainClass(GameOfLife.class);
        pannel.setBackground(Color.BLACK);

        int donot;
        while(stop == false) {
        	Thread.sleep(1000/60);
	        while(end == false){
	            count++;
	            pannel.clear();
	            for(int i = 1; i < board.length-2; i++){
	                for(int j = 1; j< board.length-2; j++){
	                      int num = findNeighbors(board,i,j);
	                      nextBoard[i][j] = (pos[num]+board[i][j])/2;
	                      
	                }
	            }
	            board = nextBoard; 
	            nextBoard = new int[length][width];
	            printBoard(board,xDilation, yDilation, g);
	            if(count == 3000){
	                end = true;
	            }
	            try {
					Thread.sleep(1000/(speed+1));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	        }
        }
    }
    public static void fill(int[][] a){
        for(int i = 1; i<a.length;i++){
            for(int j =1; j <a.length; j++ ){
                if(Math.random()*100 >80){
                    a[i][j] = 1;
                }else{
                    a[i][j] = 0;
                }
            }
        }		
    }
    public static int findNeighbors(int[][] a, int x, int y){
        int count = a[x+1][y]+ a[x-1][y]+ a[x][y+1]+ a[x][y-1]+ a[x+1][y+1]+ a[x+1][y-1]+ a[x-1][y+1]+ a[x-1][y-1];
        return count;
    } 
    public static void printBoard(int[][] a, int xDilation, int yDilation, Graphics g){
        for(int i = 1; i< a.length-2 ; i++){
            for(int j = 1; j <a.length-2; j++){
                if(a[i][j] == 1){
                	g.setColor(Color.RED);
                	g.fillRect((i-1)*xDilation, (j-1)*yDilation, xDilation, yDilation);
                }
            }
        }
    }
    public static void mouseMove(int x, int y) {
    	int i = (x/xDilation)+1;
    	int j = (y/yDilation)+1;
    	if(mouseD == true) {
    			board[i][j] = 1;
    			g.setColor(Color.RED);
            	g.fillRect((i-1)*xDilation, (j-1)*yDilation, xDilation, yDilation);
    	}
           
       
    }
    public static void mouseDown(int x, int y) {
    	int i = (x/xDilation)+1;
    	int j = (y/yDilation)+1;
    	mouseDX = i;
    	mouseDY = j;
    	if(board[i][j] == 1) {
    		board[i][j] = 0;
    	   	g.setColor(Color.BLACK);
        	g.fillRect((i-1)*xDilation, (j-1)*yDilation, xDilation, yDilation);
        	g.setColor(Color.RED);
    	}else {
    		board[i][j] = 1;
    	   	g.setColor(Color.RED);
        	g.fillRect((i-1)*xDilation, (j-1)*yDilation, xDilation, yDilation);
    	}
    	
    }
    public static void mouseUp(int x, int y) {

    	
    }
    public static void keyUpdate(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
        	if(end == false) {
        		end = true;
        	}else {
        		end = false;
        	}
            
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop = true;
            end = true;
        } else if(e.getKeyCode() == KeyEvent.VK_R) {
        	pannel.clear();
        	fill(board);
        	printBoard(board, xDilation, yDilation, g);
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
        	if(mouseD == false) {
        		mouseD = true;
        	}else {
        		mouseD = false;
        	}
        }else if(e.getKeyCode() == KeyEvent.VK_C) {
        	pannel.clear();
        	board = new int[length][width];
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
        	speed+=5;
        	speed = (speed%maxSpeed);
        	System.out.println(speed);
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        	speed-=5;
        	speed = (speed%maxSpeed);
        	System.out.println(speed);
        	
        }
        // etc.
    }

}




