package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Album;
import database.ChinookDatabase;
import Model.Track;

public class TrackDao {
    private ChinookDatabase db = new ChinookDatabase();

    public Track findTrack(long id) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM Track WHERE AlbumId = ?");
            statement.setLong(1, id);
            results = statement.executeQuery();

            if (results.next()) {
                String name = results.getString("Name");
                Track track = new Track(id, name);
                return track;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, connection);
        }
    }
//Etsit‰‰n albumia ja sen biisej‰
    public List<Track> findTrackByAlbum(Album album) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;
        ArrayList<Track> list = new ArrayList<>();

        try {
            statement = connection.prepareStatement("SELECT *, Genre.Name AS Genrename, Artist.Name AS Artistname FROM Track LEFT JOIN Genre ON Genre.GenreId = Track.GenreId LEFT JOIN Album ON Album.AlbumId = Track.AlbumId LEFT JOIN Artist ON Artist.ArtistId = Album.ArtistId WHERE Track.AlbumId = ?");
            statement.setLong(1, album.getId());
            results = statement.executeQuery();

            while (results.next()) {
                long id = results.getLong("TrackId");
                String name = results.getString("Name");
                long mediatypeid = results.getLong("MediatypeId");
                long genreid = results.getLong("GenreId");
                String composer = results.getString("Composer");
                long milliseconds = results.getLong("Milliseconds");
                long bytes = results.getLong("Bytes");
                double unitprice = results.getDouble("Unitprice");
                String genrename = results.getString("Genrename");
                String artistname = results.getString("Artistname");

                Track track = new Track(id, name, mediatypeid, genreid, composer, milliseconds, bytes, unitprice, genrename, artistname);

                list.add(track);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, connection);
        }
        return list;
    }

    public List<Track> getTracksByAlbum(Album album) {
        Connection conn = db.connect();
        PreparedStatement statement = null;
        ResultSet results = null;

        List<Track> tracks = new ArrayList<>();

        try {
            statement = conn.prepareStatement("SELECT * FROM Album WHERE AlbumId = ?");
            statement.setLong(1, album.getId());

            results = statement.executeQuery();

            while (results.next()) {
                long id = results.getLong("AlbumId");
                String title = results.getString("Title");

                tracks.add(new Track(id, title));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            db.close(results, statement, conn);
        }

        return tracks;
    }
//lis‰t‰‰n levylle biisi

    public void addTrack(Long iq, String trackName) {
        ChinookDatabase db = new ChinookDatabase();
        Connection connection = db.connect();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("INSERT INTO Track (Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) VALUES (?, ?, 1,1,'JAMI',0,0,0)");
            statement.setString(1, trackName);
            statement.setLong(2, iq);
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