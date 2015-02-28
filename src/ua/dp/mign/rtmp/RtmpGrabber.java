package ua.dp.mign.rtmp;

import ua.dp.mign.rtmp.external.RtmpDump;
import ua.dp.mign.rtmp.external.RtmpSuck;
import ua.dp.mign.rtmp.parsing.RtmpDumpParameter;
import ua.dp.mign.rtmp.parsing.RtmpParser;

public class RtmpGrabber {
    public static void main(String[] args) {
        RtmpSuck suck = new RtmpSuck(10, 3, RtmpDumpParameter.SAVING_AS.getName());
        RtmpParser parser = new RtmpParser();
        RtmpDump dump = new RtmpDump();
        dump.run(parser.createRtmpDumpString(suck.run()));
    }

}
