/**
 * An implementation of the Matrix ADT. Provides four basic operations over an immutable type.
 * 
 * Last updated 7/31/2021.
 * 
 * @author (your name), Ruben Acuna
 * @version (version)
 */

public class CompletedMatrix implements Matrix {

    private int matrixObject[][];
    public CompletedMatrix(int[][] matrix)
    {

        //
        if (matrix != null && matrix.length > 0)
        {
            int rows = matrix.length;
            int columns = matrix[0].length;

            //Set new CompletedMatrix object dimensions
            matrixObject = new int[rows][columns];

            //Deep copy of matrix contents to array of CompletedMatrix object
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++)
                {
                    matrixObject[i][j] = matrix[i][j];
                }
            }
        }
        else if (matrix.length == 0)
            matrixObject = new int[0][0];
        else
            throw new UnsupportedOperationException("Cannot create matrix with null inputs.");
    }

    @Override
    public int getElement(int y, int x)
    {
        //If coordinates are out of matrix boundaries
        if (matrixObject.length == 0) return matrixObject[y][x];

        else if (y < 0 || x < 0 || y >= matrixObject.length || x >= matrixObject[y].length)
            throw new UnsupportedOperationException("Element coordinates out of matrix bounds!");

        else
            return matrixObject[y][x];
    }

    @Override
    public int getRows() 
    {
        //If matrix is empty
        if (matrixObject == null)
            throw new UnsupportedOperationException("Matrix is empty/null!");
        
        else
            return matrixObject.length;
    }

    @Override
    public int getColumns() 
    {
        //If matrix is empty
        if (matrixObject == null)
            throw new UnsupportedOperationException("Matrix is empty/null!");
        else if (matrixObject.length == 0)
            return 0;
        else 
            return matrixObject[0].length;
    }

    @Override
    public Matrix scale(int scalar) {
        for (int i = 0; i < matrixObject.length; i++)
        {
            for (int j = 0; j < matrixObject[i].length; j++)
            {
                matrixObject[i][j] = matrixObject[i][j] * scalar;
            }
        }

        if (scalar != (int)scalar )
            throw new UnsupportedOperationException("TODO!");

        else
            return this;
    }

    @Override
    public Matrix plus(Matrix other) {
        //If both matrices have the same dimensions
        if (other.getRows() == this.getRows() && other.getColumns() == this.getColumns())
        {
            for (int i = 0; i < matrixObject.length; i++)
            {
                for (int j = 0; j < matrixObject[i].length; j++)
                {
                    matrixObject[i][j] += other.getElement(i, j);
                }
            }
            return this;
        }
        else if (other == null)
            throw new IllegalArgumentException("The other matrix is null.");
        else
            throw new RuntimeException("Both matrices must have the same dimensions!");
    }

    @Override
    public Matrix minus(Matrix other) {
        //If both matrices have the same dimensions
        if (other.getRows() == this.getRows() && other.getColumns() == this.getColumns())
        {
            for (int i = 0; i < matrixObject.length; i++)
            {
                for (int j = 0; j < matrixObject[i].length; j++)
                {
                    matrixObject[i][j] -= other.getElement(i, j);
                }
            }
            return this;
        }
        else if (other == null)
            throw new IllegalArgumentException("The other matrix is null.");
        else
            throw new RuntimeException("Both matrices must have the same dimensions!");
    }

    @Override
    public Matrix multiply(Matrix other) {

        //Create new array to accomodate for new size (original matrix cannot change in dimensions)
        int[][] dotProductMatrix = new int[this.getRows()][other.getColumns()];

        //If the size of each matrix makes this operation possible
        if (this.getColumns() == other.getRows())
        {
            for (int i = 0; i < this.getRows(); i++) //Iterate between each row of this matrix
            {
                for (int j = 0; j < other.getColumns(); j++) //Iterate between each column of other's elements
                {
                    for (int k = 0; k < this.getColumns(); k++) //Iterate through this matrix's elements
                    {
                        dotProductMatrix[i][j] += this.getElement(i, k) * other.getElement(k, j);
                    }
                }
            }

            //After dot product is created, make our new product this object's matrix
            this.matrixObject = dotProductMatrix;

            return this;
        }
        else if (other == null)
            throw new IllegalArgumentException("The other matrix is null.");
        else
            throw new RuntimeException("The number of rows in the first matrix must be equal to that of the other's columns.");
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass())
            return false;

        //For this to run, we know from the above If statement, other is a Matrix object
        boolean isEqual = false;
        for (int i = 0; i < this.getRows(); i++) //Iterate through each row
        {
            for (int j = 0; j < this.getColumns(); j++) //Iterate through each column
            {
                isEqual = this.getElement(i, j) == ((CompletedMatrix) other).getElement(i, j);
            }
        }
        return isEqual;
    }

    @Override
    public String toString()
    {
        String returnedString = "";
        for (int i = 0; i < this.getRows(); i++)
        {
            if (i > 0)
                returnedString += "\n";
            for (int j = 0; j < this.getColumns(); j++)
            {
                returnedString += this.getElement(i, j) + " ";
            }
        }

        return returnedString;
    }

    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //These tests show sample usage of the matrix, and some basic ideas for testing. They are not comprehensive.

        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data5 = {{1, 4, 7}, {2, 5, 8}};

        Matrix m1 = new CompletedMatrix(data1);
        Matrix m2 = new CompletedMatrix(data2);
        Matrix m3 = new CompletedMatrix(data3);
        Matrix m4 = new CompletedMatrix(data4);
        Matrix m5 = new CompletedMatrix(data5);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true

        //test operations (valid)
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("m1 + m1:\n" + m1.plus(m1));
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("3 * m5:\n" + m5.scale(3));

        //not tested... multiply(). you know what to do.

        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 + m5" + m1.plus(m5));
        //System.out.println("m1 - m2" + m1.minus(m2));
    }
}