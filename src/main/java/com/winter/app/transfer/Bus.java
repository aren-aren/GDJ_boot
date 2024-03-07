package com.winter.app.transfer;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bus {
    public QQQQ getBus(QQQQ q) throws InterruptedException {
        System.out.println(q);
        System.out.println("버스타기");

        Thread.sleep(1000);

        return q;
    }
}
