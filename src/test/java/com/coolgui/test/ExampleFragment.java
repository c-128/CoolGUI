package com.coolgui.test;

import com.coolgui.fragments.ButtonFragment;
import com.coolgui.fragments.Fragment;
import com.coolgui.fragments.StaticFragment;
import com.coolgui.hooks.StateHook;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ExampleFragment extends Fragment {

    StateHook<String> page = useState(String.class, "home");

    @Override
    protected void render() {
        addFragment(ButtonFragment.builder()
                .slot(0, 0)
                .item(ItemStack.builder(page.get() == "home" ? Material.GOLD_BLOCK : Material.GOLD_NUGGET).displayName(Component.text("Home")).build())
                .onClick(onClick -> {
                    page.set("home");
                })
                .build());

        addFragment(ButtonFragment.builder()
                .slot(1, 0)
                .item(ItemStack.builder(page.get() == "settings" ? Material.GOLD_BLOCK : Material.GOLD_NUGGET).displayName(Component.text("Settings")).build())
                .onClick(onClick -> {
                    page.set("settings");
                })
                .build());

        addFragment(ButtonFragment.builder()
                .slot(2, 0)
                .item(ItemStack.builder(page.get() == "statistics" ? Material.GOLD_BLOCK : Material.GOLD_NUGGET).displayName(Component.text("Statistics")).build())
                .onClick(onClick -> {
                    page.set("statistics");
                })
                .build());

        if (page.get() == "home") {
            addFragment(StaticFragment.builder()
                    .slot(0, 1)
                    .item(ItemStack.builder(Material.FISHING_ROD).displayName(Component.text("Home")).build())
                    .build());
        } else if (page.get() == "settings") {
            addFragment(StaticFragment.builder()
                    .slot(1, 1)
                    .item(ItemStack.builder(Material.ENDER_PEARL).displayName(Component.text("Settings")).build())
                    .build());

        } else if (page.get() == "statistics") {
            addFragment(StaticFragment.builder()
                    .slot(2, 1)
                    .item(ItemStack.builder(Material.RED_BED).displayName(Component.text("Statistics")).build())
                    .build());

        }
    }

    @Override
    protected Component getTitle() {
        return Component.text(page.get());
    }
}
