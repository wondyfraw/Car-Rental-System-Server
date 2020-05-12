package edu.cs.cs472.carerent.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cs.cs472.carerent.model.Car;
import edu.cs.cs472.carerent.model.CarCopy;
import edu.cs.cs472.carerent.service.CarCopyService;
import edu.cs.cs472.carerent.util.JSONUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CarCopyController" , description = "Car Copy Controller Class",
            urlPatterns = {"/carCopy/new", "/carCopy/list"})
public class CarCopyController extends HttpServlet {

    private CarCopyService carCopyService;

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
            CarCopy newCarCopy = new CarCopy();
            newCarCopy.setCarCopyId(newCarCopy.hashCode());
            newCarCopy = gson.fromJson(strNewCarDataInJSON, CarCopy.class);
            this.carCopyService.registerNewCarCopy(newCarCopy);
            System.out.println("Added CarCopy : "+ newCarCopy);
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
        String carJSONData = carCopyService.getAllRegisteredCarCopyJSON();
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
