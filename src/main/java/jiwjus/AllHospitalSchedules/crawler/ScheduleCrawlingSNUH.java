package jiwjus.AllHospitalSchedules.crawler;

import jiwjus.AllHospitalSchedules.entity.Doctor;
import jiwjus.AllHospitalSchedules.entity.Hospital;
import jiwjus.AllHospitalSchedules.entity.HospitalDepartment;
import jiwjus.AllHospitalSchedules.entity.Schedule;
import jiwjus.AllHospitalSchedules.repository.DoctorRepository;
import jiwjus.AllHospitalSchedules.repository.HospitalDepartmentRepository;
import jiwjus.AllHospitalSchedules.repository.HospitalRepository;
import jiwjus.AllHospitalSchedules.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


//서울대학교병원
@Component
@RequiredArgsConstructor
public class ScheduleCrawlingSNUH {

    private final HospitalRepository hospitalRepository;
    private final HospitalDepartmentRepository hospitalDepartmentRepository;
    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;

    public void doit() {

        WebDriver driver = null;
        try {
            // drvier 설정
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");

            // Chrome 드라이버 인스턴스 설정
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");       //크롬창 안 띄우기
            //options.addArguments("start-maximized");
            driver = new ChromeDriver(options);

            // 스크립트를 사용하기 위한 캐스팅
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 서울대학교병원 인터넷 진료예약 URL로 접속
            driver.get("https://www.snuh.org/reservation/reservation.do");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);    // 대기

            // 로그인
            WebElement element = driver.findElement(By.xpath("//*[@id=\"id\"]"));   // 아이디 입력칸
            element.sendKeys("bella434107");
            element = driver.findElement(By.xpath("//*[@id=\"pass\"]"));            // 비밀번호 입력칸
            element.sendKeys("doby9980@@");
            element = driver.findElement(By.xpath("//*[@id=\"loginBtn\"]"));        // 로그인 버튼
            element.sendKeys(Keys.ENTER);
            Thread.sleep(1000);                                                // 대기 1초==1000

            Hospital hospital = hospitalRepository.findByName("혜화서울대병원");
            System.out.println(hospital);

            WebElement btnNext = driver.findElement(By.className("btnNext"));
            List<WebElement> pubElements = driver.findElements(By.xpath("//div[contains(@class, \"feSlItem\")]"));
            for (WebElement pubElement : pubElements) {                                                                     // 과 목록 묶음(한 화면에 보여지는 과 12개가 한 묶음)
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                List<WebElement> departmentElements = pubElement.findElements(By.xpath(".//span"));
                for (WebElement departmentElement : departmentElements) {                                                   // 과
                    System.out.println("-------------------------------------------------------------------------------");

                    String hospitalDepartmentName = departmentElement.getText();

                    HospitalDepartment hospitalDepartment = hospitalDepartmentRepository.findByHospitalAndName(hospital, hospitalDepartmentName);
                    if(hospitalDepartment == null)
                        continue;
                    System.out.println(hospitalDepartment);

                    js.executeScript("arguments[0].click();", departmentElement);
                    Thread.sleep(2000);

                    // 해당 진료과 의사 목록
                    List<WebElement> doctorElements = driver.findElements(By.xpath("//*[@id=\"doctorList\"]//li"));

                    System.out.println("의사수: " + doctorElements.size());

                    int i = 1;
                    // 의사별 진료일정
                    for(WebElement doctorElement : doctorElements){

                        String doctorName = doctorElement.findElement(By.tagName("img")).getAttribute("alt"); //*[@id="doctorList"]/li[2]/div/div[2]/em[1]
                        Thread.sleep(1000);

                        Doctor doctor = doctorRepository.findByHospitalDepartmentAndName(hospitalDepartment, doctorName);
                        if(doctor == null)
                            continue;
                        System.out.println(doctor);

                        //해당 의사의 예약 가능한 진료날짜 조회
                        try{
                            WebElement doctorButtonElement = doctorElement.findElement(By.tagName("button"));                // 의사 선택버튼
                            doctorButtonElement.sendKeys(Keys.ENTER);
                            Thread.sleep(1000);
                        } catch (Throwable e) {     // 선택버튼이 없는 경우(전화 예약만 가능)
                            continue;
                        }

                        String yy = "";
                        String mm = "";
                        try {
                            yy = driver.findElement(By.className("ui-datepicker-year")).getText();   //년
                            mm = driver.findElement(By.className("ui-datepicker-month")).getText();  //월
                            Thread.sleep(1000);
                        } catch(Throwable e){       // "예약 가능한 일정이 조회되고 있지 않습니다." 얼럿 창 뜨는 경우
                            continue;
                        }

                        List<WebElement> scheduleElements = new ArrayList<WebElement>();

                        //scheduleElements.addAll(driver.findElements(By.xpath("//a[ contains(@class, \"scheduleAll\") ]")));
                        scheduleElements.addAll(driver.findElements(By.xpath("//a[ contains(@class, \"schedulAM\") or contains(@class, \"schedulPM\") or contains(@class, \"scheduleAll\") ]")));
                        Thread.sleep(1000);

                        int scheduleElementsSize = scheduleElements.size();

                        for(int j =0; j<scheduleElementsSize; j++){

                            List<WebElement> scheduleTempElements = new ArrayList<WebElement>();
                            //scheduleTempElements.addAll(driver.findElements(By.xpath("//a[ contains(@class, \"scheduleAll\") ]")));
                            scheduleTempElements.addAll(driver.findElements(By.xpath("//a[ contains(@class, \"schedulAM\") or contains(@class, \"schedulPM\") or contains(@class, \"scheduleAll\") ]")));

                            WebElement element2 = scheduleTempElements.get(j);

                            String dd = element2.getText();
                            if(dd.length() == 1)
                                dd = "0" + dd;
                            Thread.sleep(1000);

                            // 해당날짜의 예약 가능한 진료시간 조회
                            element2.click();
                            Thread.sleep(1000);

                            // 시간들
                            List<WebElement> elements3 = driver.findElement(By.xpath("//*[@class=\"time current\"]")).findElements(By.tagName("span"));
                            for (WebElement element3 : elements3) {
                                String hhmm = element3.getText().substring(0, 2) + element3.getText().substring(3, 5);

                                Schedule schedule = new Schedule(doctor, yy + mm + dd + hhmm);
                                scheduleRepository.save(schedule);

                                System.out.println(schedule);

                                Thread.sleep(1000);
                            }
                        }
                    }

                }
                js.executeScript("arguments[0].click();", btnNext);
            }
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
