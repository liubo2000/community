package com.liub.community.community.controller;

import com.liub.community.community.dto.AccessTokenDTO;
import com.liub.community.community.provider.GithubProvider;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("c8d51d6dbcc5633129a9");
        accessTokenDTO.setClient_secret("2869947536a24223ddc2b033615cfbd9fffce354");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        githubProvider.getAccessToken(new AccessTokenDTO());
        return "index";
    }
}
