package com.example.tkf;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "uploadServlet", value = "/upload-servlet")
public class UploadServlet extends HttpServlet {
    // 保存文件的目录
    private static String PATH_FOLDER = "/";
    // 存放临时文件的目录
    private static String TEMP_FOLDER = "/";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // 结果json对象
        HashMap<String, Integer> result = new HashMap<>();
        String saveName;
        //上传的文件名
        String filename;
        try {
            request.setCharacterEncoding("utf-8"); // 设置编码;
            // 获得磁盘文件条目工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
            // 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
            factory.setRepository(new File(TEMP_FOLDER));
            // 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
            factory.setSizeThreshold(1024 * 1024);
            // 高水平的API文件上传处理
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 提交上来的信息都在这个list里面
            List<FileItem> list = upload.parseRequest(request);
            // 获取上传的文件
            for (FileItem file : list) {
                // 获取文件名
                filename = getUploadFileName(file);
                System.out.println("获取的文件名============================"
                        + filename);
                String suffix = filename
                        .substring(filename.lastIndexOf(".") + 1);
                System.out
                        .println("获取的文件后缀名=========================" + suffix);
                String[] regular = { "JPG", "GIF","PNG","JPEG", "BMP"};
                boolean flag = false;
                // 判断文件格式是否合法
                for (String str : regular) {
                    if (suffix.equalsIgnoreCase(str)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    throw new Exception("文件格式不合法！");
                }
                // 保存后的文件名
                saveName = new Date().getTime()
                        + filename.substring(filename.lastIndexOf("."));
                // 保存后图片的浏览器访问路径
                System.out.println("存放目录:=================================="
                        + PATH_FOLDER);
                System.out.println("文件名:===================================="
                        + filename);
                // 真正写到磁盘上
                file.write(new File(PATH_FOLDER, saveName)); // 第三方提供的
            }
            result.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("errorCode", 0);
        } finally {
            String json = new Gson().toJson(result);
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        }
    }

    private String getUploadFileName(FileItem item) {
        // 获取路径名
        String value = item.getName();
        // 索引到最后一个反斜杠
        int start = value.lastIndexOf("/");
        // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
        return value.substring(start + 1);
    }

    public void init(ServletConfig config) throws ServletException {
        ServletContext servletCtx = config.getServletContext();
        // 初始化路径
        // 缩略图保存路径
        PATH_FOLDER = "/";
        System.out.println("保存缩略图的目录===========" + PATH_FOLDER);
        // 存放临时文件的目录,存放xxx.tmp文件的目录
        TEMP_FOLDER = servletCtx.getRealPath("/temp");
        System.out.println("存放临时文件的目录===========" + TEMP_FOLDER);
        File dir = new File(PATH_FOLDER);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        File dir2 = new File(TEMP_FOLDER);
        if (!dir2.isDirectory()) {
            dir2.mkdir();
        }
    }
}
