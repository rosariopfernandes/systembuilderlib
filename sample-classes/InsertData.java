import com.rpfsoftwares.systembuilderlib.database.JContentValues;
import com.rpfsoftwares.systembuilderlib.database.JMySQLHelper;

public class InsertData {

	public static void main(String[] args) {
		JMySQLHelper db = new JMySQLHelper("localhost", "video_store", "root", "");
		
		JContentValues values = new JContentValues();
		values.put("name", "John Doe");
		values.put("phone", "123456789");
		values.put("address", "Some cool Street nr 502");
		
		if(db.insert("clients", values))
			System.out.println("Client inserted! :)");
		else
			System.out.println("Couldn't insert client :(");
		
		db.close();
	}

}
