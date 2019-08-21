package net.floodlightcontroller.ufs_qos;

import java.util.Collection;
import java.util.Map;

import org.projectfloodlight.openflow.protocol.OFFactory;
import org.projectfloodlight.openflow.protocol.OFMatchV3;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFPacketIn;
import org.projectfloodlight.openflow.protocol.OFPacketQueue;
import org.projectfloodlight.openflow.protocol.OFQueueGetConfigReply;
import org.projectfloodlight.openflow.protocol.OFQueueGetConfigRequest;
import org.projectfloodlight.openflow.protocol.OFType;
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
import net.floodlightcontroller.core.internal.OFMessageDecoder;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;


import net.floodlightcontroller.core.IFloodlightProviderService;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.staticentry.IStaticEntryPusherService;
import net.floodlightcontroller.storage.IStorageSourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.ListenableFuture;


public class QoS implements IOFMessageListener, IFloodlightModule {

	
	protected IFloodlightProviderService floodlightProvider;
	protected Set<Long> macAddresses;
	protected static Logger logger;
	protected IOFSwitchService switchService;
	protected IStorageSourceService storageSource;
	protected IStaticEntryPusherService flowPusher;
	
	
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
	}

	/**
	 * Metodo ouvinte
	 */
	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		
		floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
		
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

}
