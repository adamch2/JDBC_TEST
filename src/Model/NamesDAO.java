package Model;

import java.sql.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.*;
import java.io.*;

/**
 * Created by adam on 16/02/17.
 */
public class NamesDAO {
    private final static String DBURL = "jdbc:mysql://localhost:3306"
           // + "?verifyServerCertificate=false" //  bypassing the certificate validation
           // + "&useSSL=true"
           // + "&requireSSL=true"
           ;
    private final static String DBUSER = "root";
    private final static String DBPASS = "zaq12wsx";
    private final static String DBDRIVER = "com.mysql.jdbc.Driver";
    //obiekt tworzacy polaczenie z baza danych
    private Connection connection;
    //obiekt pozwalajacy tworzyc nowe wyrazenia sql
    private Statement statement;


    public Statement getStatement() {
        return statement;
    }

    public void setConnection(){
        try {
            Class.forName(DBDRIVER).newInstance();
            connection = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            statement = connection.createStatement();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void closeConnection(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void save(String param1,String param2,String param3){
        String query = "INSERT INTO test.test_names VALUES('0','"+param1+"','"+param2+"','"+param3+"');";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String database, int idTable){
        String query1 = "DELETE FROM test."+database+" WHERE id"+database+"="+(idTable+1)+";";
        String query2 = "SET @count:=0";
        String query3 = "UPDATE test."+database+" SET id"+database+" = @count:= @count + 1;";
        String query4 = "ALTER TABLE test."+database+" AUTO_INCREMENT=1;";

        try {
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectName(String select_name , String select_age){
        String query = "INSERT INTO test.select_names VALUES('0','"+select_name+"','"+select_age+"');";

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void exportCsv(){
        try {
            ResultSet rs = statement.executeQuery("SELECT name,surname,age FROM test.test_names;");
            HSSFWorkbook hw = new HSSFWorkbook();
            HSSFSheet sheet = hw.createSheet("dupa");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("Name");
            rowhead.createCell((short) 1).setCellValue("Surname");
            rowhead.createCell((short) 2).setCellValue("Age");
            int i = 1;
            while (rs.next()){
                HSSFRow row = sheet.createRow((short) i);
                row.createCell((short) 0).setCellValue(rs.getString("Name"));
                row.createCell((short) 1).setCellValue(rs.getString("Surname"));
                row.createCell((short) 2).setCellValue(rs.getString("Age"));
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream("/Users/adam/Desktop/dupa.xls");
            hw.write(fileOut);
            fileOut.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
