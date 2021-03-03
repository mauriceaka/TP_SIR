package servlet;

import dao.UserDao;
import metier.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    @WebServlet(name="userinfo", urlPatterns={"/UserInfo"})
    public class UserInfo extends HttpServlet {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        UserDao userDao = new UserDao(manager);

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            PrintWriter p = new PrintWriter(resp.getOutputStream());

            List<User> userList = userDao.userList();
            p.println("<HTML>\n<BODY>\n" +
                    "<H1>Recapitulatif des informations</H1>\n" +
                    "<UL>\n");
            for (User user1 : userList) {
                p.println(
                        " <LI>Nom: "
                                + user1.getNom() + "\n" +
                                " <LI>Profession: "
                                + user1.getProfession()+ "\n <hr>");
            }
            p.println("</UL>\n" +
                    "</BODY></HTML>");

            p.flush();
        }

        public void doPost(HttpServletRequest request,
                           HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html");

//            create object user
            User user = new User(request.getParameter("name"), request.getParameter("firstname"));
            userDao.createUser(user);
            PrintWriter out = response.getWriter();

            out.println("<HTML>\n<BODY>\n" +
                    "<H1>Recapitulatif des informations</H1>\n" +
                    "<UL>\n" +
                    " <LI>Nom: "
                    + request.getParameter("name") + "\n" +
                    " <LI>Prenom: "
                    + request.getParameter("firstname") + "\n" +
                    " <LI>Age: "
                    + request.getParameter("age") + "\n" +
                    "</UL>\n" +
                    "</BODY></HTML>");
        }
    }


