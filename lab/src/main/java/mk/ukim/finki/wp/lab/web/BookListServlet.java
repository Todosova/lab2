package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;

@WebServlet (urlPatterns = "/listBooks")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private  final BookServiceImpl bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine,BookServiceImpl bookService)
    {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService=bookService;
    }
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("books", bookService.listBooks());

      springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }
}
