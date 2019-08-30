package net.floodlightcontroller.ufs_qos;

import java.util.List;

import net.floodlightcontroller.core.module.IFloodlightService;

public interface IQoSService extends IFloodlightService{
	
	/**
     * Enables/disables the Quality of Service Tool.
     * @param enable Whether to enable or disable the Tool.
     */
    public void enableQoS(boolean enable);
    
    /**
     * Checked the enabledness
     * @param boolean.
     */
    public boolean isEnabled();
    
    /**
     * Adds a Type of Service
     */
    public void addService(QoSTypeOfService service);
    
    /**
     * Adds a Type of Service
     */
    public List<QoSTypeOfService> getServices();

    /**
     * Deletes a Type of Service
     */
    public void deleteService(int sid);

    /**
     * Returns all of the QoS rules
     * @return List of all rules
     */
    public List<QoSPolicy> getPolicies();
    
    /**
     * 
     * @param policy
     */
    public void addPolicyToNetwork(QoSPolicy policy);
    
    /**
     * Adds a QoS Policy
     */
    public void addPolicy(QoSPolicy policy, String swid);

    /**
     * adds policy to switch
     */
    public void addPolicy(QoSPolicy policy);
    
    /**
     * 
     * @param policy from all switches
     */
    public void deletePolicyFromNetwork(String policyName);
    
    /**
     * Deletes a QoS Policy
     */
    public void deletePolicy(QoSPolicy policy);
    
    /**
     * deletes policy from switches
     */
    public void deletePolicy(String switches, String policyName);

}
