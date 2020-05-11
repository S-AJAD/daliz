
package daliz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author S-AJAD
 */
public class Daliz {
    int cm=0;
    public static int[][] map;
    public static int[][] tempMap;
    public static int x1,x2,y1,y2 , moveCode ;
    public static int p1b=10,p2b =10;
    public static int pwin;
    public static boolean end=false;
    public static int turn=1;
    public static int horizon=0;
    public static Scanner keyboard = new Scanner(System.in);
    public static ArrayList <state> closeList = new ArrayList <state>();
    public static ArrayList <state> states = new ArrayList <state>();

    
    
    public void chengeTurn() {
        if (turn==1) turn=2;
        else turn=1;
    }
    
    public void moveTo(int player , int x , int y) {
        if(player==1) {
            map[x1][y1]=0;
            x1=x;
            y1=y;
            map[x][y]=3;
        }
        if(player==2) {
            System.out.println(1);
            map[x2][y2]=0;
            x2=x;
            y2=y;
            map[x][y]=4;
        }
    }
    
    public boolean checkMove(int player , int x , int y) {
        int tx=x;
        int ty=y;
        int px;
        int py;
        int ox,oy;
        if(player==1) {
            px=x1;
            py=y1;
            ox=x2;
            oy=y2;
        }
        else {
            px=x2;
            py=y2;
            ox=x1;
            oy=y1;
        }
        
        //down
        if(tx-px==2 && ty==py && map[px+1][ty]==2 && map[tx][ty]!=3 && map[tx][ty]!=4 ) return true;
        //up
        if(px-tx==2 && ty==py && map[px-1][ty]==2 && map[tx][ty]!=3 && map[tx][ty]!=4 ) return true;
        //left
        if(px==tx && py-ty==2 && map[px][ty-1]==2) return true;
        //right
        if(px==tx && ty-py==2 && map[px][ty+1]==2) return true;
        //jump up
        if(px-tx==4 && ty==py && map[px-1][ty]==2 && map[px-3][ty]==2 && px==ox+2 && py==oy) return true;
        //jump down
        if(tx-px==4 && ty==py && map[px+1][ty]==2 && map[px+3][ty]==2 && px==ox-2 && py==oy) return true;
        //to left-up
        if(px==ox+2 && py==oy && map[px-1][py]==2 && map[px-3][py]==1 && px-tx==2 && py-ty==2 ) return true;
        //to right-up
        if(px==ox+2 && py==oy && map[px-1][py]==2 && map[px-3][py]==1 && px-tx==2 && ty-py==2 ) return true;
        //to left-down
        if(px==ox-2 && py==oy && map[px+1][py]==2 && map[px+3][py]==1 && tx-px==2 && py-ty==2 ) return true;
        //to right-down
        if(px==ox-2 && py==oy && map[px+1][py]==2 && map[px+3][py]==1 && tx-px==2 && ty-py==2 ) return true;


        return false;
    }
    
    public void getNextMove(int player) {
        int bx1,bx2,by1,by2;
        int newx2,newy2;
        if (player==1) System.out.println("select your move: 1.move player 2.set block 3.getOutFromMap("+ p1b +")\n");
        if (player==2) System.out.println("select your move: 1.move player 2.set block 3.getOutFromMap("+ p2b +")\n");

        moveCode = keyboard.nextInt();
        if(moveCode==1) {
        
            System.out.println("enter x and y\n");
            newx2 = keyboard.nextInt();
            newy2 = keyboard.nextInt();
            if (checkMove(player,newx2,newy2)) {
                moveTo(player,newx2,newy2);

            }
            else System.out.println("you can't do this move.\n");
            
            
            
        }
        if(moveCode==2) {
            if(player==1) {
                if(p1b>0) {
                    System.out.println("enter x1 and y1 and x2 and y2 (aval noghteye samt chap ya bala ra vared konid.) \n");
                    bx1 = keyboard.nextInt();
                    by1 = keyboard.nextInt();
                    bx2 = keyboard.nextInt();
                    by2 = keyboard.nextInt();
                    if(map[bx1][by1]==2 && map[bx1][by2]==2 && map[bx1][by1+1]==2 && bx1==bx2 && by1==by2-2 ) {
                        map[bx1][by1]=1;
                        map[bx1][by1+1]=1;
                        map[bx1][by2]=1;
                    }
                    else System.out.println("wrong");
                    if(map[bx1][by1]==2 && map[bx2][by2]==2 && map[bx1+1][by1]==2 && by1==by2 && bx1==bx2-2 ) {
                        map[bx1][by1]=1;
                        map[bx1+1][by1]=1;
                        map[bx2][by1]=1;
                    }
                    p1b--;
                }

                
            }
            else {
                if(p2b>0) {
                    System.out.println("enter x1 and y1 and x2 and y2 (aval noghteye samt chap ya bala ra vared konid.) \n");
                    bx1 = keyboard.nextInt();
                    by1 = keyboard.nextInt();
                    bx2 = keyboard.nextInt();
                    by2 = keyboard.nextInt();
                    if(map[bx1][by1]==2 && map[bx1][by2]==2 && map[bx1][by1+1]==2 && bx1==bx2 && by1==by2-2 ) {
                        map[bx1][by1]=1;
                        map[bx1][by1+1]=1;
                        map[bx1][by2]=1;
                    }
                    else System.out.println("wrong");
                    if(map[bx1][by1]==2 && map[bx2][by2]==2 && map[bx1+1][by1]==2 && by1==by2 && bx1==bx2-2 ) {
                        map[bx1][by1]=1;
                        map[bx1+1][by1]=1;
                        map[bx2][by1]=1;
                    }
                    p2b--;
                }

                else System.out.println("you don't have available block.");
            }
        }
        if(moveCode==3) {
            int r;
            r= winner(player);
            if(r==0) System.out.println("you can't");
            else {
                end=true;
                pwin=r;
            }
          
        }
            
        
        chengeTurn();
    
    }
    
    public void setBlock(state state) {
        
        if(state.player==1 && state.p1b!=0) {
            //ofoghi
            for(int i=1;i<17;i=i+2) {
                for(int j=0;j<15;j=j+2) {

                    if(state.map[i][j]==2 && state.map[i][j+1]==2 && state.map[i][j+2]==2) {
                        tempMap[i][j]=1;
                        tempMap[i][j+1]=1;
                        tempMap[i][j+2]=1;
                        state newState = new state(tempMap,state.x1,state.y1,state.x2,state.y2,2);
                        newState.depth = state.depth+1;
                        newState.point = point(newState);
                        newState.parent=state;
                        
                        states.add(newState);
                        tempMap[i][j]=2;
                        tempMap[i][j+1]=2;
                        tempMap[i][j+2]=2;

                    }
                }
            }
            //amoodi
            for(int j=1;j<17;j=j+2) {
                for(int i=0;i<15;i=i+2) {

                    if(state.map[i][j]==2 && state.map[i+1][j]==2 && state.map[i+2][j]==2) {
                        tempMap[i][j]=1;
                        tempMap[i+1][j]=1;
                        tempMap[i+2][j]=1;
                        state newState = new state(tempMap,state.x1,state.y1,state.x2,state.y2,2);
                     
                        newState.depth = state.depth+1;
                        newState.point = point(newState);
                        newState.parent=state;

                        states.add(newState);
                        tempMap[i][j]=2;
                        tempMap[i+1][j]=2;
                        tempMap[i+2][j]=2;

                    }
                }
            }
            
        }
        if(state.player==2 && p2b!=0) {
            //player2
            //ofoghi
            for(int i=1;i<17;i=i+2) {
                for(int j=0;j<15;j=j+2) {

                    if(state.map[i][j]==2 && state.map[i][j+1]==2 && state.map[i][j+2]==2) {
                        tempMap[i][j]=1;
                        tempMap[i][j+1]=1;
                        tempMap[i][j+2]=1;
                        state newState = new state(tempMap,state.x1,state.y1,state.x2,state.y2,1);
                        newState.depth = state.depth+1;
                        newState.point = point(newState);
                        newState.parent=state;
                        
                        states.add(newState);
                        tempMap[i][j]=2;
                        tempMap[i][j+1]=2;
                        tempMap[i][j+2]=2;

                    }
                }
            }
            //amoodi
            for(int j=1;j<17;j=j+2) {
                for(int i=0;i<15;i=i+2) {

                    if(state.map[i][j]==2 && state.map[i+1][j]==2 && state.map[i+2][j]==2) {
                        tempMap[i][j]=1;
                        tempMap[i+1][j]=1;
                        tempMap[i+2][j]=1;
                        state newState = new state(tempMap,state.x1,state.y1,state.x2,state.y2,1);
                        newState.depth = state.depth+1;
                       

                        newState.point = point(newState);
                        newState.parent=state;

                        states.add(newState);
                        tempMap[i][j]=2;
                        tempMap[i+1][j]=2;
                        tempMap[i+2][j]=2;

                    }
                }
            }
            
        }
    }
    
    public void printMap(int[][] map) {
        int k=0;
        System.out.print(" x\\y ");
        for(int i=0;i<17;i++) {
            if(i<11) System.out.print(" ");
            System.out.printf("("+ i +")");
        }
        for(int i=0 ; i<17 ;i++) {
            
            System.out.println();
            System.out.print("(" +(k)+") ");
            if(k<10) System.out.print(" ");
            k++;
            System.out.printf("|");
            for(int j=0 ; j<17; j++) {
                if(map[i][j]==0)System.out.printf( "   |" );
                if(map[i][j]==1)System.out.printf( "XXX|" );
                if(map[i][j]==2)System.out.printf( " . |" );
                if(map[i][j]==3)System.out.printf( " A |" );
                if(map[i][j]==4)System.out.printf( " B |" );
            }
            
        } 
        System.out.println();
    }
    
    public int point(state state) {
        
        int point1, point2;
        int b1=0,b2=0;
        
        for(int i=state.x1 ; i<17;i++) {
            if(state.map[i][state.y1]==1) b1++;
        }
        point1 = state.x1-b1;
        
        
       
        for(int i=0 ; i<state.x2+1;i++) {
            if(state.map[i][state.y2]==1) b2++;
        }
        point2 = 16-state.x2-b2;
        
        

        return point1 - point2;
        
    }
    
    public int winner(int player) {
        
        if (player == 1 && x1==16 ) return 1;
        if (player==2 && x2==0 ) return 2;
        return 0;
        
    }
     
    
    public void sortArray(ArrayList<state> array) {
        state temp;
        
     for (int i = 0; i < array.size(); i++)
        {
            for (int j = i + 1; j < array.size(); j++)
            {
                int f1= array.get(i).point;
                int f2= array.get(j).point;

                if (f1 > f2)
                {
                    temp =  array.get(i);
                    array.set(i,array.get(j));
                    array.set(j,temp);
                }
            }
        }
     
    }
    
    public void placeAMove(state state ,  int player) {
        if(player==1 ) {
            
            map[x1][y1]=0;
            x1=state.x1;
            y1=state.y1;
            map[x1][y1]=3;
        }
        if(player==2 ) {
            
            map[x2][y2]=0;
            x2=state.x2;
            y2=state.y2;
            map[x2][y2]=4;
        }
        for(int i=0 ; i<17 ;i++) {
            for(int j=0 ; j<17; j++) {
                map[i][j]=state.map[i][j];
            }
        }
        
    }
   
    public void succsesor (state state) {
        
        int x ,y;
        for(int p=0; p<17; p++) {
            for(int l=0; l<17; l++)
                tempMap[p][l]=state.map[p][l];
        }
        
        if(state.player==1) {
            x=state.x1;
            y= state.y1;
            
            //down
            if(x!=16 && state.map[x+2][y]==0 && state.map[x+1][y]==2) {
                tempMap[x][y]=0;
                tempMap[x+2][y]=3;
                state newState = new state(tempMap,x+2,y,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+2][y]=0;
            }
            //up
            if(x!=0 && state.map[x-2][y]==0 && state.map[x-1][y]==2) {
                tempMap[x][y]=0;
                tempMap[x-2][y]=3;
                state newState = new state(tempMap,x-2,y,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-2][y]=0;
            }
            //left
            if(y!=0 && state.map[x][y-2]==0 && state.map[x][y-1]==2) {
                tempMap[x][y]=0;
                tempMap[x][y-2]=3;
                state newState = new state(tempMap,x,y-2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x][y-2]=0;
            }
            //right
            if(y!=16 && state.map[x][y+2]==0 && state.map[x][y+1]==2) {
                tempMap[x][y]=0;
                tempMap[x][y+2]=3;
                state newState = new state(tempMap,x,y+2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x][y+2]=0;
            }
            //jump-up
            if(x!=0 && state.map[x-2][y]==4 && state.map[x-4][y]==0 && state.map[x-1][y]==2 && state.map[x-3][y]==2) {
                tempMap[x][y]=0;
                tempMap[x-4][y]=3;
                state newState = new state(tempMap,x-4,y,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-4][y]=0;
            }
            //jump-down
            if(x!=16 && state.map[x+2][y]==4 && state.map[x+4][y]==0 && state.map[x+1][y]==2 && state.map[x+3][y]==2) {
                tempMap[x][y]=0;
                tempMap[x+4][y]=3;
                state newState = new state(tempMap,x+4,y,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+4][y]=0;
            }
            //jump-up-right
            if(x!=0 && state.map[x-2][y]==4 && state.map[x-2][y+2]==0 && state.map[x-1][y]==2 && state.map[x-2][y+1]==0 && state.map[x-3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x-2][y+2]=3;
                state newState = new state(tempMap,x-2,y+2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-2][y+2]=0;
            }
            //jump-up-left
            if(x!=0 && state.map[x-2][y]==4 && state.map[x-2][y-2]==0 && state.map[x-1][y]==2 && state.map[x-2][y-1]==0 && state.map[x-3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x-2][y-2]=3;
                state newState = new state(tempMap,x-2,y-2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-2][y-2]=0;
            }
            //jump-down-right
            if(x!=16 && state.map[x+2][y]==4 && state.map[x+2][y+2]==0 && state.map[x+1][y]==2 && state.map[x+2][y+1]==0 && state.map[x+3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x+2][y+2]=3;
                state newState = new state(tempMap,x+2,y+2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+2][y+2]=0;
            }
            //jump-down-left
            if(x!=16 && state.map[x+2][y]==4 && state.map[x+2][y-2]==0 && state.map[x+1][y]==2 && state.map[x+2][y-1]==0 && state.map[x+3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x+2][y-2]=3;
                state newState = new state(tempMap,x+2,y-2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+2][y-2]=0;
            }
            
            
            //get out from down
            if(x==16 && state.player==1 ) {
                end = true;
                pwin =1;
                
                state newState = new state(tempMap,x+2,y-2,state.x2,state.y2,2);
                newState.depth = state.depth+1;
                newState.point = 1000;

                newState.parent=state;
                states.add(newState);
                tempMap[x][y]=0;
                
            }
            setBlock(state);
            
        }
        
        
        else {
            x=state.x2;
            y= state.y2;
            
            
            //down
            if(x!=16 && state.map[x+2][y]==0 && state.map[x+1][y]==2) {
                tempMap[x][y]=0;
                tempMap[x+2][y]=4;
                state newState = new state(tempMap,state.x1,state.y1,x+2,y,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;
           
                states.add(newState);
                tempMap[x][y]=4;
                tempMap[x+2][y]=0;
            }
            //up
            if(x!=0 && state.map[x-2][y]==0 && state.map[x-1][y]==2) {
                tempMap[x][y]=0;
                tempMap[x-2][y]=4;
                state newState = new state(tempMap,state.x1,state.y1,x-2,y,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=4;
                tempMap[x-2][y]=0;
            }
            //left
            if(y!=0 && state.map[x][y-2]==0 && state.map[x][y-1]==2) {
                tempMap[x][y]=0;
                tempMap[x][y-2]=4;
                state newState = new state(tempMap,state.x1,state.y1,x,y-2,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=4;
                tempMap[x][y-2]=0;
            }
            //right
            if(y!=16 && state.map[x][y+2]==0 && state.map[x][y+1]==2) {
                tempMap[x][y]=0;
                tempMap[x][y+2]=4;
                state newState = new state(tempMap,state.x1,state.y1,x,y+2,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;


                states.add(newState);
                tempMap[x][y]=4;
                tempMap[x][y+2]=0;
            }
            
            //jump-up
            if(x!=0 && state.map[x-2][y]==4 && state.map[x-4][y]==0 && state.map[x-1][y]==2 && state.map[x-3][y]==2) {
                tempMap[x][y]=0;
                tempMap[x-4][y]=3;
                state newState = new state(tempMap,state.x1,state.y1,x-4,y,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-4][y]=0;
            }
            //jump-down
            if(x!=16 && state.map[x+2][y]==4 && state.map[x+4][y]==0 && state.map[x+1][y]==2 && state.map[x+3][y]==2) {
                tempMap[x][y]=0;
                tempMap[x+4][y]=3;
                state newState = new state(tempMap,state.x1,state.y1,x+4,y,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;


                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+4][y]=0;
            }
            //jump-up-right
            if(x!=0 && state.map[x-2][y]==4 && state.map[x-2][y+2]==0 && state.map[x-1][y]==2 && state.map[x-2][y+1]==0 && state.map[x-3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x-2][y+2]=3;
                state newState = new state(tempMap,state.x1,state.y1,x-2,y+2,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;


                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-2][y+2]=0;
            }
            //jump-up-left
            if(x!=0 && state.map[x-2][y]==4 && state.map[x-2][y-2]==0 && state.map[x-1][y]==2 && state.map[x-2][y-1]==0 && state.map[x-3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x-2][y-2]=3;
                state newState = new state(tempMap,state.x1,state.y1,x-2,y-2,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;


                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x-2][y-2]=0;
            }
            //jump-down-right
            if(x!=16 && state.map[x+2][y]==4 && state.map[x+2][y+2]==0 && state.map[x+1][y]==2 && state.map[x+2][y+1]==0 && state.map[x+3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x+2][y+2]=3;
                state newState = new state(tempMap,state.x1,state.y1,x+2,y+2,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;


                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+2][y+2]=0;
            }
            //jump-down-left
            if(x!=16 && state.map[x+2][y]==4 && state.map[x+2][y-2]==0 && state.map[x+1][y]==2 && state.map[x+2][y-1]==0 && state.map[x+3][y]==1) {
                tempMap[x][y]=0;
                tempMap[x+2][y-2]=3;
                state newState = new state(tempMap,state.x1,state.y1,x+2,y-2,1);
                newState.depth = state.depth+1;
                newState.point = point(newState);
                newState.parent=state;
               

                states.add(newState);
                tempMap[x][y]=3;
                tempMap[x+2][y-2]=0;
            }
            //get out from up
//            if(x==0 && state.player==2 ) {
//                end = true;
//                pwin =2;
//                
//                state newState = new state(tempMap,x+2,y-2,state.x2,state.y2,2);
//                newState.depth = state.depth+1;
//                newState.point = -1000;
//                newState.parent=state;
//                
//
//                states.add(newState);
//                tempMap[x][y]=0;
//                
//            }
            setBlock(state);

            
        }
        
        
        
        
        
    }
    
    public void dls(state startState , int limit) {

    Stack stack = new Stack();
    stack.add(startState);
    closeList.add(startState);
        while(!stack.isEmpty()){
        //removes from front of queue
            state r = (state)stack.pop();
            if(r.depth > limit) {
                continue;
            }
            if(r.depth ==limit && cm ==0) {
                cm = closeList.size();
            }
            closeList.add(r);

//            if(isGoal(r.map)) {
//                    findedGoal = r;
//                    //System.out.println("goal find.");
//                    //states.get(i).printMap();
//                    break;
//            }

            succsesor(r);

            for(int i=0;i<states.size();i++) {

                    stack.push(states.get(i));
                    //System.out.println("depth:"+ states.get(i).depth + " -point: "+states.get(i).point + " -player"+states.get(i).player);
                   //printMap(states.get(i).map);

            }
            states.clear();
        }
     //System.out.println("Algorithm: DLS \n" + statesCount);

    }

    
    public void minimax() {
        
        for(int i=closeList.size()-1;i>=0;i--) {
            
            if(closeList.get(i).depth==horizon+1) {
                closeList.get(i).point = point(closeList.get(i));
            }
                if(closeList.get(i).parent!=null && closeList.get(i).parent.player==1 && closeList.get(i).parent.point<closeList.get(i).point){
                    closeList.get(i).parent.point = closeList.get(i).point;
                    closeList.get(i).parent.bestChild = closeList.get(i);
                }
                if(closeList.get(i).parent!=null && closeList.get(i).parent.player==2 && closeList.get(i).parent.point>closeList.get(i).point){
                    closeList.get(i).parent.point = closeList.get(i).point;
                    closeList.get(i).parent.bestChild = closeList.get(i);

                }
            //}
        }
        for(int i=0 ; i<17 ;i++) {
            for(int j=0 ; j<17; j++) {
                map[i][j]=closeList.get(0).bestChild.map[i][j];
            }
         }
        x1 = closeList.get(0).bestChild.x1;
        y1 = closeList.get(0).bestChild.y1;

        x2 = closeList.get(0).bestChild.x2;

        y2 = closeList.get(0).bestChild.y2;
        
        chengeTurn();
    }
    
    public static void main(String[] args) {
        //keyboard init
        Daliz daliz = new Daliz();
        System.out.println("welcome to daliz game.\nselect difficulty : 1.easy 2.medium 3.hard");
        //keyboard init
        
        
        // difficulty init 
        
        horizon = keyboard.nextInt();
        //difficulty init
        map = new int[17][17];
        tempMap = new int[17][17];

        for(int i=0 ; i<17 ;i++) {
            for(int j=0 ; j<17; j++) {
                if(i%2==1 && j%2==1) map[i][j]=2;
                if(i%2==0 && j%2==0) map[i][j]=0;
                if(i%2==0 && j%2==1) map[i][j]=2;
                if(i%2==1 && j%2==0) map[i][j]=2;
               
            }
        }
        x1 = 0;
        y1 =8;
        x2=16;
        y2=8;
        map[x1][y1]=3;
        map[x2][y2]=4;
        state start = new state(map,0,8,16,8,1);
        start.parent=null;
        start.point = -1000;
        //daliz.succsesor(start);
        closeList.clear();
    
        daliz.dls(start,horizon);
        int bc=0;
        int sbc=0;
        for(int i=0 ; i<17 ;i++) {
            for(int j=0 ; j<17; j++) {
                
                if(map[i][j]==1) bc++;
            }
        }
        daliz.minimax();
        
        for(int i=0 ; i<17 ;i++) {
            for(int j=0 ; j<17; j++) {
                
                if(map[i][j]==1) sbc++;
            }
        }
        if(sbc>bc) p1b--;
        
        daliz.printMap(map);
        if (turn==1) System.out.println("player A :");
        else System.out.println("player B :");
        System.out.println("player A blocks :" + p1b + "player B blocks : " + p2b);

        daliz.getNextMove(turn);
        
        //System.out.println(closeList.size());
        
         while(!end) {
            start = new state(map,x1,y1,x2,y2,turn);
            closeList.clear();
            daliz.dls(start,horizon);
            bc=0;
            sbc=0;
            for(int i=0 ; i<17 ;i++) {
                for(int j=0 ; j<17; j++) {

                    if(map[i][j]==1) bc++;
                }
            }
            daliz.minimax();

            for(int i=0 ; i<17 ;i++) {
                for(int j=0 ; j<17; j++) {

                    if(map[i][j]==1) sbc++;
                }
            }
            if(sbc>bc) p1b--;
            
            daliz.printMap(map);
            if (turn==1) System.out.println("player A :");
            else System.out.println("player B :");
            System.out.println("player A blocks :" + p1b + "player B blocks : " + p2b);
            if(!end)
            daliz.getNextMove(turn);
        
        }
//        daliz.succsesor(states.get(0));
//        for(int i=0;i<states.size();i++) {
//            daliz.printMap(states.get(i).map);
//            System.out.println();
//        }
//        x1 = 0;
//        y1 =8;
//        x2=16;
//        y2=8;
//        map[x1][y1]=3;
//        map[x2][y2]=4;
//        map[11][8]=1;
//        map[11][6]=1;
//        map[11][7]=1;
//        while(!end) {
//        
//            daliz.printMap();
//            System.out.println(daliz.point(map));
//            if (turn==1) System.out.println("player A :");
//            else System.out.println("player B :");
//
//            daliz.getNextMove(turn);
//        
//        }
        if(pwin==1) System.out.println("player A win.");
        if(pwin==2) System.out.println("player B win.");


      

        
    }
    
}
