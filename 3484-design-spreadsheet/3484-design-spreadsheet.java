import java.util.*;

class Spreadsheet {
    private final Map<String, Integer> cellValues;

    public Spreadsheet(int rows) {
        cellValues = new HashMap<>();
        // Rows count is not needed if we assume cell references are valid (1 ≤ row ≤ rows)
    }

    public void setCell(String cell, int value) {
        cellValues.put(cell, value);
    }

    public void resetCell(String cell) {
        cellValues.remove(cell);
    }

    public int getValue(String formula) {
        // formula always starts with '=' and contains exactly two operands
        String expr = formula.substring(1);  // remove '='
        String[] parts = expr.split("\\+"); // split on '+'
        int sum = 0;
        for (String term : parts) {
            term = term.trim();
            if (term.isEmpty()) continue;
            char first = term.charAt(0);
            if (Character.isDigit(first)) {
                // numeric constant
                sum += Integer.parseInt(term);
            } else {
                // cell reference
                sum += cellValues.getOrDefault(term, 0);
            }
        }
        return sum;
    }
}
