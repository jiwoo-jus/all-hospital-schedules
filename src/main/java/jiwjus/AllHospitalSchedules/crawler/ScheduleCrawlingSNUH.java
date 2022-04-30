package jiwjus.AllHospitalSchedules.crawler;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

//서울대학교병원
public class ScheduleCrawlingSNUH {
    public static void main(String... args) {
        WebDriver driver = null;
        try {
            // drvier 설정
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\parkj\\Downloads\\chromedriver_win32\\chromedriver.exe");
            // Chrome 드라이버 인스턴스 설정
            driver = new ChromeDriver();
            // 스크립트를 사용하기 위한 캐스팅
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // 서울대학교병원 인터넷 진료예약 URL로 접속
            driver.get("https://www.snuh.org/reservation/reservation.do");
            // 대기 설정
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            // 아이디 입력
            WebElement element = driver.findElement(By.xpath("//*[@id=\"id\"]"));
            element.sendKeys("서울대병원아이디");
            // 비밀번호 입력
            element = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
            element.sendKeys("서울대병원비밀번호");
            //로그인 버튼 클릭
            element = driver.findElement(By.xpath("//*[@id=\"loginBtn\"]"));
            element.sendKeys(Keys.ENTER);
            // 대기
            Thread.sleep(3000); //1초==1000

            // 진료과 가나다순 조회 라디오버튼
            element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div[1]/div/fieldset/div/label[3]"));
            // 클릭. 사실 element.click()로도 클릭이 가능한데 가끔 호환성 에러가 발생하는 경우가 있다.
            js.executeScript("arguments[0].click();", element);
            // 대기
            Thread.sleep(3000); //1초==1000

            // 이비인후과
            element = driver.findElement(By.xpath("//*[@id=\"feSlItem-pub3\"]/label[8]/span"));
            // 클릭
            js.executeScript("arguments[0].click();", element);
            Thread.sleep(3000);

            // 해당 진료과 의사 목록
            List<WebElement> elements1 = driver.findElements(By.xpath("//*[@id=\"doctorList\"]//li"));
            Thread.sleep(3000);

            int i = 1;
            // 의사별 진료일정
            for(WebElement element1 : elements1){
                System.out.println("\n doctor "+ i++);

                //의사이름
                String doctorName = element1.findElement(By.tagName("img")).getAttribute("alt"); //*[@id="doctorList"]/li[2]/div/div[2]/em[1]
                System.out.println("의사이름: " + doctorName);
                Thread.sleep(3000);

                try{
                //선택버튼(해당 의사의 예약 가능한 진료날짜 조회)
                WebElement element1btn = element1.findElement(By.tagName("button"));
                element1btn.sendKeys(Keys.ENTER);
                Thread.sleep(3000); //1초==1000
                } catch (Throwable e) {     // 선택버튼이 없는 경우(전화 예약만 가능)
                    continue;
//                    e.printStackTrace();
                }

                System.out.println(
                        driver.findElement(By.className("ui-datepicker-year")).getText() + "년 " +
                        driver.findElement(By.className("ui-datepicker-month")).getText() + "월"
                );
                Thread.sleep(3000);

                System.out.println("--오후진료");
                List<WebElement> elements2pm = driver.findElement(By.className("calendarWrap")).findElements(By.className("schedulPM"));
                Thread.sleep(3000);
                System.out.println("사이즈: " + elements2pm.size());

                for(WebElement element2 : elements2pm){
                    System.out.println(element2.getText() + "일");
                    Thread.sleep(3000);
                    // 해당날짜의 예약 가능한 진료시간 조회
                    element2.click();
                    Thread.sleep(3000);

                    // 시간들
                    List<WebElement> elements3 = driver.findElement(By.xpath("//*[@class=\"time current\"]")).findElements(By.tagName("span"));
                    for(WebElement element3 : elements3){
                        System.out.println(element3.getText());
                        Thread.sleep(3000);
                    }

                }

//                System.out.println("오전진료");
//                List<WebElement> elements2am = driver.findElement(By.className("calendarWrap")).findElements(By.className("schedulAM"));
//                System.out.println("종일진료");
//                //List<WebElement> elements2all = driver.findElement(By.className("calendarWrap")).findElements(By.className("schedulAll"));
            }
        } catch (Throwable e){
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
