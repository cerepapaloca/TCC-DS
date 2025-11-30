package co.edu.udec.domain.entity;

import java.util.UUID;

public record FinishedProducts(UUID uuid, String name, String description, long cost, long price, long dateEntry) {

}
