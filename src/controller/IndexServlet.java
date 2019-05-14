package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.ArtistDao;
import Model.Artist;

@WebServlet("")
public class IndexServlet extends HttpServlet {

    private ArtistDao dao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  resp.addHeader("Content-Type", "text/plain");

        List<Artist> allArtists = dao.getAllArtists();

       req.setAttribute("artists",  allArtists);
        
       req.getRequestDispatcher("/WEB-INF/views/index.jsp").include(req,  resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String artistName = req.getParameter("Name");
        artistName = artistName.substring(0, 1).toUpperCase()+artistName.substring(1);
        dao.addNewArtist(artistName);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}