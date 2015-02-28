package ua.dp.mign.rtmp.parsing;

public enum RtmpDumpParameter {
    TC_URL("tcUrl"),
    PLAY_PATH("Playpath"),
    SWF("swfUrl"),
    PAGE_URL("pageUrl"),
    SAVING_AS("Saving as");
    
    private RtmpDumpParameter(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static boolean contains(String str) {
        for(RtmpDumpParameter p : values()) {
            if(p.getName().equals(str))
                return true;
        }
        return false;
    }
    
    private String name;
}
