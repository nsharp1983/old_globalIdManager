package com.ats.aempi.apixpdqadapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ats.aempi.context.Context;
import com.ats.aempi.model.IdentifierDomainDict;
import com.ats.aexchange.datamodel.Identifier;
import com.ats.apixpdq.api.IPixPdqAdapter;

/**
 * 内容: CONTEXT 启动,从配置文件加载机构身份信息
 * 
 * @author yrh-2012-04-10
 *
 */
public class BasePixPdqAdapter implements IPixPdqAdapter
{
	protected final Log log = LogFactory.getLog(getClass());
	
	String UniversalType="ISO";
	
	//初始化
	public BasePixPdqAdapter() {
		try 
		{
			Context.startup();
			
		} catch (Throwable e) 
		{
			log.error("Failed while initializing the Pix/Pdq Adapter due to: " + e, e);
		}
	}

	public Set<Identifier> getDomainIdentifiers(Set<Identifier> allIdentifiers)
	{
		Set<Identifier> idSet = new HashSet<Identifier>();
		
		for(Identifier id:allIdentifiers)
		{
			//yrh-2012-03-29
			//机构身份判别,机构类型为指定,如"ISO"						
			if(id.getUniversalId()!=null && id.getNamespaceId()!=null && id.getAuthorityNameString()!=null && id.getUniversalIdType()!=null)
			{
				if( id.getUniversalIdType().equals(UniversalType))idSet.add(id);
			}
		}
		
		log.debug("在配置文件中加载了 " + idSet.size() + "个Domain");
		
//		List<IdentifierDomainDict> domains = Context.getPersonQueryService().getIdentifierDomains();
//		
//		for (IdentifierDomainDict domain : domains)
//		{
//			if(domain.getUniversalIdentifierTypeCode()!=null && domain.getUniversalIdentifierTypeCode().equals(UniversalType))
//				
//			idSet.add(ConversionHelper.convertIdentifierDomain(domain));
//		}
//		
//		log.debug("在EMPI配置中加载了另外" + domains.size() + "个Domain，共" + idSet.size() + "个Domain");
		
		return idSet;
	}
	
	//yrh-2012-03-29
	//全局机构身份判别,机构类型为指定,如"ISO"	
	public Identifier getGlobalDomainIdentifier(Identifier globalIdentifiers) 
	{
		if(globalIdentifiers.getUniversalId()!=null && globalIdentifiers.getNamespaceId()!=null && globalIdentifiers.getAuthorityNameString()!=null && globalIdentifiers.getUniversalIdType()!=null)
		{
			if(globalIdentifiers.getUniversalIdType().equals(UniversalType))
			{
				log.debug("在配置文件中加载 GlobalDomain" + globalIdentifiers);
				
				return globalIdentifiers;
			}
		}

		return null;
	}
}
