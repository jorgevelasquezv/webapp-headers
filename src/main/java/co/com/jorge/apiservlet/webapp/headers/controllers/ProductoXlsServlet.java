package co.com.jorge.apiservlet.webapp.headers.controllers;

import co.com.jorge.apiservlet.webapp.headers.models.Producto;
import co.com.jorge.apiservlet.webapp.headers.services.ProductoService;
import co.com.jorge.apiservlet.webapp.headers.services.ProductoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/prodcutos.xls", "/productos.html", "/productos"})
public class ProductoXlsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImpl();

        List<Producto> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls= servletPath.endsWith(".xls");

        if (esXls){
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=productos.xls");
        }

        try(PrintWriter out = resp.getWriter()) {
            if (!esXls) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset='UTF-8'/>");
                out.println("       <title>Cabeceras HTTP Request</title> ");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Listado de Productos!</h1>");
                out.println("       <p><a href='" + req.getContextPath() + "/productos.xls'>Exportar a xls</a></p>" );
                out.println("       <p><a href='" + req.getContextPath() + "/productos.json'>Mostrar JSON</a></p>" );
            }
            out.println("       <table>");
            out.println("           <tr>");
            out.println("               <th>Id</th>");
            out.println("               <th>Nombre</th>");
            out.println("               <th>Tipo</th>");
            out.println("               <th>Precio</th>");
            out.println("           </tr>");
            productos.forEach(p ->{
                out.println("           <tr>");
                out.println("               <th>" + p.getId() + "</th>");
                out.println("               <th>" + p.getNombre() + "</th>");
                out.println("               <th>" + p.getTipo() + "</th>");
                out.println("               <th>" + p.getPrecio() + "</th>");
                out.println("           </tr>");
            });
            out.println("       </table>");
            if (!esXls){
                out.println("   </body>");
                out.println("</html>");
            }

        }
    }
}
