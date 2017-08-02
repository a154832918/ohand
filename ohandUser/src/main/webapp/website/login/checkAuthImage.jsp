<%@ page import="java.io.*"%> 
  <%  
       String  rand  =""+session.getAttribute("rand"); 
		  response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter outter = null;
        try {
            outter = response.getWriter();
            outter.write("{ \"value\": \""+rand+"\" }");
            outter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outter != null) {
                outter.close();
            }
        }

     %>  
