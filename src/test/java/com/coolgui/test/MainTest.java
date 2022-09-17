package com.coolgui.test;

import com.coolgui.fragments.ButtonFragment;
import com.coolgui.fragments.Fragment;
import com.coolgui.gui.CoolGUI;
import com.coolgui.hooks.StateHook;
import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class MainTest {

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        InstanceContainer instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer();
        instanceContainer.setGenerator(unit ->
                unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
        });

        MinecraftServer.getCommandManager().register(new Command("gui") {{
            setDefaultExecutor((sender, context) -> {
                CoolGUI.open(sender.asPlayer(), InventoryType.CHEST_6_ROW, new ExampleFragment());
            });
        }});

        minecraftServer.start("0.0.0.0", 25565);
    }
}