package br.com.dev.javaclient;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaClientTest {
    public static void main(String[] args) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String user = "toyo";
        String password = "devdojo";

        try{
            URL url = new URL("http://localhost:8080/v1/protected/students/1");
            connection =  (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic " + encoderUsernamePassword(user, password));
//            connection.addRequestProperty("Authorization", "Basic dG95bzpkZXZkb2pv");
            System.out.println(encoderUsernamePassword(user, password));
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonSB = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null){
                jsonSB.append(line);
            }
            System.out.println(jsonSB.toString());

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
            if(connection != null){
                connection.disconnect();
            }
        }
    }

    private static String encoderUsernamePassword(String user, String password){
        String userPassword = user + ":" + password;
        return new String(Base64.encodeBase64(userPassword.getBytes()));
    }
}
