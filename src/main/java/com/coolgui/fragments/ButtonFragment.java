package com.coolgui.fragments;

import com.coolgui.events.OnClickEvent;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.inventory.condition.InventoryConditionResult;
import net.minestom.server.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ButtonFragment extends Fragment {

    public static Builder builder() {
        return new Builder();
    }

    private final int slot;
    private final Supplier<ItemStack> itemStackSupplier;
    private final Consumer<OnClickEvent> onClickConsumer;

    protected ButtonFragment(int slot, Supplier<ItemStack> itemStackSupplier, Consumer<OnClickEvent> onClickConsumer) {
        this.slot = slot;
        this.itemStackSupplier = itemStackSupplier;
        this.onClickConsumer = onClickConsumer;
    }

    @Override
    public ItemStack[] getItems() {
        ItemStack[] stacks = new ItemStack[slot + 1];
        stacks[slot] = itemStackSupplier.get();
        return stacks;
    }

    @Override
    public void onClick(Player player, int slot, ClickType type, InventoryConditionResult result) {
        super.onClick(player, slot, type, result);
        if (this.slot == slot)
            onClickConsumer.accept(new OnClickEvent(player, slot, type, result));
    }

    @Override
    protected void render() {
    }

    public static class Builder {

        private int slot;
        private Supplier<ItemStack> itemStackSupplies;
        private Consumer<OnClickEvent> onClickConsumer;

        protected Builder() {
            this.slot = 0;
            this.itemStackSupplies = () -> ItemStack.AIR;
        }

        /**
         * Sets the button's slot in the window
         */
        public Builder slot(int slot) {
            this.slot = slot;
            return this;
        }

        /**
         * Sets the button's slot in the window
         * @param x X coordinate in the window
         * @param y Y coordinate in the window
         */
        public Builder slot(int x, int y) {
            this.slot = (y * 9) + x;
            return this;
        }

        /**
         * Set's the button's item
         * @param item The item which will be shown
         */
        public Builder item(Supplier<ItemStack> item) {
            this.itemStackSupplies = item;
            return this;
        }

        /**
         * Set's the button's on-click listener
         * @param onClick The event which will be run once the button is clicked
         */
        public Builder onClick(Consumer<OnClickEvent> onClick) {
            this.onClickConsumer = onClick;
            return this;
        }

        /**
         * Builds the fragment
         * @return The built fragment
         */
        public ButtonFragment build() {
            return new ButtonFragment(slot, itemStackSupplies, onClickConsumer);
        }
    }
}
