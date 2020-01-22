import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkAuthentication", urlPatterns = "/checkAuthentication")
public class checkAuthentication extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("password");
        String remember = request.getParameter("remember");
        if (name.equals("Admin") && pass.equals("12345")) {
            request.getSession().setAttribute("Authentication", true);
            if (remember != null) {
                request.getSession().setMaxInactiveInterval(1209600);
                response.sendRedirect("ui.jsp");
            } else {
                response.sendRedirect("ui.jsp");
            }
        } else {
            response.sendRedirect("authentication.jsp");
        }
    }
}
