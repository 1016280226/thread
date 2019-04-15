package lang.thread.notifymessage_03;

/**
 * Created by Calvin on 2019/4/15
 * 标题: 多线程通讯
 */
public class Session_01 {

    public String sex;
    public String name;
    // 线程通讯标识
    public boolean flag = false;

    static class InputThread extends Thread {

        private Session_01 session_01;

        public InputThread(Session_01 session_01) {
            this.session_01 = session_01;
        }


        @Override
        public void run() {
            int count = 0;
            while (true) {
                synchronized (session_01) {
                    if (session_01.flag) {
                        try {
                            // Wait 必须暂定当前正在执行的线程,并释放资源锁,让其他线程可以有机会运行
                            session_01.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (count == 0) {
                        session_01.sex = "男";
                        session_01.name = "Calvin";
                    } else {
                        session_01.name = "罗建广";
                        session_01.sex = "男";
                    }
                    count = (count + 1) % 2;
                    session_01.flag = true;
                    // notify/notifyall: 唤醒锁池中的线程,使之运行
                    session_01.notify();
                }
            }
        }
    }

    static class OutputThread extends Thread {
        private Session_01 session_01;

        public OutputThread(Session_01 session_01) {
            this.session_01 = session_01;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (session_01) {
                    if(!session_01.flag){
                        try {
                            session_01.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(session_01.name + " -- " + session_01.sex);
                    session_01.flag = true;
                    // 唤醒当前线程
                    session_01.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Session_01 session_01 = new Session_01();
        InputThread inputThread = new InputThread(session_01);
        OutputThread outputThread = new OutputThread(session_01);
        inputThread.start();
        outputThread.start();
    }
}

/** 结果1：造成数据混乱
 * 罗建广 -- 男
 * 罗建广 -- 男
 * Calvin -- 男
 * 罗建广 -- 男
 * 罗建广 -- 男
 * 罗建广 -- 男
 * Calvin -- 男
 * 罗建广 -- 男
 */

/*** 结果2：加上synchroized,解决造成数据混乱
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * Calvin -- 男
 * 罗建广 -- 男
 */
