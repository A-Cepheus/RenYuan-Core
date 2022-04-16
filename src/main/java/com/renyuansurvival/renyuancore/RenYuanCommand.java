package com.renyuansurvival.renyuancore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RenYuanCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")){
            sender.sendMessage( RenYuanCore.getPrefix() + "任渊生存服务端-帮助" );
            sender.sendMessage( RenYuanCore.getPrefix() + "/RenYuanCore help - 获取帮助" );
            sender.sendMessage( RenYuanCore.getPrefix() + "/RenYuanCore reload config - 重载配置文件" );
            sender.sendMessage( RenYuanCore.getPrefix() + "/RenYuanCore reload all - 重载插件" );
        }else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("renyuancore.reload")){
            if(args[1].equalsIgnoreCase("config")){
                RenYuanCore.reloadPluginConfig();
                sender.sendMessage(RenYuanCore.getPrefix() + "配置文件已经重载(仅部分功能重载)");
            }else if(args[1].equalsIgnoreCase("all")) {
                RenYuanCore.reloadPlugin();
                sender.sendMessage(RenYuanCore.getPrefix() + "插件已经完成重载");
                sender.sendMessage(RenYuanCore.getPrefix() + "这个功能仍未完成,仅能开启部分未开启的模块,也不受支持");
                sender.sendMessage(RenYuanCore.getPrefix() + "如需要彻底重载请重启服务器");
            }
        }else{
            sender.sendMessage( RenYuanCore.getPrefix() + "请使用/RenYuanCore help获取帮助");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1){
            List<String> list = new ArrayList<>();
            list.add("help");
            list.add("reload");
            return list;
        }else if (args.length == 2 && args[0].equalsIgnoreCase("reload")) {
            List<String> list = new ArrayList<>();
            list.add("config");
            list.add("all");
            return list;
        }
        return null;
    }
}