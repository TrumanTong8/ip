package jiarui;

public class Parser {

    public static ParsedCommand parse(String input) {
        assert input != null : "Input should not be null";

        String trimmed = input.trim();
        assert !trimmed.isEmpty() : "trimmed input should not be empty";

        String[] parts = input.trim().split("\\s+", 2);
        String keyword = parts[0];
        assert !keyword.isEmpty() : "keyword should not be empty";

        String args = (parts.length < 2) ? "" : parts[1].trim();
        return new ParsedCommand(keyword, args);
    }

    public static int parseIndex(String arg) throws JiaRuiException {
        try {
            int num = Integer.parseInt(arg.trim());
            return num - 1;
        } catch (NumberFormatException e) {
            throw new JiaRuiException("No! Task number must be a number.");
        }
    }
}
