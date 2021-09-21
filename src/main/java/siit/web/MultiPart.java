package siit.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import siit.model.PeopleAdapter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Controller
//@MultipartConfig
//@WebServlet("/addLocalnici")
//public class MultiPart extends HttpServlet {
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//        Part part = req.getPart("picture");
//        String type = part.getContentType();
//        byte[] byt = type.getBytes(StandardCharsets.UTF_8);
//
//    }
//}
