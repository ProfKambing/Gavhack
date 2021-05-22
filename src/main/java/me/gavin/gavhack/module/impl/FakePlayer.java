package me.gavin.gavhack.module.impl;

import com.mojang.authlib.GameProfile;
import me.gavin.gavhack.module.Category;
import me.gavin.gavhack.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

public class FakePlayer extends Module {
    public FakePlayer() {
        super("FakePlayer", "a fake player", Category.Player);
    }

    private EntityOtherPlayerMP fakePlayer;

    @Override
    public void onEnable() {
        if (mc.world != null) {
            fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), "Notch"));
            fakePlayer.copyLocationAndAnglesFrom(mc.player);
            mc.world.addEntityToWorld(-99, fakePlayer);
        }
    }

    @Override
    public void onDisable() {
        if (mc.world != null && fakePlayer != null) {
            mc.world.removeEntity(fakePlayer);
            fakePlayer = null;
        }
    }
}
