package com.db.datastoreserver.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
class UploadServiceTest {
    @Autowired
    private UploadService uploadService;

    @Test
    public void uploadTest() throws IOException {
        MultipartFile testFile = new MockMultipartFile("build.gradle", new FileInputStream("./build.gradle"));

        Assertions.assertDoesNotThrow(() -> uploadService.upload(testFile));
    }
}