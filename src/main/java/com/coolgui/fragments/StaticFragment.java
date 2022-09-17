package com.coolgui.fragments;

import net.minestom.server.item.ItemStack;

public class StaticFragment extends Fragment {

    public static Builder builder() {
        return new Builder();
    }

    private final int slot;
    private final ItemStack stack;

    protected StaticFragment(int slot, ItemStack stack) {
        this.slot = slot;
        this.stack = stack;
    }

    @Override
    public ItemStack[] getItems() {
        ItemStack[] stacks = new ItemStack[slot + 1];
        stacks[slot] = stack;
        return stacks;
    }

    @Override
    protected void render() {
    }

    public static class Builder {

        private int slot;
        private ItemStack stack;

        protected Builder() {
            this.slot = 0;
            this.stack = ItemStack.AIR;
        }

        /**
         * Sets the fragment's slot in the window
         */
        public Builder slot(int slot) {
            this.slot = slot;
            return this;
        }

        /**
         * Sets the fragment's slot in the window
         * @param x X coordinate in the window
         * @param y Y coordinate in the window
         */
        public Builder slot(int x, int y) {
            this.slot = (y * 9) + x;
            return this;
        }

        /**
         * Set's the fragment's item
         * @param item The item which will be shown
         */
        public Builder item(ItemStack item) {
            this.stack = item;
            return this;
        }

        /**
         * Builds the fragment
         * @return The built fragment
         */
        public StaticFragment build() {
            return new StaticFragment(slot, stack);
        }
    }
}
