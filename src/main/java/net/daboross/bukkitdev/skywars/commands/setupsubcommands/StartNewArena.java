/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.skywars.commands.setupsubcommands;

import java.io.File;
import lombok.NonNull;
import net.daboross.bukkitdev.commandexecutorbase.SubCommand;
import net.daboross.bukkitdev.commandexecutorbase.filters.ArgumentFilter;
import net.daboross.bukkitdev.skywars.api.SkyWars;
import net.daboross.bukkitdev.skywars.api.translations.SkyTrans;
import net.daboross.bukkitdev.skywars.api.translations.TransKey;
import net.daboross.bukkitdev.skywars.commands.setupstuff.SetupData;
import net.daboross.bukkitdev.skywars.commands.setupstuff.SetupStates;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class StartNewArena extends SubCommand {

    private final SkyWars plugin;
    private final SetupStates states;

    public StartNewArena(@NonNull SkyWars plugin, @NonNull SetupStates states) {
        super("start", false, null, SkyTrans.get(TransKey.SWS_START_DESCRIPTION));
        addArgumentNames(SkyTrans.get(TransKey.SWS_START_NAME_ARGUMENT));
        addCommandFilter(new ArgumentFilter(ArgumentFilter.ArgumentCondition.EQUALS, 1, SkyTrans.get(TransKey.SWS_START_ONE_ARGUMENT)));
        this.plugin = plugin;
        this.states = states;
    }

    @Override
    public void runCommand(CommandSender sender, Command baseCommand, String baseCommandLabel, String subCommandLabel, String[] subCommandArgs) {
        SetupData state = new SetupData();
        state.setArenaName(subCommandArgs[0]);
        state.setSaveFile(new File(plugin.getConfiguration().getArenaFolder(), subCommandArgs[0] + ".yml"));
        states.setSetupState(sender.getName(), state);
        sender.sendMessage(SkyTrans.get(TransKey.SWS_START_CONFIRMATION, subCommandArgs[0]));
    }
}
