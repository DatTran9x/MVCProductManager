package controller;

import model.Product;
import service.ProductServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private ProductServiceImp service = new ProductServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateProductForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                showProduct(request, response);
                break;
            case "find":
                showFindProduct(request, response);
                break;
            default:
                showList(request, response);
                break;
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) {
        List<Product> list = service.showAll();
        request.setAttribute("Product", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        tryCatch(request, response, requestDispatcher);
    }

    private void showFindProduct(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/Find.jsp");
        tryCatch(request, response, requestDispatcher);
    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int index = service.findIndex(id);
        RequestDispatcher requestDispatcher;
        if (index == -1) {
            requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
        } else {
            Product product = service.findByIndex(index);
            if (product == null) {
                requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
            } else {
                request.setAttribute("Product", product);
                requestDispatcher = request.getRequestDispatcher("/view/Show.jsp");
            }
        }
        tryCatch(request, response, requestDispatcher);
    }

    private void showCreateProductForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/Create.jsp");
        tryCatch(request, response, requestDispatcher);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int index = service.findIndex(id);
        RequestDispatcher requestDispatcher;
        if (index == -1) {
            requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
        } else {
            Product product = service.findByIndex(index);
            if (product == null) {
                requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
            } else {
                request.setAttribute("Product", product);
                requestDispatcher = request.getRequestDispatcher("/view/Edit.jsp");
            }
        }
        tryCatch(request, response, requestDispatcher);
    }

    private void tryCatch(HttpServletRequest request, HttpServletResponse response, RequestDispatcher requestDispatcher) {
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int index = service.findIndex(id);
        RequestDispatcher requestDispatcher;
        if (index == -1) {
            requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
        } else {
            Product product = service.findByIndex(index);
            if (product == null) {
                requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
            } else {
                request.setAttribute("Product", product);
                request.setAttribute("message", "Delete this product?");
                requestDispatcher = request.getRequestDispatcher("/view/Delete.jsp");
            }
        }
        tryCatch(request, response, requestDispatcher);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "find":
                findProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void findProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Product product = service.findByName(name);
        RequestDispatcher requestDispatcher;
        if (product == null) {
            requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
        } else {
            request.setAttribute("Product", product);
            requestDispatcher = request.getRequestDispatcher("/view/Show.jsp");
        }
        tryCatch(request, response, requestDispatcher);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        int id = (int) (Math.random() * 1000);
        service.save(new Product(id, name, description, img));
        request.setAttribute("message", "Product is created!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/Create.jsp");
        tryCatch(request, response, requestDispatcher);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String img = request.getParameter("img");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = new Product(id, name, description, img);
        int index = service.findIndex(id);
        service.update(index, product);
        request.setAttribute("Product", product);
        request.setAttribute("message", "Product is updated!!!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view/Edit.jsp");
        tryCatch(request, response, requestDispatcher);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int index = service.findIndex(id);
        RequestDispatcher requestDispatcher;
        if (index == -1) {
            requestDispatcher = request.getRequestDispatcher("/view/error.jsp");
            tryCatch(request, response, requestDispatcher);
        } else {
            service.remove(index);
            response.sendRedirect("/products");
        }

    }
}
