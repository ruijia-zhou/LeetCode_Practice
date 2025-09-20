import java.util.*;

// LeetCode 3481
public class ApplySubstitutions {
    private static final char MARKER = '%';
    private Map<Character, String> charToReplacement;
    private Map<Character, List<Character>> charToEdges;
    private Map<Character, Integer> charToIndegrees;

    public String applySubstitutions(List<List<String>> replacements, String text) {
        this.charToReplacement = new HashMap<>();
        this.charToIndegrees = new HashMap<>();
        this.charToEdges = new HashMap<>();
        buildGraph(replacements);
        topoSort();
        for (char placeholder : charToReplacement.keySet()) {
            text = text.replaceAll(String.valueOf(MARKER + placeholder + MARKER), charToReplacement.get(placeholder));
        }

        return text;
    }

    private void topoSort() {
        Queue<Character> verticesWithNoIndegrees = getStartingVertices();
        while (!verticesWithNoIndegrees.isEmpty()) {
            Character from = verticesWithNoIndegrees.poll();
            List<Character> destinations = charToEdges.getOrDefault(from, new ArrayList<>());
            for (char destination : destinations) {
                updateReplacement(from, destination);
                this.charToIndegrees.put(destination, this.charToIndegrees.get(destination) - 1);
                if (this.charToIndegrees.get(destination) == 0) verticesWithNoIndegrees.offer(destination);
            }
        }
    }

    private void updateReplacement(char from, char to) {
        String placeHoldingString = String.valueOf(MARKER + from + MARKER);
        String replaceString = this.charToReplacement.get(from);
        String newReplacement = this.charToReplacement.get(to).replaceAll(placeHoldingString, replaceString);
        this.charToReplacement.put(to, newReplacement);
    }

    private Queue<Character> getStartingVertices() {
        Queue<Character> startingVertices = new LinkedList<>();
        for (char vertice : charToReplacement.keySet()) {
            if (!this.charToIndegrees.containsKey(vertice)) startingVertices.offer(vertice);
        }

        return startingVertices;
    }

    private void countIndegrees(char placeHolder, String replaceString) {
        Set<Character> fromCharacters = new HashSet<>();
        for (int i = 0; i < replaceString.length(); i++) {
            if (replaceString.charAt(i) == '%' &&
                    !fromCharacters.contains(replaceString.charAt(i + 1))) {
                addToEdges(replaceString.charAt(++i), placeHolder);
                this.charToIndegrees.put(placeHolder, this.charToIndegrees.getOrDefault(placeHolder, 0) + 1);
                fromCharacters.add(replaceString.charAt(++i));
            }
        }
    }

    private void addToEdges(char from, char to) {
        List<Character> destinations = this.charToEdges.getOrDefault(from, new ArrayList<>());
        destinations.add(to);
        this.charToEdges.put(from, destinations);
    }

    private void buildGraph(List<List<String>> replacements) {
        for (List<String> replacement : replacements) {
            char placeHolder = replacement.get(0).charAt(0);
            charToReplacement.put(placeHolder, replacement.get(1));
            countIndegrees(placeHolder, replacement.get(1));
        }
    }
}
