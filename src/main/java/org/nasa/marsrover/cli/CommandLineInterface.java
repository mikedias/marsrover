package org.nasa.marsrover.cli;

import org.nasa.marsrover.Direction;
import org.nasa.marsrover.Field;
import org.nasa.marsrover.Rover;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Mike Dias
 */
public class CommandLineInterface {

    public static void main(String args[]) {

        System.out.println(
            "  __  __                  _____                     \n" +
            " |  \\/  |                |  __ \\                    \n" +
            " | \\  / | __ _ _ __ ___  | |__) |_____   _____ _ __ \n" +
            " | |\\/| |/ _` | '__/ __| |  _  // _ \\ \\ / / _ \\ '__|\n" +
            " | |  | | (_| | |  \\__ \\ | | \\ \\ (_) \\ V /  __/ |   \n" +
            " |_|  |_|\\__,_|_|  |___/ |_|  \\_\\___/ \\_/ \\___|_|   \n" +
            "                                                    \n" +
            " ---------------------------------------------------\n" +
            " Input Example (with comments): \n\n" +
            "   5 5        (Field dimensions) \n" +
            "   1 2 N      (Rover initial position and direction) \n" +
            "   LMLMLMLMM  (Move commands) \n" +
            "   3 3 E      (Another Rover...) \n" +
            "   LMLMLMLMM  (More Move commands...) \n\n" +
            " Go ahead and try yourself:"
        );

        processInput(System.in, System.out);

    }

    public static void processInput(InputStream in, PrintStream out) {
        Scanner sc = new Scanner(in);

        int width = sc.nextInt();
        int height = sc.nextInt();

        Field f = new Field(width, height);

        while (sc.hasNext()) {

            int x = sc.nextInt();
            int y = sc.nextInt();
            Direction d = Direction.valueOf(sc.next());

            Rover r = new Rover(f, x, y, d);

            for (char cmd : sc.next().toCharArray()) {
                r.performCmd(cmd);
            }

            out.println(r.getX() + " " + r.getY() + " " + r.getDirection().name());

        }
    }
}
