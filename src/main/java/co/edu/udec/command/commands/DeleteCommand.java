package co.edu.udec.command.commands;

import co.edu.udec.Main;
import co.edu.udec.Service;
import co.edu.udec.command.Arguments;
import co.edu.udec.command.Command;

import java.util.UUID;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super("delete", "borras una fila en la base de datos.");
    }

    @Override
    public void execute(Arguments arg) {
        switch (arg.get(0).toLowerCase()){
            case "catalogo" -> {
                if (arg.length() >= 2){
                    Main.CATALOG_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "carro" -> {
                if (arg.length() >= 2){
                    Main.INVENTORY_CAR_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "pieces" -> {
                if (arg.length() >= 2){
                    Main.INVENTORY_PIECES_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "cliente" -> {
                if (arg.length() >= 2){
                    Main.CLIENT_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "vendedor" -> {
                if (arg.length() >= 2){
                    Main.SELLER_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            case "log" -> {
                if (arg.length() >= 2){
                    Main.SELL_LOG_DATA_BASE.removeRow(arg.get(1));
                }else {
                    Service.sendMessage("Tienes que añadir la id");
                }
            }
            default -> Service.sendMessage("Usa al menos <log> <vendedor> <cliente> <pieces> <car> <catalogo>");
        }
    }
}
