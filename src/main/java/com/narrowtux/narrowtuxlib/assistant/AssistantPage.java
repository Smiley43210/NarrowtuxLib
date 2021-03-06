/*
 * Copyright (C) 2011 Moritz Schmale <narrow.m@gmail.com>
 *
 * NarrowtuxLib is free software: you can redistribute it and/or modify
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */

package com.narrowtux.narrowtuxlib.assistant;

import org.bukkit.ChatColor;

public class AssistantPage {
	private String title = "Generic Page";
	private String text = "Enter some weird text here.";
	private Assistant assistant = null;
	public AssistantPage(Assistant assistant){
		this.assistant = assistant;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param assistant the assistant to set
	 */
	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}

	/**
	 * @return the assistant
	 */
	public Assistant getAssistant() {
		return assistant;
	}

	/**
	 * Will be called when this is the current page and the player types something in chat
	 * Override this method to provide your own handling.
	 * @param text the text the player typed into chat
	 * @return the Action
	 * @see AssistantAction
	 */
	public AssistantAction onPageInput(String text){
		assistant.sendMessage(assistant.formatLine(ChatColor.YELLOW+"You: "+ChatColor.WHITE+text));
		return AssistantAction.CONTINUE;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/*
	 * Page actions
	 */
	/**
	 * Shows the title and text of the page. Will be called whenever this has become the current page.
	 */
	public void play(){
		getAssistant().render(this);
		String message = "";
		if(!getTitle().equals("")){
			message += assistant.getSeparator()+"\n";
			message += assistant.formatLine(getTitle())+"\n";
		}
		message += assistant.getSeparator()+"\n";
		if(!getText().equals("")){
			for(String line:getText().split("\n")){
				message += assistant.formatLine(line)+"\n";
			}
			message += assistant.getSeparator();
		}
		assistant.sendMessage(message);
		
	}

	/**
	 * Sends a message to the player.
	 * Multiple lines will be sent seperately.
	 * @param text the text to send.
	 */
	public void sendMessage(String text){
		getAssistant().sendMessage(text);
	}
}
