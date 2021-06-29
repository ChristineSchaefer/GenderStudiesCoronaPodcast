//package app;
//
//import java.io.File;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import io.FilterInformations;
//import io.ImportData;
//import processing.ClusterTopics;
//import processing.FilterStopwords;
//
//public class MainApplication {
//
//	private static File inputFolder = new File("input/Coronavirus_Update_NDR");
//	private static File stopwords = new File("input/stopwords.txt");
//	private static File[] listOfFiles;
//
//	private static Map<String, List<String>> textToDrosten;
//	private static Map<String, List<String>> textToCiesek;
//
//	private static Map<String, List<String>> questionsToDrosten;
//	private static Map<String, List<String>> questionsToCiesek;
//
//	private static Map<String, List<String>> potCategoryD;
//	private static Map<String, List<String>> potCategoryC;
//
//	private static Map<String, Map<String, Double>> termWeightD;
//	private static Map<String, Map<String, Double>> termWeightC;
//	
//	private static Map<String, Set<String>> podTopicsC;
//
//	public static void main(String[] args) throws Exception {
//
//		System.out.println("--start import");
//		ImportData io = new ImportData();
//		listOfFiles = io.readXMLFile(inputFolder);
//		System.out.println("finish import--");
//
//		FilterInformations filter = new FilterInformations();
//		FilterStopwords filterSW = new FilterStopwords(stopwords);
//		ClusterTopics ct = new ClusterTopics();
//
//		filter.checkScientist(listOfFiles);
//
//		textToDrosten = filter.filterText("DROSTEN");
//		textToCiesek = filter.filterText("CIESEK");
//		//System.out.println(textToCiesek);
//
//		questionsToDrosten = filter.filterQuestions(textToDrosten);
//		questionsToCiesek = filter.filterQuestions(textToCiesek);
//		//System.out.println(questionsToCiesek);
//
//		potCategoryD = filterSW.processStrings(questionsToDrosten);
//		potCategoryC = filterSW.processStrings(questionsToCiesek);
//		//System.out.println(potCategoryC);
//		
//		termWeightD = ct.termWeighter(potCategoryD);
//		termWeightC = ct.termWeighter(potCategoryC);
//		
//		System.out.println(termWeightC);
//		
////		podTopicsC = ct.selectTopics(termWeightC);
//	}
//
//}
