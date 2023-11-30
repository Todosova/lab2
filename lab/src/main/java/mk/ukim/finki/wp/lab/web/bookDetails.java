package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookDetails", urlPatterns = "/bookDetails")
public class bookDetails  extends HttpServlet {
     private final SpringTemplateEngine springTemplateEngine;
     private final BookServiceImpl bookService;

     public bookDetails(SpringTemplateEngine springTemplateEngine, BookServiceImpl bookDetails)
     {
         this.springTemplateEngine = springTemplateEngine;
         this.bookService = bookDetails;
     }
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         IWebExchange webExchange = JakartaServletWebApplication
                 .buildApplication(getServletContext())
                 .buildExchange(req,resp);
         WebContext context = new WebContext(webExchange);

         String bookIsbn = req.getParameter("bookIsbn");
         String authorId = req.getParameter("authorId");

         Book book = bookService.findBookByIsbn(bookIsbn);

         bookService.addAuthorToBook(Long.parseLong(authorId), bookIsbn);
         bookService.addBookToAuthor(bookIsbn, Long.parseLong(authorId));

         //System.out.println(book);

         context.setVariable("detailsBook", book);

         springTemplateEngine.process("bookDetails.html", context, resp.getWriter());
     }

}
