package ua.dp.mign.rtmp.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RtmpDump {
    public void run(String exec) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(exec);
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                String line;
                while((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
