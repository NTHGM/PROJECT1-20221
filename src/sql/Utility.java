package sql;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

public class Utility {
	public Date setDate(JDateChooser getD) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date(0, 0, 0);
		
		try {
			d.setDate(sdf.parse(sdf.format(getD.getDate())).getDate());
			d.setMonth(sdf.parse(sdf.format(getD.getDate())).getMonth());
			d.setYear(sdf.parse(sdf.format(getD.getDate())).getYear());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return d;

	}
	public String getDate(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		return date;
	}
	
	public boolean checkDate(Date ngayCanCheck,Date ngay) {
		boolean checkDate = false;
        if(ngay.getYear()<ngayCanCheck.getYear()) {
        	checkDate = true;
        }else if(ngay.getYear()==ngayCanCheck.getYear()) {
        	if(ngay.getMonth()<ngayCanCheck.getMonth()) {
        		checkDate = true;
        	}else if(ngay.getMonth()==ngayCanCheck.getMonth()){
        		if(ngay.getDate()<ngayCanCheck.getDate()) {
        			checkDate = true;
        		}else checkDate = false;
        	}else checkDate = false;
        }else checkDate = false;
        return checkDate;
	}
}
