package de.dengpeng.projects;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.amazonaws.services.dynamodbv2.document.Item;

// TODO: Auto-generated Javadoc
/**
 * The Class Helper.
 * This class helps you parse CSV file.
 * 
 */
public class Helper {

	/**
	 * Parses the it.
	 *
	 * @param nextLine the next line
	 * @return the item
	 * @throws ParseException the parse exception
	 */
	public Item parseIt(String[] nextLine) throws ParseException {
		Item newItem = new Item();
		String Id = nextLine[0];

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// Specify your time zone
		df.setTimeZone(TimeZone.getTimeZone("GMT+8:00")); 

		Date parsedDate = df.parse(nextLine[1]);
		
		// Convert ms to seconds
		Long dateTime = parsedDate.getTime() / 1000; 

		newItem.withPrimaryKey("Id", Id, "Date", dateTime);
		
		// Parse your CSV file
//		newItem.withFloat("00:15", Float.valueOf(nextLine[2]));
//		newItem.withFloat("00:30", Float.valueOf(nextLine[3]));
//		newItem.withFloat("00:45", Float.valueOf(nextLine[4]));
//		newItem.withFloat("01:00", Float.valueOf(nextLine[5]));
//		
//		newItem.withFloat("01:15", Float.valueOf(nextLine[6]));
//		newItem.withFloat("01:30", Float.valueOf(nextLine[7]));
//		newItem.withFloat("01:45", Float.valueOf(nextLine[8]));
//		newItem.withFloat("02:00", Float.valueOf(nextLine[9]));
//		
//		newItem.withFloat("02:15", Float.valueOf(nextLine[10]));
//		newItem.withFloat("02:30", Float.valueOf(nextLine[11]));
//		newItem.withFloat("02:45", Float.valueOf(nextLine[12]));
//		newItem.withFloat("03:00", Float.valueOf(nextLine[13]));
//		
//		newItem.withFloat("03:15", Float.valueOf(nextLine[14]));
//		newItem.withFloat("03:30", Float.valueOf(nextLine[15]));
//		newItem.withFloat("03:45", Float.valueOf(nextLine[16]));
//		newItem.withFloat("04:00", Float.valueOf(nextLine[17]));
//		
//		newItem.withFloat("04:15", Float.valueOf(nextLine[18]));
//		newItem.withFloat("04:30", Float.valueOf(nextLine[19]));
//		newItem.withFloat("04:45", Float.valueOf(nextLine[20]));
//		newItem.withFloat("05:00", Float.valueOf(nextLine[21]));
//		
//		newItem.withFloat("05:15", Float.valueOf(nextLine[22]));
//		newItem.withFloat("05:30", Float.valueOf(nextLine[23]));
//		newItem.withFloat("05:45", Float.valueOf(nextLine[24]));
//		newItem.withFloat("06:00", Float.valueOf(nextLine[25]));
//		
//		newItem.withFloat("06:15", Float.valueOf(nextLine[26]));
//		newItem.withFloat("06:30", Float.valueOf(nextLine[27]));
//		newItem.withFloat("06:45", Float.valueOf(nextLine[28]));
//		newItem.withFloat("07:00", Float.valueOf(nextLine[29]));
//		
//		newItem.withFloat("07:15", Float.valueOf(nextLine[30]));
//		newItem.withFloat("07:30", Float.valueOf(nextLine[31]));
//		newItem.withFloat("07:45", Float.valueOf(nextLine[32]));
//		newItem.withFloat("08:00", Float.valueOf(nextLine[33]));
//		
//		newItem.withFloat("08:15", Float.valueOf(nextLine[34]));
//		newItem.withFloat("08:30", Float.valueOf(nextLine[35]));
//		newItem.withFloat("08:45", Float.valueOf(nextLine[36]));
//		newItem.withFloat("09:00", Float.valueOf(nextLine[37]));
//		
//		newItem.withFloat("09:15", Float.valueOf(nextLine[38]));
//		newItem.withFloat("09:30", Float.valueOf(nextLine[39]));
//		newItem.withFloat("09:45", Float.valueOf(nextLine[40]));
//		newItem.withFloat("10:00", Float.valueOf(nextLine[41]));
//		
//		newItem.withFloat("10:15", Float.valueOf(nextLine[42]));
//		newItem.withFloat("10:30", Float.valueOf(nextLine[43]));
//		newItem.withFloat("10:45", Float.valueOf(nextLine[44]));
//		newItem.withFloat("11:00", Float.valueOf(nextLine[45]));
//		
//		newItem.withFloat("11:15", Float.valueOf(nextLine[46]));
//		newItem.withFloat("11:30", Float.valueOf(nextLine[47]));
//		newItem.withFloat("11:45", Float.valueOf(nextLine[48]));
//		newItem.withFloat("12:00", Float.valueOf(nextLine[49]));
//		
//		newItem.withFloat("12:15", Float.valueOf(nextLine[50]));
//		newItem.withFloat("12:30", Float.valueOf(nextLine[51]));
//		newItem.withFloat("12:45", Float.valueOf(nextLine[52]));
//		newItem.withFloat("13:00", Float.valueOf(nextLine[53]));
//		
//		newItem.withFloat("13:15", Float.valueOf(nextLine[54]));
//		newItem.withFloat("13:30", Float.valueOf(nextLine[55]));
//		newItem.withFloat("13:45", Float.valueOf(nextLine[56]));
//		newItem.withFloat("14:00", Float.valueOf(nextLine[57]));
//		
//		newItem.withFloat("14:15", Float.valueOf(nextLine[58]));
//		newItem.withFloat("14:30", Float.valueOf(nextLine[59]));
//		newItem.withFloat("14:45", Float.valueOf(nextLine[60]));
//		newItem.withFloat("15:00", Float.valueOf(nextLine[61]));
//		
//		newItem.withFloat("15:15", Float.valueOf(nextLine[62]));
//		newItem.withFloat("15:30", Float.valueOf(nextLine[63]));
//		newItem.withFloat("15:45", Float.valueOf(nextLine[64]));
//		newItem.withFloat("16:00", Float.valueOf(nextLine[65]));
//		
//		newItem.withFloat("16:15", Float.valueOf(nextLine[66]));
//		newItem.withFloat("16:30", Float.valueOf(nextLine[67]));
//		newItem.withFloat("16:45", Float.valueOf(nextLine[68]));
//		newItem.withFloat("17:00", Float.valueOf(nextLine[69]));
//		
//		newItem.withFloat("17:15", Float.valueOf(nextLine[70]));
//		newItem.withFloat("17:30", Float.valueOf(nextLine[71]));
//		newItem.withFloat("17:45", Float.valueOf(nextLine[72]));
//		newItem.withFloat("18:00", Float.valueOf(nextLine[73]));
//		
//		newItem.withFloat("18:15", Float.valueOf(nextLine[74]));
//		newItem.withFloat("18:30", Float.valueOf(nextLine[75]));
//		newItem.withFloat("18:45", Float.valueOf(nextLine[76]));
//		newItem.withFloat("19:00", Float.valueOf(nextLine[77]));
//		
//		newItem.withFloat("19:15", Float.valueOf(nextLine[78]));
//		newItem.withFloat("19:30", Float.valueOf(nextLine[79]));
//		newItem.withFloat("19:45", Float.valueOf(nextLine[80]));
//		newItem.withFloat("20:00", Float.valueOf(nextLine[81]));
//		
//		newItem.withFloat("20:15", Float.valueOf(nextLine[82]));
//		newItem.withFloat("20:30", Float.valueOf(nextLine[83]));
//		newItem.withFloat("20:45", Float.valueOf(nextLine[84]));
//		newItem.withFloat("21:00", Float.valueOf(nextLine[85]));
//		
//		newItem.withFloat("21:15", Float.valueOf(nextLine[86]));
//		newItem.withFloat("21:30", Float.valueOf(nextLine[87]));
//		newItem.withFloat("21:45", Float.valueOf(nextLine[88]));
//		newItem.withFloat("22:00", Float.valueOf(nextLine[89]));
//		
//		newItem.withFloat("22:15", Float.valueOf(nextLine[90]));
//		newItem.withFloat("22:30", Float.valueOf(nextLine[91]));
//		newItem.withFloat("22:45", Float.valueOf(nextLine[92]));
//		newItem.withFloat("23:00", Float.valueOf(nextLine[93]));
//		
//		newItem.withFloat("23:15", Float.valueOf(nextLine[94]));
//		newItem.withFloat("23:30", Float.valueOf(nextLine[95]));
//		newItem.withFloat("23:45", Float.valueOf(nextLine[96]));
//		newItem.withFloat("24:00", Float.valueOf(nextLine[97]));

		return newItem;
	}
}
