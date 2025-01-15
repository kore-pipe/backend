package org.dongkoer.com.controller;

import com.alibaba.fastjson2.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.dongkoer.com.entity.Predict;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/predict")
public class PredictController {
    OkHttpClient client = new OkHttpClient();

    @PostMapping("/predict")
    public String getAllByYear(@RequestBody Predict predict){
        String jsonString = JSON.toJSONString(predict);

      okhttp3.RequestBody requestJsonBody = okhttp3.RequestBody.create(
              jsonString,
                MediaType.parse("application/json")
        );
        Request postRequest = new Request.Builder()
                .url("127.0.0.1")
                .post(requestJsonBody)
                .build();
        try {
            Response response = client.newCall(postRequest).execute();
            System.out.println(response.body().string());
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
       Predict predict = new Predict();
       predict.setCity("111");
       predict.setDate(new Date("2024/12/03"));
       //predict.setProvince("重庆");
        String json = JSON.toJSONString(predict);
        System.out.println(json);
    }
}

