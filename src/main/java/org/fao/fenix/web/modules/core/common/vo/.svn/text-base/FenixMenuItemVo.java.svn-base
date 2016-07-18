package org.fao.fenix.web.modules.core.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FenixMenuItemVo implements IsSerializable {

	private String name;
	
	private int level;
	
	private String parent;
	
	private boolean children;
	
	private String command;
	
	private String tooltip;
	
	private String iconStyle;
	
	private String description;
	
	private String category;
	
	public FenixMenuItemVo() {
		this.setTooltip("");
	}
	
	public FenixMenuItemVo(String name, int level, String parent, boolean children, String command) {
		this.setName(name);
		this.setLevel(level);
		this.setParent(parent);
		this.setChildren(children);
		this.setCommand(command);
	}
	
	public FenixMenuItemVo(String name, int level, String parent, boolean children, String tooltip, String command) {
		this.setName(name);
		this.setLevel(level);
		this.setParent(parent);
		this.setChildren(children);
		this.setCommand(command);
		this.setTooltip(tooltip);
	}
	
	public FenixMenuItemVo(String name, int level, String parent, boolean children, String tooltip, String command, String iconStyle) {
		this.setName(name);
		this.setLevel(level);
		this.setParent(parent);
		this.setChildren(children);
		this.setCommand(command);
		this.setTooltip(tooltip);
		this.setIconStyle(iconStyle);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public boolean hasChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getIconStyle() {
		return iconStyle;
	}

	public void setIconStyle(String iconStyle) {
		this.iconStyle = iconStyle;
	}

	public boolean isChildren() {
		return children;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}