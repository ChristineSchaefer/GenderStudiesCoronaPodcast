package processing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.lemmatizer.LemmatizerME;
import opennlp.tools.lemmatizer.LemmatizerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * methods for preprocessing questions: tokenizer, pos-tagger usage of
 * opennlp-tools https://opennlp.apache.org
 * 
 * @author ChristineSchaefer
 *
 */
public class NLPProcessing {

	/**
	 * separate given String into single tokens
	 * 
	 * @param questions
	 * @return single tokens of given questions
	 * @throws IOException
	 */
	public String[] tokenize(String questions) throws IOException {
		TokenizerModel model;
		try (InputStream modelIn = new FileInputStream("input/nlp/de-token.bin")) {
			model = new TokenizerModel(modelIn);
		}

		Tokenizer tokenizer = new TokenizerME(model);
		String[] token = tokenizer.tokenize(questions);
		return token;
	}

	/**
	 * adds pos-tag to given token
	 * 
	 * @param tokens
	 * @return pos-tags of given token
	 * @throws IOException
	 */
	public String[] tagger(String[] tokens) throws IOException {
		POSModel model;
		try (InputStream modelIn = new FileInputStream("input/nlp/de-posTagger.bin")) {
			model = new POSModel(modelIn);
		}
		POSTaggerME tagger = new POSTaggerME(model);
		String[] tags = tagger.tag(tokens);

		return tags;
	}

	// TODO Lemmatizer deutsch finden - falls notwendig
	public String[] lemmatize(String[] tokens, String[] posTags) throws FileNotFoundException, IOException {
		LemmatizerModel model;
		try (InputStream modelIn = new FileInputStream("en-lemmatizer.bin")) {
			model = new LemmatizerModel(modelIn);
		}
		LemmatizerME lemmatizer = new LemmatizerME(model);
		String[] lemma = lemmatizer.lemmatize(tokens, posTags);

		return lemma;
	}

	/**
	 * find nouns using the pos-tags of token
	 * 
	 * @param token
	 * @param posTags
	 * @return
	 */
	public String[] findNouns(String[] token, String[] posTags) {
		List<String> relevantNouns = new ArrayList<String>();
		for (int i = 0; i < token.length; i++) {
			if (posTags[i].equals("NOUN")) {
				relevantNouns.add(token[i]);
			}
		}
		String[] toReturn = new String[relevantNouns.size()];
		relevantNouns.toArray(toReturn);
		return toReturn;
	}

}
