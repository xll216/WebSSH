package com.lanou.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class MyDownloadAction extends ActionSupport {
    /*定义一个供下载的输入流对象，作为动作方法
    的result返回参数传递给页面*/
    private InputStream inputStream;
    private String fileName;//要下载的文件名称

    /**
     * 文件下载
     **/
    public String download() {
        /*获得下载路径*/
        String dirPath = ServletActionContext.getServletContext()
                .getRealPath("files");

//        fileName = "idea.png";//初始化文件名称

        //要下载的文件对象
        File file = new File(dirPath, fileName);

        try {
            /*构建输入流对象*/
            inputStream = new FileInputStream(file);

            /*将文件名称中包含中文的部分进行浏览器兼容处理，放在inputStream赋值
            * 之后，动作方法返回之前*/
            fileName = filenameEncoding(fileName,
                    ServletActionContext.getRequest(),
                    ServletActionContext.getResponse());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }


    /**
     * 处理浏览器文件下载包含中文的问题
     *
     * @param filename 要进行中文处理的原文件名称
     * @return 返回处理完成的文件名称
     **/
    public String filenameEncoding(String filename,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器类型
        System.out.println(agent);

        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else if (agent.contains("Safari")) {
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
