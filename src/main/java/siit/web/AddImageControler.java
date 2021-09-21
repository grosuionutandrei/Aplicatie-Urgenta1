//package siit.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import siit.sevices.PeopleAdapter.PeopleAdapterService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.*;
//@Controller
//@MultipartConfig
//public class AddImageControler extends HttpServlet {
//    @Autowired
//    PeopleAdapterService peopleAdapterService;
//    @PostMapping("/addLocalnici")
//       protected void processRequest(
//            @RequestParam("name") String name,
//            @RequestParam("state") String state,
//            @RequestParam("observatii") String observatii,
//            @RequestParam("date") String date,
//            @RequestParam("settlement") String settlement,
//            @RequestParam("isIzolat") boolean izolat,
//            @RequestParam("map") String map,
//            @RequestParam("mijloc de acces") String mijlocDeAcces,
//            @RequestParam("isHandicap") boolean handicap,
//            @RequestParam("tip handicap") String tipHandicap,
//            @RequestParam("picture") MultipartFile picture
//            , HttpServletResponse response ){
//           response.setContentType("text/html;charset=UTF-8");
//           try(PrintWriter out = response.getWriter()){
//               Part part =  ;
//               String fileName = part.getSubmittedFileName();
//               String realPath = getServletContext().getRealPath("/"+"imagedata"+ File.separator+fileName);
//               peopleAdapterService.name(fileName);
//               InputStream is = part.getInputStream();
//      boolean succes=uploadFile(is,realPath);
//      if(succes){
//          out.println("File Uploaded to this directory" + realPath);
//      }else{
//          out.println("error");
//      }
//           } catch (IOException | ServletException e) {
//               e.printStackTrace();
//           }
//       }
//
//       public boolean uploadFile(InputStream inputStream, String path){
//           boolean test= false;
//           try {
//               byte[] byt = new byte[inputStream.available()];
//               inputStream.read();
//               FileOutputStream ops =  new FileOutputStream(path);
//               ops.flush();
//               ops.close();
//               test=true;
//           }catch(Exception e){
//               e.printStackTrace();
//           }
//           return  test;
//       }
//}
