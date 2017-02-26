package Controller;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import Model.NamesDAO;
/**
 * Created by adam on 20/02/17.
 */
public class ResultController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String button = request.getParameter("button");
        NamesDAO nd = new NamesDAO();

        switch (button){
            case "1":
                int row = Integer.parseInt(request.getParameter("row"));
                nd.setConnection();
                nd.delete(row);
                nd.closeConnection();
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);
                break;


            case "2":
                PrintWriter printWriter = response.getWriter();
                printWriter.println("Wyeksportowano do pliku CSV");
                nd.setConnection();
                nd.exportCsv();
                nd.closeConnection();
                break;
        }





    }

}
