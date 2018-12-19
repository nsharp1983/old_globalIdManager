package com.ats.apixpdq.api;

import java.util.List;

import com.ats.aexchange.datamodel.MessageHeader;
import com.ats.aempi.ApplicationException;
import com.ats.aempi.model.ExtendForPerson;

/**
 *  PdqSupplierAdapter是一接口,为 PdSupplier提供病人数据源,实现PDQ病人的查询.
 *
 * @author yrh-2012-04-10
 */
public interface IPdSupplierAdapter extends IPixPdqAdapter{

	 /**
    *
    * 查询返回一个扩展字段集
    *
    * @param query the ExtendForPerson
    * @param header the <code>MessageHeader</code>
    * @throws PdSupplierException
    * @return 扩展字段集      
	 * @throws ApplicationException 
    * 
    */
	public List<ExtendForPerson> findExtendFields(int PidCount) throws PdSupplierException, ApplicationException;
	
	public List<ExtendForPerson> findAllFields() throws PdSupplierException, ApplicationException;
	
    /**
     *
     * 查询返回一个符合PDQ查询参数的病人列表
     *
     * @param query the <code>PdqQuery</code>
     * @param header the <code>MessageHeader</code>
     * @throws PdSupplierException when there is trouble finding the patients
     * @return a <code>PdqResult</code> which contains a list of list 
     *         of <code>Patient</code  The first list is a list 
     *         of different logic patients, while the second list is a list of 
     *         the same patient in different domain systems. PdqResult also 
     *         contains a continuation reference number.
     * @see PdqResult        
     * 
     */
    public PdqResult findPatients(PdqQuery query, MessageHeader header) throws PdSupplierException;
 
    /**
     * Cancels the existing PDQ Query whose reference id is given by pointer.
     * 
     * @param String the tag of query to be canceled.
     * @param messageQueryName the messageQueryName 
     * @throws PdSupplierException when there is trouble canceling the query.
     */
    public void cancelQuery(String queryTag, String messageQueryName) throws PdSupplierException;
}
