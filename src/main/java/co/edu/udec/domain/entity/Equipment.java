package co.edu.udec.domain.entity;

import java.util.UUID;

public record Equipment(UUID uuid, String model, String name, long price) {

}
