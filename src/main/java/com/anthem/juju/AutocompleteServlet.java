package com.anthem.juju;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AutocompleteServlet extends HttpServlet {


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json; charset=utf-8");
        String search = (String)req.getAttribute("juju-user-search");
        System.out.println("Search String" + search);
        PrintWriter out = res.getWriter();
        String json = outsideUsers();
        out.write(json);
        System.out.println("JSON: " + json.toString());       
        out.close();
    }

    public String outsideUsers(){

      String csvFile = "/Users/mmaheu/Development/users.csv";
      BufferedReader br = null;
      String line = "";
      String results = "";
      String cvsSplitBy = ",";
      String[] users = null;
      StringBuilder sb = new StringBuilder();
      try {

        br = new BufferedReader(new FileReader(csvFile));
        sb.append("[");
        while ((line = br.readLine()) != null) {

                // use comma as separator
          users = line.split(cvsSplitBy);
          sb.append("{\"label\":\n");
          sb.append('"'+ users[0] +'"' + "\n" );
          sb.append(",\"value\":\n");
          sb.append('"'+ users[1] +'"' + "},");
        }

        results  = sb.toString().substring(0, sb.toString().length()-1) + "]";

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (br != null) {
          try {
            br.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      return results;
    }

}
