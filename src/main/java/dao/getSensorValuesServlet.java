package dao;

import com.google.gson.Gson;
import model.SensorValue;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "getSensorValuesServlet", urlPatterns = "/getSensorValuesServlet")
public class getSensorValuesServlet extends HttpServlet {
    private static final String SQL = "SELECT * FROM sensor_values WHERE sensor_id = ? AND type_value = ? AND date_time > (NOW() - INTERVAL 6 HOUR)";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        PreparedStatement statement;
        String sensorID = request.getParameter("sensorID");
        String typeValue = request.getParameter("typeValue");
        ArrayList<SensorValue> list = null;
        try {
            statement = connectionDAO.getConnectionDAO().prepareStatement(SQL);
            statement.setString(1, sensorID);
            statement.setString(2, typeValue);
            ResultSet rs = statement.executeQuery();
            list = new ArrayList();
            while (rs.next()) {
                SensorValue sv = new SensorValue(rs.getTimestamp("date_time"), rs.getDouble("sensor_value"));
                list.add(sv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDAO.closeConnectionDAO();
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
//        String tmp = jsonString.replaceAll("date","x");
//        jsonString = tmp.replaceAll("value","y");
        response.setContentType("application/json");
        response.getWriter().write(jsonString);
    }
}
