package com.liub.community.community.provider;


import com.alibaba.fastjson.JSON;
import com.liub.community.community.dto.AccessTokenDTO;
import com.liub.community.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType=MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            String token =string.split("&")[0]
                    .split("=")[1];
            return string;
        }catch(IOException e){

        }
        return null;
    }

    public GithubUser getUser(String AccessToken){
        System.out.println(AccessToken);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?"+AccessToken)
                .build();
        System.out.println(request.url());
        try{
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            GithubUser gitHubUser = JSON.parseObject(res, GithubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
