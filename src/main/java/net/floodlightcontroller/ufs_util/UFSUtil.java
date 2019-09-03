package net.floodlightcontroller.ufs_util;

import org.projectfloodlight.openflow.types.EthType;

public abstract class UFSUtil {
	
	
	/**
	 * 
	 * @param eth
	 * @return verdadeiro se o o protocolo for igual a IPv6 ou IPv4
	 */
	public boolean isIPv4v6(EthType eth){
		return (EthType.IPv4 == eth) || (EthType.IPv6 == eth); 
	}
	
	/**
	 * 
	 * @param eth
	 * @return verdadeiro se o o protocolo for igual a IPv4
	 */
	public boolean isIPv4(EthType eth) {
		return EthType.IPv4 == eth;
	}
	
	/**
	 * 
	 * @param eth
	 * @return verdadeiro se o o protocolo for igual a IPv6
	 */
	public boolean isIPv6(EthType eth) {
		return EthType.IPv6 == eth;
	}
	

}
