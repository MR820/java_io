package cn.org.wyxxt.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * @author xingzhiwei
 * @createBy IntelliJ IDEA
 * @time 2021/3/17 10:17 上午
 * @email jsjxzw@163.com
 */

public class SocketIO {


    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090,20);

        System.out.println("step1: new ServerSocket(9090) ");

        while (true) {
            final Socket client = server.accept();  //阻塞1
            System.out.println("step2:client\t" + client.getPort());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream in = null;
                    try {
                        in = client.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        while (true) {
                            String dataline = reader.readLine(); //阻塞2

                            if (null != dataline) {
                                System.out.println(dataline);
                            } else {
                                client.close();
                                break;
                            }
                        }
                        System.out.println("客户端断开");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }).start();

        }
    }


}
