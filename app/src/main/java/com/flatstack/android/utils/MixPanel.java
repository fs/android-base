package com.flatstack.android.utils;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor(suppressConstructorProperties = true)
public @Value class MixPanel {
    @NotNull MixpanelAPI mixpanelAPI;

    public void track(@NotNull String what, @NotNull Object... map) {
        track(what, map(map));
    }

    void track(@NotNull String eventName, @NotNull Map<String, ?> map) {
        mixpanelAPI.track(eventName, new JSONObject(map));
    }

    @SuppressWarnings("unchecked") @NotNull
    static <T> Map<String, T> map(@NotNull Object... objects) {
        Map<String, T> ret = new HashMap<>();
        for (int i = 0; i < objects.length; i += 2) {
            ret.put((String) objects[i], (T) objects[i + 1]);
        }
        return ret;
    }
}
