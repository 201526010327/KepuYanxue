
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.*;

@WebServlet("/UploadRtfServlet")
public class UploadRtfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
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
                        String text = item.getString("utf-8");
                        PreparedStatement preparedStatement = DbHelp.getConn().prepareStatement("insert into kepuProduct(title,text) values(?,?)");
                        preparedStatement.setString(1,"标题");

                        //注意item.getString(utf-8)，否则中文乱码（及时上面）;
                        preparedStatement.setString(2,text);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
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
