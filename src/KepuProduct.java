import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/KepuProduct/*")
public class KepuProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "";
        try {
            result = KepuService.getKepuService().find(request.getRequestURL().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().append(result);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}