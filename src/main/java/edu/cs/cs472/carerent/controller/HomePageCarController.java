package edu.cs.cs472.carerent.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cs.cs472.carerent.model.Car;
import edu.cs.cs472.carerent.service.CarService;
import edu.cs.cs472.carerent.util.JSONUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "HomePageCarController", description = "Manage car request",
             urlPatterns = {"/car/newCar","/car/list"})
public class HomePageCarController extends HttpServlet {

    private CarService carService;
    public HomePageCarController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        try(BufferedReader bufferedReader = request.getReader()){
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            String strNewCarDataInJSON = stringBuffer.toString();
            System.out.println(strNewCarDataInJSON);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, JSONUtils.getJsonLocalDateDeserializer()).create();
            Car newCar = new Car();
            //newCar.setCarId(newCar.getClass().hashCode()+1);
            newCar = gson.fromJson(strNewCarDataInJSON, newCar.getClass());
            newCar.setCarId(newCar.getClass().hashCode()+1);
            this.carService.registerNewCar(newCar);
            System.out.println("Added Car: "+ newCar);
        }
        catch (Exception exp){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(exp.toString());
        }

        setAccessControlHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carJSONData = carService.getAllRegisteredCarsJSON();
        System.out.println(carJSONData);
        setAccessControlHeaders(response);
        response.setContentType("application/json");
        response.setBufferSize(4096);
        response.getWriter().println(carJSONData);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp);
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = config.getServletContext();

        List<Car> cars = List.of(
                new Car(123456780,"Toyota", "RAVA4", 2001, 1000,
                       "Red","Transmission" ),
                new Car(123456781,"Honda", "V8 Model", 2001, 1000,
                        "White","Transmission2"),
                new Car(123456782,"Ford", "Ford-Lexus", 2001, 1000,
                        "Red","Transmission3")
        );
        // End temp
        if(servletContext.getAttribute("carList") == null) {
            List<Car> dataStore = new Vector<>();
            this.carService = new CarService(dataStore);
            servletContext.setAttribute("carList", dataStore);
        }
    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST");
    }
}