public class ComplexMatrix {
    private int rows;
    private int cols;
    private ComplexNum[][] matr;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matr = new ComplexNum[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                matr[i][j] = new ComplexNum(0,0);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ComplexNum getElem(int row, int col){
        return matr[row][col];
    }

    public void setElem(int row, int col, ComplexNum val){
        matr[row][col] = val;
    }

    public void display() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("[" + matr[i][j].display() + "]" + " ");
            }
            System.out.println();
        }
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Сложение матриц с разным размером невозможно");
        }
        ComplexMatrix res = new ComplexMatrix(rows,cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.setElem(i, j, this.matr[i][j].add(other.matr[i][j]));
            }
        }
        return res;
    }

    public ComplexMatrix transp() {
        ComplexMatrix res = new ComplexMatrix(cols,rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.setElem(j, i, this.matr[i][j]);
            }
        }
        return res;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно совпадать с числом строк второй матрицы");
        }
        ComplexMatrix res = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < res.cols; j++) {
                ComplexNum summa = new ComplexNum(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    summa = summa.add(this.matr[i][k].multiply(other.matr[k][j]));
                }
                res.setElem(i, j, summa);
            }
        }
        return res;
    }

    private ComplexMatrix getMinor(int f_row, int f_col) {
        ComplexMatrix sub = new ComplexMatrix(this.rows - 1, this.cols - 1);
        int r = 0;
        for (int i = 0; i < this.rows; i++) {
            if (i == f_row) {
                continue;
            }
            int c = 0;
            for (int j = 0; j < this.cols; j++) {
                if (j == f_col) {
                    continue;
                }
                sub.setElem(r, c, this.matr[i][j]);
                c++;
            }
            r++;
        }
        return sub;
    }

    public ComplexNum det() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
        if (this.rows == 1) {
            return this.matr[0][0];
        }
        if (this.rows == 2) {
            return ((this.matr[0][0].multiply(this.matr[1][1])).subtract((this.matr[0][1]).multiply(this.matr[1][0])));
        }
        ComplexNum summa = new ComplexNum(0, 0);
        for (int j = 0; j < this.cols; j++) {
            ComplexMatrix minor = getMinor(0, j);
            ComplexNum slag = this.matr[0][j].multiply(minor.det());
            if (j % 2 == 0) {
                summa = summa.add(slag);
            }
            else {
                summa = summa.subtract(slag);
            }
        }
        return summa;
    }

    public ComplexMatrix inverseMatrix() {
        if (this.rows != this.cols) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
        ComplexNum deter = this.det();
        if (deter.getRe() == 0 && deter.getIm() == 0) {
            throw new ArithmeticException("Матрица должна быть невырожденной");
        }
        if (this.rows == 1) {
            ComplexMatrix inv = new ComplexMatrix(1, 1);
            inv.setElem(0, 0, new ComplexNum(1, 0).divide(this.matr[0][0]));
            return inv;
        }
        ComplexMatrix inv = new ComplexMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ComplexNum minorDet = getMinor(i, j).det();
                if ((i + j)%2 != 0) {
                    minorDet = new ComplexNum(0, 0).subtract(minorDet);
                }
                ComplexNum val = minorDet.divide(deter);
                inv.setElem(j, i, val);
            }
        }
        return inv;
    }

    public ComplexMatrix divide(ComplexMatrix other) {
        return this.multiply(other.inverseMatrix());
    }
}
