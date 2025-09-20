import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// LeetCode 1733
public class MinimumNumberOfPeopleToTeach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Map<Integer, Set<Integer>> userToLanguages = getUserToLanguages(languages);
        Set<Integer> usersWhoCannotCommunicate = getUsersWhoCannotCommunicate(friendships, userToLanguages);

        return usersWhoCannotCommunicate.size() - getNumSpeakersOfMostSpokenLanguage(n,
                                                                                     usersWhoCannotCommunicate,
                                                                                     userToLanguages);
    }

    private int getNumSpeakersOfMostSpokenLanguage(int n,
                                                   Set<Integer> usersWhoCannotCommunicate,
                                                   Map<Integer, Set<Integer>> userToLanguages) {
        int[] numSpeakersOfLanguages = new int[n + 1];
        int numSpeakersOfMostSpokenLanguage = 0;

        for (int user : usersWhoCannotCommunicate) {
            Set<Integer> languagesUserSpeaks = userToLanguages.get(user);
            for (int language : languagesUserSpeaks) {
                numSpeakersOfLanguages[language]++;
                numSpeakersOfMostSpokenLanguage = Math.max(numSpeakersOfMostSpokenLanguage,
                                                           numSpeakersOfLanguages[language]);
            }
        }

        return numSpeakersOfMostSpokenLanguage;
    }

    private Set<Integer> getUsersWhoCannotCommunicate(int[][] friendships,
                                                      Map<Integer, Set<Integer>> userToLanguages) {
        Set<Integer> usersWhoCannotCommunicate = new HashSet<>();

        for (int[] friendship : friendships) {
            int user1 = friendship[0];
            int user2 = friendship[1];
            if (!canCommunicate(user1, user2, userToLanguages)) {
                usersWhoCannotCommunicate.add(user1);
                usersWhoCannotCommunicate.add(user2);
            }
        }

        return usersWhoCannotCommunicate;
    }

    private boolean canCommunicate(int user1,
                                   int user2,
                                   Map<Integer, Set<Integer>> userToLanguages) {
        Set<Integer> languagesOfUser1 = userToLanguages.get(user1);
        Set<Integer> languagesOfUser2 = userToLanguages.get(user2);

        for (int language : languagesOfUser1) {
            if (languagesOfUser2.contains(language)) return true;
        }

        return false;
    }

    private Map<Integer, Set<Integer>> getUserToLanguages(int[][] languages) {
        Map<Integer, Set<Integer>> userToLanguages = new HashMap<>();

        for (int i = 0; i < languages.length; i++) {
            int user = i + 1;
            for (int language : languages[i]) {
                Set<Integer> languagesOfUser1 = userToLanguages.getOrDefault(user, new HashSet<>());
                languagesOfUser1.add(language);
                userToLanguages.put(user, languagesOfUser1);
            }
        }

        return userToLanguages;
    }
}
