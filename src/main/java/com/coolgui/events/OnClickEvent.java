package com.coolgui.events;

import net.minestom.server.entity.Player;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.inventory.condition.InventoryConditionResult;

public class OnClickEvent extends Event {

    private final Player player;
    private final int slot;
    private final ClickType type;
    private final InventoryConditionResult result;

    public OnClickEvent(Player player, int slot, ClickType type, InventoryConditionResult result) {
        this.player = player;
        this.slot = slot;
        this.type = type;
        this.result = result;
    }

    public Player getPlayer() {
        return player;
    }

    public int getSlot() {
        return slot;
    }

    public ClickType getClickType() {
        return type;
    }

    public InventoryConditionResult getResult() {
        return result;
    }

    /**
     * Cancels the event
     */
    public void cancel() {
        result.setCancel(true);
    }

    /**
     * Uncancels the event
     */
    public void unCancel() {
        result.setCancel(false);
    }
}
