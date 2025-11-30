package co.edu.udec.model;

import java.util.UUID;

public record Equipment(UUID uuid, String model, String name, String displacement, long price) {

}
