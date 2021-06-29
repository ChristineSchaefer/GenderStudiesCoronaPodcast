package data;

import java.util.List;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

/**
 * represents a podcast-object get data from .csv
 * 
 * @author ChristineSchaefer
 *
 */
@Data
public class Podcast {

	private int id;
	@CsvBindByName(column = "Filename")
	private String file;
	@CsvBindByName(column = "Arzt")
	private String scientist;
//	private List<String> text;
	@CsvBindByName(column = "Questions")
	private String questions;
	@CsvBindByName(column = "Interviewerin")
	private String interviewer;

	private String[] tokens;
	private String[] posTags;
//	private String [] lemma;

	private List<String> topics;

//	public Podcast(int id, String file, String scientist) {
//		this.id = id;
//		this.file = file;
//		this.scientist = scientist;
//	}

	public Podcast() {
	};
}
