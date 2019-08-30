package net.floodlightcontroller.ufs_qos;

public class QoSTypeOfService implements Comparable<QoSTypeOfService>{

	//identificacao unica para o servico
	public int sid;
	
	//nome do servico
	public String name;
	
	//melhor esforco
	public byte tos = 0x00;
	
	
	
	
	/**
	 * Construtor padrao para melhor esforco
	 */
	public QoSTypeOfService(){
		this.sid = -1;
		this.name = null;
		this.tos = 0x00;
	}
	
	
	/**
	 * Gera um unico identificador para instancia
	 * @return
	 */
    public int genID() {
        int uid = this.hashCode();
        if (uid < 0) {
            uid = uid * 15551;
            uid = Math.abs(uid);
        }
        return uid;
    }	 
	
	
	@Override
	public int compareTo(QoSTypeOfService policy) {
		return this.tos - ((QoSTypeOfService)policy).tos;
	}
	
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) sid;
		if(name != null){result = prime * result +  name.hashCode();}
		result = prime * result + (int) tos;
		return result;
	}

}
