package controller;

import exception.InvalidCardinalDirectionException;
import exception.InvalidDegreeException;
import exception.InvalidFileException;
import exception.InvalidOperationException;
import ship.Ship;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
@WebServlet(name = "ShipController", value = "/ShipController")
@MultipartConfig
public class ShipController extends HttpServlet {

    Ship ship = new Ship();
    boolean s = false;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filepart = request.getPart("fileName");
        String filename = filepart.getSubmittedFileName().toLowerCase();


        try {
            s = filename.endsWith(".txt");
            if(filename.endsWith(".txt")){
                InputStream fileContent = filepart.getInputStream();
                ArrayList<String> instructions = new ArrayList<>();
                String line;
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( fileContent ) );
                while( (line = bufferedReader.readLine()) != null )
                {
                    instructions.add(line);
                    try {
                        ship.move(line);
                    } catch (InvalidOperationException | InvalidCardinalDirectionException | InvalidDegreeException e) {
                        e.printStackTrace();
                    }

                }
                request.setAttribute("ship",ship);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/shipView.jsp");
                dispatcher.forward(request,response);
            }
            else{
                throw new InvalidCardinalDirectionException("invalid File ");
            }

        } catch (InvalidCardinalDirectionException e) {
            e.printStackTrace();
            request.setAttribute("errorFile", e.getMessage().toString() );
            System.out.println(e.getMessage().toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/errorFile.jsp");
            dispatcher.forward(request,response);
        }

    }
}
