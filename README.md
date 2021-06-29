# GenderStudiesCoronaPodcast
Filtering of the occurring topics in the asked questions of the NDR Corona Podcast<br />

Project for the exercise "The Pandemic in Language and Text - Corona Podcasts & Co.". <br />
Topic: "Does Cisek get more women's questions? Gender perspective on the differences between the podcast conversations in the NDR podcast of the two podcast participant:s".<br />

Goal: Classification of the topics discussed in the interviews and analysis of these with regard to gender-specific attributions.<br />

Pre-processing: Python script to filter the questions asked to the male and female scientists. Storage of the data in csv file.<br />

Procedure:<br />
1. use of the csv data.<br />
2. tokenize and tag the questions using OpenNLP tools (tokenizer and POS tagger).<br />
3. weighting of the occurring nouns as potential topics.<br />
4. searching for the best weighted nouns as topics.<br />

Project Structure:<br />
src/app: PrepareQuestionsFromFile: Main method to run relevant classes.<br />
src/data: Podcast: Podcast object.<br />
src/io: ImportData: IO methods.<br />
src/processing: FilterStopwords: Remove stop words.<br />
src/processing: NLPProcessing: Tokenizer, POS tagger.<br />
src/processing: ClusterTopics: Filtering of topics.<br />

input/nlp: opennlp-tokenizer- and postagger-model.<br />
input: csv file with basic data, txt file with stop words.<br />

Used libraries:<br />
Super-CSV: http://super-csv.github.io/super-csv/index.html<br />
Apache OpenNLP: https://opennlp.apache.org<br />
