package jiarui;

public class Parser {

    public static ParsedCommand parse(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        String keyword = parts[0];
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
