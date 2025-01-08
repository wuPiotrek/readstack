package pl.javastart.readstack.client.category;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.javastart.readstack.domain.api.CategoryFullInfo;
import pl.javastart.readstack.domain.api.CategoryService;
import pl.javastart.readstack.domain.api.DiscoveryBasicInfo;
import pl.javastart.readstack.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();
    private final DiscoveryService discoveryService = new DiscoveryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        CategoryFullInfo category = categoryService.findById(categoryId)
                .orElseThrow();
        request.setAttribute("category", category);
        List<DiscoveryBasicInfo> discoveries = discoveryService.findAllByCategory(categoryId);
        request.setAttribute("discoveries", discoveries);
        request.getRequestDispatcher("/category.jsp").forward(request, response);
    }
}