package jiwjus.AllHospitalSchedules.scheduler;

import jiwjus.AllHospitalSchedules.crawler.ScheduleCrawlingSNUH;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor    // final 멤버 변수를 자동으로 생성합니다.
@Component                  // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가합니다.
public class SchedulerApplication {

    private final ScheduleCrawlingSNUH scheduleCrawlingSNUH;

//    @Scheduled(fixedRate = 1800000)     // 30분 간격으로 실행
    public void scheduleCrawling() throws InterruptedException{
        scheduleCrawlingSNUH.doit();    // 서울대학교병원 스케줄 크롤링
    }

}
