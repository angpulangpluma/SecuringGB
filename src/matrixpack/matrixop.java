/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrixpack;

/**
 *
 * @author Marienne Lopez
 */
public class matrixop {
    private matrix a, b;
    
    public void setFirstMatrix(matrix a){
        this.a = a;
    }
    
    public void setSecondMatrix(matrix b){
        this.b = b;
    }
    
    public matrix getFirstMatrix(){
        return a;
    }
    
    public matrix getSecondMatrix(){
        return b;
    }
    
    public matrixop getInstance(){
        return this;
    }
    
    public matrix multiplyMatrix(int mod){
        matrix c = new matrix();
        int elem = 0;
        if (a.getCol() != b.getRow()){
            System.out.println("Not conformable for multiplication!");
        } else{
            c.setRow(a.getRow());
            c.setCol(b.getCol());
            c.defineMatrix();
            
            for (int x = 0; x < c.getRow(); x++) {
                for (int y = 0; y < c.getCol(); y++) {
                elem = 0;
                    for (int i = 0; i < a.getCol(); i++) {
                        System.out.println("Multiplying element of A in row " + x + " and column " + i
                                + " to element of B in row " + i + " and column " + y + ": "
                                + a.getValue(x, i) + "*" + b.getValue(i, y));
                        
                        
                        System.out.println("Result: " + a.getValue(x, i) * b.getValue(i, y));
                        
                        
                        elem += (a.getValue(x, i) * b.getValue(i, y));
                    }
                c.setValue(x, y, elem);


                }//end col count for product matrix
            }//end row count for product matrix

            System.out.printf("Matrix A: ");
            a.displayMatrix();
            System.out.printf("\n Matrix B: ");
            b.displayMatrix();
            System.out.println("\n AB: ");
            c.displayMatrix();
        }
        return c;
    }
    
    
}
