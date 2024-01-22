package dev.archimedes.dtos;

import lombok.Data;

@Data
public class UpdateProfile {
    private String id;
    private String name;
    private String phone;
    private String dob;
}
