package banksys.logging;


import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by thiagoisaias on 1/25/16.
 */
public class Logger {

    public static void write(String message){

        System.out.println("Chamou a Logger.write");

        try{
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            File logFile = new File("log/logFile.txt");
            if (!logFile.exists()){
                logFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(logFile.getAbsoluteFile(),true);
            fileWriter.write("["+ time + "] - " + message + "\n");
            fileWriter.close();
            System.out.println(time);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
