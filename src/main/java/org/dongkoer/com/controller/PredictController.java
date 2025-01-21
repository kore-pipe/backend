package org.dongkoer.com.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.dongkoer.com.entity.Predict;
import org.dongkoer.com.entity.PredictPage;
import org.dongkoer.com.entity.PredictReceiver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


import java.util.List;
import java.util.PriorityQueue;

import com.alibaba.fastjson2.JSON;


@RestController
@RequestMapping("/api/predict")
@Tag(name = "predict AI interfaces", description = "restful interfaces")
public class PredictController {
    OkHttpClient client = new OkHttpClient();
    private final String url = "http://120.24.227.56:5000";

    @PostMapping("/predictByYear")
    @Operation(summary = "select Data using city and province")
    public ResponseEntity<String> getAllByYear(@RequestBody Predict predict) {
        String jsonString = JSON.toJSONString(predict);

//        okhttp3.RequestBody requestJsonBody = okhttp3.RequestBody.create(
//                jsonString,
//                MediaType.parse("application/json; charset=utf-8")
//        );
//        Request postRequest = new Request.Builder()
//                .url("http://120.24.227.56:5000")
//                .post(requestJsonBody)
//                .build();
//        try {
//            Response response = client.newCall(postRequest).execute();
//            String stringcode = response.body().string();
////            String s = decodeUnicode(stringcode);
//            stringcode = decodeUnicode(stringcode);
//            System.out.println(stringcode);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
//            return new ResponseEntity<>(stringcode, headers, HttpStatus.OK);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String stringcode = sendRequestion(jsonString);
        HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
            return new ResponseEntity<>(stringcode, headers, HttpStatus.OK);


    }

    public String sendRequestion(String jsonString) {
        okhttp3.RequestBody requestJsonBody = okhttp3.RequestBody.create(
                jsonString,
                MediaType.parse("application/json; charset=utf-8")
        );
        Request postRequest = new Request.Builder()
                .url(url)
                .post(requestJsonBody)
                .build();
        try {
            Response response = client.newCall(postRequest).execute();
            String stringcode = response.body().string();
//            String s = decodeUnicode(stringcode);
            stringcode = decodeUnicode(stringcode);
            System.out.println(stringcode);

            return stringcode;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/predictByDate")
    @Operation(summary = "get all predict data limited by index")
    public ResponseEntity<List<PredictReceiver>> getAllByDate(@RequestBody PredictPage predictPage) {
        if (predictPage.getFromIndex() == null || predictPage.getToIndex() == null) {
            throw new IllegalArgumentException("index is null");
        }
        if (predictPage.getFromIndex() > predictPage.getToIndex()) {
            throw new IllegalArgumentException("index is error");
        }
        if (predictPage.getFromIndex() < 0 || predictPage.getToIndex() < 0) {
            throw new IllegalArgumentException("index is error");
        }
        Predict predict = new Predict(predictPage.getProvince(), predictPage.getDate(), predictPage.getCity());
        String jsonString = JSON.toJSONString(predict);

//        okhttp3.RequestBody requestJsonBody = okhttp3.RequestBody.create(
//                jsonString,
//                MediaType.parse("application/json; charset=utf-8")
//        );
//        Request postRequest = new Request.Builder()
//                .url("http://120.24.227.56:5000")
//                .post(requestJsonBody)
//                .build();
//        try {
//            Response response = client.newCall(postRequest).execute();
//            String stringcode = response.body().string();
//
//
//            List<PredictReceiver> predictReceivers = JSON.parseArray(stringcode, PredictReceiver.class);
//
//            List<PredictReceiver> predictReceiversByIndex = predictReceivers.subList(predictPage.getFromIndex(), predictPage.getToIndex() + 1);
//            System.out.println(predictReceiversByIndex);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
//
//            return new ResponseEntity<>(predictReceiversByIndex, headers, HttpStatus.OK);
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String stringcode= sendRequestion(jsonString);
        HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);
        List<PredictReceiver> predictReceivers = JSON.parseArray(stringcode, PredictReceiver.class);

            List<PredictReceiver> predictReceiversByIndex = predictReceivers.subList(predictPage.getFromIndex(), predictPage.getToIndex() + 1);
            System.out.println(predictReceiversByIndex);
            return new ResponseEntity<>(predictReceiversByIndex, headers, HttpStatus.OK);
    }

    private String decodeUnicode(String unicodeString) {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < unicodeString.length()) {
            char c = unicodeString.charAt(i++);
            if (c == '\\' && i < unicodeString.length() && unicodeString.charAt(i) == 'u') {
                i++;
                int value = 0;
                for (int j = 0; j < 4 && i < unicodeString.length(); j++, i++) {
                    char ch = unicodeString.charAt(i);
                    if (ch >= '0' && ch <= '9') {
                        value = (value << 4) + ch - '0';
                    } else if (ch >= 'a' && ch <= 'f') {
                        value = (value << 4) + 10 + ch - 'a';
                    } else if (ch >= 'A' && ch <= 'F') {
                        value = (value << 4) + 10 + ch - 'A';
                    } else {
                        throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                    }
                }
                sb.append((char) value);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}


