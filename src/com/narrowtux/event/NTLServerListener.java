package com.narrowtux.event;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

import com.nijikokun.register.payment.Method;
import com.nijikokun.register.payment.Methods;

public class NTLServerListener extends ServerListener {
	private Methods Methods = null;
	private Method method = null;

    public NTLServerListener() {
        this.Methods = new Methods();
    }
    
	@Override
	public void onPluginDisable(PluginDisableEvent event) {
		// Check to see if the plugin thats being disabled is the one we are using
        if (this.Methods != null && this.Methods.hasMethod()) {
            Boolean check = this.Methods.checkDisabled(event.getPlugin());

            if(check) {
                method = null;
                System.out.println("[NarrowtuxLib] Payment method was disabled. No longer accepting payments.");
            }
        }
	}

	@Override
	public void onPluginEnable(PluginEnableEvent event) {
		// Check to see if we need a payment method
        if (!this.Methods.hasMethod()) {
            if(this.Methods.setMethod(event.getPlugin())) {
                // You might want to make this a public variable inside your MAIN class public Method Method = null;
                // then reference it through this.plugin.Method so that way you can use it in the rest of your plugin ;)
            	method = this.Methods.getMethod();
                System.out.println("[NarrowtuxLib] Payment method found (" + method.getName() + " version: " + method.getVersion() + ")");
            }
        }
	}
	
	public Method getMethod(){
		return method;
	}
}
