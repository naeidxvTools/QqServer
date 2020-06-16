package net.imwork.zhanlong.qq.server.model;


import net.imwork.zhanlong.qq.common.Message;
import net.imwork.zhanlong.qq.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 展龙
 */

public class MyQqServer
{
    public MyQqServer()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务器在9999端口处监听.....");

            while (true)
            {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                User user = (User) ois.readObject();
                System.out.println(user.getUserId() + ", " + user.getPassword());

                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                if ("123".equals(user.getPassword()))
                {
                    message.setMesType("1");
                    oos.writeObject(message);

                    // 这里单独开一个线程，让该线程与该客户端保持通信
                    ServerConClientThread serverConClientThread = new ServerConClientThread(socket);
                    ManageClientThread.addClientThread(user.getUserId(), serverConClientThread);
                    serverConClientThread.start();
                } else
                {
                    message.setMesType("2");
                    oos.writeObject(message);
                    socket.close();
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
