package com.blockwit.learn.comaric.api;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class Authorization {


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Map<String, String> formData = new HashMap<>();
        formData.put("log", "admin");
        formData.put("admin", "");
        formData.put("wp-submit", "Войти");
        formData.put("redirect_to", "https://visa-arkhangelsk.ru/wp-admin/");
        formData.put("testcookie", "1");

        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(formData)))
                .uri(new URI("https://visa-arkhangelsk.ru/wp-login.php?redirect_to=https%3A%2F%2Fvisa-arkhangelsk.ru%2Fwp-admin%2F&reauth=1"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.headers().firstValue("location"));
    }
    private static String getFormDataAsString(Map<String, String> formData) {
        StringBuilder formBodyBuilder = new StringBuilder();
        for (Map.Entry<String, String> singleEntry : formData.entrySet()) {
            if (formBodyBuilder.length() > 0) {
                formBodyBuilder.append("&");
            }
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
            formBodyBuilder.append("=");
            formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
        }
        return formBodyBuilder.toString();
    }



}
