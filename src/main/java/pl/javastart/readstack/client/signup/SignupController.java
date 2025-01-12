package pl.javastart.readstack.client.signup;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.javastart.readstack.domain.api.UserRegistration;
import pl.javastart.readstack.domain.api.UserService;

import java.io.IOException;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRegistration userRegistration = getUserData(request);
        userService.register(userRegistration);
        response.sendRedirect(request.getContextPath());
    }

    private UserRegistration getUserData(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        return new UserRegistration(username, email, password);
    }
}