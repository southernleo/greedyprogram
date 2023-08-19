 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 
public class halil_shapuk_2017510094 {
    public static int greedyApproach(int n, int p, int c, List<Integer> demands, List<Integer> salaries) {
        int cost = 0; // Total cost
        int rented = 0;//unrented players
        int hireCoaches = 0;//hired coach number
        int keepUnrented = 0;//rent cost
        for (int i = 0; i < n; i++) {
         
            int demand = demands.get(i); // Getting the demand for the current year
            if (p<=demand) {
            	
			hireCoaches = Math.max(0, demand - p-rented); // Calculating the number of coaches to hire (demand minus p, capped at 0)
            cost += hireCoaches * c;
            rented=0;
            }
            else {
			
            	if(demands.get(i+1)>p&&i!=n-1) {
            		 // Calculating the cost of keeping unrented players (maximum of 0 and p minus demand, multiplied by the salary)
            	  keepUnrented = Math.max(0, p - demand) * salaries.get(p-demand-1); // Calculating the cost of keeping unrented players (maximum of 0 and p minus demand, multiplied by the salary)
            	 hireCoaches = Math.max(0, demand - p); 
            	 // Checking if hiring coaches is cheaper than keeping unrented players
            	  if (hireCoaches * c <= keepUnrented) {
                 rented=p-demand;
           	     cost +=keepUnrented; 
          	         	
                                                 }   	          	
            	                                }
           
               }
              }
        
            return cost; // Returning the total cost
      }
    
    public static void main(String[] args) {
        int n = 4; // Number of years
        int p = 5; // Number of players to promote
        int c = 10; // Coach cost
        
        List<Integer> demands = readPlayerDemands("yearly_player_demand.txt");
        List<Integer> salaries = readPlayerSalaries("players_salary.txt");
        
        int cost = greedyApproach(n, p, c, demands, salaries); // Calculating the minimum cost using the greedy approach
        System.out.println("Greedy Approach Results: " + cost); // Printing the result
    }
    
    private static List<Integer> readPlayerDemands(String fileName) {
        List<Integer> demands = new ArrayList<>(); // Creating a new ArrayList to store the demands
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = reader.readLine(); // Reading the first line (header) and discarding it
            while ((line = reader.readLine()) != null) { // Reading the remaining lines until the end of the file
                String[] parts = line.trim().split("\t"); // Splitting the line by tab ('\t') delimiter
                int year = Integer.parseInt(parts[0]); // Parsing the year from the first part
                int demand = Integer.parseInt(parts[1]); // Parsing the demand from the second part
                demands.add(demand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return demands;
    }
    
    private static List<Integer> readPlayerSalaries(String fileName) {
        List<Integer> salaries = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = reader.readLine(); // Reading the first line (header) and discarding it
            while ((line = reader.readLine()) != null) { // Reading the remaining lines until the end of the file
                String[] parts = line.trim().split("\t"); // Splitting the line by tab ('\t') delimiter
                int year = Integer.parseInt(parts[0]); // Parsing the year from the first part
                int salary = Integer.parseInt(parts[1]); // Parsing the salary from the second part
                salaries.add(salary);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return salaries;
    }
}
