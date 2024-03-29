package com.ats.apixpdq.impl.v2;

import org.apache.log4j.Logger;
import com.ats.aexchange.actorconfig.Configuration;
import com.ats.aexchange.actorconfig.IActorDescription;
import com.ats.aexchange.actorconfig.IheConfigurationException;
import com.ats.aexchange.actorconfig.net.IConnectionDescription;
import com.ats.aexchange.audit.IheAuditTrail;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.Application;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.model.v25.datatype.HD;
import ca.uhn.hl7v2.model.v25.group.RSP_K21_QUERY_RESPONSE;
import ca.uhn.hl7v2.model.v25.message.QBP_Q21;
import ca.uhn.hl7v2.model.v25.message.RSP_K21;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;

import com.ats.apixpdq.api.IPdSupplier;
import com.ats.apixpdq.api.IPdSupplierAdapter;
import com.ats.apixpdq.common.PixPdqConstants;
import com.ats.apixpdq.common.HL7Actor;
import com.ats.apixpdq.impl.v2.hl7.HL7Server;
import com.ats.apixpdq.impl.v2.hl7.HL7Util;
import com.misys.hieportal.sysmon.IJMXEventNotifier;

/**
 * PDQ主服务类，主要用于处理服务
 * 
 */
public class PdSupplier extends HL7Actor implements IPdSupplier {
	/* Logger for problems */
	private static Logger log = Logger.getLogger(PdSupplier.class);

	/** The main server connection this actor will be using */
	protected IConnectionDescription connection = null;

	/** The PDQ Server */
	private HL7Server server = null;
	/** PDQ event notifier **/
	private IJMXEventNotifier pixEvent = null;

	/**
	 * Creates a new PdSupplier that will talk to a PDQ consumer over the connection description supplied.
	 * 
	 * @param actor
	 *            The actor description of this PD Supplier
	 * @param auditTrail
	 *            The audit trail for this PD Supplier
	 * @throws IheConfigurationException
	 */
	public PdSupplier(IActorDescription actor, IheAuditTrail auditTrail) throws IheConfigurationException {
		super(actor, auditTrail);
		this.connection = Configuration.getConnection(actor, PixPdqConstants.SERVER, true);
	}

	@Override
	public void start() {
		// call the super one to initiate standard start process
		super.start();
		// now begin the local start, initiate pdq supplier
		LowerLayerProtocol llp = LowerLayerProtocol.makeLLP(); // The transport protocol
		PipeParser parser = new PipeParser();
		parser.setValidationContext(new MessageValidation());
		server = new HL7Server(connection, llp, parser);
		Application handler = new PdQueryHandler(this);
		server.registerApplication("QBP", "Q22", handler); // PDQ Query message
		server.registerApplication("QCN", "J01", handler); // Query Cancel message
		server.registerApplication("QBP", "ZV1", handler);
		// now start the Pdq Supplier server
		server.start();

		if (log.isInfoEnabled()) {
			log.info("Started Patient Demographics Supplier: " + this.getName());
		}
	}

	@Override
	public void stop() {
		// now end the local stop, stop the pix manager server
		server.stop();

		// call the super one to initiate standard stop process
		super.stop();

		if (log.isInfoEnabled()) {
			log.info("Stopped Patient Demographics Supplier: " + this.getName());
		}
	}

	/**
	 * Gets the main server connection of this actor.
	 * 
	 * @return the main connection
	 */
	public IConnectionDescription getConnection() {
		return connection;
	}

	public static void main(String[] args) throws Exception {

		// String msg = "MSH|^~\\&|EHR_MISYS|MISYS|PAT_IDENTITY_X_REF_MGR_IBM1|IBM|20060817212747-0400||QBP^Q22|PDQ_0|P|2.5\r" +
		// "QPD|QRY_PDQ_1001^Query By Name^IHEDEMO|QRY_PDQ_0|@PID.5.1^DEPINTO~@PID.5.2^JOE\r" +
		// "RCP|I";

		// String msg = "MSH|^~\\&|CLINREG|WESTCLIN|HOSPMPI|HOSP|199912121135-0600||QBP^Q22^QBP_Q21|1|D|2.5\r" +
		// "QPD|Q22^Find Candidates^HL7nnn|111069|@PID.5.1^SMITH~@PID.5.2^JOHN~@PID.8^M|80|MATCHWARE|1.2||^^^METRO HOSPITAL~^^^SOUTH LAB|\r" +
		// "RCP|I|20^RD";

		String msg = "MSH|^~\\&|OTHER_KIOSK|HIMSSSANDIEGO|EHR_MISYS|MISYS|20060821150004-0500||RSP^K22|115619040409488ibmod|P|2.5||1940933422:1156190185984|\r"
				+ "MSA|AA|PDQ_6|\r" + "QAK|QRY_PDQ_0|OK|\r" + "QPD|QRY_PDQ_1001^Query By Name^IHEDEMO|QRY_PDQ_0|@PID.5.2^JO*\r"
				+ "PID|||JD12294^^^HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO||Doe^John||||||900 Main St^^Green Bay^WI^23221||^PRN^PH|\r" + "QRI|174.0\r"
				+ "PID|||12345678^^^HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO||DePinto^Joe^V^Jr|Wang|19580325|M|||||^PRN^PH^^^716^3856235|\r" + "QRI|174.0\r"
				+ "PID|||12345679^^^HIMSS2005&1.3.6.1.4.1.21367.2005.1.1&ISO||DePinto^Joe^V^Jr|Wang|19580325|M|||||^PRN^PH^^^716^3856235|\r" + "QRI|174.0\r"
				+ "DSC|2058980185:1156190210438|I|";
		PipeParser pipeParser = new PipeParser();
		try {
			Message message = pipeParser.parse(msg);
			if (message instanceof QBP_Q21) {
				System.out.println("Type= QBP_Q21");
			} else {
				System.out.println("Not QBP_Q21");
			}
			Terser in = new Terser(message);
			RSP_K21 outMessage = new RSP_K21();
			Terser out = new Terser(outMessage);
			MSH msh = outMessage.getMSH();
			// MSH-1
			msh.getFieldSeparator().setValue("|");
			// MSH-2
			msh.getEncodingCharacters().setValue("^~\\&");
			// MSH-3
			HD hd = msh.getSendingApplication();
			hd.getNamespaceID().setValue("Sendapp");

			// MSH-11
			msh.getProcessingID().getProcessingID().setValue("P");
			// MSH-12
			msh.getVersionID().getVersionID().setValue("2.5");

			RSP_K21_QUERY_RESPONSE qr = outMessage.getQUERY_RESPONSE();
			// PID pid = qr.getPID();
			// XPN pn = pid.getPatientName(0);
			// pn.getFamilyName().getSurname().setValue("Pin");
			// pn.getGivenName().setValue("John");

			//Hansen 2011-12-03
			//String pidName = qr.add(PID.class, false, true);
			//PID pid2 = (PID) qr.get(pidName);
			//pid2.getPatientName(0).getGivenName().setValue("FirstName2");

			Structure s = qr.get("PID2");

			// PID pid = (PID)out.getSegment("/.PID");
			// out.set(pid, 1, 0, 1, 1, "happy");
			// PID pid2 = (PID)out.getSegment("/.PID(1)");
			// out.set(pid2, 1, 0, 1, 1, "happy2");

			HL7Util.echoQPD(out, in);

			// String ret= pipeParser.encode( (PID)out.getSegment("PID"), new EncodingCharacters('|', "^~\\&" ) );
			String ret = pipeParser.encode(outMessage);
			System.out.println(" ret" + ret);

			// PdSupplier.PdSupplierHandler handler = new PdQueryHandler(null, null);
			// handler.processMessage( message );
			//
			// handler.processMessage( message );

		} catch (HL7Exception e) {
			e.printStackTrace();
		}
		// catch (ApplicationException e) {
		// e.printStackTrace();
		// }

	}

	public IJMXEventNotifier getPixEvent() {
		return pixEvent;
	}

	public void setPixEvent(IJMXEventNotifier pixEvent) {
		this.pixEvent = pixEvent;
	}
}
