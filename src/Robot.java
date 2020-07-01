import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot {


    public String emdList() throws IOException {

        String str = "057-123456789057-123456788057-123456787057-123456786057-123456785057-123456784057-123456783";
        List<String> list = Arrays.asList(str.split("057-"));
        String listString = String.join(", ", list);
        return listString;
    }


    public String log(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output1.txt", true), true);
        out.write(message);
        out.close();
        return toString();
    }


    public void iterateList() throws IOException {

        String iteration = emdList();
        String[] arrOfStr = iteration.split(",");
        for (String a : arrOfStr)
            this.scriptAmadeus(a);

    }


    public String scriptAmadeus(String emd) throws IOException {

        String script = "send \"EWD/EMD057-"+emd+"\""+ "\n" +
                "capture line:1,column:74,length:6 assign to pnr\n" +
                "send \"TRF057-" + emd + "/EMD\"\n" +
                "capture line:1,column:8,length:8 assign to responseSystem\n" +
                "if(\"REJECTED\"==responseSystem){\n" +
                "append \"QE99C1-\" + pnr to commandline\n" +
                "send commandline\n" +
                "}else{\n" +
                "send \"TRFP\"\n" +
                "}";
        return this.log(script);
    }
}






















