package com.coolgui.hooks;

import com.coolgui.fragments.Fragment;
import org.jetbrains.annotations.ApiStatus;

public class StateHook<T> {

    private final Fragment fragment;
    private T value;

    @ApiStatus.Internal
    public StateHook(Fragment fragment, T value) {
        this.fragment = fragment;
        this.value = value;
    }

    /**
     * Set's the state's value
     * @param value The new value
     */
    public void set(T value) {
        this.value = value;
        fragment.onStateUpdate();
    }

    /**
     * Fetches the state's value
     * @return The state's value
     */
    public T get() {
        return value;
    }
}
