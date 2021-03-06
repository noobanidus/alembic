package com.noobanidus.alembic.commands;

import com.noobanidus.alembic.Alembic;
import com.noobanidus.alembic.AlembicConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class AlembicCommand extends CommandBase {
    @Override
    public String getName() {
        return "alembic";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.alembic.usage";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("alembic");
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new WrongUsageException(getUsage(sender));
        }

        String command = args[0].toLowerCase().trim();

        if (command.equals("count")) {
            if (!AlembicConfig.enable) {
                sender.sendMessage(new TextComponentTranslation("command.alembic.disabled"));
            } else {
                sender.sendMessage(new TextComponentTranslation("command.alembic.count", Alembic.RESEARCH_HANDLER.invalidAdvancementCount(), Alembic.RESEARCH_HANDLER.advancementCount()));
            }
        }
    }
}

