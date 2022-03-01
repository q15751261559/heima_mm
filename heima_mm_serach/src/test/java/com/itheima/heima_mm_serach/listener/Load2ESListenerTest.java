package com.itheima.heima_mm_serach.listener;

import com.itheima.heima_mm_serach.HeimaMmSerachApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HeimaMmSerachApplication.class)
class Load2ESListenerTest {
    @Autowired
    Load2ESListener load2ESListener;
    @Test
    void test1() throws IOException {
        load2ESListener.loadQuestionToEs("1");
    }
}
