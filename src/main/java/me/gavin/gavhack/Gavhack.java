package me.gavin.gavhack;

import me.gavin.gavhack.manager.DiscordManager;
import me.gavin.gavhack.manager.ModuleManager;
import me.gavin.gavhack.util.ForgeEventTranslator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import me.gavin.quasar.EventSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Gavhack.MOD_ID,
        name = Gavhack.MOD_NAME,
        version = Gavhack.VERSION,
        clientSideOnly = true
)
public class Gavhack {

    public static final String MOD_ID = "gavhack";
    public static final String MOD_NAME = "Gavhack";
    public static final String VERSION = "1.0";

    @Mod.Instance(MOD_ID)
    public static Gavhack INSTANCE;

    public Logger logger;
    public EventSystem eventSys;
    public ModuleManager moduleManager;
    public DiscordManager discordManager;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger = LogManager.getLogger(MOD_ID);

        logger.info("Starting " + MOD_NAME + " " + VERSION + "...");

        eventSys = new EventSystem();
        logger.info("Event system initialized");

        new ForgeEventTranslator();
        logger.info("Forge event translator initialized");

        discordManager = new DiscordManager();
        logger.info("Discord manager initialized");

        moduleManager = new ModuleManager();
        logger.info("Module manager initialized");

        logger.info(Gavhack.MOD_NAME + " finished initialization");

        discordManager.startRPC();
    }
}