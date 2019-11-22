import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/UploadImgServlet")
public class UploadImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveUpload(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


    private void saveUpload(HttpServletRequest request,HttpServletResponse response) {

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String realPath = getServletContext().getRealPath("/");
            factory.setRepository(new File(realPath));

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(1024 * 1024 * 50);
            upload.setFileSizeMax(1024 * 1024 * 10); //10M一下
            upload.setHeaderEncoding("utf-8");

            if (upload.isMultipartContent(request)) {
                List<FileItem> list = upload.parseRequest(request);
                for (FileItem item : list) {
                    //文本
                    if (item.isFormField()) {
                        System.out.println(item.getFieldName());
                    }
                    //文件
                    else {
                        InputStream input = item.getInputStream();
                        String fileName = item.getName();
                        String name = item.getFieldName();
                        String contentType = item.getContentType();
                        System.out.println(input);
                        System.out.println(fileName);
                        System.out.println(name);
                        System.out.println(contentType);
                        String dir = getServletContext().getRealPath("/images");
                        File directory = new File(dir);
                        if (!directory.exists()) {
                            directory.mkdir();
                        }
                        if (fileName.contains("/")) {
                            fileName = fileName.substring(fileName.indexOf("/") + 1);
                            System.out.println(fileName);
                        }

                        String path = UUID.randomUUID().toString() + "_" + fileName;
                        System.out.println(path);
                        File savePath = new File(dir, path);
                        item.write(savePath);
                        item.delete();
                        input.close();
                        response.getWriter().append("/images/"+path);
                    }

                }
            } else {
                System.out.println("表单不含文件");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
