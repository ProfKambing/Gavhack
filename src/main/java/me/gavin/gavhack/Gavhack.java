package me.gavin.gavhack;

import me.gavin.gavhack.clickgui.impl.GUI;
import me.gavin.gavhack.clickgui.impl.HUD;
import me.gavin.gavhack.manager.FriendManager;
import me.gavin.gavhack.manager.*;
import me.gavin.gavhack.module.impl.ClickGUI;
import me.gavin.gavhack.module.impl.ColorModule;
import me.gavin.gavhack.util.ForgeEventTranslator;
import me.gavin.gavhack.util.font.CFontLoader;
import me.gavin.gavhack.util.font.CFontRenderer;
import me.gavin.quasar.EventSystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = Gavhack.MOD_ID,
        name = Gavhack.MOD_NAME,
        version = Gavhack.VERSION,
        clientSideOnly = true
)
public class Gavhack {

    public static final String MOD_ID = "gavhack";
    public static final String MOD_NAME = "Gavhack";
    public static final String VERSION = "v1.0";

    public static String PREFIX = "-";

    @Mod.Instance(MOD_ID)
    public static Gavhack INSTANCE;

    public Logger logger;
    public EventSystem eventSys;
    public CFontRenderer fontRenderer;
    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public ColorManager colorManager;
    public ColorModule colorModule;
    public DiscordManager discordManager;
    public ProjectionManager projectionManager;
    public FriendManager friendManager;
    public GUI gui;
    public HUD hudEditor;
    public ClickGUI clickGUI;
    public TpsManager tpsManager;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger = LogManager.getLogger(MOD_ID);

        logger.info("Starting " + MOD_NAME + " " + VERSION + "...");

        eventSys = new EventSystem();
        logger.info("Event system initialized");

        fontRenderer = new CFontRenderer(CFontLoader.MULI_SEMIBOLD, true, true);
        logger.info("Font renderer initialized");

        discordManager = new DiscordManager();
        logger.info("Discord manager initialized");

        moduleManager = new ModuleManager();
        logger.info("Module manager initialized");

        commandManager = new CommandManager();
        logger.info("Command manager initialized");

        colorManager = new ColorManager();
        logger.info("Color manager initialized");

        projectionManager = new ProjectionManager();
        logger.info("Projection manager initialized");

        gui = new GUI();
        logger.info("Click GUI initialized");

        hudEditor = new HUD();
        logger.info("HUD editor initialized");

        friendManager = new FriendManager();
        logger.info("FriendManager initialized");

        new ForgeEventTranslator();
        logger.info("Forge event translator initialized");

        tpsManager = new TpsManager();
        logger.info("TPS manager initialized");

        logger.info(Gavhack.MOD_NAME + " finished initialization");

        discordManager.startRPC();
    }
}
