package com.agilesolutions.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
public record Person(String name, String department) {
}
