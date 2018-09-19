/* Koon Chua
 * EN 605.202.81
 * Lab 3
 *
 * ReadWrite class parses input file and writes output
 */

import java.io.*;

public class ReadWrite {
    private String input_string = new String();
    private String output_string = new String();
    private File input_file;
    private File output_file;
    private BufferedReader input;
    private BufferedWriter output;
    private String z;

    /**
     * Constructor Method
     * Opens input file and output file
     * @param read  input file
     * @param write output file
     */
    public ReadWrite(String read, String write) {
        File input_file = new File(read);
        File output_file = new File(write);

        // In case of invalid input/output file
        try {
            input = new BufferedReader(new FileReader(input_file)); // read input
            output = new BufferedWriter(new FileWriter(output_file)); // write output
        } catch (Exception e) {
            System.err.println("Error: Invalid input/output parameters.");
        }
    }

    /**
     * Function that reads in strings of integers from input file
     * @return String of integers
     */
    public String readMatrix() {
        try {
            z = input.readLine();
        } catch (IOException e) {
            System.err.println("ERROR: matrix dimension should be one value, check input file");
        }
        return z;
    }

    /**
     * Function that writes the matrix + determinant result to the output file
     * @param list_arr  Specified input matrix
     * @param det       value of determinant
     */
    public void writeOutput(Mat_LL[] list_arr, int det) {
        int size;
        int list_size;
        int node_value;

        Mat_LL mat = new Mat_LL();
        size = list_arr.length;
        try {

            for (int i = 0; i < size; i++) {
                mat = list_arr[i];
                list_size = mat.getSize();

                for (int j = 0; j < list_size; j++) {
                    node_value = mat.getValue(j);
                    output.write(node_value + " ");
                }
                output.newLine();
            }
            output.write("\nThe Determinant of the matrix is: " + det + "\n\n");
            output.newLine();

        } catch (Exception e) {
            System.err.println("Error: Could not write to Output.");
        }
    }

    /**
     * Function to close input and output files.
     */
    public void closeOutput() {
        try {
            input.close();
            output.close();
        } catch (Exception e){
            System.err.println("Error: Could not close input/output files.");
        }
    }
}