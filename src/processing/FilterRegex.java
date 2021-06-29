//package processing;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class FilterRegex {
//	
//
//	public boolean startsWithUpperCase(String s) {
//		boolean checked = false;
//		Pattern upperCase = Pattern.compile("^\\s*\\p{Upper}");
//		Matcher m = upperCase.matcher(s);
//		if (m.matches()) {
//			if (endsWithQuestionMark(s)) {
//				checked = true;
//			}
//		}
//
//		return checked;
//	}
//
//	public boolean endsWithQuestionMark(String s) {
//		boolean checked = false;
//		Pattern upperCase = Pattern.compile(".*\\?$");
//		Matcher m = upperCase.matcher(s);
//		if (m.matches()) {
//			checked = true;
//		}
//
//		return checked;
//	}
//
//	public List<String> splittedString(String s) {
//		List<String> toReturn = new ArrayList<String>();
//		String[] split = s.split("[.!]");
//		for (String string : split) {
//			toReturn.add(string);
//		}
//		return toReturn;
//	}
//	
//	public String [] splittedStringByWS(String s) {
//		String[] split = s.split(" ");
//		for(String string : split) {
//			string.toLowerCase();
//		}
//		return split;
//	}
//
//
//}
