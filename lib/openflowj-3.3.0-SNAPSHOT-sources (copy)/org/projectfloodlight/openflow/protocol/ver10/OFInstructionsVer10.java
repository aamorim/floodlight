// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_factory_class.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver10;

import org.projectfloodlight.openflow.protocol.*;
import org.projectfloodlight.openflow.protocol.action.*;
import org.projectfloodlight.openflow.protocol.actionid.*;
import org.projectfloodlight.openflow.protocol.bsntlv.*;
import org.projectfloodlight.openflow.protocol.errormsg.*;
import org.projectfloodlight.openflow.protocol.meterband.*;
import org.projectfloodlight.openflow.protocol.instruction.*;
import org.projectfloodlight.openflow.protocol.instructionid.*;
import org.projectfloodlight.openflow.protocol.match.*;
import org.projectfloodlight.openflow.protocol.stat.*;
import org.projectfloodlight.openflow.protocol.oxm.*;
import org.projectfloodlight.openflow.protocol.oxs.*;
import org.projectfloodlight.openflow.protocol.queueprop.*;
import org.projectfloodlight.openflow.types.*;
import org.projectfloodlight.openflow.util.*;
import org.projectfloodlight.openflow.exceptions.*;
import java.util.List;
import java.util.Set;


public class OFInstructionsVer10 implements OFInstructions {
    public final static OFInstructionsVer10 INSTANCE = new OFInstructionsVer10();




    public OFInstructionApplyActions.Builder buildApplyActions() {
        throw new UnsupportedOperationException("OFInstructionApplyActions not supported in version 1.0");
    }
    public OFInstructionApplyActions applyActions(List<OFAction> actions) {
        throw new UnsupportedOperationException("OFInstructionApplyActions not supported in version 1.0");
    }

    public OFInstructionClearActions clearActions() {
        throw new UnsupportedOperationException("OFInstructionClearActions not supported in version 1.0");
    }

    public OFInstructionGotoTable.Builder buildGotoTable() {
        throw new UnsupportedOperationException("OFInstructionGotoTable not supported in version 1.0");
    }
    public OFInstructionGotoTable gotoTable(TableId tableId) {
        throw new UnsupportedOperationException("OFInstructionGotoTable not supported in version 1.0");
    }

    public OFInstructionWriteActions.Builder buildWriteActions() {
        throw new UnsupportedOperationException("OFInstructionWriteActions not supported in version 1.0");
    }
    public OFInstructionWriteActions writeActions(List<OFAction> actions) {
        throw new UnsupportedOperationException("OFInstructionWriteActions not supported in version 1.0");
    }

    public OFInstructionWriteMetadata.Builder buildWriteMetadata() {
        throw new UnsupportedOperationException("OFInstructionWriteMetadata not supported in version 1.0");
    }
    public OFInstructionWriteMetadata writeMetadata(U64 metadata, U64 metadataMask) {
        throw new UnsupportedOperationException("OFInstructionWriteMetadata not supported in version 1.0");
    }

    public OFInstructionBsnArpOffload bsnArpOffload() {
        throw new UnsupportedOperationException("OFInstructionBsnArpOffload not supported in version 1.0");
    }

    public OFInstructionBsnAutoNegotiation bsnAutoNegotiation() {
        throw new UnsupportedOperationException("OFInstructionBsnAutoNegotiation not supported in version 1.0");
    }

    public OFInstructionBsnDeny bsnDeny() {
        throw new UnsupportedOperationException("OFInstructionBsnDeny not supported in version 1.0");
    }

    public OFInstructionBsnDhcpOffload bsnDhcpOffload() {
        throw new UnsupportedOperationException("OFInstructionBsnDhcpOffload not supported in version 1.0");
    }

    public OFInstructionBsnDisableL3 bsnDisableL3() {
        throw new UnsupportedOperationException("OFInstructionBsnDisableL3 not supported in version 1.0");
    }

    public OFInstructionBsnDisableSplitHorizonCheck bsnDisableSplitHorizonCheck() {
        throw new UnsupportedOperationException("OFInstructionBsnDisableSplitHorizonCheck not supported in version 1.0");
    }

    public OFInstructionBsnDisableSrcMacCheck bsnDisableSrcMacCheck() {
        throw new UnsupportedOperationException("OFInstructionBsnDisableSrcMacCheck not supported in version 1.0");
    }

    public OFInstructionBsnDisableVlanCounters bsnDisableVlanCounters() {
        throw new UnsupportedOperationException("OFInstructionBsnDisableVlanCounters not supported in version 1.0");
    }

    public OFInstructionBsnHashSelect.Builder buildBsnHashSelect() {
        throw new UnsupportedOperationException("OFInstructionBsnHashSelect not supported in version 1.0");
    }
    public OFInstructionBsnHashSelect bsnHashSelect(Set<OFBsnHashSelectFlags> flags) {
        throw new UnsupportedOperationException("OFInstructionBsnHashSelect not supported in version 1.0");
    }

    public OFInstructionBsnInternalPriority.Builder buildBsnInternalPriority() {
        throw new UnsupportedOperationException("OFInstructionBsnInternalPriority not supported in version 1.0");
    }
    public OFInstructionBsnInternalPriority bsnInternalPriority(long value) {
        throw new UnsupportedOperationException("OFInstructionBsnInternalPriority not supported in version 1.0");
    }

    public OFInstructionBsnNdpOffload bsnNdpOffload() {
        throw new UnsupportedOperationException("OFInstructionBsnNdpOffload not supported in version 1.0");
    }

    public OFInstructionBsnPacketOfDeath bsnPacketOfDeath() {
        throw new UnsupportedOperationException("OFInstructionBsnPacketOfDeath not supported in version 1.0");
    }

    public OFInstructionBsnPermit bsnPermit() {
        throw new UnsupportedOperationException("OFInstructionBsnPermit not supported in version 1.0");
    }

    public OFInstructionBsnPrioritizePdus bsnPrioritizePdus() {
        throw new UnsupportedOperationException("OFInstructionBsnPrioritizePdus not supported in version 1.0");
    }

    public OFInstructionBsnRequireVlanXlate bsnRequireVlanXlate() {
        throw new UnsupportedOperationException("OFInstructionBsnRequireVlanXlate not supported in version 1.0");
    }

    public OFInstructionBsnSpanDestination bsnSpanDestination() {
        throw new UnsupportedOperationException("OFInstructionBsnSpanDestination not supported in version 1.0");
    }

    public OFInstructionMeter.Builder buildMeter() {
        throw new UnsupportedOperationException("OFInstructionMeter not supported in version 1.0");
    }
    public OFInstructionMeter meter(long meterId) {
        throw new UnsupportedOperationException("OFInstructionMeter not supported in version 1.0");
    }

    public OFInstructionStatTrigger.Builder buildStatTrigger() {
        throw new UnsupportedOperationException("OFInstructionStatTrigger not supported in version 1.0");
    }
    public OFInstructionStatTrigger statTrigger(Set<OFStatTriggerFlags> flags, OFOxsList thresholds) {
        throw new UnsupportedOperationException("OFInstructionStatTrigger not supported in version 1.0");
    }

    public OFMessageReader<OFInstruction> getReader() {
        throw new UnsupportedOperationException("Reader<OFInstruction> not supported in version 1.0");
    }


    public OFVersion getVersion() {
            return OFVersion.OF_10;
    }
}
