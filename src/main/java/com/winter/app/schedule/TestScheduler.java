package com.winter.app.schedule;

import com.winter.app.member.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
    @Autowired
    MemberDAO memberDAO;

//    @Scheduled(fixedRate = 1000, initialDelay = 1000)
//    public void useFixRate(){
//        System.out.println("Fix Rate");
//    }

    /*
        Fix Rate - 주기마다 메서드의 종료와 관계없이 실행
        Fix Delay - 주기마다 메서드 종료 후부터 시간을 세서 실행
     */
//    @Scheduled(fixedRateString = "1000", initialDelayString = "1000")
//    public void useFixRate(){
//        System.out.println("Fix Rate");
//    }
//
//    @Scheduled(fixedDelay = 2000, initialDelayString = "1000")
//    public void useFixDelay(){
//        System.out.println("Fix Delay");
//    }

    /*
        cron ( 초 분 시 일 월 요일 )
        요일 -
            0 : 일요일
            1 : 월요일
            2 : 화요일 ...
        매 주기마다는 *
        * /n : n을 주기로
        n-m : n부터 m까지 1단위로
        n,m : n과 m일 때
     */
    @Scheduled(cron = "0 */30 * * * *")
    public void useCron(){
        System.out.println("30분마다 알람!!!");
    }
}
