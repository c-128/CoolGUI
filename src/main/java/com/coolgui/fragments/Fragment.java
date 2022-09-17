package com.coolgui.fragments;

import com.coolgui.hooks.Hookable;
import com.coolgui.hooks.StateHook;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.inventory.condition.InventoryConditionResult;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Fragment implements Hookable {

    private Player player;
    private InventoryType type;
    private final Set<Fragment> fragments;

    public Fragment() {
        this.fragments = new HashSet<>();
    }

    @Override
    public <T> StateHook<T> useState(Class<T> type, T value) {
        return new StateHook<>(this, value);
    }

    @Override
    @ApiStatus.Internal
    public void onStateUpdate() {
        Inventory inventory = player.getOpenInventory();
        if (inventory == null) return;
        inventory.setTitle(getTitle());
        fillInventory(inventory);
    }

    /**
     * Adds a new fragment
     * @param fragment The fragment to be added
     */
    protected void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    @ApiStatus.Internal
    public void open() {
        Inventory inventory = new Inventory(type, getTitle());

        inventory = fillInventory(inventory);
        inventory.addInventoryCondition((player1, slot, clickType, result) -> {
            result.setCancel(true);
            onClick(player1, slot, clickType, result);
        });

        player.openInventory(inventory);
    }

    @ApiStatus.Internal
    protected Inventory fillInventory(Inventory inventory) {
        fragments.clear();
        render();
        inventory.clear();
        ItemStack[] rendered = render(inventory.getSize());
        for (int i = 0; i < rendered.length; i++) {
            if (rendered[i] == null) continue;
            inventory.setItemStack(i, rendered[i]);
        }
        return inventory;
    }

    @ApiStatus.Internal
    protected ItemStack[] render(int size) {
        ItemStack[] stacks = new ItemStack[size];
        ItemStack[] localItems = getItems();
        if (localItems != null)
            for (int i = 0; i < localItems.length; i++) stacks[i] = localItems[i];

        fragments.forEach(fragment -> {
            ItemStack[] result = fragment.render(size);
            for (int i = 0; i < result.length; i++) {
                if (result[i] != null) stacks[i] = result[i];
            }
        });

        return stacks;
    }

    @ApiStatus.Internal
    protected ItemStack[] getItems() {
        return null;
    }

    @ApiStatus.Internal
    protected void onClick(Player player, int slot, ClickType type, InventoryConditionResult result) {
        Fragment[] fragmentsArray = fragments.toArray(new Fragment[0]);
        for (Fragment fragment : fragmentsArray) fragment.onClick(player, slot, type, result);
    }

    @ApiStatus.Internal
    public void setPlayer(Player player) {
        this.player = player;
    }

    @ApiStatus.Internal
    public void setType(InventoryType type) {
        this.type = type;
    }

    /**
     * Get the window's title
     */
    protected Component getTitle() {
        return Component.text("");
    }

    public Player getPlayer() {
        return player;
    }

    public InventoryType getType() {
        return type;
    }

    public Set<Fragment> getFragments() {
        return fragments;
    }

    protected abstract void render();
}
