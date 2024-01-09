import java.util.*;
public class SentimentAnalyser {
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length]; // output

        for (int i = 0; i < featureSet.length; i++) {
            String[] featureSynonyms = featureSet[i];
            for (String feature : featureSynonyms) {
                int opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
                if (opinion == 0) {
                    opinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
                }

                featureOpinions[i] = opinion;
                if (opinion != 0) {
                    break; // If an opinion is found, move to the next feature
                }
            }
        }

        return featureOpinions;
    }
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        for (String posWord : posOpinionWords) {
            if (review.contains(posWord)) {
                return 1; // Positive opinion
            }
        }

        for (String negWord : negOpinionWords) {
            if (review.contains(negWord)) {
                return -1; // Negative opinion
            }
        }

        return 0; // No opinion found

// your code
    }
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String[] sentences = review.split("[.!?]");
        //String[] sentences = review.split("\\.");

        for (String sentence : sentences) {
            if (sentence.contains(feature) && sentence.contains("was")) {
                opinion = getOpinionOnFeature(sentence, feature, posOpinionWords, negOpinionWords);
                if (opinion != 0) {
                    break;
                }
            }
        }

        return opinion;
    }
    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int opinion = 0;
        String[] sentences = review.split("[,.!?]");
        //String[] sentences = review.split("\\.");

        for (String sentence : sentences) {
            if (sentence.contains(feature)) {
                opinion = getOpinionOnFeature(sentence, feature, posOpinionWords, negOpinionWords);
                if (opinion != 0) {
                    break;
                }
            }
        }

        return opinion;
    }
    public static void main(String[] args) {
        String review = "Haven't been here in years! good service and the food was delicious!Definetly will be a frequent flyer! Francisco was very attentive!";
        review=review.toLowerCase();
        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress",
                        "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly",
                "great", "excellent", "amazing", "awesome",
                "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible",
                "awful", "unprofessional", "poor" };
        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        LogBack.slf4jLogger.debug("Opinions on Features: " + Arrays.toString(featureOpinions));
//        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}

