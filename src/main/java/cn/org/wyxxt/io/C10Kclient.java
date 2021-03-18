package cn.org.wyxxt.io;

/**
 * @author xingzhiwei
 * @createBy IntelliJ IDEA
 * @time 2021/3/17 5:24 下午
 * @email jsjxzw@163.com
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class C10Kclient {

    public static void main(String[] args) {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        InetSocketAddress serverAddr = new InetSocketAddress("172.16.213.134", 9090);

        //端口号的问题：65535
        //  windows
        for (int i = 10000; i < 10001; i++) {
            try {
                SocketChannel client1 = SocketChannel.open();

                SocketChannel client2 = SocketChannel.open();

                /*
                linux中你看到的连接就是：
                client...port: 10508
                client...port: 10508
                 */

                client1.bind(new InetSocketAddress("10.20.134.70", i));
                //  192.168.150.1：10000   192.168.150.11：9090
                client1.connect(serverAddr);
                clients.add(client1);

                client2.bind(new InetSocketAddress("172.16.213.1", i));
                //  192.168.110.100：10000  192.168.150.11：9090
                client2.connect(serverAddr);
                clients.add(client2);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        System.out.println("clients "+ clients.size());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
