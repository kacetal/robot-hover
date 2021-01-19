package com.ihover;

import com.ihover.robot.Direction;
import com.ihover.robot.Robot;

import java.awt.Rectangle;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.ToIntBiFunction;

import static com.ihover.robot.Action.writeActionFromStream;
import static java.text.MessageFormat.format;

public class RobotControl {

    public static void main(String[] args) {
        Utils.println("Welcome to the application 'IHover Robot Control'.");

        final var room = configureRoom(inputStreamIntScanner(System.in));
        final var robot = configureRobot(room, inputStreamIntScanner(System.in));

        Utils.println("Select command for Robot");

        do {
            writeActionFromStream(System.in).forEach(robot::action);
            Utils.println("Robot after command: " + robot);
            final String endMsg = """
                    Do you want to continuer?
                    1. Yes
                    2. No\
                    """;
            Utils.println(endMsg);
        } while (inputStreamIntScanner(System.in).applyAsInt(1, 2) == 1);
    }

    private static ToIntBiFunction<Integer, Integer> inputStreamIntScanner(InputStream inputStream) {
        return (min, max) -> {
            final String selectMsg = format("Select number from {0} to {1} : ", min, max);
            Utils.print(selectMsg);
            final Scanner scanner = new Scanner(inputStream);
            while (true) {
                if (!scanner.hasNextInt()) {
                    scanner.next();
                    Utils.print(selectMsg);
                    continue;
                }
                final int result = scanner.nextInt();
                if (result < min || result > max) {
                    Utils.print(selectMsg);
                    continue;
                }
                return result;
            }
        };
    }

    private static Rectangle configureRoom(final ToIntBiFunction<Integer, Integer> sc) {
        final int minHeight = 1;
        final int maxHeight = Integer.MAX_VALUE;
        final int minWidth = 1;
        final int maxWidth = Integer.MAX_VALUE;

        String welcomeMessage = """
                Configure your room dimension:
                Min Height : %d
                Max Height : %d
                Min Width  : %d
                Max Width  : %d
                """.formatted(minHeight, maxHeight, minWidth, maxWidth);
        Utils.print(welcomeMessage);

        Utils.println("Select room height");
        final int roomHeight = sc.applyAsInt(minHeight, maxHeight);
        Utils.println("Height selected: " + roomHeight);

        Utils.println("Select room width");
        final int roomWidth = sc.applyAsInt(minWidth, maxWidth);
        Utils.println("Width selected: " + roomWidth);

        final var room = new Rectangle(roomWidth, roomHeight);
        Utils.println("Your room's dimension : height = " + room.height + " width = " + room.width);
        return room;
    }

    private static Robot configureRobot(Rectangle room, ToIntBiFunction<Integer, Integer> sc) {
        Utils.println("Select position initial:");

        Utils.println("Select position X");
        final int posX = sc.applyAsInt(0, room.width);
        Utils.println("Position X selected: " + posX);

        Utils.println("Select position Y");
        final int posY = sc.applyAsInt(0, room.height);
        Utils.println("Position Y selected: " + posY);

        final var directions = Direction.values();
        final String formatted = """
                Select direction initial:
                1. %s
                2. %s
                3. %s
                4. %s
                """.formatted((Object[]) directions);
        Utils.print(formatted);

        final var direction = directions[sc.applyAsInt(1, directions.length) - 1];
        Utils.println("Direction initial is: " + direction);

        final var robot = new Robot(room, direction, posX, posY);
        Utils.println("Robot created : " + robot);
        return robot;
    }
}
