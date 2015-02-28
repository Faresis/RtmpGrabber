package ua.dp.mign.rtmp.external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RtmpSuck {
    public List<String> run() {
        List<String> output = new ArrayList<String>();
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(PROGRAM_NAME);
            System.console().readLine("Start video to grab and press enter.");
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
                boolean contentGrabbed = false;
                int attempt = 0;
                while (!contentGrabbed && attempt < maxAttempts) {
                    String line; 
                    long timeout = System.currentTimeMillis() + responseTimeout * 1000;
                    while(System.currentTimeMillis() < timeout) {
                        if (p.getErrorStream().available() > 0) {
                            line = input.readLine();
                            if(line != null) {
                                output.add(line);
                                System.out.println(line);
                                if(line.contains(finalLine)) {
                                    contentGrabbed = true;
                                    System.out.println("Content successfully grabbed.");
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("Timeout exceeded. Attempt #" + attempt);
                    attempt++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(p != null) {
                p.destroy();
            }
        }
        System.out.println("Closing rtmpstuck.");
        return output;
    }
    
    /**
     * Constructs RtmpSuck object for launching rtmpsuck process
     * @param responseTimeout timeout of program response
     * @param maxAttempts number of attempts will be performed with a specified timeout
     * @param finalLine line that will indicate that all required data is read from the process
     */
    public RtmpSuck(long responseTimeout, int maxAttempts, String finalLine) {
        this.responseTimeout = responseTimeout;  
        this.finalLine = finalLine;
        this.maxAttempts = maxAttempts;
    }
    
    private final long responseTimeout; 
    private final String finalLine; 
    private final int maxAttempts;
    private final static String PROGRAM_NAME = "rtmpsuck";
}
