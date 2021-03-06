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
package net.daboross.bukkitdev.skywars.commands.setupstuff;

import lombok.AllArgsConstructor;
import net.daboross.bukkitdev.commandexecutorbase.ColorList;
import net.daboross.bukkitdev.commandexecutorbase.CommandFilter;
import net.daboross.bukkitdev.commandexecutorbase.CommandPreCondition;
import net.daboross.bukkitdev.commandexecutorbase.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@AllArgsConstructor
public class StartedArenaCondition implements CommandPreCondition, CommandFilter {

    private final SetupStates states;
    private final boolean started;

    @Override
    public boolean canContinue(CommandSender sender, SubCommand subCommand) {
        if ((states.getSetupState(sender.getName()) == null) == started) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canContinue(CommandSender sender, Command baseCommand, SubCommand subCommand, String baseCommandLabel, String subCommandLabel, String[] subCommandArgs) {
        if ((states.getSetupState(sender.getName()) == null) == started) {
            return false;
        }
        return true;
    }

    @Override
    public String[] getDeniedMessage(CommandSender sender, Command baseCommand, SubCommand subCommand, String baseCommandLabel, String subCommandLabel, String[] subCommandArgs) {
        if (states.getSetupState(sender.getName()) == null) {
            return new String[]{ColorList.ERR + "You haven't started creating an arena yet"};
        } else {
            return new String[]{ColorList.ERR + "You already started creating an arena"};
        }
    }
}
