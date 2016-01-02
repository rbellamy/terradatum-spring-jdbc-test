package com.terradatum;

import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.scriptsupport.CLI;
import org.jboss.as.process.protocol.StreamUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by rbellamy on 12/28/15.
 */
public class ArquillianTestHelper {
    public static void processCliFile(File file) {
        CLI cli = CLI.newInstance();
        cli.connect("localhost", 9990, null, null);
        CommandContext commandContext = cli.getCommandContext();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (commandContext.getExitCode() == 0 && !commandContext.isTerminated() && line != null) {
                commandContext.handleSafe(line.trim());
                line = reader.readLine();
            }
        } catch (Throwable e) {
            throw new IllegalStateException("Failed to process file '" + file.getAbsolutePath() + "'", e);
        } finally {
            StreamUtils.safeClose(reader);
        }
    }
}
