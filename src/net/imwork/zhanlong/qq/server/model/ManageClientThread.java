package net.imwork.zhanlong.qq.server.model;

import java.util.HashMap;

public class ManageClientThread extends Thread
{
    public static HashMap<String, ServerConClientThread> map = new HashMap();

    public static void addClientThread(String uid, ServerConClientThread serverConClientThread)
    {
        map.put(uid, serverConClientThread);
    }

    public static ServerConClientThread getClientThread(String uid)
    {
        return map.get(uid);
    }


}
