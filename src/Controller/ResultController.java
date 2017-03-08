package Controller;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import Model.NamesDAO;
/**
 * Created by adam on 20/02/17.
 */
public class ResultController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String button = request.getParameter("button");
        String selectDatabase = request.getParameter("table");
        String database="";
        NamesDAO nd = new NamesDAO();

        if (selectDatabase.equals("1")){
            database = "test_names";
        }else if(selectDatabase.equals("2")){
            database = "select_names";
        }

        switch (button){
            case "1":
                int row = Integer.parseInt(request.getParameter("row"));
                nd.setConnection();
                nd.delete(database,row);
                nd.closeConnection();
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);
                break;

            case "2":
                int selectRow = Integer.parseInt(request.getParameter("row"));
                nd.setConnection();
                try {
                    ResultSet rs = nd.getStatement().executeQuery("SELECT name,age FROM test.test_names WHERE idtest_names="+(selectRow+1)+";");
                    rs.next();
                    String select_name = rs.getString("Name");
                    String select_age = rs.getString("Age");
                    nd.selectName(select_name,select_age);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                nd.closeConnection();
                RequestDispatcher rd2 = request.getRequestDispatcher("result.jsp");
                rd2.forward(request, response);
                break;


            case "3":
                PrintWriter printWriter = response.getWriter();
                printWriter.println("Wyeksportowano do pliku CSV");
                nd.setConnection();
                nd.exportCsv();
                nd.closeConnection();
                break;
        }





    }

}
