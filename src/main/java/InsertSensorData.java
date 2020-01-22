import dao.ConnectionDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "InsertSensorData", urlPatterns = "/InsertSensorData")
public class InsertSensorData extends HttpServlet {
    private final SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static String SQL = "INSERT INTO sensor_values(date_time,sensor_id,sensor_value,type_value) VALUES(?,?,?,?)";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int sensor = Integer.parseInt(request.getParameter("sensor"));
        double value = Double.parseDouble(request.getParameter("value"));
        String date = sd.format(new Date());
        int type_value = Integer.parseInt(request.getParameter("type"));
        String s = insertDB( date, sensor, value, type_value);
        response.sendRedirect("insertdata.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    private String insertDB(String date, int sensor,double value, int type){
        String req = "";
        ConnectionDAO cdao = new ConnectionDAO();
        try {
            PreparedStatement statement = cdao.getConnectionDAO().prepareStatement(SQL);
            statement.setString(1, date);
            statement.setInt(2, sensor);
            statement.setDouble(3, value);
            statement.setInt(4, type);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                req = "New value was inserted successfully!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req = "fail";
        }finally {
            cdao.closeConnectionDAO();
        }
        return req;
    }
}
