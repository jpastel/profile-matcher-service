package com.gameloft.profilematcher.data.entities;

public record Device(Long id,
                     String model,
                     String carrier,
                     String firmware) {
}
