package com.ihover.robot;

import com.ihover.Utils;

import java.awt.*;
import java.util.Objects;

import static java.text.MessageFormat.format;

public final class Robot {

    private final Rectangle room;
    private Direction direction;
    private int x;
    private int y;

    public Robot(Rectangle room, Direction direction, int x, int y) {
        this.room = room;
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    public Rectangle getRoom() {
        return room;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Robot action(final Action action) {
        Utils.println("Robot before action = " + this);
        Utils.println("Action = " + action);
        switch (action) {
            case ROTATE_RIGHT, ROTATE_LEFT -> rotate(action);
            case MOVE -> move();
        }
        Utils.println("Robot after action = " + this);
        return this;
    }

    private void rotate(Action action) {
        this.direction = this.direction.rotate(action);
    }

    private void move() {
        if (!canMove()) {
            Utils.println("Robot can not move to outside the room");
            return;
        }
        switch (direction) {
            case NORTH -> this.y++;
            case EAST -> this.x++;
            case WEST -> this.x--;
            case SOUTH -> this.y--;
        }
    }

    private boolean canMove() {
        return switch (direction) {
            case NORTH -> y < room.height;
            case EAST -> x < room.width;
            case WEST -> x > 0;
            case SOUTH -> y > 0;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return x == robot.x && y == robot.y && Objects.equals(room, robot.room) && direction == robot.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, direction, x, y);
    }

    @Override
    public String toString() {
        return format("Robot [ room = (width = {0}, height = {1}), direction = {2}, position = ({3}, {4}) ]",
                room.width,
                room.height,
                direction,
                x,
                y);
    }
}
