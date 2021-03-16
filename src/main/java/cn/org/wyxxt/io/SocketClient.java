package cn.org.wyxxt.io;

import java.io.*;
import java.net.Socket;

/**
 * @author xingzhiwei
 * @createBy IntelliJ IDEA
 * @time 2021/3/16 3:59 下午
 * @email jsjxzw@163.com
 * 客户端
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("127.0.0.1",9090);

            client.setSendBufferSize(20);
            client.setTcpNoDelay(true);
            OutputStream out = client.getOutputStream();

            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true){
                String line = reader.readLine();
                if(line != null ){
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        out.write(b);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
