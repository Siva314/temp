package util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import help.HelperException;

import java.util.Objects;
import java.util.regex.Matcher;

public class HelperUtils {
	public static String getDateTime(long milliSecond) {
		LocalDateTime date=Instant.ofEpochMilli(milliSecond).atZone(ZoneId.systemDefault()).toLocalDateTime();
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return date.format(format);
	}
	public static long minusDates(int days) {
		Instant dates=Instant.ofEpochMilli(System.currentTimeMillis());
		Instant afterdate=dates.minus(Period.ofDays(days));
		return afterdate.toEpochMilli();
	}
	//	public static String getReferenceId(Long millisecond) {
	//		Random r=new Random();
	//		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//		char letter=alphabet.charAt(r.nextInt(alphabet.length()));
	//		String referenceId=String.valueOf(letter).concat(String.valueOf(millisecond%1666670000000l));
	//		return referenceId;
	//	}

	public static void isValidEmail(String email) throws HelperException{
		Pattern patternForEmail=Pattern.compile("^[\\p{Alnum}+\\p{Punct}]+@[\\p{Lower}]+\\.[\\p{Lower}]{2,6}$");
		Matcher matching=patternForEmail.matcher(email);
		if(!matching.find()) {
			throw new HelperException(" Please Enter Valid EmailId");
		}
	}

	public static void isValidMobile(Long mobile) throws HelperException{
		Pattern patternForMobile=Pattern.compile("[6-9][\\d]{9}");
		Matcher matching=patternForMobile.matcher(String.valueOf(mobile));
		if(!matching.find()) {
			throw new HelperException(" Please Enter Valid Mobile number");
		}
	}

	public static void nullCheck(Object inputObject) throws HelperException{
		if(Objects.isNull(inputObject)) {
			throw new HelperException("Input should not be null");
		}
	}

	public static void isAvailableAmount(double currentBalance,double amount)throws HelperException{
		if(amount>currentBalance) {
			throw new HelperException("Insufficient Balance");
		}
		if(amount>10000) {
			throw new HelperException("Limit is 10000");
		}
	}

	public static void isValidAmount(double amount) throws HelperException {
		if(amount<0) {
			throw new HelperException("Enter the positive value");
		}
	}

	public static long convertIntoMillisecond(String date) {
		String myDate=date.concat(" 12:00:00");
		LocalDateTime localDateTime = LocalDateTime.parse(myDate,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") );
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
}
