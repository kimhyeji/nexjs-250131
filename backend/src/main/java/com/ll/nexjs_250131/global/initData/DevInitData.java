package com.ll.nexjs_250131.global.initData;

import com.ll.nexjs_250131.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class DevInitData {
    private final MemberService memberService;
    private final PostService postService;
    @Autowired
    @Lazy
    private DevInitData self;

    @Bean
    public ApplicationRunner devInitDataApplicationRunner() {
        return args -> {
            Ut.file.downloadByHttp("http://localhost:8080/v3/api-docs/apiV1", ".");

            String cmd = "yes | npx --package typescript --package openapi-typescript openapi-typescript apiV1.json -o ../frontend/src/lib/backend/apiV1/schema.d.ts";
            Ut.cmd.runAsync(cmd);
        };
    }
}