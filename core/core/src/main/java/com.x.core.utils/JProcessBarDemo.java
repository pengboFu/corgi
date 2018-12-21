package com.x.core.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by fpb on 2018/12/20.
 */
public class JProcessBarDemo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JProgressBar processBar;

    public JProcessBarDemo() {

        setUndecorated(true); // 去掉窗口的装饰
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);// 采用指定的窗口装饰风格
        setResizable(false);
        setTitle("系统升级"); // 设置窗体标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体退出的操作
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 得到屏幕的尺寸
        setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 100, 400, 100);// 设置窗体的位置和大小
        JPanel contentPane = new JPanel(); // 创建内容面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置内容面板边框
        setContentPane(contentPane);// 应用(使用)内容面板
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));// 设置为流式布局
        processBar = new JProgressBar();// 创建进度条
        processBar.setStringPainted(true);// 设置进度条上的字符串显示，false则不能显示
        processBar.setBackground(Color.darkGray);
        // processBar.setForeground(Color.RED);
        // 创建线程显示进度
        new Thread() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100); // 让当前线程休眠0.1ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    processBar.setValue(i); // 设置进度条数值
                }
                processBar.setString("升级完成，正在重启...");// 设置提示信息
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dispose();
            }
        }.start(); // 启动进度条线程
        contentPane.add(processBar);// 向面板添加进度控件
    }

    public static void main(String[] args) {
        //进度条创建
        JProcessBarDemo JPBD = new JProcessBarDemo();
        JPBD.setVisible(true);
    }

}
