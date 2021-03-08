package servlet;

import Jpa.EntityManagerHelper;
import dao.FicheDao;
import dao.UserDao;
import metier.Fiche;
import metier.User;

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

@WebServlet(name = "userinfo", urlPatterns = {"/UserInfo"})
public class UserInfo extends HttpServlet {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
//    EntityManager manager = factory.createEntityManager();
    EntityManager manager = EntityManagerHelper.getEntityManager();
    UserDao userDao = new UserDao(manager);
    FicheDao ficheDao = new FicheDao(manager);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        PrintWriter p = new PrintWriter(resp.getOutputStream());

        List<User> userList = userDao.userList();
        p.println("<HTML><meta charset=\"utf-8\">\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n");
        for (User user1 : userList) {
            p.println(
                    " <LI>Nom: "
                            + user1.getNom() + "</li>" +
                            " <LI>Profession: "
                            + user1.getProfession() + "</li>" +
                            " <LI>Email: "
                            + user1.getEmail() + "</li>" +
                            "<li>Fiches: " + user1.getFiche().size()
                            + (user1.getFiche().size()<=1?" fiche":" fiches"));
            p.println("<ol>");
            user1.getFiche().forEach(fiche ->
                    p.println("<li>" + fiche.getLibelle() + "</li>\n"));
            p.println("</ol>");
            p.println("<hr>");
        }
        p.println("</UL>\n" +
                "</BODY></HTML>");

        p.flush();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");

//            create object user
        User user = new User(request.getParameter("name"), request.getParameter("profession"));

        user.setEmail(request.getParameter("email"));
        userDao.createUser(user);

        if (request.getParameter("fiches") != null && !request.getParameter("fiches").equals("null")) {
            Fiche fiche = ficheDao.ficheById(Long.valueOf(request.getParameter("fiches")));
            fiche.setUser(user);
            ficheDao.createFiche(fiche);
        }
        PrintWriter out = response.getWriter();

        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>Profession: "
                + request.getParameter("profession") + "\n" +
                " <LI>Email: "
                + request.getParameter("email") + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
    }
}


