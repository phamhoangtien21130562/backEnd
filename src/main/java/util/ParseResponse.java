package util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ParseResponse {
    public enum HttpStatus {
        OK(200),
        NOT_FOUND(404),
        INTERNAL_SERVER_ERROR(500);

        private final int code;

        private HttpStatus(int code) {
            this.code = code;
        }

        public int code() {
            return code;
        }
    }


    public static <T> void  Res(HttpServletResponse res, HttpStatus status,T data ) throws IOException {


        try {
            res.setStatus(status.code());
            String json = ParseRequest.toJson(data);
            res.setContentType("application/json");
            PrintWriter rs = res.getWriter();
            rs.println(json);
            rs.flush();
            rs.close();

         } catch (IOException e) {
            res.setStatus(500);
            PrintWriter rse = res.getWriter();
            rse.println("{'error':'query thanh cong nhung tra ve that bai'}");
            rse.flush();
            rse.close();
        }


    }
    public static <T> void  textRes(HttpServletResponse res, HttpStatus status,T data ) throws IOException {


        try {

            res.setStatus(status.code());

            res.setContentType("text/plain");
            PrintWriter rs = res.getWriter();
            rs.println(data.toString());
            rs.flush();
            rs.close();

        } catch (IOException e) {
            res.setStatus(500);
            PrintWriter rse = res.getWriter();
            rse.println("{'error':'query thanh cong nhung tra ve that bai'}");
            rse.flush();
            rse.close();
        }


    }

}
