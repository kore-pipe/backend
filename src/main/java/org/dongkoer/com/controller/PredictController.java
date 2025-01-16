package org.dongkoer.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.dongkoer.com.entity.Predict;
import org.dongkoer.com.entity.ResponseFromPython;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

@RestController
@RequestMapping("/api/predict")
@Tag(name = "预测模型控制器", description = "restful 接口")
public class PredictController {
    OkHttpClient client = new OkHttpClient();

    @PostMapping("/predictByYear")
    @Operation(summary = "获取所有预测")
    public ResponseEntity<String> getAllByYear(@RequestBody Predict predict){
        String jsonString = JSON.toJSONString(predict);

      okhttp3.RequestBody requestJsonBody = okhttp3.RequestBody.create(
              jsonString,
                MediaType.parse("application/json; charset=utf-8")
        );
        Request postRequest = new Request.Builder()
                .url("http://120.24.227.56:5000")
                .post(requestJsonBody)
                .build();
        try {
            Response response = client.newCall(postRequest).execute();
            String stringcode = response.body().string();
//            String s = decodeUnicode(stringcode);
            stringcode = decodeUnicode(stringcode);
            System.out.println(stringcode);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8);



            return new ResponseEntity<>(stringcode, headers, HttpStatus.OK);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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


