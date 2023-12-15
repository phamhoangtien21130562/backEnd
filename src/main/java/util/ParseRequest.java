package util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class ParseRequest {
    public static <T> T toObject(HttpServletRequest request, Class<T> modelclass ) {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = null;
        T returnjson=null;
        try {
            reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }


//            String bodyData = requestBody.toString();
            Gson gson = new Gson();
            returnjson = gson.fromJson(requestBody.toString(), modelclass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return returnjson ;
    }
    public static <T> String toJson(T data) {
        String json =null;
        try {
            Gson gson = new Gson();
            json = gson.toJson(data);
        }catch (Exception e){
            e.printStackTrace();
        }

        return json;
    }
}
