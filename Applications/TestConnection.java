package Applications;

import java.sql.Connection;

import db.DB;

public class TestConnection {

	public static void main(String[] args) {
       Connection conn = DB.getConnection(); 
		
		
		
		DB.closeConnection();

	}

}
