package com.ihover.robot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static com.ihover.robot.Action.*;
import static com.ihover.robot.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void rotateRightNorth() {
        assertThat(NORTH.rotate(ROTATE_RIGHT)).isEqualTo(EAST);
    }

    @Test
    void rotateLeftNorth() {
        assertThat(NORTH.rotate(ROTATE_LEFT)).isEqualTo(WEST);
    }

    @Test
    void rotateMoveNorth() {
        assertThat(NORTH.rotate(MOVE)).isEqualTo(NORTH);
    }

    @Test
    void rotateRightSouth() {
        assertThat(SOUTH.rotate(ROTATE_RIGHT)).isEqualTo(WEST);
    }

    @Test
    void rotateLeftSouth() {
        assertThat(SOUTH.rotate(ROTATE_LEFT)).isEqualTo(EAST);
    }

    @Test
    void rotateMoveSouth() {
        assertThat(SOUTH.rotate(MOVE)).isEqualTo(SOUTH);
    }

    @Test
    void rotateRightWest() {
        assertThat(WEST.rotate(ROTATE_RIGHT)).isEqualTo(NORTH);
    }

    @Test
    void rotateLeftWest() {
        assertThat(WEST.rotate(ROTATE_LEFT)).isEqualTo(SOUTH);
    }

    @Test
    void rotateMoveWest() {
        assertThat(WEST.rotate(MOVE)).isEqualTo(WEST);
    }

    @Test
    void rotateRightEast() {
        assertThat(EAST.rotate(ROTATE_RIGHT)).isEqualTo(SOUTH);
    }

    @Test
    void rotateLeftEast() {
        assertThat(EAST.rotate(ROTATE_LEFT)).isEqualTo(NORTH);
    }

    @Test
    void rotateMoveEast() {
        assertThat(EAST.rotate(MOVE)).isEqualTo(EAST);
    }
}
