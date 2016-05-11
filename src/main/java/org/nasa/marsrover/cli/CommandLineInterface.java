package org.nasa.marsrover.cli;

import org.nasa.marsrover.Direction;
import org.nasa.marsrover.Field;
import org.nasa.marsrover.Rover;

import java.util.Scanner;

/**
 * @author Mike Dias
 */
public class CommandLineInterface {

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);

        int width = in.nextInt();
        int height = in.nextInt();

        Field f = new Field(width, height);

        while (true) {

            int x = in.nextInt();
            int y = in.nextInt();
            Direction d = Direction.valueOf(in.next());

            Rover r = new Rover(f, x, y, d);

            for (char cmd : in.next().toCharArray()) {
                switch (cmd) {
                    case 'L': r.turnLeft(); break;
                    case 'R': r.turnRight(); break;
                    case 'M': r.move(); break;
                    default: // invalid command. ignoring
                }
            }

            System.out.println(r.getX() + " " + r.getY() + " " + r.getDirection().name());

        }

    }
}
