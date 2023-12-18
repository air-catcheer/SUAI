import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqResp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("tyt");
        String filedb = "//home//shusha//Code//SUAI//Java//Labs//Lab16//lab16//src//main//resources//pack.txt";
        FileReader fr = new FileReader(filedb);
        BufferedReader bfr = new BufferedReader(fr);
        String tmp = new String();
        ArrayList<Kit> nd = new ArrayList<>();
        while (((tmp = bfr.readLine())) != null) {

            String namenum[] = tmp.split("//"); // =>> name//header//text//data
            String namet = namenum[0];
            StringBuilder sb = new StringBuilder();
            for (String tp : namenum) {
                if (tp.equals(namet)) {
                    continue;
                }
                sb.append(tp);
                sb.append(" ");

            }
            Kit tmpKit = new Kit(namet, sb);
            nd.add(tmpKit);
        }
        bfr.close();
        System.out.println(nd);
        response.getWriter().println(nd);
    }

    public class Kit {
        public String mainName;
        public StringBuilder arr;

        Kit(String name, StringBuilder sb) {
            mainName = name;
            arr = sb;

        }

        @Override
        public String toString() {
            return mainName + " " + arr;
        }
    }
}
