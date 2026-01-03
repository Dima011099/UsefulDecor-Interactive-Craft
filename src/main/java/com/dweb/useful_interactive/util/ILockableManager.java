package com.dweb.useful_interactive.util;

public interface ILockableManager {
    boolean isLocked();
    boolean tryUnlock(String key);
    void bindKey(String key);
    boolean hasKey();
}
