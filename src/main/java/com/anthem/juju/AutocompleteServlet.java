package com.anthem.juju;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

//Added these imports to load the CSV file from JIRA_HOME
import java.io.File;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.util.JiraHome;


//For Logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class AutocompleteServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(AutocompleteServlet.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json; charset=utf-8");
       
        String search = req.getParameter("term");        
        System.out.println("Search String" + search);
        
        PrintWriter out = res.getWriter();
        String json = searchUsers(search);
        out.write(json);
        System.out.println("JSON: " + json.toString());       
        out.close();
    }

    public String searchUsers(String searchString){

     // String csvFile = "/Users/mmaheu/Development/users.csv";  ** Show class that we can avoid hardcoding paths
      BufferedReader br = null;
      String line = "";
      String results = "";
      String cvsSplitBy = ",";
      String[] users;
      StringBuilder sb = new StringBuilder();
      try {

        br = new BufferedReader(new FileReader(getFile()));
        sb.append("[");
        while ((line = br.readLine()) != null) {

                // use comma as separator
          //Add the code to only pull results based on search letters
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

    private File getFile(){
        File csvFile = null;

        try {
            File fromJIRAHome = ComponentAccessor.getComponent(JiraHome.class).getHome();
            csvFile = new File(fromJIRAHome, "users.csv");

        }catch (Exception e) {
            log.error("Error loading file", e);
        }
        return csvFile;
    }

}
