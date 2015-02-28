package ua.dp.mign.rtmp.parsing;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RtmpParser {
    public String createRtmpDumpString(Iterable<String> input) {
        final Pattern pattern = Pattern.compile("(\\w+\\s?\\w+): (\\S+)");
        Map<String, String> parameters = new HashMap<String, String>();
        for(String elem : input) {
            Matcher m = pattern.matcher(elem);
            if(m.matches()) {
                String paramName = m.group(1);
                if(RtmpDumpParameter.contains(paramName)) {
                    parameters.put(paramName, m.group(2));
                }
            }
        }
        return String.format("rtmpdump -r \"%s\" -y %s -W %s -p %s? -o %s", 
                             parameters.get(RtmpDumpParameter.TC_URL.getName()),
                             parameters.get(RtmpDumpParameter.PLAY_PATH.getName()).split("\\?")[0],
                             parameters.get(RtmpDumpParameter.SWF.getName()).split("\\?")[0],
                             parameters.get(RtmpDumpParameter.PAGE_URL.getName()).split("\\?")[0],
                             parameters.get(RtmpDumpParameter.SAVING_AS.getName()));
    }
}
