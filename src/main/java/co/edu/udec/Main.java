package co.edu.udec;

import co.edu.udec.command.CommandHandler;
import co.edu.udec.command.commands.*;
import co.edu.udec.storage.sql.*;
import lombok.Getter;
import lombok.Setter;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Arrays;
public class Main {

    @Getter
    private static final CommandHandler commandHandler = new CommandHandler();
    @Setter
    @Getter
    private static boolean isRunning = true;

    static{
        commandHandler.registerCommand(
                new HelpCommand(),
                new ExitCommand(),
                new ShowCommand(),
                new AddCommand(),
                new DeleteCommand()
        );
    }

    public static void main(String[] a) throws IOException {
        Terminal terminal = TerminalBuilder.builder().system(true).build();
        Service.sendMessage("Bienvenido. Usa help para ver todos los comandos\n");

        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .build();

        while (isRunning) {
            String line;
            try {
                line = reader.readLine("> ");
                String[] args = line.split(" ");
                try {
                    String repose = commandHandler.onCommand(args[0], Arrays.copyOfRange(args, 1, args.length));
                    if (repose != null) {
                        System.out.println(repose);
                    }
                } catch (Exception e) {
                    Service.sendMessage("Error al ejecutar el comando: " + args[0], e);
               }
            } catch (UserInterruptException | EndOfFileException e) {
                break;
            }
        }
    }
}