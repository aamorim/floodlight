package net.floodlightcontroller.ufs_qos;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.projectfloodlight.openflow.protocol.OFActionType;
import org.projectfloodlight.openflow.protocol.OFFactories;
import org.projectfloodlight.openflow.protocol.OFFactory;
import org.projectfloodlight.openflow.protocol.OFFlowAdd;
import org.projectfloodlight.openflow.protocol.OFFlowMod;
import org.projectfloodlight.openflow.protocol.OFMatchV3;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFPacketIn;
import org.projectfloodlight.openflow.protocol.OFPacketOut;
import org.projectfloodlight.openflow.protocol.OFPacketQueue;
import org.projectfloodlight.openflow.protocol.OFQueueGetConfigReply;
import org.projectfloodlight.openflow.protocol.OFQueueGetConfigRequest;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.protocol.OFVersion;
import org.projectfloodlight.openflow.protocol.action.OFAction;
import org.projectfloodlight.openflow.protocol.action.OFActionEnqueue;
import org.projectfloodlight.openflow.protocol.match.Match;
import org.projectfloodlight.openflow.protocol.match.MatchField;
import org.projectfloodlight.openflow.protocol.queueprop.OFQueueProp;
import org.projectfloodlight.openflow.protocol.queueprop.OFQueuePropMaxRate;
import org.projectfloodlight.openflow.protocol.queueprop.OFQueuePropMinRate;
import org.projectfloodlight.openflow.protocol.ver13.OFFactoryVer13;
import org.projectfloodlight.openflow.protocol.ver13.OFQueuePropertiesSerializerVer13;
import org.projectfloodlight.openflow.types.DatapathId;
import org.projectfloodlight.openflow.types.EthType;
import org.projectfloodlight.openflow.types.IPv4Address;
import org.projectfloodlight.openflow.types.IpDscp;
import org.projectfloodlight.openflow.types.IpProtocol;
import org.projectfloodlight.openflow.types.MacAddress;
import org.projectfloodlight.openflow.types.OFPort;
import org.projectfloodlight.openflow.types.OFPortBitMap.Builder;
import org.projectfloodlight.openflow.types.OFVlanVidMatch;
import org.projectfloodlight.openflow.types.TransportPort;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.internal.IOFSwitchService;
import net.floodlightcontroller.core.internal.OFSwitchManager;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.core.IFloodlightProviderService;
import java.util.ArrayList;
import java.util.Set;
import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.packet.EthernetTest;
import net.floodlightcontroller.packet.IPv4;
import net.floodlightcontroller.packet.IPv6;
import net.floodlightcontroller.staticentry.IStaticEntryPusherService;
import net.floodlightcontroller.storage.IResultSet;
import net.floodlightcontroller.storage.IStorageSourceService;
import net.floodlightcontroller.storage.StorageException;
import net.floodlightcontroller.topology.Archipelago;

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
	protected OFFactory myFactory;
	
	//regex for dpid string, this can/needs to be more elegant. Maybe use of a Matcher
	protected String dpidPattern = "^[\\d|\\D][\\d|\\D]:[\\d|\\D][\\d|\\D]:" +
										"[\\d|\\D][\\d|\\D]:[\\d|\\D][\\d|\\D]:" +
										"[\\d|\\D][\\d|\\D]:[\\d|\\D][\\d|\\D]:" +
										"[\\d|\\D][\\d|\\D]:[\\d|\\D][\\d|\\D]$";
	
	
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
	 * Carrega todas as dependencias desse modulo
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
		switchService = context.getServiceImpl(IOFSwitchService.class);
		logger = LoggerFactory.getLogger(QoS.class);
		storageSource = context.getServiceImpl(IStorageSourceService.class);
		policies = new ArrayList<QoSPolicy>();
		services = new ArrayList<QoSTypeOfService>();
		myFactory = OFFactories.getFactory(OFVersion.OF_13);

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
			
			if(cntx == null) {
				return Command.CONTINUE;
			}
			
			eth = IFloodlightProviderService.bcStore.get(cntx,
                    IFloodlightProviderService.CONTEXT_PI_PAYLOAD);
			
			//Caso o pacote seja IPv4 ou IPv6
			if(isIPv4v6(eth.getEtherType())) {
				
				if(isIPv4(eth.getEtherType())) {
					IPv4 ipv4 = (IPv4) eth.getPayload();
					ipv4.setDiffServ(IpDscp.DSCP_40.getDscpValue());
					logger.info("IPv4 DSCP: {} ", ipv4.getDiffServ());
					logger.info("IPv4 PROT: {} ", ipv4.getProtocol().toString());
				} else if(isIPv4(eth.getEtherType())) {
					IPv6 ipv6 = (IPv6) eth.getPayload();
					logger.info("IPv6 DSCP: {} ", ipv6.getTrafficClass());
				}
				
				
				
			}        	
			break;

		default:
			break;
		}        
        
        return Command.CONTINUE;
	}
	
	
	private boolean isIPv4v6(EthType eth){
		return (EthType.IPv4 == eth) || (EthType.IPv6 == eth); 
	}
	
	private boolean isIPv4(EthType eth) {
		return EthType.IPv4 == eth;
	}
	
	private boolean isIPv6(EthType eth) {
		return EthType.IPv6 == eth;
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
        		
        		QoSTypeOfService service = new QoSTypeOfService();
        		if(!row.containsKey(COLUMN_SID) 
        				|| !row.containsKey(COLUMN_TOSBITS)){
        			logger.error("Skipping entry with required fields {}", row);
        			continue;
        		}
        		try{
        			service.sid = Integer.parseInt((String) row.get(COLUMN_SID));
        			service.tos = Byte.parseByte((String) row.get(COLUMN_TOSBITS));
        			
        			for(String key: row.keySet()){
        				if(row.get(key) == null){
        					continue;
        				}
        				else if(key.equals(COLUMN_SID)
        						|| key.equals(COLUMN_TOSBITS)){
        					continue;
        				}
        				else if(key.equals(COLUMN_SNAME)){
        					service.name = (String) row.get(COLUMN_SNAME);
        				}
        			}
        			
        		}catch(ClassCastException e){
        			logger.error("Error, Skipping rule, Bad Data "
        					+ e.getMessage()+" on Rule {}", service.sid);
        		} 
        		if(service.tos != -1){
        		logger.info("readServicesFromStorage QoSTypeOfService: {}", service.toString());
        		l.add(service);
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
	/*	OFFlowMod flow = policyToFlowMod(policy);
		
		logger.info("adding policy-flow {} to all switches",flow.toString());
		//add to all switches
		Map<DatapathId, IOFSwitch> switches = switchService.getAllSwitchMap();
		//simple check
		if(!(switches.isEmpty())){
			for(IOFSwitch sw : switches.values()){
				if(!(sw.isConnected())){
					break;// cannot add
				}
				logger.info("Add flow Name: {} Flow: {} Switch "+ sw.getId().toString(), 
				policy.name, flow.toString());
				//add unique flow names based on dpid hasCode :)
				flowPusher.addFlow(policy.name+Integer.toString(sw.getId().toString().hashCode()), 
								  flow, sw.getId());
				
			}
		}*/
		
	}

	@Override
	public synchronized void addPolicy(QoSPolicy policy, String swid) {		
		// TODO Auto-generated method stub
	}

	@Override
	public void addPolicy(QoSPolicy policy) {
		//debug
		logger.debug("Adding Policy to List and Storage");
		//create the UID
		policy.policyid = policy.genID();
		
		int p = 0;
		for (p = 0; p < this.policies.size(); p++){
			//check if empy
			if(this.policies.isEmpty()){
				//p is zero
				break;
			}
			//starts at the first(lowest) policy based on priority
			//insertion sort, gets hairy when n # of switches increases. 
			//larger networks may need a merge sort.
			if(this.policies.get(p).priority >= policy.priority){
				//this keeps "p" in the correct position to place new policy in
				break;
			}
		}
		if (p <= this.policies.size()) {
			this.policies.add(p, policy);
			} 
		else {
			this.policies.add(policy);
			}	
		//Add to the storageSource
		Map<String, Object> policyEntry = new HashMap<String, Object>();
		policyEntry.put(COLUMN_POLID, Long.toString(policy.policyid));
		policyEntry.put(COLUMN_NAME, policy.name);
		policyEntry.put(COLUMN_MATCH_PROTOCOL, Short.toString(policy.protocol));
		policyEntry.put(COLUMN_MATCH_ETHTYPE, Short.toString(policy.ethtype));
		policyEntry.put(COLUMN_MATCH_INGRESSPRT, Short.toString(policy.ingressport));
		policyEntry.put(COLUMN_MATCH_IPSRC, Integer.toString(policy.ipsrc));
		policyEntry.put(COLUMN_MATCH_IPDST, Integer.toBinaryString(policy.ipdst));
		policyEntry.put(COLUMN_MATCH_VLANID, Short.toString(policy.vlanid));
		policyEntry.put(COLUMN_MATCH_ETHSRC, policy.ethsrc);
		policyEntry.put(COLUMN_MATCH_ETHDST, policy.ethdst);
		policyEntry.put(COLUMN_MATCH_TCPUDP_SRCPRT, Short.toString(policy.tcpudpsrcport));
		policyEntry.put(COLUMN_MATCH_TCPUDP_DSTPRT, Short.toString(policy.tcpudpdstport));
		policyEntry.put(COLUMN_NW_TOS, policy.service);
		policyEntry.put(COLUMN_SW, policy.sw);
		policyEntry.put(COLUMN_QUEUE, Short.toString(policy.queue));
		policyEntry.put(COLUMN_ENQPORT, Short.toString(policy.enqueueport));
		policyEntry.put(COLUMN_PRIORITY, Short.toString(policy.priority));
		policyEntry.put(COLUMN_SERVICE, policy.service);
		storageSource.insertRow(TABLE_NAME, policyEntry);
		
		/**
		* TODO Morph this to use a String[] of switches
		**/
		
		if (policy.sw.equals("all")){
			logger.debug("Adding Policy {} to Entire Network", policy.toString());
			addPolicyToNetwork(policy);
		}
		/** [NOTE] Note utilized yet, future revision used to "save" policies
		 *  to the controller, then modified to be added to switched **/
		else if (policy.sw.equals("none")){
			logger.debug("Adding Policy {} to Controller", policy.toString());
		}
		//add to a specified switch b/c "all" not specified
		else if(policy.sw.matches(dpidPattern)){
			logger.debug("Adding policy {} to Switch {}", policy.toString(), policy.sw);
			// add appropriate hex string converted to a long type
			addPolicy(policy, policy.sw);
			}	
		else{
			logger.error("***Policy {} error at switch input {} ***" +
					"", policy.toString(), policy.sw);
		}
		
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
	
	/**
	 * Returns a flowmod from a policy
	 * @param policy
	 * @return
	 */
	public OFFlowMod policyToFlowMod(QoSPolicy policy){
		//initialize a match structure that matches everything
		Match.Builder match = myFactory.buildMatch();
		//Based on the policy match appropriately.
	
		//no wildcards
		//match.setWildcards(0);
		
		if(policy.ethtype != -1){
			match.setExact(MatchField.ETH_TYPE, EthType.of(policy.ethtype));
			logger.debug("setting match on eth-type");
		} 

		if(policy.protocol != -1){
			match.setExact(MatchField.IP_PROTO, IpProtocol.of(policy.protocol));
			//match.setNetworkProtocol(policy.protocol);
			//logger.debug("setting match on protocol ");
		}
		if(policy.ingressport != -1){
			match.setExact(MatchField.IN_PORT,OFPort.of(policy.ingressport));
			//match.setInputPort(policy.ingressport);
			//logger.debug("setting match on ingress port ");
		}
		if(policy.ipdst != -1){
			match.setExact(MatchField.IPV4_DST,IPv4Address.of(policy.ipdst));
			//match.setNetworkDestination(policy.ipdst);
			//logger.debug("setting match on network destination");
		}
		if(policy.ipsrc != -1){
			match.setExact(MatchField.IPV4_SRC,IPv4Address.of(policy.ipsrc));
			//match.setNetworkSource(policy.ipsrc);
			//logger.debug("setting match on network source");
		}
		if(policy.vlanid != -1){
			match.setExact(MatchField.VLAN_VID,OFVlanVidMatch.ofVlan(policy.vlanid));
			//match.setDataLayerVirtualLan(policy.vlanid);
			//logger.debug("setting match on VLAN");
		}
		if(policy.tos != -1){
			match.setExact(MatchField.IP_DSCP,IpDscp.of(policy.tos));
			//match.setNetworkTypeOfService(policy.tos);
			//logger.debug("setting match on ToS");
		}
		if(policy.ethsrc != null){
			match.setExact(MatchField.ETH_SRC,MacAddress.of(policy.ethsrc));
			//match.setDataLayerSource(policy.ethsrc);
			//logger.debug("setting match on data layer source");
		}
		if(policy.ethdst != null){
			match.setExact(MatchField.ETH_DST,MacAddress.of(policy.ethdst));
			//match.setDataLayerDestination(policy.ethdst);
			//logger.debug("setting match on data layer destination");
		}
		if(policy.tcpudpsrcport != -1){
			match.setExact(MatchField.TCP_SRC,TransportPort.of(policy.tcpudpsrcport));
			//match.setTransportSource(policy.tcpudpsrcport);
			//logger.debug("setting match on transport source port");
		}
		if(policy.tcpudpdstport != -1){
			match.setExact(MatchField.TCP_DST,TransportPort.of(policy.tcpudpdstport));
			//match.setTransportDestination(policy.tcpudpdstport);
			//logger.debug("setting match on transport destination");
		}
	
		//Create a flow mod using the previous match structure
		OFFlowMod fm = new OFFlowMod();
		fm.setType(OFType.FLOW_MOD);
		//depending on the policy nw_tos or queue the flow mod
		// will change the type of service bits or enqueue the packets
		if(policy.queue > -1 && policy.service == null){
			logger.info("This policy is a queuing policy");
			List<OFAction> actions = new ArrayList<OFAction>();
			
			//add the queuing action
			OFActionEnqueue enqueue = new OFActionEnqueue();
			enqueue.setLength((short) 0xffff);
			enqueue.setType(OFActionType.OPAQUE_ENQUEUE); // I think this happens anyway in the constructor
			enqueue.setPort(policy.enqueueport);
			enqueue.setQueueId(policy.queue);
			actions.add((OFAction) enqueue);
			
			logger.info("Match is : {}", match.toString());
			//add the matches and actions and return
			fm.setMatch(match)
				.setActions(actions)
				.setIdleTimeout((short) 0)  // infinite
				.setHardTimeout((short) 0)  // infinite
				.setBufferId(OFPacketOut.BUFFER_ID_NONE)
				.setFlags((short) 0)
				.setOutPort(OFPort.OFPP_NONE.getValue())
				.setPriority(policy.priority)
				.setLengthU((short)OFFlowMod.MINIMUM_LENGTH + OFActionEnqueue.MINIMUM_LENGTH);
				
		}
		else if(policy.queue == -1 && policy.service != null){
			logger.info("This policy is a type of service policy");
			List<OFAction> actions = new ArrayList<OFAction>();
			
			//add the queuing action
			OFActionNetworkTypeOfService tosAction = new OFActionNetworkTypeOfService();
			tosAction.setType(OFActionType.SET_NW_TOS);
			tosAction.setLength((short) 0xffff);
			
			//Find the appropriate type of service bits in policy
			Byte pTos = null;
			List<QoSTypeOfService> serviceList = this.getServices();
			for(QoSTypeOfService s : serviceList){
				if(s.name.equals(policy.service)){
					//policy's service ToS bits
					pTos = s.tos;
				}
			}
			tosAction.setNetworkTypeOfService(pTos);
			actions.add((OFAction)tosAction);
			
			logger.info("Match is : {}", match.toString());
			//add the matches and actions and return.class.ge
			fm.setMatch(match)
				.setActions(actions)
				.setIdleTimeout((short) 0)  // infinite
				.setHardTimeout((short) 0)  // infinite
				.setBufferId(OFPacketOut.BUFFER_ID_NONE)
				.setFlags((short) 0)
				.setOutPort(OFPort.OFPP_NONE.getValue())
				.setPriority(Short.MAX_VALUE)
				.setLengthU((short)OFFlowMod.MINIMUM_LENGTH + OFActionNetworkTypeOfService.MINIMUM_LENGTH);
		}
		else{
			logger.error("Policy Misconfiguration");
		}
	return fm;	
	}
	
	
	
}
