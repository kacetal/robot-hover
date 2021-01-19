package com.ihover.robot;

public enum Direction {
    NORTH {
        @Override
        public Direction rotate(Action action) {
            return switch (action) {
                case ROTATE_RIGHT -> EAST;
                case ROTATE_LEFT -> WEST;
                case MOVE -> this;
            };
        }
    },
    EAST {
        @Override
        public Direction rotate(Action action) {
            return switch (action) {
                case ROTATE_RIGHT -> SOUTH;
                case ROTATE_LEFT -> NORTH;
                case MOVE -> this;
            };
        }
    },
    WEST {
        @Override
        public Direction rotate(Action action) {
            return switch (action) {
                case ROTATE_RIGHT -> NORTH;
                case ROTATE_LEFT -> SOUTH;
                case MOVE -> this;
            };
        }
    },
    SOUTH {
        @Override
        public Direction rotate(Action action) {
            return switch (action) {
                case ROTATE_RIGHT -> WEST;
                case ROTATE_LEFT -> EAST;
                case MOVE -> this;
            };
        }
    };

    public abstract Direction rotate(Action action);
}
