package controller;

import model.Products;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "ProductServlet", urlPatterns = "/p")
public class ProductServlet extends javax.servlet.http.HttpServlet {
    ProductService productService = new ProductServiceImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            default:
                break;
        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Products products = this.productService.findByID(id);
            RequestDispatcher dispatcher;
            if (products == null) {
                dispatcher = request.getRequestDispatcher("error.jsp");
            } else {
                this.productService.delete(id);
                response.sendRedirect("/p");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        float price = Float.valueOf(request.getParameter("price"));
        try {
            Products pd = this.productService.findByID(id);
            RequestDispatcher dispatcher;
            if (pd == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                pd.setProduct_code(code);
                pd.setProduct_name(name);
                pd.setProduct_price(price);
                this.productService.update(id, pd);
                request.setAttribute("product", pd);
                request.setAttribute("mess", "This product was updated!");
                dispatcher = request.getRequestDispatcher("update.jsp");

                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));

        Products product = new Products(code, name, price);
        try {
            this.productService.save(product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
            request.setAttribute("mess", "New product was created!");

            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "update":
                showUpdateFrom(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Products products = this.productService.findByID(id);
            RequestDispatcher dispatcher;
            if (products == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                request.setAttribute("delete", products);
                dispatcher = request.getRequestDispatcher("/delete.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateFrom(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Products products = this.productService.findByID(id);
            RequestDispatcher dispatcher;
            if (products == null) {
                dispatcher = request.getRequestDispatcher("error-404.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("update.jsp");
                request.setAttribute("product", products);
                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Products> products = productService.findAll();
            request.setAttribute("products", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
