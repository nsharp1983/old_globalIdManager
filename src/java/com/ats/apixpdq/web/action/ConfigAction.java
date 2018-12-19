package com.ats.apixpdq.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.apixpdq.web.servlet.PixPdqConfigurationLoader;
import com.ats.apixpdq.web.beans.ConfigBean;

public class ConfigAction extends AtsAction {

	private static final long serialVersionUID = 1L;
	
	private String action;
	private String configFile;
	private String logfile;
	private String[] actors;

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the actors.
	 */
	public String[] getActors() {
		return actors;
	}

	/**
	 * @param actors The actors to set.
	 */
	public void setActors(String[] actors) {
		this.actors = actors;
	}

	/**
	 * @return Returns the configFile.
	 */
	public String getConfigFile() {
		return configFile;
	}

	/**
	 * @param configFile The configFile to set.
	 */
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	/**
	 * @return Returns the logfile.
	 */
	public String getLogfile() {
		return logfile;
	}

	/**
	 * @param logfile The logfile to set.
	 */
	public void setLogfile(String logfile) {
		this.logfile = logfile;
	}

	public String execute() throws Exception {
		
		ConfigBean cb = new ConfigBean();
		cb.setConfigFile(configFile);
		cb.setLogfile(logfile);
		cb.setAction(action);
		cb.setActors(actors);
		
		try {
			if (cb.getAction().equalsIgnoreCase(""))
			{
				List<IActorDescription> l = (ArrayList<IActorDescription>) PixPdqConfigurationLoader.getInstance().getActorDescriptions();
				
				Collections.sort(l, new compareTypes());
				request.setAttribute("ActorList", l);
				List aList = new LinkedList();
				String[] sList = new String[l.size()];
				int x = 0;
				for (IActorDescription ida : l) {
					if (ida.isInstalled()) {
						sList[x++] = ida.getName();
					}
				}
				cb.setActors(sList);
				request.setAttribute("ConfiBean", cb);
				return "success";
			}
		
			if (cb.getAction().equalsIgnoreCase("load")) {
				//First reset the config settings before loading
				PixPdqConfigurationLoader.getInstance().resetConfiguration(null, null);
				PixPdqConfigurationLoader.getInstance().loadConfiguration(cb.getConfigFile(), false);
				List<IActorDescription> l = (ArrayList<IActorDescription>) PixPdqConfigurationLoader.getInstance().getActorDescriptions();
				Collections.sort(l, new compareTypes());
				request.setAttribute("ActorList", l);
				List aList = new LinkedList();
				String[] sList = new String[l.size()];
				int x = 0;
				for (IActorDescription ida : l) {
					if (ida.isInstalled()) {
						sList[x++] = ida.getName();
						//aList.add(ida.getId());
					}
				}
				cb.setActors(sList);
				request.setAttribute("ConfiBean", cb);
				return "success";
			}
			
			else if (cb.getAction().equalsIgnoreCase("save")) {
				List<Object> lString = new LinkedList<Object>();
				StringBuffer selectedActors = new StringBuffer();
				for (String s : cb.getActors()) {
					if (selectedActors.length() > 0)
						selectedActors.append(",");
					selectedActors.append(s);
					lString.add(s);
				}
				String sLogFile = cb.getLogfile();
				if (sLogFile != null && !sLogFile.equals("")) {
					PixPdqConfigurationLoader.getInstance().resetConfiguration(lString, sLogFile);
				} else {
					PixPdqConfigurationLoader.getInstance().resetConfiguration(lString, null);
				}
				List<IActorDescription> l = (ArrayList<IActorDescription>) PixPdqConfigurationLoader.getInstance().getActorDescriptions();
				Collections.sort(l, new compareTypes());
				request.setAttribute("ActorList", l);
				request.setAttribute("ConfiBean", cb);
				return "success";
			}
			else if (cb.getAction().equalsIgnoreCase("stop all")) {
				PixPdqConfigurationLoader.getInstance().resetConfiguration(null, null);
				List<IActorDescription> l = (ArrayList<IActorDescription>) PixPdqConfigurationLoader.getInstance().getActorDescriptions();
				Collections.sort(l, new compareTypes());
				request.setAttribute("ActorList", l);
				cb.setActors(null);
				request.setAttribute("ConfiBean", cb);
				return "success";
			}

		} catch (Exception e) {
			return null;
		}
		return "success";
	}
	
	public String Config() throws Exception
	{
		ConfigBean cb = new ConfigBean();
		cb.setConfigFile(configFile);
		cb.setLogfile(logfile);
		cb.setAction(action);
		cb.setActors(actors);
		
		try {
			if (cb.getAction()==null || cb.getAction().equalsIgnoreCase(""))
			{
				List<IActorDescription> l = (ArrayList<IActorDescription>) PixPdqConfigurationLoader.getInstance().getActorDescriptions();
				
				Collections.sort(l, new compareTypes());
				request.setAttribute("ActorList", l);
				List aList = new LinkedList();
				String[] sList = new String[l.size()];
				int x = 0;
				for (IActorDescription ida : l) {
					if (ida.isInstalled()) {
						sList[x++] = ida.getName();
					}
				}
				cb.setActors(sList);
				request.setAttribute("ConfiBean", cb);
				return "success";
			}
		}
		catch (Exception e)
		{
			return "error";
		}
		return "success";
	}

	private class compareTypes implements Comparator {

		public int compare(Object first, Object second) {
			try {
				IActorDescription f = (IActorDescription) first;
				IActorDescription s = (IActorDescription) second;
				return f.getType().compareToIgnoreCase(s.getType());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

}
