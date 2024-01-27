import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.userService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu POST
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập
        if (userService.authenticateUser(username, password)) {
            // Đăng nhập thành công
            String jsonResponse = "{\"success\": true, \"username\": \"" + username + "\"}";
            response.getWriter().write(jsonResponse);
        } else {
            // Đăng nhập thất bại
            String jsonResponse = "{\"success\": false}";
            response.getWriter().write(jsonResponse);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu GET (nếu cần)
    }
}
