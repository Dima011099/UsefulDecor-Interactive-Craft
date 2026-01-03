package com.dweb.useful_interactive.items;

import java.util.UUID;

public final class UniqueIdUtil {
    public static String generate32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}