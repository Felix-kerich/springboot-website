package org.travel.safirilinks.serviceLayer;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MpesaPayment {

    public static Map<String, Object> mpesaPayment(String amount, String phoneNumber) {
        // Safaricom Developer Portal Credentials
        String consumerKey = "IWw4FAjmtDGWWwUzFWSLIPUtLyZhsYxmPC8TdbvZC2QAGraY";
        String consumerSecret = "YJw6GASORyg5ytGo3FAUQUiGlLV458Z23kGBNA2tOrdXjXZ0wuVcJGXdVn23A7Bq";

        // M-Pesa Shortcode and Passkey
        String shortcode = "174379";
        String passkey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";

        // API Endpoints
        String accessTokenUrl = "https://api.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials";
        String paymentUrl = "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest";

        // Get access token
        String accessToken = getAccessToken(accessTokenUrl, consumerKey, consumerSecret);

        // Format phone number in international format
        String internationalPhoneNumber = phoneNumber;

        // Calculate Lipa Na M-Pesa Online password
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String lipaNaMpesaOnlinePassword = Base64.getEncoder().encodeToString((shortcode + passkey + timestamp).getBytes());

        // Prepare STK push request payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("BusinessShortCode", shortcode);
        payload.put("Password", lipaNaMpesaOnlinePassword);
        payload.put("Timestamp", timestamp);
        payload.put("TransactionType", "CustomerPayBillOnline");
        payload.put("Amount", amount);
        payload.put("PartyA", internationalPhoneNumber);
        payload.put("PartyB", shortcode);
        payload.put("PhoneNumber", internationalPhoneNumber);
        payload.put("CallBackURL", "https://mydomain.com/path");
        payload.put("AccountReference", "SafariLink Travellers");
        payload.put("TransactionDesc", "Payment of X");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);

        // Send STK push request
        ResponseEntity<Map> responseEntity = new RestTemplate().exchange(paymentUrl, HttpMethod.POST,
                new HttpEntity<>(payload, headers), Map.class);

        return responseEntity.getBody();
    }

    private static String getAccessToken(String accessTokenUrl, String consumerKey, String consumerSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(consumerKey, consumerSecret);

        ResponseEntity<Map> responseEntity = new RestTemplate().exchange(accessTokenUrl, HttpMethod.GET,
                new HttpEntity<>(headers), Map.class);

        return (String) responseEntity.getBody().get("access_token");
    }

    public static void main(String[] args) {
        String amount = "100";
        String phoneNumber = "254704494196"; // Replace with the actual phone number

        Map<String, Object> response = mpesaPayment(amount, phoneNumber);
        System.out.println(response);
    }
}
