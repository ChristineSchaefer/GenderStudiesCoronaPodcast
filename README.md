# GenderStudiesCoronaPodcast
Filtering of the occurring topics in the asked questions of the NDR Corona Podcast

Project for the exercise "The Pandemic in Language and Text - Corona Podcasts & Co.". 
Topic: "Does Cisek get more women's questions? Gender perspective on the differences between the podcast conversations in the NDR podcast of the two podcast participant:s".

Goal: Classification of the topics discussed in the interviews and analysis of these with regard to gender-specific attributions.

Pre-processing: Python script to filter the questions asked to the male and female scientists. Storage of the data in csv file.

Procedure:
1. use of the csv data.
2. tokenize and tag the questions using OpenNLP tools (tokenizer and POS tagger).
3. weighting of the occurring nouns as potential topics.
4. searching for the best weighted nouns as topics.

Project Structure:
src/app: PrepareQuestionsFromFile: Main method to run relevant classes.
src/data: Podcast: Podcast object.
src/io: ImportData: IO methods.
src/processing: FilterStopwords: Remove stop words.
src/processing: NLPProcessing: Tokenizer, POS tagger.
src/processing: ClusterTopics: Filtering of topics.

input/nlp: opennlp-tokenizer- and postagger-model.
input: csv file with basic data, txt file with stop words.

Used libraries:
Super-CSV: http://super-csv.github.io/super-csv/index.html
Apache OpenNLP: https://opennlp.apache.org
