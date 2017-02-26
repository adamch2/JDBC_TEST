package Controller;

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.*;
import Model.NamesDAO;

/**
 * Created by adam on 16/02/17.
 */
public class NamesController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String button = request.getParameter("button");

        if (button.equals("1")){
            String param1 = request.getParameter("param1");
            String param2 = request.getParameter("param2");
            String param3 = request.getParameter("param3");
            NamesDAO namesDAO = new NamesDAO();
            namesDAO.setConnection();
            namesDAO.save(param1, param2, param3);
            namesDAO.closeConnection();

            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);

        }else if (button.equals("2")) {
            RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
            rd.forward(request, response);
        }


    }
}
