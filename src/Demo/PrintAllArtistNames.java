package Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrintAllArtistNames {
	public static final String DATABASE_URL = "jdbc:sqlite:M:\\sqlite\\Chinook_Sqlite.sqlite";
	public static final String SELECT_NAMES = "SELECT Name FROM Artist";
	public static void main(String[] args) throws SQLException {
		// System.out.println(DATABASE_URL);
		Connection connection = DriverManager.getConnection(DATABASE_URL);
		
		PreparedStatement statement = connection.prepareStatement(SELECT_NAMES);
		
		ResultSet results = statement.executeQuery();
		
		while(results.next()){
		System.out.println(results.getString("Name"));
		}
		
		results.close();
		statement.close();
		connection.close();
	}

}
