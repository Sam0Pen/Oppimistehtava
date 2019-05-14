package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Album;
import Model.Artist;
import database.dao.AlbumDao;
import database.dao.TrackDao;
import database.dao.ArtistDao;
import Model.Track;

@WebServlet("/album")
public class AlbumServlet extends HttpServlet {


    private TrackDao trackDao = new TrackDao();
    private AlbumDao albumDao = new AlbumDao();
    private ArtistDao artistDao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParameter = req.getParameter("id");
        long id = Long.parseLong(idParameter); // this may fail due to null value or invalid numeric string

        // 2. Load album from the database (may be null if id does not match)
        Album album = albumDao.findAlbum(id);
        Artist a = artistDao.getArtistByAlbumId(id);

        // 3. Load all tracks from the database for the album
        List<Track> tracks = trackDao.findTrackByAlbum(album);

        req.setAttribute("album", album);
        req.setAttribute("tracks", tracks);
        req.setAttribute("artist", a);
        req.getRequestDispatcher("/WEB-INF/views/album.jsp").include(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)){
            doDelete(req, resp);
        } else if("add".equals(action)) {


            String idParameter = req.getParameter("artistId");
            String albumNameParameter = req.getParameter("albumName");
            long id = Long.parseLong(idParameter);


            albumDao.addAlbum(id, albumNameParameter);

            resp.sendRedirect(req.getContextPath() + "/artist?id=" + id);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParameter = req.getParameter("albumId");
        long id = Long.parseLong(idParameter);
        Artist a = artistDao.getArtistByAlbumId(id);

        albumDao.deleteAlbum(id);
        System.out.println("########## "+ id + " " + a.getId());
        resp.sendRedirect(req.getContextPath() + "/artist?id=" + a.getId());

    }

}
