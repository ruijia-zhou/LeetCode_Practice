import java.util.ArrayList;
import java.util.List;

//LeetCode 131
class PalindromePartitioning {
    private String s;
    private int stringLength;
    private boolean[][] palindromeTable;
    private List<List<List<String>>> partitioningForEachLength;

    public List<List<String>> partition(String s) {
        this.s = s;
        this.stringLength = s.length();
        this.palindromeTable = new boolean[stringLength][stringLength];
        this.generatePalindromeTable();
        this.partitioningForEachLength = new ArrayList<>();
        this.generatePalindromePartitions();
        return this.partitioningForEachLength.get(stringLength - 1);
    }

    private void generatePalindromePartitions(){
        for (int end = 0; end < stringLength; end++) {
            List<List<String>> curPartioningList = new ArrayList<>();
            for (int start = 0; start <= end; start++) {
                this.addNewPartitions(start, end, curPartioningList);
            }
            this.partitioningForEachLength.add(curPartioningList);
        }
    }

    private void addNewPartitions(int start,
                                  int end,
                                  List<List<String>> curPartioningList) {
        if (this.palindromeTable[start][end]) {
            List<String> newPartition = new ArrayList<>();
            if (start == 0) {
                newPartition.add(this.s.substring(start, end + 1));
                curPartioningList.add(newPartition);
            } else {
                for (List<String> prePartion : partitioningForEachLength.get(start - 1)) {
                    newPartition = new ArrayList<String>(prePartion);
                    newPartition.add(this.s.substring(start, end + 1));
                    curPartioningList.add(newPartition);
                }
            }
        }
    }

    private void generatePalindromeTable(){
        for (int prefix = 0; prefix < stringLength; prefix++) {
            for (int row = 0; row + prefix < stringLength; row++) {
                palindromeTable[row][row + prefix] = isPalindrome(row, row + prefix);
            }
        }
    }

    private boolean isPalindrome(int start, int end) {
        if (end - start == 0) return true;
        if (s.charAt(start) != s.charAt(end)) return false;
        if (end - start == 1) return true;
        return this.palindromeTable[start + 1][end - 1];
    }
}