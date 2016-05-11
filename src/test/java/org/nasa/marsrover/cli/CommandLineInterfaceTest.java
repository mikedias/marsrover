package org.nasa.marsrover.cli;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mike Dias
 */
public class CommandLineInterfaceTest {

    @Test
    public void goldenTest() {

        String goldenInput = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n";

        ByteArrayInputStream bais = new ByteArrayInputStream(goldenInput.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        CommandLineInterface.processInput(bais, new PrintStream(baos));

        assertThat(new String(baos.toByteArray()), is("1 3 N\n5 1 E\n"));

    }

}