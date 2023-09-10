/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package temporal;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis(); // Registra el tiempo de inicio
            
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\temporal\\src\\temporal\\dummy_data.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("processed_data.csv"));

            String line = reader.readLine();
            String[] headers = line.split(",");
            writer.write("Username,Birthdate,Age,Income,Debt,IncomeMinusDebt\n");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String username = values[0];
                Date birthdate = dateFormat.parse(values[1]);
                double income = Double.parseDouble(values[2]);
                double debt = Double.parseDouble(values[3]);

                long ageInMillis = currentDate.getTime() - birthdate.getTime();
                int age = (int) (ageInMillis / (1000 * 60 * 60 * 24 * 365.25));

                double incomeMinusDebt = income - debt;

                writer.write(username + "," + values[1] + "," + age + "," + income + "," + debt + "," + incomeMinusDebt + "\n");
            }

            reader.close();
            writer.close();
            
            long endTime = System.currentTimeMillis(); // Registra el tiempo de finalización
            double durationSeconds = (endTime - startTime) / 1000.0; // Calcula la duración en segundos
            
            System.out.println("ETL process completed.");
            System.out.println("Tiempo de ejecución: " + durationSeconds + " segundos");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
