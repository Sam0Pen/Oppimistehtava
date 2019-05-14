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
import Model.Track;
import database.dao.AlbumDao;
import database.dao.ArtistDao;
import database.dao.TrackDao;


@WebServlet("/track")
public class TrackServlet extends HttpServlet {
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
        req.getRequestDispatcher("/WEB-INF/views/track.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String trackName = req.getParameter("trackName");
        String idParameter = req.getParameter("albumId");
        long iq = Long.parseLong(idParameter);
        trackDao.addTrack(iq, trackName);

        resp.sendRedirect(req.getContextPath() + "/track?id=" + iq);
    }}