import java.util.*;
public class SentimentAnalyser {
    public static int[] detectProsAndCons(String userReview, String[][] features, String[] positiveWords, String[] negativeWords) {
        int[] featureSentiments = new int[features.length];

        for (int i = 0; i < features.length; i++) {
            String[] featureSynonyms = features[i];
            for (String feature : featureSynonyms) {
                int sentiment = getOpinonOnFeature(userReview, feature, positiveWords, negativeWords);
                if (sentiment != 0) {
                    featureSentiments[i] = sentiment;
                    break; // Move to the next feature set
                }
            }
        }

        return featureSentiments;
    }

    private static int getOpinonOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int sentiment = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);

        if (sentiment == 0) {
            sentiment = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        }

        return sentiment;
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int sentiment = 0;
        String pattern = feature + " was ";

        if (review.toLowerCase().contains(pattern)) {
            for (String posOpinion : posOpinionWords) {
                if (review.toLowerCase().contains(pattern + posOpinion)) {
                    sentiment = 1;
                    break;
                }
            }

            if (sentiment == 0) {
                for (String negOpinion : negOpinionWords) {
                    if (review.toLowerCase().contains(pattern + negOpinion)) {
                        sentiment = -1;
                        break;
                    }
                }
            }
        }

        return sentiment;
    }

    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int sentiment = 0;

        for (String sentence : review.split("\\.")) {
            for (String posOpinion : posOpinionWords) {
                if (sentence.toLowerCase().contains(posOpinion + " " + feature)) {
                    sentiment = 1;
                    return sentiment;
                }
            }

            for (String negOpinion : negOpinionWords) {
                if (sentence.toLowerCase().contains(negOpinion + " " + feature)) {
                    sentiment = -1;
                    return sentiment;
                }
            }
        }

        return sentiment;
    }

    public static void main(String[] args) {
        String userReview = "Haven't been here in years! Fantastic service and the food was delicious! Definitely will be a frequent flyer! Francisco was very attentive";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress", "bartender", "staff", "server"}
        };
        String[] positiveWords = {"good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome", "delicious"};
        String[] negativeWords = {"slow", "bad", "horrible", "awful", "unprofessional", "poor"};

        int[] featureSentiments = detectProsAndCons(userReview, featureSet, positiveWords, negativeWords);
        LogBack.slf4jLogger.debug("Opinions  on Features: " + Arrays.toString(featureSentiments));
    }
}

