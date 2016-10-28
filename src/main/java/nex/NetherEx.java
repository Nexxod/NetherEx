/*
 * Copyright (C) 2016.  LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nex;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nex.handler.IMCHandler;
import nex.init.NetherExBiomes;
import nex.init.NetherExRecipes;
import nex.proxy.IProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = NetherEx.MOD_ID, name = NetherEx.NAME, version = NetherEx.VERSION, guiFactory = NetherEx.GUI_FACTORY, updateJSON = NetherEx.UPDATE_JSON, dependencies = NetherEx.DEPEND)
public class NetherEx
{
    public static final String MOD_ID = "nex";
    public static final String NAME = "NetherEx";
    public static final String VERSION = "@VERSION@";
    public static final String GUI_FACTORY = "nex.client.gui.GuiFactory";
    public static final String UPDATE_JSON = "https://gist.github.com/LogicTechCorp/0274bc72f4359c497d490c29c1ced425";
    public static final String DEPEND = "required-after:Forge@[1.10.2-12.18.2.2114,);";
    private static final String CLIENT_PROXY = "nex.proxy.CombinedClientProxy";
    private static final String SERVER_PROXY = "nex.proxy.DedicatedServerProxy";

    @Mod.Instance(MOD_ID)
    public static NetherEx instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static IProxy proxy;

    public static final CreativeTabs CREATIVE_TAB = new NetherExCreativeTab();

    private static final Logger LOGGER = LogManager.getLogger("NetherEx");

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event)
    {
        LOGGER.info("PreInitialization started.");

        NetherExBiomes.replaceNether();
        proxy.preInit();

        LOGGER.info("PreInitialization ended.");
    }

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event)
    {
        LOGGER.info("Initialization started.");

        NetherExRecipes.init();
        proxy.init();

        LOGGER.info("Initialization ended.");
    }

    @Mod.EventHandler
    public void onInterModCommunication(FMLInterModComms.IMCEvent event)
    {
        LOGGER.info("Inter Mod Compatibility started.");

        IMCHandler.routeMessages(event);

        LOGGER.info("Inter Mod Compatibility ended.");
    }

    @Mod.EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event)
    {
        LOGGER.info("PostInitialization started.");

        proxy.postInit();

        LOGGER.info("PostInitialization ended.");
    }
}