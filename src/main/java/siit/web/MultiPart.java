package siit.web;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Controller;
import siit.model.PeopleAdapter;
import siit.sevices.PeopleAdapter.PeopleAdapterService;
import siit.sevices.PeopleService;
import siit.sevices.SettlementService;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;


@Controller
@MultipartConfig
@WebServlet("/addLocalnici")
public class MultiPart extends HttpServlet {
@Autowired
    HttpSession session;
@Autowired
SettlementService settlementService;
@Autowired
    PeopleAdapterService peopleAdapterService;
@Autowired
PeopleService peopleService;
    private final String URL = "jdbc:h2:file:./db/store" ;
    private final String user = "sa";
    private final String password = "";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PeopleService peopleService = new PeopleService();
        req.setAttribute("states",peopleService.getState());
        req.setAttribute("settlements",settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
        req.getRequestDispatcher("/WEB-INF/HomePage2/AddPeople.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("name").equals("") && !req.getParameter("date").equals("")) {
            PeopleAdapter peopleAdapter = new PeopleAdapter();
            peopleAdapter.setName(req.getParameter("name"));
            peopleAdapter.setState(req.getParameter("state"));
            peopleAdapter.setObservatii(req.getParameter("observatii"));
            peopleAdapter.setDate(req.getParameter("date"));
            peopleAdapterService.setSettlementService(req.getParameter("settlement"), peopleAdapter);
            peopleAdapter.setMap(req.getParameter("map"));
            peopleAdapter.setMijlocDeAcces(req.getParameter("mijloc de acces"));
            peopleAdapter.setTipHandicap(req.getParameter("tip handicap"));
            Connection conn = null;
            try {
                Part part = req.getPart("picture");
                InputStream stream = part.getInputStream();
                String insert = "INSERT INTO LOCALNICI(name,state,observatii,dateofbirth,picture)VALUES(?,?,?,?,?)";
                Properties props = new Properties();
                props.setProperty("user",user);
                props.setProperty("password", password);
                conn  = DriverManager.getConnection(URL,props);
                PreparedStatement pstmt = conn.prepareStatement(insert);
                pstmt.setString(1,req.getParameter("name"));
                pstmt.setString(2,req.getParameter("state"));
                pstmt.setString(3,req.getParameter("observatii"));
                pstmt.setDate(4, Date.valueOf(req.getParameter("date")));
                pstmt.setBlob(5,stream);
                pstmt.execute();
            }catch(IOException e){
                System.out.println(e.getCause());
            }catch(SQLException e){
                System.out.println(e.getStackTrace());
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


            boolean isIzolated = Boolean.valueOf(req.getParameter("isIzolat"));
            boolean isHandicap = Boolean.valueOf(req.getParameter("isHandicap"));
            peopleAdapterService.PeopleAdapterToPeople(peopleAdapter, isIzolated, isHandicap);
            resp.sendRedirect("/homePage2");

        }else{
            String error = "Campuri obligatorii";
           req.setAttribute("error",error);
           req.setAttribute("states",peopleService.getState());
           req.setAttribute("settlements", settlementService.displaySettlements(session.getAttribute("selected_commune").toString()));
           req.getRequestDispatcher("/WEB-INF/HomePage2/AddPeople.jsp").forward(req,resp);
        }
    }

}
