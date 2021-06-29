//package io;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import data.Podcast;
//import lombok.Data;
//import processing.FilterRegex;
//
//@Data
//public class FilterInformations {
//
//	private List<File> drostenFiles = new ArrayList<File>();
//	private List<File> ciesekFiles = new ArrayList<File>();
//	private Podcast po;
//	private List<Podcast> podcast = new ArrayList<Podcast>();
//	private FilterRegex fr = new FilterRegex();
//
//	public void checkScientist(File[] listOfFiles) throws SAXException, IOException, ParserConfigurationException {
//
//		Map<File, Element> filePath = findNodeList("personen", listOfFiles);
//		int count = 0;
//		for (File file : filePath.keySet()) {
//			Element eElement = filePath.get(file);
//			if (eElement.getElementsByTagName("person").item(1).getTextContent().contains("CHRISTIAN DROSTEN")) {
//				drostenFiles.add(file);
//				//po = new Podcast(count, file.toString(), "CD");
//				count++;
//				podcast.add(po);
//			} else if (eElement.getElementsByTagName("person").item(1).getTextContent().contains("SANDRA CIESEK")) {
//				ciesekFiles.add(file);
//				//po = new Podcast(count, file.toString(), "SC");
//				count++;
//				podcast.add(po);
//			}
//		}
//
//		System.out.println(drostenFiles.size() + " files are available for Drosten: " + drostenFiles);
//		System.out.println(ciesekFiles.size() + " files are available for Ciesek: " + ciesekFiles);
//		// System.out.println(podcast.size());
//	}
//
//	public Map<String, List<String>> filterText(String scientist)
//			throws ParserConfigurationException, SAXException, IOException {
//		Map<String, List<String>> textFromFile = new HashMap<String, List<String>>();
//
//		Element eElement = null;
//
//		if (scientist == "DROSTEN") {
//			if (drostenFiles.isEmpty()) {
//				System.err.println("No files for " + scientist + " available.");
//			} else {
//				for (File file : drostenFiles) {
//					List<String> text = new ArrayList<String>();
//					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//					DocumentBuilder db = dbf.newDocumentBuilder();
//					Document doc = db.parse(file);
//					doc.getDocumentElement().normalize();
//
//					NodeList nodeList = doc.getElementsByTagName("text");
//
//					for (int itr = 0; itr < nodeList.getLength(); itr++) {
//						Node node = nodeList.item(itr);
//
//						if (node.getNodeType() == Node.ELEMENT_NODE) {
//							eElement = (Element) node;
//							if (!(eElement.getAttribute("sprecher").equals("Christian Drosten"))) {
//								String content = eElement.getTextContent();
//								content = content.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("[0-9]", "")
//										.replaceAll("\t", " ");
//								// System.out.println(content);
//								text.add(content.trim());
//							}
//						}
//					}
//
//					textFromFile.put(file.getPath(), text);
//					for (Podcast p : podcast) {
//						if (p.getFile() == file.toString()) {
//							//p.setText(text);
//
//						}
//					}
////					System.out.println("Text to " + scientist + ": " + text.size());
//				}
//			}
//		}
//
//		else if (scientist == "CIESEK") {
//			if (ciesekFiles.isEmpty()) {
//				System.err.println("No files for " + scientist + " available.");
//			} else {
//				for (File file : ciesekFiles) {
//					List<String> text = new ArrayList<String>();
//					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//					DocumentBuilder db = dbf.newDocumentBuilder();
//					Document doc = db.parse(file);
//					doc.getDocumentElement().normalize();
//
//					NodeList nodeList = doc.getElementsByTagName("text");
//
//					for (int itr = 0; itr < nodeList.getLength(); itr++) {
//						Node node = nodeList.item(itr);
//
//						if (node.getNodeType() == Node.ELEMENT_NODE) {
//							eElement = (Element) node;
//							if (!(eElement.getAttribute("sprecher").equals("Sandra Ciesek"))) {
//								String content = eElement.getTextContent();
//								content = content.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("[0-9]", "")
//										.replaceAll("\t", " ");
//								text.add(content.trim());
//							}
//						}
//					}
//					textFromFile.put(file.getPath(), text);
//					for (Podcast p : podcast) {
//						if (p.getFile() == file.toString()) {
//							//p.setText(text);
//
//						}
//					}
////					System.out.println("Text to " + scientist + ": " + text.size());
////					System.out.println(text);
//				}
//			}
//
//		}
//
//		// System.out.println(text);
//
//		// System.out.println("Podcast: " + podcast.size());
//		
//		//System.out.println(textFromFile);
//
//		return textFromFile;
//
//	}
//
//	public Map<File, Element> findNodeList(String nodes, File[] files)
//			throws ParserConfigurationException, SAXException, IOException {
//
//		Map<File, Element> filePath = new HashMap<File, Element>();
//		Element eElement = null;
//		for (File file : files) {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document doc = db.parse(file);
//			doc.getDocumentElement().normalize();
//
//			NodeList nodeList = doc.getElementsByTagName(nodes);
//
//			for (int itr = 0; itr < nodeList.getLength(); itr++) {
//				Node node = nodeList.item(itr);
//
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					eElement = (Element) node;
//					filePath.put(file, eElement);
//				}
//			}
//		}
//		return filePath;
//
//	}
//
//	public Map<String, List<String>> filterQuestions(Map<String, List<String>> textToDrosten) throws Exception {
//		Map<String, List<String>> toReturn = new HashMap<String, List<String>>();
//
//		for (String s : textToDrosten.keySet()) {
//			List<String> questions = new ArrayList<String>();
//			List<String> content = textToDrosten.get(s);
//			for (String subContent : content) {
//				List<String> completeContent = fr.splittedString(subContent);
//				for (String st : completeContent) {
//					// System.out.println(st);
//					if (fr.endsWithQuestionMark(st)) {
//						questions.add(st);
//					}
//				}
//			}
//			// System.out.println("Number of questions: " + questions.size());
//			toReturn.put(s, questions);
//			for (Podcast p : podcast) {
//				if (p.getFile() == s.toString()) {
//					//System.out.println(questions);
//					//p.setQuestions(questions);
//				}
//			}
//		}
//
//		ImportData.writeToCSV(podcast);
//		
//		return toReturn;
//	}
//
//}
