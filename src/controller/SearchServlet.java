package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.AlbumDao;
import database.dao.ArtistDao;
import Model.Album;
import Model.Artist;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	
	private AlbumDao albumDao = new AlbumDao();
	private ArtistDao artistDao = new ArtistDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String keyword = req.getParameter("keyword");
		System.out.println(keyword);
		
		List<Album> albums = albumDao.findAlbumsByTitle(keyword);
		List<Artist> artists = artistDao.findArtists(keyword);
		
		req.setAttribute("albums",  albums);
		req.setAttribute("artists",  artists);
		req.setAttribute("keyword",  keyword);
		
		req.getRequestDispatcher("/WEB-INF/views/search.jsp").include(req,  resp);
	}
}
