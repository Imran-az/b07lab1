import java.util.*;
public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        coefficients = new double[] {0};
    }

    public Polynomial(double[] arr1){
        coefficients = new double[arr1.length];
        //sets the arr1 value at the i to the index of coefficients array
        for (int i = 0; i < arr1.length; i++){
            coefficients[i] = arr1[i];
        }
    }
    public Polynomial add(Polynomial poly1){
        //figure out the max length of our new array to store the added arrays
        int max_length = Math.max(coefficients.length, poly1.coefficients.length);
        int min_length = Math.min(coefficients.length, poly1.coefficients.length);
        double[] new_arr = new double[max_length];
        Polynomial arr2 = new Polynomial();

        
        for(int i = 0; i < min_length; i++){
            new_arr[i] = coefficients[i] + poly1.coefficients[i];
        }
        if (coefficients.length > poly1.coefficients.length){
            for(int i = min_length; i < max_length; i++){
                new_arr[i] = coefficients[i];
            }
        }
        
        else {
            for (int i = min_length; i < max_length; i++){
                new_arr[i] = poly1.coefficients[i];
            }
        }
        arr2.coefficients = new_arr;
        return arr2;
    }

    public double evaluate(double x){
        double total = 0;

        for (int i = 0; i < coefficients.length; i++){
            //adds the element at the index which is the coefficent and multiplies it by x value 
            //raised to the i expoennt
            total += coefficients[i] * Math.pow(x,i);
        }
        return total;
    }
    
    public boolean hasRoot(double val){
        if (evaluate(val) == 0){
            return true;
        }
        return false;
    }

    
}