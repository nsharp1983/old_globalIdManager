package com.ats.aempi.configuration;

import java.util.HashMap;
import java.util.Map;

import com.ats.aempi.model.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Component extends BaseObject
{
	private static final long serialVersionUID = 6836460519679990976L;
	protected static final Log log = LogFactory.getLog(Component.class);

	private String name;
	private String description;
	private ComponentType componentType;
	
	private Map<ExtensionInterface, Extension> extensionByInterface = new HashMap<ExtensionInterface, Extension>();
	
	public Component() {
	}
	
	public Component(String name) {
		this.name = name;
	}
	
	public void addExtension(String name, String implementationKey, ExtensionInterface extensionInterface) {
		log.debug("Adding extension [" + name + "] of extension interface " + extensionInterface.extensionInterface() + " to component " + getName());
		Extension extension = new Extension(name, implementationKey, extensionInterface);
		extensionByInterface.put(extensionInterface, extension);

	}
	
	public Extension getExtensionByExtensionInterface(ExtensionInterface extensionInterface) {
		log.debug("Looking up extension interface " + extensionInterface.name() + " in component " + name);
		return extensionByInterface.get(extensionInterface);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Component))
			return false;
		Component castOther = (Component) other;
		return new EqualsBuilder().append(name, castOther.name).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}

	public enum ComponentType
	{
		BLOCKING(1, "blocking"),
		MATCHING(2, "matching"),
		FILELOADER(3, "fileloader");
		
		private final int id;
		private final String componentTypeName;
		
		ComponentType(int id, String componentTypeName) {
			this.id = id;
			this.componentTypeName = componentTypeName;
		}
		
		public int id() {
			return id;
		}
		
		public String componentTypeName() {
			return componentTypeName;
		}
	}
	
	public enum ExtensionInterface
	{
		CONFIGURATION_LOADER(1, "configurationLoader"),
		CONFIGURATION_GUI(2, "configurationGui"),
		IMPLEMENTATION(3, "implementation");
		
		private final int id;
		private final String extensionInterface;
		
		ExtensionInterface(int id, String extensionInterface) {
			this.id = id;
			this.extensionInterface = extensionInterface;
		}
		
		public int id() {
			return id;
		}
		
		public String extensionInterface() {
			return extensionInterface;
		}
	}

	public class Extension
	{
		private ExtensionInterface extensionInterface;
		private String name;
		private String implementationKey;
		
		public Extension(String name, String implementationKey, ExtensionInterface extensionInterface) {
			this.name = name;
			this.implementationKey = implementationKey;
			this.extensionInterface = extensionInterface;
		}
		
		public ExtensionInterface getExtensionInterface() {
			return extensionInterface;
		}
		public void setExtensionInterface(ExtensionInterface extensionInterface) {
			this.extensionInterface = extensionInterface;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getImplementationKey() {
			return implementationKey;
		}
		public void setImplementationKey(String implementationKey) {
			this.implementationKey = implementationKey;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append(
				"componentType", componentType).toString();
	}
}
