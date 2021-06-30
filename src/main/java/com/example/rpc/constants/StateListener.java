package com.example.rpc.constants;

/**
 * @author Don
 * @date 2021/6/30.
 */
public interface StateListener {
    int DISCONNECTED = 0;

    int CONNECTED = 1;

    int RECONNECTED = 2;

    void stateChanged(int connected);
}
