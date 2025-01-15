package org.dongkoer.com.controller;

import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.dongkoer.com.entity.Predict;
import org.dongkoer.com.entity.ResponseFromPython;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

            return ResponseEntity.ok(stringcode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private String decodeUnicode(String unicodeString) {
        try {
            return URLDecoder.decode(unicodeString, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return unicodeString;
        }
    }
}

