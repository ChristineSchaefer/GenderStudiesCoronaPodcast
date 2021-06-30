package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;
import org.xml.sax.SAXException;

import data.Podcast;
import lombok.Data;

/**
 * io methods optional usage of super-csv
 * http://super-csv.github.io/super-csv/index.html
 * 
 * @author ChristineSchaefer
 *
 */
@Data
public class ImportData {

//	private static final String CSV_SEPARATOR = ",";
//
//	public File[] readXMLFile(File inputFolder) throws SAXException, IOException, ParserConfigurationException {
//		if (!inputFolder.exists()) {
//			System.err.println("Path is not available. Please choose another one.");
//		}
//		File[] listOfFiles = inputFolder.listFiles();
//		System.out.println("number of available files: " + listOfFiles.length);
//
//		return listOfFiles;
//
//	}

	/**
	 * write list to txt-file
	 * 
	 * @param list
	 * @param path
	 * @throws IOException
	 */
	public void writeListToTxt(List<String> list, String path) throws IOException {
		FileWriter writer = new FileWriter(path);
		for (String str : list) {
			writer.write(str + "," + System.lineSeparator());
		}
		writer.close();
	}

//	public static void writeToCSV(List<Podcast> podcast) {
//		try {
//			BufferedWriter bw = new BufferedWriter(
//					new OutputStreamWriter(new FileOutputStream("output/podcast.csv"), "UTF-8"));
//			for (Podcast product : podcast) {
//				StringBuffer oneLine = new StringBuffer();
//				oneLine.append(product.getId());
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(product.getFile().trim());
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(product.getScientist());
////				oneLine.append(CSV_SEPARATOR);
////				oneLine.append(product.getText());
//				oneLine.append(CSV_SEPARATOR);
//				oneLine.append(product.getQuestions());
//				bw.write(oneLine.toString());
//				bw.newLine();
//			}
//			bw.flush();
//			bw.close();
//		} catch (UnsupportedEncodingException e) {
//		} catch (FileNotFoundException e) {
//		} catch (IOException e) {
//		}
//	}
//
//	private static CellProcessor[] getProcessors() {
//
//		final CellProcessor[] processors = new CellProcessor[] { 
//				new UniqueHashCode(), // podcastID
//				new NotNull(), // fileName
//				new NotNull(), // scientist
//				new NotNull(), // text
//				new NotNull(), // questions
//		};
//
//		return processors;
//	}
//
//	public static void writeWithCsvListWriter(List<Podcast> podcast) throws Exception {
//
//		ICsvListWriter listWriter = null;
//		try {
//			listWriter = new CsvListWriter(new FileWriter("output/podcast.csv"),
//					CsvPreference.STANDARD_PREFERENCE);
//
//			final CellProcessor[] processors = getProcessors();
//			final String[] header = new String[] { "ID", "Filename", "Wissenschaftler:in", "Text", "Fragen"};
//
//			// write the header
//			listWriter.writeHeader(header);
//
//			// write the customer lists
//			listWriter.write(podcast, processors);
//
//		} finally {
//			if (listWriter != null) {
//				listWriter.close();
//			}
//		}
//	}
//	
//	public void writeMapToTxt(Map<?, ?> map, String path) {
//		try {
//	        File fileOne=new File(path);
//	        FileOutputStream fos=new FileOutputStream(fileOne);
//	        ObjectOutputStream oos=new ObjectOutputStream(fos);
//
//	        oos.writeObject(map);
//	        oos.flush();
//	        oos.close();
//	        fos.close();
//	    } catch(Exception e) {}
//	}
//	
//	

}
