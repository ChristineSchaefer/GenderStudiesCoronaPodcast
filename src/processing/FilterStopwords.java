package processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * methods to filter stopwords and remove them from text
 * 
 * @author ChristineSchaefer
 *
 */
public class FilterStopwords {

	private static Set<String> stopwords;
//	private FilterRegex fr = new FilterRegex();

	/**
	 * constructor to get needed list of stopwords
	 * 
	 * @param stopwordFile
	 * @throws IOException
	 */
	public FilterStopwords(File stopwordFile) throws IOException {
		stopwords = new HashSet<String>();
		BufferedReader in = new BufferedReader(new FileReader(stopwordFile));
		String line = in.readLine();
		while (line != null) {
			stopwords.add(line.trim());
			line = in.readLine();
		}
		in.close();
	}

//	public List<String> removeStopwords(Map<File, List<String>> questions) {
//		List<String> toReturn = new ArrayList<String>();
//		for (File file : questions.keySet()) {
//			List<String> question = questions.get(file);
//			for (String q : question) {
//				String[] token = fr.splittedStringByWS(q);
////				for (int i = 0; i < token.length; i++) {
////					System.out.println(token[i]);
////				}
//				StringBuilder sb = new StringBuilder();
//				for (String t : token) {
//					if (!stopwords.contains(t)) {
//						sb.append(t);
//						sb.append(" ");
//					}
//				}
//
//				toReturn.add(sb.toString().replaceAll("[?]", ""));
//			}
//		}
//		// System.out.println(toReturn);
//		return toReturn;
//	}

	/**
	 * remove stopwords
	 * 
	 * @param questions
	 * @return list of questions without stopwords
	 */
	public String[] removeStopwords(String[] questions) {
		List<String> questionsWithoutStopwords = new ArrayList<String>();
		for (String q : questions) {
			if (!stopwords.contains(q)) {
				questionsWithoutStopwords.add(q);
			}
		}
		String[] toReturn = new String[questionsWithoutStopwords.size()];
		questionsWithoutStopwords.toArray(toReturn);
		return toReturn;
	}
//
//	public Map<String, List<String>> processStrings(Map<String, List<String>> questions) {
//		Map<String, List<String>> toReturn = new HashMap<String, List<String>>();
//
//		for (String file : questions.keySet()) {
//			List<String> potTopics = new ArrayList<String>();
//			List<String> question = questions.get(file);
//			for (String q : question) {
//				String[] token = fr.splittedStringByWS(q);
//				// System.out.println(token.length);
//				for (int i = 0; i < token.length; i++) {
//					if (!(stopwords.contains(token[i]))) {
//						potTopics.add(token[i].toLowerCase().replaceAll("[?,]", ""));
//					}
//				}
//
//			}
//			// System.out.println(potTopics);
//			toReturn.put(file, potTopics);
//		}
//		// System.out.println(toReturn);
//		return toReturn;
//	}

}
