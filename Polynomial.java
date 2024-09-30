import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial{
    double[] coefficients;
    int[] exponents;

    public Polynomial(){
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }

    public Polynomial(double[] arr1, int[] arr2){
        coefficients = new double[arr1.length];
        exponents = new int[arr2.length];
        //sets the arr1 value at the i to the index of coefficients array
        for (int i = 0; i < arr1.length; i++){
            coefficients[i] = arr1[i];
            exponents[i] = arr2[i];
        }
    }
    public Polynomial(File file){
        String str = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            str = br.readLine();

            String[] terms = str.split("(?=[+-])");
            int index = 0;
            coefficients = new double[terms.length];
            exponents = new int[terms.length];


            for(String term : terms) {
                //finds the index of x
                int index_x = term.indexOf("x");

                //if x does not exist add only the coeffient term and 0 for exponent
                if (index_x == -1){
                    String coeff = term.substring(0, term.length());
                    coeff = coeff.replace("+", "");
                    double double_coeff = Double.parseDouble(coeff);

                    coefficients[index] = double_coeff;
                    exponents[index] = 0;
                    index++;
                }
                //otherwise index exists find the coeff term and exponent term and add it into our desired arrays
                else{
                String coeff = term.substring(0, index_x);
                coeff = coeff.replace("+","");
                double double_coeff = Double.parseDouble(coeff);

                String exp = term.substring(index_x + 1, term.length());
                int int_exp = Integer.parseInt(exp);

                coefficients[index] = double_coeff;
                exponents[index] = int_exp;
                index++;
                }
            }
            br.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
    }


    public void saveToFile(String filename){
        try {
            FileWriter writer = new FileWriter(filename);
            String line = "";

            for (int i = 0; i < exponents.length; i++){
                //there exists an exponent at location
                if (exponents[i] != 0){
                    line += coefficients[i] + "x" + exponents[i];
                    //if the index is not the last index and the next coefficent is greater than 0 add a "+"
                    if (i < (coefficients.length - 1) && coefficients[i+1] > 0){
                        line += "+";
                    }
                    //otherwise a "-" is naturally added 
                }
                //no exponent exists
                else{
                    line += coefficients[i];
                    //if the index is not the last index and the next coefficent is greater than 0 add a "+"
                    if (i < (coefficients.length - 1) && coefficients[i+1] > 0){
                        line += "+";
                    }
                }
                //otherwise a "-" is naturally added 
            }
            
            writer.write(line);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public Polynomial add(Polynomial poly1){
        HashMap<Integer, Double> call_obj = new HashMap<>();

        //populating a hashmap for calling object
        for (int i = 0; i < exponents.length; i++){
            call_obj.put(exponents[i], coefficients[i]);
        }

        for (int i = 0; i < poly1.exponents.length;i++){
            if (call_obj.containsKey(poly1.exponents[i])){
                double addition = call_obj.get(poly1.exponents[i]) + poly1.coefficients[i];
                if (addition == 0){
                    call_obj.remove(poly1.exponents[i]);
                }
                else{
                    call_obj.put(poly1.exponents[i], addition);
                }
            }
            else{
                call_obj.put(poly1.exponents[i], poly1.coefficients[i]);
            }
        }

       int index = 0;
       int size = call_obj.size();
       double[] new_coefficents = new double[size];
       int[] new_exponents = new int[size];
       Polynomial new_arr = new Polynomial(new_coefficents, new_exponents);
        //converting it to Polynomial type
        for (Integer obj_key : call_obj.keySet()){
            new_arr.coefficients[index] = call_obj.get(obj_key);
            new_arr.exponents[index] = obj_key;
            index++;
        }
        
        return new_arr;
    }

    public double evaluate(double x){
        double total = 0;

        for (int i = 0; i < coefficients.length; i++){
            //adds the element at the index which is the coefficent and multiplies it by x value 
            //raised to the i expoennt
            total += coefficients[i] * Math.pow(x,exponents[i]);
        }
        return total;
    }
    
    public boolean hasRoot(double val){
        if (evaluate(val) == 0){
            return true;
        }
        return false;
    }


    public Polynomial multiply(Polynomial poly){
        HashMap<Integer, Double> call_obj = new HashMap<>();
        HashMap<Integer, Double> arg = new HashMap<>();
        HashMap<Integer, Double> result = new HashMap<>();
        

        //populating a hashmap for calling object
        for (int i = 0; i < exponents.length; i++){
            call_obj.put(exponents[i], coefficients[i]);
        }

        //populating a hashmap for argument
        for (int i = 0; i < poly.exponents.length; i++){
            arg.put(poly.exponents[i], poly.coefficients[i]);
        }
        for (Integer obj_key : call_obj.keySet()){
            for(Integer arg_key : arg.keySet()){
                //saves multiplied values
                int new_exp = obj_key + arg_key;
                double new_coeff = call_obj.get(obj_key) * arg.get(arg_key);
                
                //if exponent already exists in result array add the coefficents
                if (result.containsKey(new_exp)){
                    double add_coeff = result.get(new_exp) + new_coeff;
                    result.put(new_exp, add_coeff);
                }
                //if expoenent doesn't exist add the new expoenent and coefficient together
                else{
                    result.put(new_exp, new_coeff);
                }
            }
        }
        int size = result.size();
        double[] new_coefficents = new double[size];
        int[] new_exponents = new int[size];
        Polynomial new_poly = new Polynomial(new_coefficents, new_exponents);
        int index = 0;
        for (Integer result_key : result.keySet()){
            new_poly.exponents[index] = result_key;
            new_poly.coefficients[index] = result.get(result_key);
            index++;
        }
        
        return new_poly;
    }

    
}
