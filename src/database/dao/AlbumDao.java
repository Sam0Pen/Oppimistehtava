package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ChinookDatabase;
import Model.Album;
import Model.Artist;

public class AlbumDao {
    private ChinookDatabase db = new ChinookDatabase();

    public List<Album> getAlbumsByArtist(Artist artist) {
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        List<Album> albums = new ArrayList<>();

        try {
            statement = conn.prepareStatement("SELECT AlbumId, Title, ArtistId FROM Album WHERE ArtistId = ?");
            statement.setLong(1, artist.getId());

            results = statement.executeQuery();

            while (results.next()) {
                long id = results.getLong("AlbumId");
                String title = results.getString("Title");

                albums.add(new Album(id, title));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, conn);
        }

        return albums;
    }

    public List<Album> findAlbumsByTitle(String keyword) {
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        List<Album> albums = new ArrayList<>();

        keyword = keyword.toLowerCase();

        try {
            statement = conn.prepareStatement("SELECT * FROM Album WHERE lower(Title) LIKE ?");
            statement.setString(1, "%" + keyword + "%");

            results = statement.executeQuery();

            while (results.next()) {
                long id = results.getLong("AlbumId");
                String title = results.getString("Title");

                albums.add(new Album(id, title));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, conn);
        }

        return albums;
    }
    public Album findAlbum(long id) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM Album WHERE AlbumId = ?");
            statement.setLong(1, id);
            results = statement.executeQuery();

            if (results.next()) {
                String title = results.getString("Title");
                Album album = new Album(id, title);
                return album;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, connection);
        }
    }
    public void addAlbum(Long id, String name) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO Album (Title, ArtistId) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {

            }
            try {
                connection.close();
            } catch (Exception e) {

            }
        }

    }
    public void deleteAlbum(Long id) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("DELETE FROM Album WHERE albumId = ?");

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {

            }
            try {
                connection.close();
            } catch (Exception e) {

            }
        }

    }
}