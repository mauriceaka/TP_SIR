package form;

import Jpa.EntityManagerHelper;
import dao.FicheDao;
import metier.Fiche;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "userform", urlPatterns = {"/userForm"})
public class userForm extends HttpServlet {

    EntityManager manager = EntityManagerHelper.getEntityManager();
    FicheDao ficheDao = new FicheDao(manager);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        PrintWriter p = new PrintWriter(resp.getOutputStream());

        List<Fiche> fiches = ficheDao.ficheList();

        p.println("<html>\n" +
                "<body>\n" +
                "<FORM Method=\"POST\" Action=\"/UserInfo\">\n" +
                "    Name : \t\t<INPUT type=\"text\" size=\"20\" name=\"name\"><BR>\n" +
                "    Profession : \t<INPUT type=\"text\" size=\"20\" name=profession><BR>\n" +
                "    Email :\t\t<INPUT type=\"text\" size=\"20\" name=email><BR>\n" +
                "Fiche : <select id=\"fiches\" name=\"fiches\">");
        fiches.forEach(fiche ->
                p.println("<option value=" + fiche.getId() + ">" + fiche.getLibelle() + "</option>"));

        p.println("</select>\n" +
                "    <INPUT type=submit value=Send>\n" +
                "</FORM>\n" +
                "<FORM Method=\"GET\" Action=\"/UserInfo\">\n" +
                "    <INPUT type=submit value=\"Show users\">\n" +
                "</FORM>\n" +
                "</body>\n" +
                "</html>");

        p.flush();
    }
}
