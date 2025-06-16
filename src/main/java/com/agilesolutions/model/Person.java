package com.agilesolutions.model;

import lombok.Builder;

@Builder
public record Person(String name, String department) {
}
