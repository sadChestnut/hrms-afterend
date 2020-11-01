package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Random;
public class ValiPhoto {
    //图片的宽度
    private int width = 150;
    //图片的高度
    private int height = 50;
    //每张图片验证码字符的个数
    private int charnum = 4;
    //验证码干扰线
    private int linenum = 100;
    //验证码
    private String valicode = "";
    //创建一个BufferedImage对象，可访问图像数据缓冲区，利用缓冲区
    //操作图片
    private BufferedImage img = null;
    //验证码字符取值范围，去除‘0（数字0）’和‘O（字母）’

    private char[]charrange = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
        'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public ValiPhoto(int width,int height){
        this.width = width;
        this.height = height;
        this.makeValiPho();
    }

    public ValiPhoto(int width,int height,int charnum,int linenum){
        this.width = width;
        this.height = height;
        this.charnum = charnum;
        this.linenum = linenum;
        this.makeValiPho();
    }

    public void makeValiPho(){
        int x = 0,fontHeight = 0,charBline = 0;
        int red = 0,green = 0,blue = 0;
        x = width / (charnum + 2); //最终生成的验证码左右两边各空一个字符
        fontHeight = height - 2; //字体的高度
        charBline = height - 4;
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //创建一个Graphics2D对象，可以将它绘制到BufferedImage中
        Graphics2D g = img.createGraphics();
        //生成随机数
        Random ran = new Random();
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        //设置图片上字体的样式，字体类型，字体风格（普通），字体大小
        Font font = new Font("宋体",Font.PLAIN,fontHeight);
        g.setFont(font);
        //设置干扰线
        for(int i = 0; i <= linenum; i++) {
        //设置每条干扰线的开始和结束的坐标
            int xs = ran.nextInt(width);
            int ys = ran.nextInt(height);
            int xe = xs + ran.nextInt(width / 8);
            int ye = ys + ran.nextInt(height / 8);
            //随机生成颜色值
            red = ran.nextInt(225);
            green = ran.nextInt(225);
            blue = ran.nextInt(225);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
      }
        //存放生成的验证码
        StringBuffer valichars = new StringBuffer();
        //生成验证码
        for(int i = 0; i < charnum; i++) {
            String valistr=String.valueOf(charrange[ran.nextInt(charrange.length)]);
            red = ran.nextInt(225);
            green = ran.nextInt(225);
            blue = ran.nextInt(225);
            g.setColor(new Color(red,green,blue));
            g.drawString(valistr, (i + 1) * x, charBline);
            valichars.append(valistr);
        }
        valicode = valichars.toString();
        }

    public void write(OutputStream sos)throws IOException{
        //将图片写入ImageIO流
        ImageIO.write(img,"png",sos);
    }
    //返回文本验证码
    public String getValicode(){
        return valicode;
    }
}
