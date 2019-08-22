package services.helper;

public class WaitHelper {

    public void waitATime(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitATime(int sec){
        try {
            Thread.sleep(sec *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
