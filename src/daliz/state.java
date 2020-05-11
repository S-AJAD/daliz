/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daliz;

/**
 *
 * @author S-AJAD
 */
public class state {
    
    int[][] map;
    int x1,x2,y1,y2;
    int player;
    int point;
    int depth;
    state parent;
    state bestChild;
    int p1b, p2b;
    state(int[][] map , int x1,int y1, int x2,int y2,int player) {

    this.map = new int[17][17];
    this.player= player;
    if(player==1) {
        this.point = -1000;
    }
    else {
        this.point = 1000;
    }
    for(int p=0; p<17; p++) {
        for(int l=0; l<17; l++)
            this.map[p][l]=map[p][l];
    }
    this.x1= x1;
    this.x2= x2;
    this.y1= y1;
    this.y2= y2;
        
    }
    
    

    
}
