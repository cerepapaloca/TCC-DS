package co.edu.udec.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class UserData {

    private final UUID uuid;
    private final String name;
    private final String address;
    private final int telephone;

}
