package com.dweb.useful_interactive.common;

public final class LockComponent {

    private String boundKey;
    private boolean locked = false;

    public boolean hasKey() {
        return boundKey != null;
    }

    public void bindKey(String key) {
        if (boundKey == null) {
            boundKey = key;
        }
    }

    public boolean tryUnlock(String key) {
        if (boundKey == null) return false;
        if (!boundKey.equals(key)) return false;

        locked = !locked;
        return true;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public String getKey() {
        return boundKey;
    }
}