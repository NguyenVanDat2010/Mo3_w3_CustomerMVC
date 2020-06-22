package customer.controller;

import customer.model.Customer;
import customer.service.CustomerService;
import customer.service.ICustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CustomerServlet",urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    ICustomerService customerService = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "edit":
                editCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * Chức năng xóa một customer by id, id lấy từ form (list.jsp). Update tại form (delete.jsp)
     */
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = this.customerService.findById(id);

        RequestDispatcher dispatcher;

        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.customerService.delete(id);
            try {
                response.sendRedirect("/customers");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Chức năng sửa một customer by id, id lấy từ form (list.jsp). Update tại form (edit.jsp)
     */
    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;

        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            customer.setName(name);
            customer.setEmail(email);
            customer.setAddress(address);

            this.customerService.update(id, customer);
//            request.setAttribute("customer", customer);
            request.setAttribute("message", "Customer information was updated");

            dispatcher = request.getRequestDispatcher("customer/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thêm mới 1 customer trong form (create.jsp)
     */
    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int id = (int) (Math.random() * 10000);

        Customer customer = new Customer(id, name, email, address);
        this.customerService.save(customer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        request.setAttribute("message", "New customer was created");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//-------------------------------------------------------------------------------------------------------


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                showViewCustomer(request, response);
                break;
            default:
                listCustomer(request, response);
                break;
        }
    }

    /** Hiển thị thông tin khách hàng khi lấy đc id */
    private void showViewCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);

        RequestDispatcher dispatcher;

        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            /** Hiển thị các dữ liệu bản ghi ra form "view.jsp" */
            request.setAttribute("customer", customer);
            dispatcher = request.getRequestDispatcher("customer/view.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String value = request.getParameter("submitValue");
//        List<Customer> customerList = this.customerService.findAll();
//
//        RequestDispatcher dispatcher;
//        Customer customer = null;
//
//        if (value == null){
//            dispatcher = request.getRequestDispatcher("error-404.jsp");
//        }else {
//            for (int i=0;i<customerList.size();i++){
//                if (
//                        customerList.get(i).getId()==Integer.parseInt(value)||
//                        customerList.get(i).getName().toLowerCase().contains(value) ||
//                        customerList.get(i).getEmail().toLowerCase().contains(value)||
//                        customerList.get(i).getAddress().toLowerCase().contains(value)
//                ) {
//                    customer = this.customerService.findById(i);
//                    break;
//                }
//            }
//            request.setAttribute("customer",customer);
//            dispatcher = request.getRequestDispatcher("customer/view.jsp");
//        }
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Hiển thị form delete khi lấy ra được id (delete.jsp)
     */
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);

        RequestDispatcher dispatcher;

        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            /** Hiển thị các dữ liệu bản ghi ra form "delete.jsp" của id cần xóa */
            request.setAttribute("customer", customer);
            dispatcher = request.getRequestDispatcher("customer/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hiển thị form edit khi có lấy ra được id (edit.jsp)
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);

        RequestDispatcher dispatcher;

        if (customer == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            /** Hiển thị các dữ liệu bản ghi ra form "edit.jsp" của id cần sửa */
            request.setAttribute("customer", customer);
            dispatcher = request.getRequestDispatcher("customer/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hiển thị form tạo mới (create.jsp)
     */
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Danh sách customer (list.jsp)
     */
    private void listCustomer(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customerList = this.customerService.findAll();

        request.setAttribute("customers", customerList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
