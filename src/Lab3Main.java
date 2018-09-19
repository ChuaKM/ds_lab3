/* Koon Chua
 * EN 605.202.81
 * Lab 3
 *
 * Lab 3 Driver
 * Uses ReadWrite to open an input file, and write to an output file
 * Instantiate a matrix using Mat_LL class, reading in input data
 * Run computeDeterminant to calculate the determinant for a nxn matrix
 * Output file is written with the input matrix followed by the calculated determinant
 */

public class Lab3Main {

    public static void main(String[] args) {
        Mat_LL matrix;
        Mat_LL[] arr_list;
        String z;
        String[] split_string;
        int dim;
        int index;
        int val;
        int det;

        // initiate main algorithm
        try {
            ReadWrite readWrite = new ReadWrite(args[0], args[1]);
            z = readWrite.readMatrix();
            dim = 0;
            det = 0;

            // runtime metric
            long startTime = System.currentTimeMillis();

            while (z != null) {
                // Check for valid dimension argument
                try {
                    dim = Integer.parseInt(z);
                } catch (Exception e) {
                    System.err.println("Error: matrix dimension should be one value, check input file");
                }

                z = readWrite.readMatrix();

                // check for empty strings
                if (z.length() ==0) {
                    continue;
                }

                // Create Linked Lists to hold each row of matrix
                split_string = null;
                arr_list = new Mat_LL[dim];
                for (int i = 0; i < dim; i++) {

                    matrix = new Mat_LL();

                    try{
                        matrix.setHeader(i);
                        split_string = z.split(" ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                    index = 0;
                    for (int j = 0; j < dim; j++) {

                        try {
                            // set node value to the parsed integer
                            val = Integer.parseInt(split_string[index]);
                            matrix.insertNode(val);
                        } catch (Exception e) {
                            System.err.println(e);
                        }

                        index++;
                    }
                    arr_list[i] = matrix;
                    z = readWrite.readMatrix();

                }

                // Calculate determinant of matrix
                Determinant detCalc = new Determinant();
                det = detCalc.computeDeterminant(arr_list);
                readWrite.writeOutput(arr_list, det);
            }

            // runtime calculations
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("\n The program took " + elapsedTime + " ms to run!");

            // Close input/output files
            readWrite.closeOutput();

        } catch (Exception e) {
            System.err.println("Error: There was an input/output parameter issue, check input file");
        }

    }
}

