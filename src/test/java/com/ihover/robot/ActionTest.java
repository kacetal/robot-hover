package com.ihover.robot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static com.ihover.robot.Action.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class ActionTest {

    @ParameterizedTest
    @ValueSource(chars = {'r', 'R', 'l', 'L', 'm', 'M'})
    void parseValidCharValue(final char ch) {
        final Action action = Arrays.stream(Action.values())
                .filter(a -> Character.toUpperCase(ch) == a.getAliasChar())
                .findFirst()
                .orElseThrow();
        assertThat(Action.parseCharValue(ch)).isEqualTo(action);
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'c', 'D', '1', '2', '3'})
    void parseNotValidCharValue(final char ch) {
        assertThatIllegalStateException().isThrownBy(() -> Action.parseCharValue(ch));
    }

    @ParameterizedTest
    @ValueSource(chars = {'r', 'R', 'l', 'L', 'm', 'M'})
    void isValidChar(final char ch) {
        assertThat(Action.isValidChar(ch)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'c', 'D', '1', '2', '3'})
    void isNotValidChar(final char ch) {
        assertThat(Action.isValidChar(ch)).isFalse();
    }

    @Test
    void writeActionFromStream() {
        assertThat(Action.writeActionFromStream(new ByteArrayInputStream("RRMMLL".getBytes())))
                .containsSequence(ROTATE_RIGHT, ROTATE_RIGHT, MOVE, MOVE, ROTATE_LEFT, ROTATE_LEFT);
    }

    @Test
    void writeActionFromNotValidStream() {
        assertThat(Action.writeActionFromStream(new ByteArrayInputStream("Not Action".getBytes()))).isEmpty();
    }
}
