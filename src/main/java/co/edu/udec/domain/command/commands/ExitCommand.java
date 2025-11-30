package co.edu.udec.domain.command.commands;

import co.edu.udec.domain.Main;
import co.edu.udec.domain.Service;
import co.edu.udec.domain.command.Arguments;
import co.edu.udec.domain.command.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", "terminas el proceso");
        addAlise("stop");
    }

    @Override
    public void execute(Arguments arg) {
        Main.setRunning(false);
        Service.sendMessage("Bye");
    }
}
