package com.servlet;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "uploadServlet", value = "/upload-servlet")
public class UploadServlet extends HttpServlet {

    private static final String PATH_FOLDER =
            "D:\\Java\\Idea_project\\DataBase_2022\\food\\src\\main\\webapp\\img\\restaurant";

    private static String TEMP_FOLDER = "\\";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        this.doPost(request, response);
    }
    
    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HashMap<String, Integer> result = new HashMap<>();
        try {
            request.setCharacterEncoding("utf-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File(TEMP_FOLDER));
            factory.setSizeThreshold(5 * 1024 * 1024);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(request);

            for (FileItem file : list) {
                String filename = getUploadFileName(file);
                String suffix = filename
                        .substring(filename.lastIndexOf(".") + 1);
                String name = filename
                        .substring(0, filename.lastIndexOf("."));
                File dir = new File(PATH_FOLDER);
                if (dir.listFiles() != null) {
                    for (File imgFile : Objects.requireNonNull(dir.listFiles())) {
                        if (imgFile.getName().substring(
                                0, imgFile.getName().lastIndexOf(".")).equals(name)) {
                            boolean flag = imgFile.delete();
                            if (!flag) {
                                System.out.println("error");
                            }
                        }
                    }
                }
                String[] regular = {"JPG", "PNG", "JPEG", "BMP"};
                boolean flag = false;
                for (String str : regular) {
                    if (suffix.equalsIgnoreCase(str)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    throw new Exception("文件格式不合法！");
                }
                file.write(new File(PATH_FOLDER, name + ".jpg"));
            }
            result.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", -1);
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
        String value = item.getName();
        int start = value.lastIndexOf("/");
        return value.substring(start + 1);
    }

    public void init(ServletConfig config) {
        ServletContext servletCtx = config.getServletContext();
        TEMP_FOLDER = servletCtx.getRealPath("/temp");
        new File(PATH_FOLDER);
    }
}
