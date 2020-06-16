package net.imwork.zhanlong.qq.server.view;

import net.imwork.zhanlong.qq.server.model.MyQqServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyServerFrame extends JFrame implements ActionListener
{
    JPanel jPanel;
    JButton jb1,jb2;

    public MyServerFrame()
    {
        jPanel = new JPanel();
        jb1 = new JButton("启动服务器");
        jb1.addActionListener(this);
        jb2 = new JButton("关闭服务器");
        jb2.addActionListener(e -> System.out.println("关闭服务器"));

        jPanel.add(jb1);
        jPanel.add(jb2);

        this.add(jPanel);

        this.setSize(500, 400);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.setResizable(false);
    }

    public static void main(String[] args)
    {
        new MyServerFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("启动服务器开始....");
        if (e.getSource() == jb1)
        {
            new MyQqServer();
        }
        System.out.println("启动服务器结束....");

    }
}
