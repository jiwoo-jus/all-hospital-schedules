package jiwjus.AllHospitalSchedules.crawler;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


//서울대학교병원
@Component
public class ScheduleCrawlingSNUH {
    public static void main(String... args) {
        WebDriver driver = null;
        try {
            // drvier 설정
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\parkj\\Downloads\\chromedriver_win32\\chromedriver.exe");

            // Chrome 드라이버 인스턴스 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("start-maximized");
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

            // 진료과 가나다순 조회
            element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div[1]/div/fieldset/div/label[3]"));  //진료과 가나다순 조회 라디오버튼
            js.executeScript("arguments[0].click();", element);                // 클릭. element.click()로도 클릭이 가능한데 가끔 호환성 에러가 발생하는 경우가 있다.
            Thread.sleep(1000);

            // 이비인후과 조회
            element = driver.findElement(By.xpath("//*[@id=\"feSlItem-pub3\"]/label[8]/span"));     //이비인후과
            js.executeScript("arguments[0].click();", element);
            Thread.sleep(1000);

            // 해당 진료과 의사 목록
            List<WebElement> doctorElements = driver.findElements(By.xpath("//*[@id=\"doctorList\"]//li"));
            Thread.sleep(1000);

            int i = 1;
            // 의사별 진료일정
            for(WebElement doctorElement : doctorElements){
                System.out.println("\n doctor "+ i++);

                String doctorName = doctorElement.findElement(By.tagName("img")).getAttribute("alt"); //*[@id="doctorList"]/li[2]/div/div[2]/em[1]
                System.out.println("의사이름: " + doctorName);
                Thread.sleep(1000);

                //해당 의사의 예약 가능한 진료날짜 조회
                try{
                    WebElement doctorButtonElement = doctorElement.findElement(By.tagName("button"));                // 의사 선택버튼
                    doctorButtonElement.sendKeys(Keys.ENTER);
                    Thread.sleep(1000);
                } catch (Throwable e) {     // 선택버튼이 없는 경우(전화 예약만 가능)
                    continue;
                }

                try {
                    System.out.println(
                            driver.findElement(By.className("ui-datepicker-year")).getText() + "년 " +
                            driver.findElement(By.className("ui-datepicker-month")).getText() + "월"
                    );
                    Thread.sleep(1000);
                } catch(Throwable e){       // "예약 가능한 일정이 조회되고 있지 않습니다." 얼럿 창 뜨는 경우
                    continue;
                }

                List<WebElement> scheduleElements = new ArrayList<WebElement>();
                scheduleElements.addAll(driver.findElements(By.className("schedulAM")));
                scheduleElements.addAll(driver.findElements(By.className("schedulPM")));
//                scheduleElements.addAll(driver.findElements(By.className("schedulAll")));
                Thread.sleep(1000);

                int scheduleElementsSize = scheduleElements.size();
                System.out.println("사이즈: " + scheduleElementsSize);

                for(int j =0; j<scheduleElementsSize; j++){
                    System.out.println(j);

                    List<WebElement> scheduleTempElements = new ArrayList<WebElement>();
                    scheduleTempElements.addAll(driver.findElements(By.className("schedulAM")));
                    scheduleTempElements.addAll(driver.findElements(By.className("schedulPM")));

                    WebElement element2 = scheduleTempElements.get(j);
                    Thread.sleep(1000);

                    System.out.println(element2.getText() + "일");

                    // 해당날짜의 예약 가능한 진료시간 조회
                    element2.click();
                    Thread.sleep(1000);

                    // 시간들
                    List<WebElement> elements3 = driver.findElement(By.xpath("//*[@class=\"time current\"]")).findElements(By.tagName("span"));
                    for (WebElement element3 : elements3) {
                        System.out.println(element3.getText());
                        Thread.sleep(1000);
                    }
                }
            }
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
