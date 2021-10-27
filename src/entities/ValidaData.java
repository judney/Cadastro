package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;

public class ValidaData {

	public static boolean isDateValid(String strDate) {
	    String dateFormat = "dd/MM/uuuu";

	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
	    .ofPattern(dateFormat)
	    .withResolverStyle(ResolverStyle.STRICT);
	    try {
	        LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
	        return true;
	    } catch (DateTimeParseException e) {
	       return false;
	    } 
	
	
	} 
	
	
	
	public static boolean ValidDtAtu(String data1 ) {  // Valida se a data é maior que a data atual 
		
		
		
		Calendar dataAtual = Calendar.getInstance();

		Integer yy = dataAtual .get(Calendar.YEAR);
		Integer mm = dataAtual .get(Calendar.MONTH) + 1 ;
		Integer dd = dataAtual .get(Calendar.DAY_OF_MONTH);
		
		//Integer tmp = dataAtual.get(Calendar.)
		
		
		
		String hoje=dd+"/"+mm+"/"+yy; 
		//System.out.println(hoje); 

	
	try {
        SimpleDateFormat dateFormat = new 
            SimpleDateFormat ("dd/MM/yyyy");
        Date data = dateFormat.parse(data1);
        Date dtAtu       = dateFormat.parse(hoje);

       
        //System.out.println("Data Atual  : " + 
        //                  dateFormat.format(dtAtu));
        //System.out.println("Data Informada  : " +
        //                   dateFormat.format(data));
        
        
        
        if(dtAtu.before(data)){
           return false  ;
        } 
        else 
        { 
        	return true ; 
        }
	  } catch (ParseException ex) {
		  return false ;
      }	   
	
	
	} 	
		
	
	
}
