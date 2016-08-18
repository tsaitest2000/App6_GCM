/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.gcm.student.myapplication.backend;

import org.json.JSONException;

import java.io.IOException;

import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("text/plain");
      resp.getWriter().println("Please use the form to POST to this url");
   }

   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String name = req.getParameter("name");
      resp.setContentType("text/plain");
      if (name == null) {
         resp.getWriter().println("Please enter a name");
      }
      resp.getWriter().println("Hello " + name);

      //
      try {
         // 先執行App (c31_01_GCM)建置到手機，再執行Gcm_Backend，開啟預設http://localhost:8080/，輸入Ok，手機收到Ok
         GcmSender.send("GcmSender", name);
      } catch (JSONException e) {
         e.printStackTrace();
      }
   }
}
