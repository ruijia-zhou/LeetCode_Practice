import java.util.*;

public class ApplySubstitutions {
    private Map<Character, String> replacementMap;
    private Map<Character, List<Character>> edges;
    private Map<Character, Integer> inDegrees;

    public String applySubstitutions(List<List<String>> replacements, String text) {
        this.replacementMap = new HashMap<>();
        this.inDegrees = new HashMap<>();
        this.edges = new HashMap<>();
        addToMap(replacements);
        topoSort();
        for (char placeholder : replacementMap.keySet()) {
            text = text.replaceAll("%" + placeholder + "%", replacementMap.get(placeholder));
        }
        return text;
    }

    private void topoSort() {
        Queue<Character> verticesWithNoIndegrees = getStartingVertices();
        while (!verticesWithNoIndegrees.isEmpty()) {
            Character from = verticesWithNoIndegrees.poll();
            List<Character> destinations = edges.getOrDefault(from, new ArrayList<>());
            for (char destination : destinations) {
                updateReplacement(from, destination);
                this.inDegrees.put(destination, this.inDegrees.get(destination) - 1);
                if (this.inDegrees.get(destination) == 0) verticesWithNoIndegrees.offer(destination);
            }
        }
    }

    private void updateReplacement(char from, char to) {
        String placeHoldingString = "%" + from + "%";
        String replaceString = this.replacementMap.get(from);
        String newReplacement = this.replacementMap.get(to).replaceAll(placeHoldingString, replaceString);
        this.replacementMap.put(to, newReplacement);
    }

    private Queue<Character> getStartingVertices() {
        Queue<Character> startingVertices = new LinkedList<>();
        for (char vertice : replacementMap.keySet()) {
            if (!this.inDegrees.containsKey(vertice)) startingVertices.offer(vertice);
        }
        return startingVertices;
    }

    private void countIndegrees(char placeHolder, String replaceString) {
        Set<Character> fromCharacters = new HashSet<>();
        for (int i = 0; i < replaceString.length(); i++) {
            if (replaceString.charAt(i) == '%' &&
                    !fromCharacters.contains(replaceString.charAt(i + 1))) {
                addToEdges(replaceString.charAt(++i), placeHolder);
                this.inDegrees.put(placeHolder, this.inDegrees.getOrDefault(placeHolder, 0) + 1);
                fromCharacters.add(replaceString.charAt(++i));
            }
        }
    }

    private void addToEdges(char from, char to) {
        List<Character> destinations = this.edges.getOrDefault(from, new ArrayList<>());
        destinations.add(to);
        this.edges.put(from, destinations);
    }

    private void addToMap(List<List<String>> replacements) {
        for (List<String> replacement : replacements) {
            char placeHolder = replacement.get(0).charAt(0);
            replacementMap.put(placeHolder, replacement.get(1));
            countIndegrees(placeHolder, replacement.get(1));
        }
    }
}
