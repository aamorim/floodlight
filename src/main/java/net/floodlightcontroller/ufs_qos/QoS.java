package net.floodlightcontroller.ufs_qos;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.projectfloodlight.openflow.protocol.OFFactories;
import org.projectfloodlight.openflow.protocol.OFFactory;
import org.projectfloodlight.openflow.protocol.OFMatchV3;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFPacketIn;
import org.projectfloodlight.openflow.protocol.OFPacketQueue;
import org.projectfloodlight.openflow.protocol.OFQueueGetConfigReply;
import org.projectfloodlight.openflow.protocol.OFQueueGetConfigRequest;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.protocol.OFVersion;
import org.projectfloodlight.openflow.protocol.match.Match;
import org.projectfloodlight.openflow.protocol.queueprop.OFQueueProp;
import org.projectfloodlight.openflow.protocol.queueprop.OFQueuePropMaxRate;
import org.projectfloodlight.openflow.protocol.queueprop.OFQueuePropMinRate;
import org.projectfloodlight.openflow.protocol.ver13.OFFactoryVer13;
import org.projectfloodlight.openflow.protocol.ver13.OFQueuePropertiesSerializerVer13;
import org.projectfloodlight.openflow.types.DatapathId;
import org.projectfloodlight.openflow.types.OFPort;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.internal.IOFSwitchService;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.core.IFloodlightProviderService;
import java.util.ArrayList;
import java.util.Set;
import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.staticentry.IStaticEntryPusherService;
import net.floodlightcontroller.storage.IResultSet;
import net.floodlightcontroller.storage.IStorageSourceService;
import net.floodlightcontroller.storage.StorageException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QoS implements IOFMessageListener, IFloodlightModule, IQoSService {

	
	protected IFloodlightProviderService floodlightProvider;
	protected Set<Long> macAddresses;
	protected static Logger logger;
	protected IOFSwitchService switchService;
	protected IStorageSourceService storageSource;
	protected IStaticEntryPusherService flowPusher;
	protected List<QoSPolicy> policies; //Synchronized 
	protected List<QoSTypeOfService> services; //Synchronized
	
	
	public static final String TABLE_NAME = "controller_qos";
	public static final String COLUMN_POLID = "policyid";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_MATCH_PROTOCOL = "protocol";
	public static final String COLUMN_MATCH_ETHTYPE = "eth-type";
	public static final String COLUMN_MATCH_INGRESSPRT = "ingressport";
	public static final String COLUMN_MATCH_IPDST = "ipdst";
	public static final String COLUMN_MATCH_IPSRC = "ipsrc";
	public static final String COLUMN_MATCH_VLANID = "vlanid";
	public static final String COLUMN_MATCH_ETHSRC = "ethsrc";
	public static final String COLUMN_MATCH_ETHDST = "ethdst";
	public static final String COLUMN_MATCH_TCPUDP_SRCPRT = "tcpudpsrcport";
	public static final String COLUMN_MATCH_TCPUDP_DSTPRT = "tcpudpdstport";
	public static final String COLUMN_NW_TOS = "nw_tos";
	public static final String COLUMN_SW = "switches";
	public static final String COLUMN_QUEUE = "queue";
	public static final String COLUMN_ENQPORT = "equeueport";
	public static final String COLUMN_PRIORITY = "priority";
	public static final String COLUMN_SERVICE = "service";
	public static String ColumnNames[] = { COLUMN_POLID,
			COLUMN_NAME,COLUMN_MATCH_PROTOCOL, COLUMN_MATCH_ETHTYPE,COLUMN_MATCH_INGRESSPRT,
			COLUMN_MATCH_IPDST,COLUMN_MATCH_IPSRC,COLUMN_MATCH_VLANID,
			COLUMN_MATCH_ETHSRC,COLUMN_MATCH_ETHDST,COLUMN_MATCH_TCPUDP_SRCPRT,
			COLUMN_MATCH_TCPUDP_DSTPRT,COLUMN_NW_TOS,COLUMN_SW,
			COLUMN_QUEUE,COLUMN_ENQPORT,COLUMN_PRIORITY,COLUMN_SERVICE,};
	
	public static final String TOS_TABLE_NAME = "controller_qos_tos";
	public static final String COLUMN_SID = "serviceid";
	public static final String COLUMN_SNAME = "servicename";
	public static final String COLUMN_TOSBITS = "tosbits";
	public static String TOSColumnNames[] = {COLUMN_SID, COLUMN_SNAME, COLUMN_TOSBITS};
	
	
	@Override
	public String getName() {
		return QoS.class.getSimpleName();
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		return null;
	}
 
	/**
	 * Esse método carrega todos os modulos a qual esse modulo depende.
	 */
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		Collection<Class<? extends IFloodlightService>> collection = new ArrayList<Class<? extends IFloodlightService>>();
		collection.add(IFloodlightProviderService.class);
		collection.add(IStorageSourceService.class);
		return collection;
	}

	/**
	 * Ação execultada ao iniciar o modulo
	 */
	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {

		floodlightProvider = context.getServiceImpl(IFloodlightProviderService.class);
		logger = LoggerFactory.getLogger(QoS.class);
		storageSource = context.getServiceImpl(IStorageSourceService.class);
		policies = new ArrayList<QoSPolicy>();
		services = new ArrayList<QoSTypeOfService>();

	}

	/**
	 * Metodo ouvinte
	 */
	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		
		floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
		//Storage for policies
        storageSource.createTable(TABLE_NAME, null);
        storageSource.setTablePrimaryKeyName(TABLE_NAME, COLUMN_POLID);
        //avoid thread issues for concurrency
        synchronized (policies) {
            this.policies = readPoliciesFromStorage(); 
            }
        
        //Storage for services
        storageSource.createTable(TOS_TABLE_NAME, null);
        storageSource.setTablePrimaryKeyName(TOS_TABLE_NAME, COLUMN_SID);
        //avoid thread issues for concurrency
        synchronized (services) {
            this.services = readServicesFromStorage(); 
            }
        
        // create default "Best Effort" service
        // most networks use this as default, adding here for defaulting
        try{
        	QoSTypeOfService service = new QoSTypeOfService();
        	service.name = "Best Effort";
        	service.tos = (byte)0x00;
        	service.sid = service.genID();
        	this.addService(service);
        }catch(Exception e){
        	logger.error("Error adding default Best Effort {}", e);
        }
		
	}

	/**
	 * Acao a ser tomada para as mensagens PACKET_IN
	 */
	@Override
	public Command receive(IOFSwitch sw, OFMessage msg, FloodlightContext cntx) {
		
        StringBuffer sb =  new StringBuffer("");
        Ethernet eth;

		switch (msg.getType()) {
		case PACKET_IN:
			OFPacketIn pktIn = ( OFPacketIn ) msg;
			sb.append("\ntype: " + pktIn.toString());
			sb.append("\npacket_in [ ");
            sb.append(sw.getId().toString());
            sb.append(" -> Controller");
            sb.append(" ]");

            sb.append("\ntotal length: ");
            sb.append(pktIn.getTotalLen());
            sb.append("\nin_port: ");
            sb.append(pktIn.getInPort());
            sb.append("\ndata_length: ");
            sb.append(pktIn.getTotalLen() - 0);
            sb.append("\nbuffer: ");
            sb.append(pktIn.getBufferId());

            // If the conext is not set by floodlight, then ignore.
            if (cntx != null) {
            // packet type  icmp, arp, etc.
                eth = IFloodlightProviderService.bcStore.get(cntx,
                        IFloodlightProviderService.CONTEXT_PI_PAYLOAD);
                if (eth != null)
                	   sb.append("\ndata eth: ");
                       sb.append(eth.toString());

            }
            logger.info("QoS-UFS data: {} ",sb.toString());
            
            //logger.debug("PACKET_IN recieved");
        	byte[] packetData = OFMessage.getData(sw, msg, cntx);
        	
        	//Temporary match from packet to compare
        	OFFactory my13Factory = OFFactories.getFactory(OFVersion.OF_13);
        	Match myMatch = my13Factory.buildMatch().build();
        	myMatch.loadFromPacket(packetData, OFPort.ZERO.getValue());
        	checkIfQoSApplied(tmpMatch);
        	
			break;

		default:
			break;
		}
		
//		Ethernet eth =
//                IFloodlightProviderService.bcStore.get(cntx,
//                                            IFloodlightProviderService.CONTEXT_PI_PAYLOAD); 
//        Long sourceMACHash = eth.getSourceMACAddress().getLong();
//        if (!macAddresses.contains(sourceMACHash)) {
//            macAddresses.add(sourceMACHash);
//            logger.info("MAC Address: {} seen on switch: {}",
//                    eth.getSourceMACAddress().toString(),
//                    sw.getId().toString());
//        }
        
        
        return Command.CONTINUE;
	}
	
	
	/**
     * Reads the policies from the storage and creates a sorted 
     * ArrayList of QoSPolicy's from them.
     * @return the sorted ArrayList of Policy instances (rules from storage)
     * 
     *  Based on work from below, Credit to
     *  @author Amer Tahir
     *  @edited KC Wang
     *  @author (re-authored) Ryan Wallner 
     */
    protected ArrayList<QoSPolicy> readPoliciesFromStorage() {
        ArrayList<QoSPolicy> l = new ArrayList<QoSPolicy>();
        try{
        	Map<String, Object> row;

        	IResultSet policySet = storageSource
        			.executeQuery(TABLE_NAME, ColumnNames, null, null );
        	for( Iterator<IResultSet> iter = policySet.iterator(); iter.hasNext();){
        		row = iter.next().getRow();
        		
        		QoSPolicy p = new QoSPolicy();
        		if(!row.containsKey(COLUMN_POLID) 
        				|| !row.containsKey(COLUMN_SW)
        				|| !row.containsKey(COLUMN_QUEUE)
        				|| !row.containsKey(COLUMN_ENQPORT)
        				|| !row.containsKey(COLUMN_SERVICE)){
        			logger.error("Skipping entry with required fields {}", row);
        			continue;
        		}
        		
        		try{
        			p.policyid = Integer.parseInt((String) row.get(COLUMN_POLID));
        			p.queue = Short.parseShort((String) row.get(COLUMN_QUEUE));
        			p.enqueueport = Short.parseShort((String) row.get(COLUMN_ENQPORT));
        			p.service = (String) row.get(COLUMN_SERVICE);
        			
        			//TODO change for String[] of switches
        			p.sw = (String) row.get(COLUMN_SW);
        			
        			for(String key: row.keySet()){
        				if(row.get(key) == null){
        					continue;
        				}
        				else if(key.equals(COLUMN_POLID)
        						|| key.equals(COLUMN_SW)
        						|| key.equals(COLUMN_QUEUE)
        						|| key.equals(COLUMN_ENQPORT)
        						|| key.equals(COLUMN_SERVICE)){
        					continue;
        				}
        				else if(key.equals(COLUMN_NAME)){
        					p.name = (String) row.get(COLUMN_NAME);
        				}
        				else if(key.equals(COLUMN_MATCH_ETHDST)){
        					p.ethdst = (String) row.get(COLUMN_MATCH_ETHDST);
        				}
        				else if(key.equals(COLUMN_MATCH_ETHSRC)){
        					p.ethsrc = (String) row.get(COLUMN_MATCH_ETHSRC);
        				}
        				else if(key.equals(COLUMN_MATCH_ETHTYPE)){
        					p.ethtype = Short.parseShort((String) 
        							row.get(COLUMN_MATCH_ETHTYPE));
        				}
        				else if(key.equals(COLUMN_MATCH_INGRESSPRT)){
        					p.ingressport = Short.parseShort((String) 
        							row.get(COLUMN_MATCH_INGRESSPRT));
        				}
        				else if(key.equals(COLUMN_MATCH_IPDST)){
        					p.ipdst = Integer.parseInt((String) 
        							row.get(COLUMN_MATCH_IPDST));
        				}
        				else if(key.equals(COLUMN_MATCH_IPSRC)){
        					p.ipsrc = Integer.parseInt((String) 
        							row.get(COLUMN_MATCH_IPSRC));
        				}
        				else if(key.equals(COLUMN_MATCH_PROTOCOL)){
        					p.protocol = Byte.parseByte((String) 
        							row.get(COLUMN_MATCH_PROTOCOL));
        				}
        				else if(key.equals(COLUMN_MATCH_TCPUDP_DSTPRT)){
        					p.tcpudpdstport = Short.parseShort((String)
        							row.get(COLUMN_MATCH_TCPUDP_DSTPRT));
        				}
        				else if(key.equals(COLUMN_MATCH_TCPUDP_SRCPRT)){
        					p.tcpudpsrcport = Short.parseShort((String)
        							row.get(COLUMN_MATCH_TCPUDP_SRCPRT));
        				}
        				else if(key.equals(COLUMN_MATCH_VLANID)){
        					p.vlanid = Short.parseShort((String) 
        							row.get(COLUMN_MATCH_VLANID));
        				}
        				else if(key.equals(COLUMN_NW_TOS)){
        					p.tos = Byte.parseByte((String) 
        							row.get(COLUMN_NW_TOS));
        				}
        				
        				else if(key.equals(COLUMN_PRIORITY)){
        					p.priority = Short.parseShort((String) 
        							row.get(COLUMN_PRIORITY));
        				}
        			}
        			
        		}catch(ClassCastException e){
        			logger.error("Error, Skipping rule, Bad Data "
        					+ e.getMessage()+" on Rule {}", p.policyid);
        		} 
        		//make sure its a queueing rule or service rule only.
        		if(p.enqueueport != -1 && p.queue != -1 && p.service != null){
        			l.add(p);
        		}
        		else if(p.enqueueport > -1 && p.queue > -1 && p.service == null){
        			l.add(p);
        		}
        		else{
        			continue;//not a valid rule
        		}
        		
        	}
        }catch(StorageException e){
        	logger.error("Error with storage source: {}", e);
        }
        Collections.sort(l);
		return l;
    }
    
    
    /**
     * Reads the types of services from the storage and creates a 
     * sorted ArrayList of QoSTypeOfService from them
     * @return the sorted ArrayList of Type of Service instances (rules from storage)
     * 
     *  Based on work from below, Credit to
     *  @author Amer Tahir
     *  @edited KC Wang
     *  @author (re-authored) Ryan Wallner 
     */
    protected ArrayList<QoSTypeOfService> readServicesFromStorage() {
        ArrayList<QoSTypeOfService> l = new ArrayList<QoSTypeOfService>();
        try{
        	Map<String, Object> row;

        	IResultSet serviceSet = storageSource
        			.executeQuery(TOS_TABLE_NAME, TOSColumnNames, null, null );
        	for( Iterator<IResultSet> iter = serviceSet.iterator(); iter.hasNext();){
        		row = iter.next().getRow();
        		
        		QoSTypeOfService s = new QoSTypeOfService();
        		if(!row.containsKey(COLUMN_SID) 
        				|| !row.containsKey(COLUMN_TOSBITS)){
        			logger.error("Skipping entry with required fields {}", row);
        			continue;
        		}
        		try{
        			s.sid = Integer.parseInt((String) row.get(COLUMN_SID));
        			s.tos = Byte.parseByte((String) row.get(COLUMN_TOSBITS));
        			
        			for(String key: row.keySet()){
        				if(row.get(key) == null){
        					continue;
        				}
        				else if(key.equals(COLUMN_SID)
        						|| key.equals(COLUMN_TOSBITS)){
        					continue;
        				}
        				else if(key.equals(COLUMN_SNAME)){
        					s.name = (String) row.get(COLUMN_SNAME);
        				}
        			}
        			
        		}catch(ClassCastException e){
        			logger.error("Error, Skipping rule, Bad Data "
        					+ e.getMessage()+" on Rule {}", s.sid);
        		} 
        		if(s.tos != -1){
        		l.add(s);
        		}
        	}
        }catch(StorageException e){
        	logger.error("Error with storage source: {}", e);
        }
        Collections.sort(l);
		return l;
    }
    
    
    /**
	 * Add a service class to use in policies
	 * Used to make ToS/DiffServ Bits human readable. 
	 * Bit notation 000000 becomes "Best Effort"
	 * @param QoSTypeOfService
	 */
	@Override
	public synchronized void addService(QoSTypeOfService service) {
		//debug
		logger.debug("Adding Service to List and Storage");
		//create the UID
		service.sid = service.genID();
		
		//check tos bits are within bounds
        if (service.tos >= (byte)0x00 && service.tos <= (byte)0x3F ){
        	try{
        		//Add to the list of services
        		//un-ordered, naturally a short list
        		this.services.add(service);
        		
        		//add to the storage source
        		Map<String, Object> serviceEntry = new HashMap<String,Object>();
        		serviceEntry.put(COLUMN_SID, Integer.toString(service.sid));
        		serviceEntry.put(COLUMN_SNAME, service.name);
        		serviceEntry.put(COLUMN_TOSBITS, Byte.toString(service.tos));
        		
        		//ad to storage
        		storageSource.insertRow(TOS_TABLE_NAME, serviceEntry);
        		
        	}catch(Exception e){
        		logger.debug("Error adding service, error: {}" ,e);
        	}
        }
        else{
        	logger.debug("Type of Service must be 0-64");
        }
	}
	
	/**
	 * Removes a Network Type of Service
	 * @category by sid
	 */
	@Override
	public synchronized void deleteService(int sid) {
		Iterator<QoSTypeOfService> sIter = this.services.iterator();
		while(sIter.hasNext()){
			QoSTypeOfService s = sIter.next();
			if(s.sid == sid){
				sIter.remove();
				break; //done only one can exist
			}
		}
	}

	@Override
	public void enableQoS(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QoSTypeOfService> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QoSPolicy> getPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPolicyToNetwork(QoSPolicy policy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPolicy(QoSPolicy policy, String swid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPolicy(QoSPolicy policy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePolicyFromNetwork(String policyName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePolicy(QoSPolicy policy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePolicy(String switches, String policyName) {
		// TODO Auto-generated method stub
		
	}
    

}
