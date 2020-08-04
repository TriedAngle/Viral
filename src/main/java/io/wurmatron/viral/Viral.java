package io.wurmatron.viral;
import io.wurmatron.viral.common.references.Global;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Global.MODID)
public class Viral {


  public static final Logger LOGGER = LogManager.getLogger(Global.MODID);

  public Viral() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(FMLCommonSetupEvent event) {
    LOGGER.info("Setting up Viral");
  }

  private void doClientStuff(FMLClientSetupEvent event) {

  }

  @SubscribeEvent
  public void onServerStarting(FMLServerStartedEvent event) {
    LOGGER.info("Hello from Viral");
  }
}
