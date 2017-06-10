/*
 * To set this license header, choose License Headers in Project Properties.
 * To set this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixpack;

/**
 *
 * Created on: 11-17-2015 Desc: program that defines and does operations on a
 * defined matrix; school requirement for ADVDISC for SY 2015-2016 T1 Authors:
 * Marienne Lopez & Arces Talavera
 */
public class MatrixOpSample {

    private matrix a, b;
    private static MatrixOpSample opInstance = new MatrixOpSample();

    public void setFirstMatrix(matrix a) {
        this.a = a;
    }

    public void setSecondMatrix(matrix b) {
        this.b = b;
    }

    public matrix getFirstMatrix() {
        return a;
    }

    public matrix getSecondMatrix() {
        return b;
    }

    public static MatrixOpSample getInstance() {
        return opInstance;
    }

    public boolean hasZeroRow(matrix b) {
        int zero = 0;
        for (int i = 0; i < b.getRow(); i++) {
            for (int j = 0; j < b.getCol(); j++) {
                if (b.getValue(i, j) == 0) {
                    zero++;
                }
            }
            if (zero == b.getCol()) {
                return true;
            }
            zero = 0;
        }
        return false;
    }

    public boolean isIdentityMatrix(matrix b) {
        boolean identity = false;
        for (int i = 0; i < b.getRow(); i++) {
            for (int j = 0; j < b.getCol(); j++) {
                if (i == j && b.getValue(i, j) == 1) {
                    identity = true;
                } else if (i != j && b.getValue(i, j) == 0) {
                    identity = true;
                } else {
                    identity = false;
                }
            }//end inner for
        }//end outer for
        return identity;
    }

    public float getLeadingEntry(float[] b) {
        float lead = 0;
        System.out.printf("Row entries: ");
        for (int j = 0; j < b.length; j++) {
            System.out.printf("%.2f ", b[j]);
        }

        for (int i = 0; i < b.length && lead == 0; i++) {
            if (b[i] != 0) {
                lead = b[i];
            }
        }
        return lead;
    }

    //get inverse of matrix using elementary row operations
    public matrix getInverse(matrix b) { //get inverse of B in order to multiply with A
        //initializing augemented matrix for inverse
        //c will represent the original matrix, should be identity matrix after
        matrix c = new matrix(b.getMatrix(), b.getRow(), b.getCol());
        //d will represent the identity matrix, should be the inverted matrix after
        matrix d = new matrix();
        d.setCol(b.getCol());
        d.setRow(b.getRow());
        d.defineMatrix();
        for (int i = 0; i < d.getRow(); i++) {
            for (int j = 0; j < d.getCol(); j++) {
                if (i == j) {
                    d.setValue(i, j, 1);
                } else {
                    d.setValue(i, j, 0);
                }
            }
        }

        //preliminary checks
        if (b.getCol() != b.getRow()) {
            System.out.println("The matrix does not have an inverse because it is not a square matrix."
                    + "\n A square matrix is a matrix with n rows and n columns.");
        } else if (hasZeroRow(b)) {
            System.out.println("Singular matrix! Row of zeroes detected!");
        } else {
            //convert into inverse here
            //gauss-jordan c & d!
            float lead = 0;
            for (int i = 0; i < c.getRow(); i++) {
                //get leading entry
                lead = getLeadingEntry(c.getMatrixRow(i));
                System.out.println("Leading entry is " + lead);

                //set values after making leading entry 1
                System.out.println("Changed Lead from " + lead + " to 1, changing values for row " + i);
                //j = column
                for (int j = 0; j < c.getCol(); j++) {
                    if (c.getValue(i, j) != 0) {
                        float g = c.getValue(i, j) / lead;
                        System.out.println("C BEFORE = " + c.getValue(i, j));
                        c.setValue(i, j, g);
                        System.out.println("C AFTER = " + c.getValue(i, j));
                    }

                }
                for (int j = 0; j < d.getCol(); j++) {
                    System.out.println("i = " + i + " j = " + j);
                    float g2 = d.getValue(i, j) / lead;

                    System.out.println("D BEFORE = " + d.getValue(i, j));
                    d.setValue(i, j, g2);
                    System.out.println("D AFTER = " + d.getValue(i, j));
                }

                System.out.println("--------\n AFTER CHANGING ROW" + i + "\n-----------");
                System.out.println("Partial result: \n c:");
                c.displayMatrix();
                System.out.println("\n d:");
                d.displayMatrix();

                System.out.println("--------\nCHANGE OTHER ENTRIES\n-----------");
                //let's zero the other entries on the same column
                //as the leading entry!
                for (int j = 0; j < c.getRow(); j++) {
                    //System.out.println("j = " + j + " | LEAD2 = " + lead2);
                    float[] f = c.getMatrixRow(i);
                    float[] f2 = d.getMatrixRow(i);
                    // i = current row
                    // j = other rows

                    float lead2 = c.getValue(j, i);
                    for (int k = 0; k < c.getCol(); k++) {
                        System.out.println("I'm at (" + j + ", " + k + ")");
                        if (j != i) {
                            float g = f[k] * lead2 * -1;
                            System.out.println("C VALUE BEFORE = " + c.getValue(j, k));
                            c.setValue(j, k, c.getValue(j, k) + g);
                            System.out.println("C VALUE AFTER = " + c.getValue(j, k));
                            float g2 = f2[k] * lead2 * -1;
                            System.out.println("D VALUE BEFORE = " + d.getValue(j, k));
                            d.setValue(j, k, d.getValue(j, k) + g2);
                            System.out.println("D VALUE AFTER = " + d.getValue(j, k));

                        }
                    }
                    System.out.println("Partial result: \n c:");
                    c.displayMatrix();
                    System.out.println("\n d:");
                    d.displayMatrix();
                }
            }
            System.out.println("After Gauss-Jordan: \n c:");
            c.displayMatrix();
            System.out.println("\n d:");
            d.displayMatrix();
            
            if (isIdentityMatrix(c)) {
                System.out.println("Your inverse is:");
                d.displayMatrix();
            } else {
                d = null;
                System.out.println("Your matrix is singular!");
            }

        }//end else
        return d;
    }//end getInverse

    public matrix multiplyMatrix(matrix a, matrix b) {
        matrix c = new matrix();
        float elem = 0;
        if (a.getCol() != b.getRow()) {
            System.out.println("Not conformable for multiplication!");
        } else {
            //initialize product matrix
            c.setRow(a.getRow());
            c.setCol(b.getCol());
            c.defineMatrix();

            //multiply here
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

        }//end else
        return c;
    }//end multiplyMatrix
}
