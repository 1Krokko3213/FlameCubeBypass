import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketJoinGame;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraft.util.text.ITextComponent;
import ru.bareapi.botclient.bots.Bot;
import ru.bareapi.botclient.pluginsystem.IPlugin;
import ru.bareclient.CommandManagerRU;
import ru.bareclient.helpers.ChatUtils;

public class FlameCubeBypass implements IPlugin {
	
	public static CopyOnWriteArrayList<ChestBypassBot> chestBots = new CopyOnWriteArrayList<>();

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "1Krokko3213";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Обход инвентарь клик";
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "FlameCubeBypass";
	}

	@Override
	public void onBotBossBar(SPacketUpdateBossInfo arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotDisconnected(ITextComponent arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotJoinedWorld(SPacketJoinGame arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotOpenWindow(String arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotOpenWindowTextComponent(ITextComponent arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotPacketReceive(Packet<?> arg0, Bot arg1) {

	    if (arg0 instanceof SPacketOpenWindow) {

	        SPacketOpenWindow packet = (SPacketOpenWindow) arg0;

	        String windowTitle = ChatUtils.removeFormatting(
	                packet.getWindowTitle().getFormattedText()
	        ).toLowerCase();


	        ChestBypassBot chestBot = new ChestBypassBot(arg1, windowTitle);
	        chestBots.add(chestBot);
	    }

	    if (arg0 instanceof SPacketWindowItems) {

	        Iterator<ChestBypassBot> iterator = chestBots.iterator();

	        while (iterator.hasNext()) {

	            ChestBypassBot chestBot = iterator.next();

	            if (chestBot.bot != arg1)
	                continue;

	            if (!((arg1.getBot()).openContainer instanceof ContainerChest))
	                continue;

	            ContainerChest chest =
	                    (ContainerChest)(arg1.getBot()).openContainer;

	            if (chest == null || chest.inventorySlots == null)
	                continue;

	            String target = chestBot.findItem.toLowerCase();

	            for (int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); i++) {

	                ItemStack stack =
	                        chest.getLowerChestInventory().getStackInSlot(i);

	                if (stack.isEmpty())
	                    continue;

	                String itemName = ChatUtils.removeFormatting(
	                        stack.getDisplayName()
	                ).toLowerCase();

	                if (itemName.equals(target)) {

	                    CommandManagerRU.showMessageWithPrefix(
	                            "&a&l[&r&2" + arg1.getBotName() + "] &aFOUND -> &f" + itemName
	                    );

	                    arg1.actions.clickWindow(
	                            ((Slot) chest.inventorySlots.get(i)).slotNumber,
	                            (arg1.getBot()).openContainer.windowId
	                    );

	                    chestBots.remove(chestBot);
	                    return;
	                }
	            }
	        }
	    }
	}
	  public class ChestBypassBot {
		    public Bot bot;
		    
		    public String findItem;
		    
		    public ChestBypassBot(Bot bot, String findItem) {
		      this.bot = bot;
		      this.findItem = findItem;
		    }
		  }

	@Override
	public void onBotPacketSent(Packet<?> arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotScreenSubTitle(String arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotScreenTitle(String arg0, Bot arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotStringPool(String arg0, String arg1, Bot arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBotWindowItems(SPacketWindowItems packet, Bot bot) {
		
	}

	@Override
	public void onClientDisconnected(ITextComponent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCommand(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPacketRecieve(Packet<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPacketSent(Packet<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRender2D() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRender3D() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
