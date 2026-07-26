package com.coopermor.hideothersbarrows;

import java.util.Set;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Renderable;
import net.runelite.api.gameval.NpcID;
import net.runelite.client.callback.RenderCallback;
import net.runelite.client.callback.RenderCallbackManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Hide Others Barrows"
)
public class HideOthersBarrowsPlugin extends Plugin implements RenderCallback
{
	@Inject
	private Client client;

	@Inject
	RenderCallbackManager renderCallbackManager;

	private static final Set<Integer> BARROWS_BROTHERS = Set.of(
		NpcID.BARROWS_AHRIM, NpcID.BARROWS_DHAROK,
		NpcID.BARROWS_GUTHAN, NpcID.BARROWS_KARIL,
		NpcID.BARROWS_TORAG, NpcID.BARROWS_VERAC
	);

	@Override
	protected void startUp() throws Exception
	{
		renderCallbackManager.register(this);
	}

	@Override
	protected void shutDown() throws Exception
	{
		renderCallbackManager.unregister(this);
	}

	@Override
	public boolean addEntity(Renderable renderable, boolean ui)
	{
		if (!(renderable instanceof NPC))
		{
			return true;
		}

		NPC npc = (NPC) renderable;

		if (!BARROWS_BROTHERS.contains(npc.getId()))
		{
			return true;
		}

		NPC hintArrowNpc = client.getHintArrowNpc();

		return hintArrowNpc != null && npc.getIndex() == hintArrowNpc.getIndex();
	}
}
