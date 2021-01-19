package com.ihover.robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.ihover.robot.Action.*;
import static com.ihover.robot.Action.MOVE;
import static com.ihover.robot.Direction.*;
import static com.ihover.robot.Direction.EAST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private Robot robot;

    private final Rectangle initRoom = new Rectangle(10, 10);

    private final Direction initDirection = NORTH;

    private final int initX = 5;

    private final int initY = 5;

    @BeforeEach
    void setUp() {
        robot = new Robot(initRoom, initDirection, initX, initY);
    }

    @Test
    void actionRight() {
        robot.action(ROTATE_RIGHT);
        assertThat(robot.getDirection()).isEqualTo(EAST);
        assertThat(robot.getX()).isEqualTo(initX);
        assertThat(robot.getY()).isEqualTo(initY);
    }

    @Test
    void actionLeft() {
        robot.action(ROTATE_LEFT);
        assertThat(robot.getDirection()).isEqualTo(WEST);
        assertThat(robot.getX()).isEqualTo(initX);
        assertThat(robot.getY()).isEqualTo(initY);
    }

    @Test
    void actionMove() {
        robot.action(MOVE);
        assertThat(robot.getDirection()).isEqualTo(NORTH);
        assertThat(robot.getX()).isEqualTo(initX);
        assertThat(robot.getY()).isEqualTo(initY + 1);
    }

    @Test
    void actionMoveOutside() {
        while (robot.getY() < robot.getRoom().height) {
            robot.action(MOVE);
        }
        assertThat(robot.getDirection()).isEqualTo(NORTH);
        assertThat(robot.getX()).isEqualTo(initX);
        assertThat(robot.getY()).isEqualTo(robot.getRoom().height);
        robot.action(MOVE);
        assertThat(robot.getY()).isEqualTo(robot.getRoom().height);
    }
}
