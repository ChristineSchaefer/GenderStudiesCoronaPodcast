package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import data.Podcast;
import processing.ClusterTopics;
import processing.FilterStopwords;
import processing.NLPProcessing;

/**
 * main application for filtering topics
 * 
 * @author ChristineSchaefer
 *
 */
public class PrepareQuestionsFromFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, CsvException {

		// set paths to csv- and stopword-file
		// csv-file is generated through python-script
		String fileName = "input/PodcastQuestions_pdf.csv";
		File stopwords = new File("input/stopwords.txt");

		// get data from csv-file
		List<Podcast> podcastList = new CsvToBeanBuilder<Podcast>(new FileReader(fileName)).withType(Podcast.class)
				.build().parse();

		// counter for podcast-ids
		int count = 0;

		// instantiate needed classes
		NLPProcessing nlp = new NLPProcessing();
		FilterStopwords sw = new FilterStopwords(stopwords);
		ClusterTopics ct = new ClusterTopics();

		// set podcast attributes
		for (Podcast p : podcastList) {
			p.setId(count);
			count++;
			// usage of opennlp-tools tokenizer and pos-tagger
			// set tokens: 1. remove stopwords through given txt-file 2. tokenize questions
			p.setTokens(sw.removeStopwords(nlp.tokenize(p.getQuestions())));
			// set pos-tags
			p.setPosTags(nlp.tagger(p.getTokens()));
		}

		// if all podcast have tokens and pos-tags, new iteration above them
		for (Podcast p : podcastList) {
			// find nouns
			String[] nouns = nlp.findNouns(p.getTokens(), p.getPosTags());
			// set topics in view of the occurring nouns
			p.setTopics(ct.findTopics(nouns, podcastList));
		}

		// print all podcast
		podcastList.forEach(System.out::println);

		// seperate all podcast into drosten and ciesek
		List<Podcast> drosten = new ArrayList<Podcast>();
		List<Podcast> ciesek = new ArrayList<Podcast>();
		for (Podcast p : podcastList) {
			if (p.getScientist().equals("CHRISTIAN DROSTEN")) {
				drosten.add(p);
			}
			if (p.getScientist().equals("SANDRA CIESEK")) {
				ciesek.add(p);
			}
		}

		// collect occuring topics from all podcast based on scientist and write them in
		// txt-file
		ct.collectTopics(ciesek, "output/ciesekTopics.txt");
		ct.collectTopics(drosten, "output/drostenTopics.txt");

	}

}
