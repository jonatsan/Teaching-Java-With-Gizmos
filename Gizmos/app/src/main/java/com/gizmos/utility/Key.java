package com.gizmos.utility;

public enum Key {
    W(87), A(65), S(83), D(68), SPACE(32);

    public int keycode;

    Key(int keycode) {
        this.keycode = keycode;
    }
}
