/* Koon Chua
 * EN 605.202.81
 * Lab 3
 *
 * This class calculates the cofactor and determinant of a nxn matrix
 */

public class Determinant {

    /**
     * Function recursively computes the determinant of a matrix
     * @param arr_list  original matrix/subsequent cofactor
     * @return          value of the determinant of a matrix
     */
    public int computeDeterminant(Mat_LL[] arr_list) {
        Mat_LL[] minor;
        Mat_LL matrix;
        int det;
        int i;
        int element;

        det = 0;
        i = 0;
        matrix = arr_list[0];


        for (int j = 0; j < arr_list[0].getSize(); j++) {

            // Base Case: matrix is 1x1
            if (matrix.getSize() == 1) {
                return matrix.getValue(0);

            // compute sum of products, recursive step
            } else {
                element = matrix.getValue(j);
                minor = computeCofactor(arr_list, j);
                det += (int) Math.pow(-1, (i+j))*element*computeDeterminant(minor);
            }
        }
        return det;
    }

    /**
     * Function that computes the minor of a matrix
     * @param arr_list specified matrix passed in
     * @param j        column value removed to compute the minor
     * @return         minor of the specified matrix
     */
    public Mat_LL[] computeCofactor(Mat_LL[] arr_list, int j) {
        int p;
        int mat_dim;
        int val;
        Mat_LL matrix;
        Mat_LL minor_LL;
        Mat_LL[] minor;

        mat_dim = arr_list[0].getSize();

        // If matrix is 1x1, return value in the matrix as minor;
        if (mat_dim == 1) {
            minor = arr_list;
            return minor;
        // calculate minor of matrix by created new array of lists excluding first row and jth column
        } else {

            minor = new Mat_LL[mat_dim - 1];
            p = 0;

            for (int i = 0; i < mat_dim; i++) {
                matrix = arr_list[i];
                minor_LL = new Mat_LL();

                if (i == 0) {
                    continue;
                }

                for (int z = 0; z < matrix.getSize(); z++) {
                    if (z == j) {
                        continue;
                    }

                    val = matrix.getValue(z);
                    minor_LL.insertNode(val);
                }

                minor[p] = minor_LL;
                p++;
            }
            return minor;
        }
    }

}
