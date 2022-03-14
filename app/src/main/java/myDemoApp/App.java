/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package myDemoApp;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.port;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }
    public static void main(String[] args) {
      Logger logger = LogManager.getLogger(App.class);

      int port = 4567; //Integer.parseInt(System.getenv("PORT"));
      port(port);
      logger.error("Current port number:" + port);


      port(getHerokuAssignedPort());

      get("/", (req, res) -> "Hello, World");

      post("/compute", (req, res) -> {
        //System.out.println(req.queryParams("input1"));
        //System.out.println(req.queryParams("input2"));

        String input1 = req.queryParams("input1");
        java.util.Scanner sc1 = new java.util.Scanner(input1);
        sc1.useDelimiter("[;\r\n]+");
        java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
        while (sc1.hasNext())
        {
          int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
          inputList.add(value);
        }
        sc1.close();
        System.out.println(inputList);


        String input2 = req.queryParams("input2");

        java.util.Scanner sc2 = new java.util.Scanner(input2);
        sc2.useDelimiter("[;\r\n]+");
        java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
        while (sc2.hasNext())
        {
          int value2 = Integer.parseInt(sc2.next().replaceAll("\\s",""));
          inputList2.add(value2);
        }
        sc2.close();

        int one = inputList2.get(0);
        int two = inputList2.get(1);

        //boolean result = App.search(inputList, input2AsInt);
        int result = App.sub(inputList, one,two);

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("result", result);
        return new ModelAndView(map, "compute.mustache");
      }, new MustacheTemplateEngine());


      get("/compute",
          (rq, rs) -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "compute.mustache");
          },
          new MustacheTemplateEngine());
  }

  static int getHerokuAssignedPort() {
      ProcessBuilder processBuilder = new ProcessBuilder();
      if (processBuilder.environment().get("PORT") != null) {
          return Integer.parseInt(processBuilder.environment().get("PORT"));
      }
      return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
  }

/*
    public static boolean search(ArrayList<Integer> array, int e) {
        System.out.println("inside search");
        if (array == null) return false;
  
        for (int elt : array) {
          if (elt == e) return true;
        }
        return false;
    }
*/

    public static int sub(ArrayList<Integer> array, int one, int two) {
      System.out.println("inside search");
      if (array == null) 
        System.exit(0);

      for (int i = 0; i< array.size(); i++) {
        if (array.get(i) == one) 
          one =  array.get(i);
        if (array.get(i) == two) 
          two =  array.get(i);
      }
      return Math.abs(one-two);
  }


    //Buraya method yaz !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!






}
