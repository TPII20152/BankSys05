package banksys.logging;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

/**
 * Created by thiagoisaias on 1/25/16.
 */
public class Logger {

    public static void write(String message){

        String logFile = "logFile.txt";
        String time = LocalDateTime.now().toString();

        try{
            FileWriter fileWriter = new FileWriter(logFile);
            BufferedWriter bfWriter = new BufferedWriter(fileWriter);

            bfWriter.write("["+ time + "] - " + message);
            bfWriter.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
