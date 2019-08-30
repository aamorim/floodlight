package net.floodlightcontroller.ufs_qos;

import org.projectfloodlight.openflow.util.HexString;
/**
 * 
 * @author alex
 *
 */
public class QoSPolicy implements Comparable<QoSPolicy> {

	    //Criação de parametros
	
	    /**
	     * Identificação da Politica
	     */
		public long policyid;
				
		/**
		 * Nome para a Politica
		 */
		public String name;
		
		/**
		 * Porta de entrada. Essa pode ser uma porta lógica física ou definida por comutador.		
		 */
		public short ingressport;
		
		/**
		 * Endereço de origem Ethernet. Pode usar bitmask arbitrário
		 */
		public String ethsrc;
		
		/**
		 * Endereço de destino Ethernet. Pode usar bitmask arbitrário
		 */
		public String ethdst;	
		
		/**
		 * Indica o tipo Ethernet da carga útil do pacote openFlow, após a tag VLAN
		 */
		public short ethtype;
		
		/**
		 * Identificado da VLAN
		 */
		public short vlanid;	
		
		/**
		 * Número do protocolo IPv4 ou IPv6
		 */
		public byte protocol;
		
		/**
		 * Endereço de origem IPv4/IPv6. Pode usar máscara de sub-rede ou máscara de bits arbitrária
		 */
		public int ipsrc;
		
		/**
		 * Endereço de destino IPv4. Pode usar máscara de sub-rede ou máscara de bits arbitrária
		 */
		public int ipdst;	
		
		/**
		 * Diff Serv Code Point (DSCP). Part of the IPv4 ToS field or the IPv6 Traffic Class field.
		 */
		public byte tos;
		
		/**
		 * Porta de origem UDP/TCP
		 */
		public short tcpudpsrcport;
		
		/**
		 * Porta de destino UDP/TCP
		 */
		public short tcpudpdstport;	
		
		//Can be "all", "dpid" or [TODO: list of "dpid,dpid"]
		//TODO Morph to use a String[] of Switches
		public String sw;
		
		/**
		 * Fila
		 */
		public short queue;
		
		/**
		 * Porta para enfileirar
		 */
		public short enqueueport;
		
		/**
		 * tipo de serviço padrão para o melhor esforço
		 */
		public String service;
		
		/**
		 * Prioridade padrão
		 */
		public short priority = 0;
		
		/** -1's are check in QoS.java: policyToFlowMod() **/
		public QoSPolicy(){
			this.policyid = 0;
			this.name = null;
			this.ethtype = -1;
			this.protocol = -1;
			this.ingressport = -1;
			this.ipdst = -1;
			this.ipsrc = -1;
			this.tos = -1;
			this.vlanid = -1;
			this.ethsrc = null;
			this.ethdst = null;
			this.tcpudpdstport = -1;
			this.tcpudpsrcport = -1;
			this.sw = "all";
			this.queue = -1;
			this.enqueueport = -1;
			this.service = null;
			this.priority = 32767;
			
		}
		
		/**
	     * Generates a unique ID for the instance
	     * @return int representing the unique id
	     */
	    public int genID() {
	        int uid = this.hashCode();
	        if (uid < 0) {
	            uid = uid * 15551;
	            uid = Math.abs(uid);
	        }
	        return uid;
	    }
		
		/**
	     * Comparison method for Collections.sort method
	     * @param rule the policy to compare with
	     * @return number representing the result of comparison
	     * 0 if equal
	     * negative if less than 'policy'
	     * greater than zero if greater priority policy than 'policy'
	     */
		public int compareTo(QoSPolicy policy) {
			return this.priority - ((QoSPolicy)policy).priority;
	    }
		
		/**
		 * @param policy
		 * @return
		 */
		public boolean isSameAs(QoSPolicy policy){
			//check object and unique name of policy
			if (this.equals(policy) || this.name.equals(policy.name)){
				return true;
			}
			else{
				return false;
			}
		}
		
		/**
		 * Override hashcode
		 */
		@Override
		public int hashCode(){
		final int prime = 2521;
	    int result = super.hashCode();
	    result = prime * result + (int) policyid;
	    if(name != null){result = prime * result + name.hashCode();}
	    result = prime * result + (int) ethtype;
	    result = prime * result + (int) protocol;
	    result = prime * result + (int) ingressport;
	    result = prime * result + ipdst;
	    result = prime * result + ipsrc;
	    result = prime * result + (int) tos;
	    result = prime * result + (int) vlanid;
	    if(ethsrc != null){result = prime * result + (int) HexString.toLong(ethsrc);}
	    if(ethdst != null){result = prime * result + (int) HexString.toLong(ethdst);}
	    result = prime * result + (int) tcpudpsrcport;
	    result = prime * result + (int) tcpudpdstport;
	    if(sw != null){result = prime * result + sw.hashCode();}
	    result = prime * result + (int) queue;
	    result = prime * result + (int) enqueueport;
	    if(service != null){result = prime * result + service.hashCode();}
	    result = prime * result + (int) priority;
		
	    return result;
		}

}
