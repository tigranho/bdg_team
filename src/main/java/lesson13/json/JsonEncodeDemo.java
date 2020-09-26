package lesson13.json;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

public class JsonEncodeDemo {
    public static void main(String[] args) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("name","foo");
        obj.put("num", 100);
        obj.put("balance", 1000.21);
        obj.put("is_vip", Boolean.TRUE);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);
        System.out.println(out.toString());
    }
}
