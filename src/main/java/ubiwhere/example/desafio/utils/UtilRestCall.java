package ubiwhere.example.desafio.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Auxiliary class to do the communication with the server.
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
public class UtilRestCall {

    /**
     * Execute a GET in to the server.
     *
     * @param urlAPI The full url of the API REST.
     * @return String
     *
     */
    public static String callAPIGET(final String urlAPI) {

        try {
            URL url = new URL(urlAPI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000); //set timeout to 2 seconds
            //conn.setRequestProperty("Content-Type", "application/json");
            if (conn.getResponseCode() != 200) {
                return "Failed : HTTP error code : "
                        + conn.getResponseCode();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder result = new StringBuilder();
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //  System.out.println(output);
                result.append(output);
            }

            conn.disconnect();
            return result.toString();
        } catch (MalformedURLException e) {
            System.out.println("callAPIGET Fail : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("callAPIGET Fail : " + e.getMessage());
        }

        return "";
    }

    /**
     *
     * Execute a POST in to the server.
     *
     * @param urlAPI The full url of the API REST.
     * @return the response of the server.
     */
    public static String callAPIPOST(String urlAPI, String body) {
        try {
            URL url = new URL(urlAPI);
            //System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000); //set timeout to 2 seconds
            //conn.setDoInput(true);

            StringBuilder result;
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
                os.flush();
                if (conn.getResponseCode() != 200) {
                    return "Failed : HTTP error code : "
                            + conn.getResponseCode();
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())))) {
                    String output;
                    result = new StringBuilder();
                    //System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        //  System.out.println(output);
                        result.append(output);
                    }
                }
            }
            conn.disconnect();

            return result.toString();
        } catch (IOException | RuntimeException e) {
            System.out.println("callAPIPOST Fail : " + e.toString());
            e.printStackTrace();
            return "Failed : " + e.getMessage();

        }

    }

    /**
     *
     * Execute a GET in to the server without a body.
     *
     * @param urlAPI The full url of the API REST.
     * @return the response of the server.
     */
    public static String callAPIPOST(String urlAPI) {
        return callAPIPOST(urlAPI, "");
    }

    /**
     *
     * Execute a DELETE in to the server.
     *
     * @param urlAPI The full url of the API REST.
     *
     * @return the response of the server.
     */
    public static String callAPIDELETE(String urlAPI) {
        try {
            URL url = new URL(urlAPI);
            //System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000); //set timeout to 2 seconds
            //conn.setDoInput(true);

            StringBuilder result;

            if (conn.getResponseCode() != 200) {
                return "Failed : HTTP error code : "
                        + conn.getResponseCode();
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())))) {
                String output;
                result = new StringBuilder();
                //System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    //  System.out.println(output);
                    result.append(output);
                }
            }
            conn.disconnect();

            return result.toString();
        } catch (IOException | RuntimeException e) {
            System.out.println("callAPIDELETE Fail : " + e.toString());
            e.printStackTrace();
            return "Failed : " + e.getMessage();

        }
    }

    /**
     *
     * Execute a DELETE in to the server without a body.
     *
     * @param urlAPI The full url of the API REST.
     *
     * @return the response of the server.
     */
    /**
     *
     * Execute a PUT in to the server.
     *
     * @param urlAPI The full url of the API REST.
     * @param body The information that was to be send on the body of the
     * request.
     *
     * @return the response of the server.
     */
    public static String callAPIPUT(String urlAPI, String body) {
        try {
            URL url = new URL(urlAPI);
            //System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(1000); //set timeout to 2 seconds
            //conn.setDoInput(true);

            StringBuilder result;
            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes());
                os.flush();
                if (conn.getResponseCode() != 200) {
                    return "Failed : HTTP error code : "
                            + conn.getResponseCode();
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())))) {
                    String output;
                    result = new StringBuilder();
                    //System.out.println("Output from Server .... \n");
                    while ((output = br.readLine()) != null) {
                        //  System.out.println(output);
                        result.append(output);
                    }
                }
            }
            conn.disconnect();

            return result.toString();
        } catch (IOException | RuntimeException e) {
            System.out.println("callAPIPUT Fail : " + e.toString());
            e.printStackTrace();
            return "Failed : " + e.getMessage();

        }
    }

    /**
     *
     * Execute a PUT in to the server without a body.
     *
     * @param urlAPI The full url of the API REST.
     *
     * @return the response of the server.
     */
    public static String callAPIPUT(String urlAPI) {
        return callAPIPUT(urlAPI, "");
    }
}
