package com.ihover.robot;

import com.ihover.Utils;

import java.io.InputStream;
import java.util.*;
import java.util.stream.*;

import static java.lang.Character.toUpperCase;
import static java.text.MessageFormat.format;

public enum Action {
    ROTATE_RIGHT('R'),
    ROTATE_LEFT('L'),
    MOVE('M');

    private final char aliasChar;

    Action(char aliasChar) {
        this.aliasChar = aliasChar;
    }

    public static Action parseCharValue(final char ch) {
        return switch (toUpperCase(ch)) {
            case 'R' -> ROTATE_RIGHT;
            case 'L' -> ROTATE_LEFT;
            case 'M' -> MOVE;
            default -> throw new IllegalStateException("Unexpected value: " + toUpperCase(ch));
        };
    }

    public static boolean isValidChar(final char ch) {
        return switch (toUpperCase(ch)) {
            case 'R', 'L', 'M' -> true;
            default -> false;
        };
    }

    public static Stream<Action> writeActionFromStream(final InputStream in) {
        final List<Character> characters = Arrays.stream(values())
                .map(Action::getAliasChar)
                .collect(Collectors.toList());

        final String selectMsg = format("Input character sequence with {0} valid characters : ", characters);
        Utils.print(selectMsg);

        final Scanner scanner = new Scanner(in);

        while (true) {
            if (!scanner.hasNextLine()) {
                Utils.print(selectMsg);
                continue;
            }
            return scanner.nextLine()
                    .chars()
                    .mapToObj(i -> (char) i)
                    .filter(Action::isValidChar)
                    .map(Action::parseCharValue);
        }
    }

    public char getAliasChar() {
        return this.aliasChar;
    }
}
