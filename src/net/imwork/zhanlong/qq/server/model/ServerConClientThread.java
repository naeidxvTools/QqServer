package net.imwork.zhanlong.qq.server.model;

import net.imwork.zhanlong.qq.common.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 功能：服务器和某个客户端的通信线程
 */
public class ServerConClientThread extends Thread
{
    Socket socket = new Socket();

    public ServerConClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        while (true)
        {
            // 这里该线程可以接收客户端的信息
            try
            {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) ois.readObject();

                System.out.println(message.getSender() + " 给 " + message.getGetter() + " 说：" + message.getCon());

                // 完成转发
                ServerConClientThread serverConClientThread = ManageClientThread.getClientThread(message.getGetter());
                ObjectOutputStream oos = new ObjectOutputStream(serverConClientThread.socket.getOutputStream());
                oos.writeObject(message);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}