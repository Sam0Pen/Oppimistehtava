package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ChinookDatabase;
import Model.Artist;

public class ArtistDao {
    private ChinookDatabase db = new ChinookDatabase();

    public Artist getArtist(long id) {
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM Artist WHERE ArtistId = ?");
            statement.setLong(1, id);
            results = statement.executeQuery();

            if (results.next()) {
                String name = results.getString("Name");
                long artistId = results.getLong("ArtistId");

                return new Artist(artistId, name);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, conn);
        }
    }

    public List<Artist> getAllArtists() {
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;
        List<Artist> artists = new ArrayList<>();

        try {
            // 1. Create the SQL statement
            statement = conn.prepareStatement("SELECT * FROM Artist ORDER BY Name");

            // 2. Execute the statement and get results to local variable
            results = statement.executeQuery();

            while (results.next()) {
                // 3. Transform data from DB into Java objects
                long id = results.getLong("ArtistId");
                String name = results.getString("Name");

                Artist a = new Artist(id, name);

                // 4. Add each artist to list one-by-one
                artists.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. Close all resources
            db.close(results, statement, conn);
        }

        // 6. Return all artists as a list
        return artists;
    }
    public List<Artist> findArtists(String keyword){
        ChinookDatabase db = new ChinookDatabase();
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        List<Artist> artists = new ArrayList<>();
        keyword = keyword.toLowerCase();

        try {
            statement = conn.prepareStatement("SELECT * FROM Artist WHERE lower(Name) LIKE ?");
            statement.setString(1, "%" + keyword + "%");
            results = statement.executeQuery();

            while (results.next()) {
                String name = results.getString("Name");
                long id = results.getLong("ArtistId");
                artists.add(new Artist(id, name));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, conn);
        }
        return artists;
    }
    public Artist getArtistByAlbumId(Long id) {
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            statement = conn.prepareStatement("SELECT a.name, a.artistId \n" +
                    "FROM Artist a \n" +
                    "JOIN album b ON a.ArtistId = b.ArtistId\n" +
                    "WHERE albumId = ?");
            statement.setLong(1, id);
            results = statement.executeQuery();

            if (results.next()) {
                String name = results.getString("name");
                long artistId = results.getLong("artistId");

                return new Artist(artistId, name);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, conn);
        }
    }
    public void addNewArtist(String Name) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;



        try {
            statement = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?)");
            statement.setString(1, Name);
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