package com.coolgui.gui;

import com.coolgui.fragments.Fragment;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;

public class CoolGUI {

    /**
     * Opens a window for the specified player.
     * @param player The player
     * @param type Which window type will be opened
     * @param fragment The parent fragment
     */
    public static void open(Player player, InventoryType type, Fragment fragment) {
        fragment.setPlayer(player);
        fragment.setType(type);

        fragment.open();
    }
}
