import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
     // Test 1: Adding polynomials with non-canceling terms
     double[] c1 = {3, 2, 1};   // 3 + 2x^1 + 1x^2
     int[] e1 = {0, 1, 2};      // Exponents: [0, 1, 2]
     double[] c2 = {1, 1, 2};   // 1 + 1x^1 + 2x^2
     int[] e2 = {0, 1, 2};      // Exponents: [0, 1, 2]
     Polynomial p1 = new Polynomial(c1, e1);
     Polynomial p2 = new Polynomial(c2, e2);
     Polynomial result1 = p1.add(p2);
     System.out.println("Test 1 (Add): " + Arrays.toString(result1.coefficients) + ", " + Arrays.toString(result1.exponents));

     // Test 2: Adding polynomials with some terms canceling out
     double[] c3 = {5, -3, 1};   // 5 - 3x + x^2
     int[] e3 = {0, 1, 2};       // Exponents: [0, 1, 2]
     double[] c4 = {-5, 3, 2};   // -5 + 3x + 2x^2
     int[] e4 = {0, 1, 2};       // Exponents: [0, 1, 2]
     Polynomial p3 = new Polynomial(c3, e3);
     Polynomial p4 = new Polynomial(c4, e4);
     Polynomial result2 = p3.add(p4);
     System.out.println("Test 2 (Add): " + Arrays.toString(result2.coefficients) + ", " + Arrays.toString(result2.exponents));

     // Test 3: Adding polynomials with different exponents
     double[] c5 = {2, 3};   // 2x^1 + 3x^3
     int[] e5 = {1, 3};      // Exponents: [1, 3]
     double[] c6 = {4, 1};   // 4 + x^2
     int[] e6 = {0, 2};      // Exponents: [0, 2]
     Polynomial p5 = new Polynomial(c5, e5);
     Polynomial p6 = new Polynomial(c6, e6);
     Polynomial result3 = p5.add(p6);
     System.out.println("Test 3 (Add): " + Arrays.toString(result3.coefficients) + ", " + Arrays.toString(result3.exponents));


     // Test Cases for Multiplying Polynomials

     // Test 1: Multiplying two simple polynomials
     double[] c7 = {2, 1};    // 2 + x
     int[] e7 = {0, 1};       // Exponents: [0, 1]
     double[] c8 = {3, 4};    // 3 + 4x
     int[] e8 = {0, 1};       // Exponents: [0, 1]
     Polynomial p7 = new Polynomial(c7, e7);
     Polynomial p8 = new Polynomial(c8, e8);
     Polynomial result4 = p7.multiply(p8);
     System.out.println("Test 1 (Multiply): " + Arrays.toString(result4.coefficients) + ", " + Arrays.toString(result4.exponents));

     // Test 2: Multiplying polynomials with zero terms
     double[] c9 = {0, 2};   // 0 + 2x^1
     int[] e9 = {0, 1};      // Exponents: [0, 1]
     double[] c10 = {3, 0};  // 3x^2 + 0x
     int[] e10 = {2, 0};     // Exponents: [2, 0]
     Polynomial p9 = new Polynomial(c9, e9);
     Polynomial p10 = new Polynomial(c10, e10);
     Polynomial result5 = p9.multiply(p10);
     System.out.println("Test 2 (Multiply): " + Arrays.toString(result5.coefficients) + ", " + Arrays.toString(result5.exponents));

     // Test 3: Multiplying polynomials where terms cancel out
     double[] c11 = {2, -2}; // 2x^1 - 2x^1
     int[] e11 = {1, 1};     // Exponents: [1, 1]
     Polynomial p11 = new Polynomial(c11, e11);
     Polynomial result6 = p11.multiply(p11);
     System.out.println("Test 3 (Multiply): " + Arrays.toString(result6.coefficients) + ", " + Arrays.toString(result6.exponents));


     // Test Cases for Evaluating Polynomials
     // Define some test coefficients and exponents
     double[] coefficients = {2, 5, 8, 9, 3};
     int[] exponents = {1, 3, 6, 2, 5};

     // Create a Polynomial object (assuming you have an appropriate constructor)
     Polynomial polynomial = new Polynomial(coefficients, exponents);

     // Call the saveToFile method to save the polynomial to a file
     polynomial.saveToFile("polynomial_output.txt");

     // Confirm by reading and printing the content of the file (optional)
     try {
         File file = new File("polynomial_output.txt");
         Scanner scanner = new Scanner(file);
         while (scanner.hasNextLine()) {
             System.out.println(scanner.nextLine());
         }
         scanner.close();
     } catch (FileNotFoundException e) {
         e.printStackTrace();
     }       
 
     //Now we will see if the constructor works 
     // Create a Polynomial object from a file
     File newFile = new File("polynomial_output.txt");
     Polynomial n = new Polynomial(newFile);
     System.out.println("Polynomial from file:");
     printPolynomial(n);

 }

  // Helper method to print the polynomial
  private static void printPolynomial(Polynomial p) {
     double[] bcoefficients = p.coefficients;
     int[] bexponents = p.exponents;

     for (int i = 0; i < bcoefficients.length; i++) {
         if (i > 0 && bcoefficients[i] > 0) {
             System.out.print("+");
         } else if (i > 0 && bcoefficients[i] < 0) {
             System.out.print("-");
             bcoefficients[i] = -bcoefficients[i]; // Make the coefficient positive for printing
         }
         System.out.print(bcoefficients[i] + "x^" + bexponents[i]);
     }
     System.out.println();

        
    }   
}

