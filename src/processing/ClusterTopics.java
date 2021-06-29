package processing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.Podcast;
import io.ImportData;
import lombok.Data;

/**
 * methods for weighting terms and select topics usage of tfidf
 * 
 * @author ChristineSchaefer
 *
 */
@Data
public class ClusterTopics {

	private double tf;
	private double idf;
	private double tfidf;

	/**
	 * select topics by weighting nouns of podcasts
	 * 
	 * @param nouns   (potential topic)
	 * @param podcast
	 * @return list of topics
	 */
	public List<String> findTopics(String[] nouns, List<Podcast> podcast) {
		Double[] weight = new Double[nouns.length];
		List<String> topics = new ArrayList<String>();

		// iterate over each noun of one podcast
		for (int i = 0; i < nouns.length; i++) {
			// calculate weight by tfidf
			tf = tf(nouns, nouns[i]);
			idf = idf(podcast, nouns[i]);
			tfidf = tf * idf;
			weight[i] = tfidf;

			tf = 0d;
			idf = 0d;
			tfidf = 0d;

		}

		// select best weighted topics
		topics = compareWeights(nouns, weight);
		return topics;
	}

	/**
	 * compare weight of potential topics and select best ones
	 * 
	 * @param nouns
	 * @param weights
	 * @return list of best weighted topics
	 */
	public List<String> compareWeights(String[] nouns, Double[] weights) {
		List<String> toReturn = new ArrayList<String>();
		Map<String, Double> nounWeights = new HashMap<String, Double>();
		// put string and weight together
		for (int i = 0; i < nouns.length; i++) {
			nounWeights.put(nouns[i], weights[i]);
		}
		// sort map by value (= weight of term)
		nounWeights = sortByValue(nounWeights);

		for (String string : nounWeights.keySet()) {
			if (toReturn.isEmpty()) {
				toReturn.add(string);
			} else {
				// maximum size of returned list of topics = 20
				// check if list contains strings with lower weight and remove them
				if (toReturn.size() < 20 && !(toReturn.contains(string))) {
					for (String s : toReturn) {
						if (nounWeights.get(string) >= nounWeights.get(s)) {
							toReturn.add(string);
							break;
						}
					}
				}
				if (toReturn.size() == 20 && !(toReturn.contains(string))) {
					for (String s : toReturn) {
						if (nounWeights.get(string) > nounWeights.get(s)) {
							toReturn.remove(s);
							toReturn.add(string);
							break;
						}
					}
				}

			}
		}
		return toReturn;
	}

	/**
	 * collect topics of all podcast of an specific scienties and write them in
	 * txt-file
	 * 
	 * @param podcast
	 * @param path
	 * @throws IOException
	 */
	public void collectTopics(List<Podcast> podcast, String path) throws IOException {
		List<String> collectedTopics = new ArrayList<String>();

		for (Podcast p : podcast) {
			collectedTopics.addAll(p.getTopics());
		}

		ImportData io = new ImportData();
		io.writeListToTxt(collectedTopics, path);
	}
//
//	public Map<String, Map<String, Double>> termWeighter(Map<String, List<String>> potCat) {
//		Map<String, Map<String, Double>> toReturn = new HashMap<String, Map<String, Double>>();
//		Map<String, Double> termWeight = new HashMap<String, Double>();
//
//		for (String file : potCat.keySet()) {
//			tf = 0d;
//			idf = 0d;
//			tfidf = 0d;
//
//			List<String> stringsFromDocument = potCat.get(file);
//			// System.out.println(stringsFromDocument);
//			for (String token : stringsFromDocument) {
//				tf = tf(stringsFromDocument, token);
//				// System.out.println(tf);
//				idf = idf(potCat, token);
//				// System.out.println(idf);
//				tfidf = tf * idf;
//
//				termWeight.put(token, tfidf);
//			}
//			toReturn.put(file, termWeight);
//		}
//
//		// System.out.println(termWeight);
//		return toReturn;
//	}

//	public double tf(List<String> doc, String term) {
//		double result = 0;
//		for (String word : doc) {
//			if (term.equalsIgnoreCase(word))
//				result++;
//		}
//		return result / doc.size();
//	}

	/**
	 * calculate tf
	 * 
	 * @param doc  list of strings
	 * @param term String represents a term
	 * @return term frequency of term in document
	 */
	public double tf(String[] doc, String term) {
		double result = 0;
		for (String word : doc) {
			if (term.equalsIgnoreCase(word))
				result++;
		}
		return result / doc.length;
	}

//	public double idf(Map<String, List<String>> docs, String term) {
//		double n = 0;
//		for (String file : docs.keySet()) {
//			List<String> docTerms = docs.get(file);
//			for (String s : docTerms) {
//				if (term.equalsIgnoreCase(s)) {
//					n++;
//					break;
//				}
//			}
//		}
//
//		return Math.log(docs.size() / n);
//	}

	/**
	 * calculate idf
	 * 
	 * @param docs map of files with specific terms
	 * @param term String represents a term
	 * @return the inverse term frequency of term in documents
	 */
	public double idf(List<Podcast> podcast, String term) {
		double n = 0;
		for (Podcast p : podcast) {
			String[] docTerms = p.getTokens();
			for (String s : docTerms) {
				if (term.equalsIgnoreCase(s)) {
					n++;
					break;
				}
			}
		}

		return Math.log(podcast.size() / n);
	}

	/**
	 * compare calues of given map and sort it
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return sorted map by value
	 */
	public <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Entry.comparingByValue());

		Map<K, V> result = new LinkedHashMap<>();
		for (Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}
}
