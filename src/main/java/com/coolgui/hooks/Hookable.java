package com.coolgui.hooks;

public interface Hookable {

    /**
     * Creates a new state-hook
     * @param type Which type of data will the hook store
     * @param value What will be the default value
     * @return A new hook
     * @param <T> Data type
     */
    <T> StateHook<T> useState(Class<T> type, T value);

    /**
     * Triggered when a state updates
     */
    void onStateUpdate();
}
