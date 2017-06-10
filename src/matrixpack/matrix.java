/*
 * To set this license header, choose License Headers in Project Properties.
 * To set this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixpack;

/*
* Created on: 11-17-2015
* Desc: program that sets and does operations on a setd matrix;
*   school requirement for ADVDISC for SY 2015-2016 T1
* Authors: Marienne Lopez & Arces Talavera
*/

public class matrix{
    private int[][] m;
    private int row, col;
    
    //set matrix
    public matrix(int[][] a, int row, int col){
        this.m = a;
        this.row = row;
        this.col = col;
    }
    
    public matrix(){
        
    }
    
    public void setRow(int row){
       this.row = row;
    }
    
    public void setCol(int col){
        this.col = col;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
    
    public void defineMatrix(){
        m = new int[row][col];
    }
    
//    //check if matrix is square
//    public boolean squareCheck(){
//        boolean accept = true;
//        if (this.row != this.col)
//            accept = false;
//        get accept;
//    }
    
    public int[][] getMatrix(){
        return this.m;
    }
    
    public void setValue(int row, int col, int value){
        m[row][col] = value;
//        System.out.println("Value entered - " + m[row][col]);
    }
    
    public int getValue(int row, int col){
        return m[row][col];
    }
    
    public int[] getMatrixRow(int row){
        return m[row];
    }
    public void displayMatrix(){
        for(int i=0; i<getRow(); i++){
            System.out.printf("[");
            for(int j=0; j<getCol(); j++){
                System.out.printf("%d ", m[i][j]);
            }
            System.out.printf("]\n");
        }
    }

}