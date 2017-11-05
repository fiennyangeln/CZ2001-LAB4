import java.util.*; 
import java.io.*;   

public class CityNameGenerator
{
    public static String[] generateCityNames(String file_name, int size)
    // read a record from file using specified unique keywords separated by comma ','
    {
        String[] city_names = new String[size];
        
        try 
        {
            BufferedReader file = new BufferedReader(new FileReader(file_name));
            int counter = 0;
            String current_line = file.readLine();
            while(current_line != null)
            {
                city_names[counter] = current_line.replace("\n", "");
                counter++;
                if(counter == size)
                    break;
                current_line = file.readLine();
            }
            
            file.close();
        } 
        catch (Exception e) 
        
        {
            System.out.println("Problem reading file. " + file_name + " " + e.getMessage());
            System.exit(0);
        }
        return city_names;
    }

/*     public static void main(String[] args)
    {
        String[] arr = generateCityNames("city_name.txt", 100);
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + ",");
        }
    } */
}
