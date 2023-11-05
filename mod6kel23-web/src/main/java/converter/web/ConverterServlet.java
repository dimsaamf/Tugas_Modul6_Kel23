package converter.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import converter.ejb.ConverterBean;

@WebServlet(name = "ConverterServlet", urlPatterns = {"/ConverterServlet"})
public class ConverterServlet extends HttpServlet {

    private ConverterBean cb = new ConverterBean();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Temperature Converter</title>");
        out.println("<style>");
out.println("body { font-family: Arial, sans-serif; background-color: #e39ff6; text-align: center; display: flex; flex-direction: column; align-items: center; height: 100vh; justify-content: center;}");
out.println(".container { background-color: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); width: 300px; }");
out.println("h1 { color: #333; }");
        out.println("input[type='text'] { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='submit'] { width: 49%; padding: 10px; background-color: #7a4988; color: #fff; border: none; border-radius: 5px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #2980b9; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Temperature Converter</h1>");
        out.println("<div class='container'>");
        try {
            String degree = request.getParameter("degree");
            if ((degree != null) && (degree.length() > 0)) {
                double d = Double.parseDouble(degree);
                if (request.getParameter("C TO R") != null) {
                    String centigrade = cb.ctof(d);
                    out.println("<p>" + degree + " Celsius are " + centigrade + " Reamur.</p>");
                }
                if (request.getParameter("R TO C") != null) {
                    String reamur = cb.ftoc(d);
                    out.println("<p>" + degree + " Reamur are " + reamur + " Celsius.</p>");
                }
            } else {
                out.println("<p>Enter degree to convert:</p>");
                out.println("<form method='get'>");
                out.println("<p> <input type='text' name='degree' placeholder='Enter temperature' /></p>");
                out.println("<br/>");
                out.println("<input type='submit' name='R TO C' value='Convert R TO C'>");
                out.println("<input type='submit' name='C TO R' value='Convert C TO R'>");
                out.println("</form>");
            }
        } finally {
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Temperature Converter Servlet";
    }
}
