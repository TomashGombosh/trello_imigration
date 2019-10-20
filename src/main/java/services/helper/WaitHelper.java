package services.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class WaitHelper {
    private Logger log;
    public WaitHelper(){
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
        log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
    }
    public void waitATime(){
        try {
            log.info("Wait one second");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitATime(int sec){
        try {
            log.info("Wait " + sec + " second");
            Thread.sleep(sec *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
