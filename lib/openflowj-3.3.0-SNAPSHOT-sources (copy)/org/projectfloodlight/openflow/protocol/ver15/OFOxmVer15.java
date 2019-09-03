// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_virtual_class.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver15;

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
import io.netty.buffer.ByteBuf;

abstract class OFOxmVer15 {
    // version: 1.5
    final static byte WIRE_VERSION = 6;
    final static int MINIMUM_LENGTH = 4;


    public final static OFOxmVer15.Reader READER = new Reader();

    static class Reader implements OFMessageReader<OFOxm<?>> {
        @Override
        public OFOxm<?> readFrom(ByteBuf bb) throws OFParseError {
            if(bb.readableBytes() < MINIMUM_LENGTH)
                return null;
            int start = bb.readerIndex();
            int typeLen = bb.readInt();
            bb.readerIndex(start);
            switch(typeLen) {
               case (int) 0x80002a02:
                   // discriminator value 0x80002a02L=0x80002a02L for class OFOxmArpOpVer15
                   return OFOxmArpOpVer15.READER.readFrom(bb);
               case (int) 0x80002b04:
                   // discriminator value 0x80002b04L=0x80002b04L for class OFOxmArpOpMaskedVer15
                   return OFOxmArpOpMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003006:
                   // discriminator value 0x80003006L=0x80003006L for class OFOxmArpShaVer15
                   return OFOxmArpShaVer15.READER.readFrom(bb);
               case (int) 0x8000310c:
                   // discriminator value 0x8000310cL=0x8000310cL for class OFOxmArpShaMaskedVer15
                   return OFOxmArpShaMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002c04:
                   // discriminator value 0x80002c04L=0x80002c04L for class OFOxmArpSpaVer15
                   return OFOxmArpSpaVer15.READER.readFrom(bb);
               case (int) 0x80002d08:
                   // discriminator value 0x80002d08L=0x80002d08L for class OFOxmArpSpaMaskedVer15
                   return OFOxmArpSpaMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003206:
                   // discriminator value 0x80003206L=0x80003206L for class OFOxmArpThaVer15
                   return OFOxmArpThaVer15.READER.readFrom(bb);
               case (int) 0x8000330c:
                   // discriminator value 0x8000330cL=0x8000330cL for class OFOxmArpThaMaskedVer15
                   return OFOxmArpThaMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002e04:
                   // discriminator value 0x80002e04L=0x80002e04L for class OFOxmArpTpaVer15
                   return OFOxmArpTpaVer15.READER.readFrom(bb);
               case (int) 0x80002f08:
                   // discriminator value 0x80002f08L=0x80002f08L for class OFOxmArpTpaMaskedVer15
                   return OFOxmArpTpaMaskedVer15.READER.readFrom(bb);
               case 0x30e04:
                   // discriminator value 0x30e04L=0x30e04L for class OFOxmBsnEgrPortGroupIdVer15
                   return OFOxmBsnEgrPortGroupIdVer15.READER.readFrom(bb);
               case 0x30f08:
                   // discriminator value 0x30f08L=0x30f08L for class OFOxmBsnEgrPortGroupIdMaskedVer15
                   return OFOxmBsnEgrPortGroupIdMaskedVer15.READER.readFrom(bb);
               case 0x30010:
                   // discriminator value 0x30010L=0x30010L for class OFOxmBsnInPorts128Ver15
                   return OFOxmBsnInPorts128Ver15.READER.readFrom(bb);
               case 0x30120:
                   // discriminator value 0x30120L=0x30120L for class OFOxmBsnInPorts128MaskedVer15
                   return OFOxmBsnInPorts128MaskedVer15.READER.readFrom(bb);
               case 0x32640:
                   // discriminator value 0x32640L=0x32640L for class OFOxmBsnInPorts512Ver15
                   return OFOxmBsnInPorts512Ver15.READER.readFrom(bb);
               case 0x32780:
                   // discriminator value 0x32780L=0x32780L for class OFOxmBsnInPorts512MaskedVer15
                   return OFOxmBsnInPorts512MaskedVer15.READER.readFrom(bb);
               case 0x32804:
                   // discriminator value 0x32804L=0x32804L for class OFOxmBsnIngressPortGroupIdVer15
                   return OFOxmBsnIngressPortGroupIdVer15.READER.readFrom(bb);
               case 0x32908:
                   // discriminator value 0x32908L=0x32908L for class OFOxmBsnIngressPortGroupIdMaskedVer15
                   return OFOxmBsnIngressPortGroupIdMaskedVer15.READER.readFrom(bb);
               case 0x33401:
                   // discriminator value 0x33401L=0x33401L for class OFOxmBsnIpFragmentationVer15
                   return OFOxmBsnIpFragmentationVer15.READER.readFrom(bb);
               case 0x33502:
                   // discriminator value 0x33502L=0x33502L for class OFOxmBsnIpFragmentationMaskedVer15
                   return OFOxmBsnIpFragmentationMaskedVer15.READER.readFrom(bb);
               case 0x32401:
                   // discriminator value 0x32401L=0x32401L for class OFOxmBsnL2CacheHitVer15
                   return OFOxmBsnL2CacheHitVer15.READER.readFrom(bb);
               case 0x32502:
                   // discriminator value 0x32502L=0x32502L for class OFOxmBsnL2CacheHitMaskedVer15
                   return OFOxmBsnL2CacheHitMaskedVer15.READER.readFrom(bb);
               case 0x30804:
                   // discriminator value 0x30804L=0x30804L for class OFOxmBsnL3InterfaceClassIdVer15
                   return OFOxmBsnL3InterfaceClassIdVer15.READER.readFrom(bb);
               case 0x30908:
                   // discriminator value 0x30908L=0x30908L for class OFOxmBsnL3InterfaceClassIdMaskedVer15
                   return OFOxmBsnL3InterfaceClassIdMaskedVer15.READER.readFrom(bb);
               case 0x30a04:
                   // discriminator value 0x30a04L=0x30a04L for class OFOxmBsnL3SrcClassIdVer15
                   return OFOxmBsnL3SrcClassIdVer15.READER.readFrom(bb);
               case 0x30b08:
                   // discriminator value 0x30b08L=0x30b08L for class OFOxmBsnL3SrcClassIdMaskedVer15
                   return OFOxmBsnL3SrcClassIdMaskedVer15.READER.readFrom(bb);
               case 0x30204:
                   // discriminator value 0x30204L=0x30204L for class OFOxmBsnLagIdVer15
                   return OFOxmBsnLagIdVer15.READER.readFrom(bb);
               case 0x30308:
                   // discriminator value 0x30308L=0x30308L for class OFOxmBsnLagIdMaskedVer15
                   return OFOxmBsnLagIdMaskedVer15.READER.readFrom(bb);
               case 0x32002:
                   // discriminator value 0x32002L=0x32002L for class OFOxmBsnTcpFlagsVer15
                   return OFOxmBsnTcpFlagsVer15.READER.readFrom(bb);
               case 0x32104:
                   // discriminator value 0x32104L=0x32104L for class OFOxmBsnTcpFlagsMaskedVer15
                   return OFOxmBsnTcpFlagsMaskedVer15.READER.readFrom(bb);
               case 0x31004:
                   // discriminator value 0x31004L=0x31004L for class OFOxmBsnUdf0Ver15
                   return OFOxmBsnUdf0Ver15.READER.readFrom(bb);
               case 0x31108:
                   // discriminator value 0x31108L=0x31108L for class OFOxmBsnUdf0MaskedVer15
                   return OFOxmBsnUdf0MaskedVer15.READER.readFrom(bb);
               case 0x31204:
                   // discriminator value 0x31204L=0x31204L for class OFOxmBsnUdf1Ver15
                   return OFOxmBsnUdf1Ver15.READER.readFrom(bb);
               case 0x31308:
                   // discriminator value 0x31308L=0x31308L for class OFOxmBsnUdf1MaskedVer15
                   return OFOxmBsnUdf1MaskedVer15.READER.readFrom(bb);
               case 0x31404:
                   // discriminator value 0x31404L=0x31404L for class OFOxmBsnUdf2Ver15
                   return OFOxmBsnUdf2Ver15.READER.readFrom(bb);
               case 0x31508:
                   // discriminator value 0x31508L=0x31508L for class OFOxmBsnUdf2MaskedVer15
                   return OFOxmBsnUdf2MaskedVer15.READER.readFrom(bb);
               case 0x31604:
                   // discriminator value 0x31604L=0x31604L for class OFOxmBsnUdf3Ver15
                   return OFOxmBsnUdf3Ver15.READER.readFrom(bb);
               case 0x31708:
                   // discriminator value 0x31708L=0x31708L for class OFOxmBsnUdf3MaskedVer15
                   return OFOxmBsnUdf3MaskedVer15.READER.readFrom(bb);
               case 0x31804:
                   // discriminator value 0x31804L=0x31804L for class OFOxmBsnUdf4Ver15
                   return OFOxmBsnUdf4Ver15.READER.readFrom(bb);
               case 0x31908:
                   // discriminator value 0x31908L=0x31908L for class OFOxmBsnUdf4MaskedVer15
                   return OFOxmBsnUdf4MaskedVer15.READER.readFrom(bb);
               case 0x31a04:
                   // discriminator value 0x31a04L=0x31a04L for class OFOxmBsnUdf5Ver15
                   return OFOxmBsnUdf5Ver15.READER.readFrom(bb);
               case 0x31b08:
                   // discriminator value 0x31b08L=0x31b08L for class OFOxmBsnUdf5MaskedVer15
                   return OFOxmBsnUdf5MaskedVer15.READER.readFrom(bb);
               case 0x31c04:
                   // discriminator value 0x31c04L=0x31c04L for class OFOxmBsnUdf6Ver15
                   return OFOxmBsnUdf6Ver15.READER.readFrom(bb);
               case 0x31d08:
                   // discriminator value 0x31d08L=0x31d08L for class OFOxmBsnUdf6MaskedVer15
                   return OFOxmBsnUdf6MaskedVer15.READER.readFrom(bb);
               case 0x31e04:
                   // discriminator value 0x31e04L=0x31e04L for class OFOxmBsnUdf7Ver15
                   return OFOxmBsnUdf7Ver15.READER.readFrom(bb);
               case 0x31f08:
                   // discriminator value 0x31f08L=0x31f08L for class OFOxmBsnUdf7MaskedVer15
                   return OFOxmBsnUdf7MaskedVer15.READER.readFrom(bb);
               case 0x32204:
                   // discriminator value 0x32204L=0x32204L for class OFOxmBsnVlanXlatePortGroupIdVer15
                   return OFOxmBsnVlanXlatePortGroupIdVer15.READER.readFrom(bb);
               case 0x32308:
                   // discriminator value 0x32308L=0x32308L for class OFOxmBsnVlanXlatePortGroupIdMaskedVer15
                   return OFOxmBsnVlanXlatePortGroupIdMaskedVer15.READER.readFrom(bb);
               case 0x30404:
                   // discriminator value 0x30404L=0x30404L for class OFOxmBsnVrfVer15
                   return OFOxmBsnVrfVer15.READER.readFrom(bb);
               case 0x30508:
                   // discriminator value 0x30508L=0x30508L for class OFOxmBsnVrfMaskedVer15
                   return OFOxmBsnVrfMaskedVer15.READER.readFrom(bb);
               case 0x1f610:
                   // discriminator value 0x1f610L=0x1f610L for class OFOxmConnTrackingIpv6DstVer15
                   return OFOxmConnTrackingIpv6DstVer15.READER.readFrom(bb);
               case 0x1f720:
                   // discriminator value 0x1f720L=0x1f720L for class OFOxmConnTrackingIpv6DstMaskedVer15
                   return OFOxmConnTrackingIpv6DstMaskedVer15.READER.readFrom(bb);
               case 0x1f410:
                   // discriminator value 0x1f410L=0x1f410L for class OFOxmConnTrackingIpv6SrcVer15
                   return OFOxmConnTrackingIpv6SrcVer15.READER.readFrom(bb);
               case 0x1f520:
                   // discriminator value 0x1f520L=0x1f520L for class OFOxmConnTrackingIpv6SrcMaskedVer15
                   return OFOxmConnTrackingIpv6SrcMaskedVer15.READER.readFrom(bb);
               case 0x1d810:
                   // discriminator value 0x1d810L=0x1d810L for class OFOxmConnTrackingLabelVer15
                   return OFOxmConnTrackingLabelVer15.READER.readFrom(bb);
               case 0x1d920:
                   // discriminator value 0x1d920L=0x1d920L for class OFOxmConnTrackingLabelMaskedVer15
                   return OFOxmConnTrackingLabelMaskedVer15.READER.readFrom(bb);
               case 0x1d604:
                   // discriminator value 0x1d604L=0x1d604L for class OFOxmConnTrackingMarkVer15
                   return OFOxmConnTrackingMarkVer15.READER.readFrom(bb);
               case 0x1d708:
                   // discriminator value 0x1d708L=0x1d708L for class OFOxmConnTrackingMarkMaskedVer15
                   return OFOxmConnTrackingMarkMaskedVer15.READER.readFrom(bb);
               case 0x1f204:
                   // discriminator value 0x1f204L=0x1f204L for class OFOxmConnTrackingNwDstVer15
                   return OFOxmConnTrackingNwDstVer15.READER.readFrom(bb);
               case 0x1f308:
                   // discriminator value 0x1f308L=0x1f308L for class OFOxmConnTrackingNwDstMaskedVer15
                   return OFOxmConnTrackingNwDstMaskedVer15.READER.readFrom(bb);
               case 0x1ee01:
                   // discriminator value 0x1ee01L=0x1ee01L for class OFOxmConnTrackingNwProtoVer15
                   return OFOxmConnTrackingNwProtoVer15.READER.readFrom(bb);
               case 0x1ef02:
                   // discriminator value 0x1ef02L=0x1ef02L for class OFOxmConnTrackingNwProtoMaskedVer15
                   return OFOxmConnTrackingNwProtoMaskedVer15.READER.readFrom(bb);
               case 0x1f004:
                   // discriminator value 0x1f004L=0x1f004L for class OFOxmConnTrackingNwSrcVer15
                   return OFOxmConnTrackingNwSrcVer15.READER.readFrom(bb);
               case 0x1f108:
                   // discriminator value 0x1f108L=0x1f108L for class OFOxmConnTrackingNwSrcMaskedVer15
                   return OFOxmConnTrackingNwSrcMaskedVer15.READER.readFrom(bb);
               case 0x1d204:
                   // discriminator value 0x1d204L=0x1d204L for class OFOxmConnTrackingStateVer15
                   return OFOxmConnTrackingStateVer15.READER.readFrom(bb);
               case 0x1d308:
                   // discriminator value 0x1d308L=0x1d308L for class OFOxmConnTrackingStateMaskedVer15
                   return OFOxmConnTrackingStateMaskedVer15.READER.readFrom(bb);
               case 0x1fa02:
                   // discriminator value 0x1fa02L=0x1fa02L for class OFOxmConnTrackingTpDstVer15
                   return OFOxmConnTrackingTpDstVer15.READER.readFrom(bb);
               case 0x1fb04:
                   // discriminator value 0x1fb04L=0x1fb04L for class OFOxmConnTrackingTpDstMaskedVer15
                   return OFOxmConnTrackingTpDstMaskedVer15.READER.readFrom(bb);
               case 0x1f802:
                   // discriminator value 0x1f802L=0x1f802L for class OFOxmConnTrackingTpSrcVer15
                   return OFOxmConnTrackingTpSrcVer15.READER.readFrom(bb);
               case 0x1f904:
                   // discriminator value 0x1f904L=0x1f904L for class OFOxmConnTrackingTpSrcMaskedVer15
                   return OFOxmConnTrackingTpSrcMaskedVer15.READER.readFrom(bb);
               case 0x1d402:
                   // discriminator value 0x1d402L=0x1d402L for class OFOxmConnTrackingZoneVer15
                   return OFOxmConnTrackingZoneVer15.READER.readFrom(bb);
               case 0x1d504:
                   // discriminator value 0x1d504L=0x1d504L for class OFOxmConnTrackingZoneMaskedVer15
                   return OFOxmConnTrackingZoneMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000606:
                   // discriminator value 0x80000606L=0x80000606L for class OFOxmEthDstVer15
                   return OFOxmEthDstVer15.READER.readFrom(bb);
               case (int) 0x8000070c:
                   // discriminator value 0x8000070cL=0x8000070cL for class OFOxmEthDstMaskedVer15
                   return OFOxmEthDstMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000806:
                   // discriminator value 0x80000806L=0x80000806L for class OFOxmEthSrcVer15
                   return OFOxmEthSrcVer15.READER.readFrom(bb);
               case (int) 0x8000090c:
                   // discriminator value 0x8000090cL=0x8000090cL for class OFOxmEthSrcMaskedVer15
                   return OFOxmEthSrcMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000a02:
                   // discriminator value 0x80000a02L=0x80000a02L for class OFOxmEthTypeVer15
                   return OFOxmEthTypeVer15.READER.readFrom(bb);
               case (int) 0x80000b04:
                   // discriminator value 0x80000b04L=0x80000b04L for class OFOxmEthTypeMaskedVer15
                   return OFOxmEthTypeMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002801:
                   // discriminator value 0x80002801L=0x80002801L for class OFOxmIcmpv4CodeVer15
                   return OFOxmIcmpv4CodeVer15.READER.readFrom(bb);
               case (int) 0x80002902:
                   // discriminator value 0x80002902L=0x80002902L for class OFOxmIcmpv4CodeMaskedVer15
                   return OFOxmIcmpv4CodeMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002601:
                   // discriminator value 0x80002601L=0x80002601L for class OFOxmIcmpv4TypeVer15
                   return OFOxmIcmpv4TypeVer15.READER.readFrom(bb);
               case (int) 0x80002702:
                   // discriminator value 0x80002702L=0x80002702L for class OFOxmIcmpv4TypeMaskedVer15
                   return OFOxmIcmpv4TypeMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003c01:
                   // discriminator value 0x80003c01L=0x80003c01L for class OFOxmIcmpv6CodeVer15
                   return OFOxmIcmpv6CodeVer15.READER.readFrom(bb);
               case (int) 0x80003d02:
                   // discriminator value 0x80003d02L=0x80003d02L for class OFOxmIcmpv6CodeMaskedVer15
                   return OFOxmIcmpv6CodeMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003a01:
                   // discriminator value 0x80003a01L=0x80003a01L for class OFOxmIcmpv6TypeVer15
                   return OFOxmIcmpv6TypeVer15.READER.readFrom(bb);
               case (int) 0x80003b02:
                   // discriminator value 0x80003b02L=0x80003b02L for class OFOxmIcmpv6TypeMaskedVer15
                   return OFOxmIcmpv6TypeMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000204:
                   // discriminator value 0x80000204L=0x80000204L for class OFOxmInPhyPortVer15
                   return OFOxmInPhyPortVer15.READER.readFrom(bb);
               case (int) 0x80000308:
                   // discriminator value 0x80000308L=0x80000308L for class OFOxmInPhyPortMaskedVer15
                   return OFOxmInPhyPortMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000004:
                   // discriminator value 0x80000004L=0x80000004L for class OFOxmInPortVer15
                   return OFOxmInPortVer15.READER.readFrom(bb);
               case (int) 0x80000108:
                   // discriminator value 0x80000108L=0x80000108L for class OFOxmInPortMaskedVer15
                   return OFOxmInPortMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001001:
                   // discriminator value 0x80001001L=0x80001001L for class OFOxmIpDscpVer15
                   return OFOxmIpDscpVer15.READER.readFrom(bb);
               case (int) 0x80001102:
                   // discriminator value 0x80001102L=0x80001102L for class OFOxmIpDscpMaskedVer15
                   return OFOxmIpDscpMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001201:
                   // discriminator value 0x80001201L=0x80001201L for class OFOxmIpEcnVer15
                   return OFOxmIpEcnVer15.READER.readFrom(bb);
               case (int) 0x80001302:
                   // discriminator value 0x80001302L=0x80001302L for class OFOxmIpEcnMaskedVer15
                   return OFOxmIpEcnMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001401:
                   // discriminator value 0x80001401L=0x80001401L for class OFOxmIpProtoVer15
                   return OFOxmIpProtoVer15.READER.readFrom(bb);
               case (int) 0x80001502:
                   // discriminator value 0x80001502L=0x80001502L for class OFOxmIpProtoMaskedVer15
                   return OFOxmIpProtoMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001804:
                   // discriminator value 0x80001804L=0x80001804L for class OFOxmIpv4DstVer15
                   return OFOxmIpv4DstVer15.READER.readFrom(bb);
               case (int) 0x80001908:
                   // discriminator value 0x80001908L=0x80001908L for class OFOxmIpv4DstMaskedVer15
                   return OFOxmIpv4DstMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001604:
                   // discriminator value 0x80001604L=0x80001604L for class OFOxmIpv4SrcVer15
                   return OFOxmIpv4SrcVer15.READER.readFrom(bb);
               case (int) 0x80001708:
                   // discriminator value 0x80001708L=0x80001708L for class OFOxmIpv4SrcMaskedVer15
                   return OFOxmIpv4SrcMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003610:
                   // discriminator value 0x80003610L=0x80003610L for class OFOxmIpv6DstVer15
                   return OFOxmIpv6DstVer15.READER.readFrom(bb);
               case (int) 0x80003720:
                   // discriminator value 0x80003720L=0x80003720L for class OFOxmIpv6DstMaskedVer15
                   return OFOxmIpv6DstMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003804:
                   // discriminator value 0x80003804L=0x80003804L for class OFOxmIpv6FlabelVer15
                   return OFOxmIpv6FlabelVer15.READER.readFrom(bb);
               case (int) 0x80003908:
                   // discriminator value 0x80003908L=0x80003908L for class OFOxmIpv6FlabelMaskedVer15
                   return OFOxmIpv6FlabelMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004006:
                   // discriminator value 0x80004006L=0x80004006L for class OFOxmIpv6NdSllVer15
                   return OFOxmIpv6NdSllVer15.READER.readFrom(bb);
               case (int) 0x8000410c:
                   // discriminator value 0x8000410cL=0x8000410cL for class OFOxmIpv6NdSllMaskedVer15
                   return OFOxmIpv6NdSllMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003e10:
                   // discriminator value 0x80003e10L=0x80003e10L for class OFOxmIpv6NdTargetVer15
                   return OFOxmIpv6NdTargetVer15.READER.readFrom(bb);
               case (int) 0x80003f20:
                   // discriminator value 0x80003f20L=0x80003f20L for class OFOxmIpv6NdTargetMaskedVer15
                   return OFOxmIpv6NdTargetMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004206:
                   // discriminator value 0x80004206L=0x80004206L for class OFOxmIpv6NdTllVer15
                   return OFOxmIpv6NdTllVer15.READER.readFrom(bb);
               case (int) 0x8000430c:
                   // discriminator value 0x8000430cL=0x8000430cL for class OFOxmIpv6NdTllMaskedVer15
                   return OFOxmIpv6NdTllMaskedVer15.READER.readFrom(bb);
               case (int) 0x80003410:
                   // discriminator value 0x80003410L=0x80003410L for class OFOxmIpv6SrcVer15
                   return OFOxmIpv6SrcVer15.READER.readFrom(bb);
               case (int) 0x80003520:
                   // discriminator value 0x80003520L=0x80003520L for class OFOxmIpv6SrcMaskedVer15
                   return OFOxmIpv6SrcMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000408:
                   // discriminator value 0x80000408L=0x80000408L for class OFOxmMetadataVer15
                   return OFOxmMetadataVer15.READER.readFrom(bb);
               case (int) 0x80000510:
                   // discriminator value 0x80000510L=0x80000510L for class OFOxmMetadataMaskedVer15
                   return OFOxmMetadataMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004404:
                   // discriminator value 0x80004404L=0x80004404L for class OFOxmMplsLabelVer15
                   return OFOxmMplsLabelVer15.READER.readFrom(bb);
               case (int) 0x80004508:
                   // discriminator value 0x80004508L=0x80004508L for class OFOxmMplsLabelMaskedVer15
                   return OFOxmMplsLabelMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004601:
                   // discriminator value 0x80004601L=0x80004601L for class OFOxmMplsTcVer15
                   return OFOxmMplsTcVer15.READER.readFrom(bb);
               case (int) 0x80004702:
                   // discriminator value 0x80004702L=0x80004702L for class OFOxmMplsTcMaskedVer15
                   return OFOxmMplsTcMaskedVer15.READER.readFrom(bb);
               case (int) 0xffff5406:
                   // discriminator value 0xffff5406L=0xffff5406L for class OFOxmOvsTcpFlagsVer15
                   return OFOxmOvsTcpFlagsVer15.READER.readFrom(bb);
               case (int) 0xffff5508:
                   // discriminator value 0xffff5508L=0xffff5508L for class OFOxmOvsTcpFlagsMaskedVer15
                   return OFOxmOvsTcpFlagsMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002402:
                   // discriminator value 0x80002402L=0x80002402L for class OFOxmSctpDstVer15
                   return OFOxmSctpDstVer15.READER.readFrom(bb);
               case (int) 0x80002504:
                   // discriminator value 0x80002504L=0x80002504L for class OFOxmSctpDstMaskedVer15
                   return OFOxmSctpDstMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002202:
                   // discriminator value 0x80002202L=0x80002202L for class OFOxmSctpSrcVer15
                   return OFOxmSctpSrcVer15.READER.readFrom(bb);
               case (int) 0x80002304:
                   // discriminator value 0x80002304L=0x80002304L for class OFOxmSctpSrcMaskedVer15
                   return OFOxmSctpSrcMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001c02:
                   // discriminator value 0x80001c02L=0x80001c02L for class OFOxmTcpDstVer15
                   return OFOxmTcpDstVer15.READER.readFrom(bb);
               case (int) 0x80001d04:
                   // discriminator value 0x80001d04L=0x80001d04L for class OFOxmTcpDstMaskedVer15
                   return OFOxmTcpDstMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001a02:
                   // discriminator value 0x80001a02L=0x80001a02L for class OFOxmTcpSrcVer15
                   return OFOxmTcpSrcVer15.READER.readFrom(bb);
               case (int) 0x80001b04:
                   // discriminator value 0x80001b04L=0x80001b04L for class OFOxmTcpSrcMaskedVer15
                   return OFOxmTcpSrcMaskedVer15.READER.readFrom(bb);
               case 0x14004:
                   // discriminator value 0x14004L=0x14004L for class OFOxmTunnelIpv4DstVer15
                   return OFOxmTunnelIpv4DstVer15.READER.readFrom(bb);
               case 0x14108:
                   // discriminator value 0x14108L=0x14108L for class OFOxmTunnelIpv4DstMaskedVer15
                   return OFOxmTunnelIpv4DstMaskedVer15.READER.readFrom(bb);
               case 0x13e04:
                   // discriminator value 0x13e04L=0x13e04L for class OFOxmTunnelIpv4SrcVer15
                   return OFOxmTunnelIpv4SrcVer15.READER.readFrom(bb);
               case 0x13f08:
                   // discriminator value 0x13f08L=0x13f08L for class OFOxmTunnelIpv4SrcMaskedVer15
                   return OFOxmTunnelIpv4SrcMaskedVer15.READER.readFrom(bb);
               case (int) 0x80002002:
                   // discriminator value 0x80002002L=0x80002002L for class OFOxmUdpDstVer15
                   return OFOxmUdpDstVer15.READER.readFrom(bb);
               case (int) 0x80002104:
                   // discriminator value 0x80002104L=0x80002104L for class OFOxmUdpDstMaskedVer15
                   return OFOxmUdpDstMaskedVer15.READER.readFrom(bb);
               case (int) 0x80001e02:
                   // discriminator value 0x80001e02L=0x80001e02L for class OFOxmUdpSrcVer15
                   return OFOxmUdpSrcVer15.READER.readFrom(bb);
               case (int) 0x80001f04:
                   // discriminator value 0x80001f04L=0x80001f04L for class OFOxmUdpSrcMaskedVer15
                   return OFOxmUdpSrcMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000e01:
                   // discriminator value 0x80000e01L=0x80000e01L for class OFOxmVlanPcpVer15
                   return OFOxmVlanPcpVer15.READER.readFrom(bb);
               case (int) 0x80000f02:
                   // discriminator value 0x80000f02L=0x80000f02L for class OFOxmVlanPcpMaskedVer15
                   return OFOxmVlanPcpMaskedVer15.READER.readFrom(bb);
               case (int) 0x80000c02:
                   // discriminator value 0x80000c02L=0x80000c02L for class OFOxmVlanVidVer15
                   return OFOxmVlanVidVer15.READER.readFrom(bb);
               case (int) 0x80000d04:
                   // discriminator value 0x80000d04L=0x80000d04L for class OFOxmVlanVidMaskedVer15
                   return OFOxmVlanVidMaskedVer15.READER.readFrom(bb);
               case 0x32c06:
                   // discriminator value 0x32c06L=0x32c06L for class OFOxmBsnInnerEthDstVer15
                   return OFOxmBsnInnerEthDstVer15.READER.readFrom(bb);
               case 0x32d0c:
                   // discriminator value 0x32d0cL=0x32d0cL for class OFOxmBsnInnerEthDstMaskedVer15
                   return OFOxmBsnInnerEthDstMaskedVer15.READER.readFrom(bb);
               case 0x32e06:
                   // discriminator value 0x32e06L=0x32e06L for class OFOxmBsnInnerEthSrcVer15
                   return OFOxmBsnInnerEthSrcVer15.READER.readFrom(bb);
               case 0x32f0c:
                   // discriminator value 0x32f0cL=0x32f0cL for class OFOxmBsnInnerEthSrcMaskedVer15
                   return OFOxmBsnInnerEthSrcMaskedVer15.READER.readFrom(bb);
               case 0x33002:
                   // discriminator value 0x33002L=0x33002L for class OFOxmBsnInnerVlanVidVer15
                   return OFOxmBsnInnerVlanVidVer15.READER.readFrom(bb);
               case 0x33104:
                   // discriminator value 0x33104L=0x33104L for class OFOxmBsnInnerVlanVidMaskedVer15
                   return OFOxmBsnInnerVlanVidMaskedVer15.READER.readFrom(bb);
               case 0x33202:
                   // discriminator value 0x33202L=0x33202L for class OFOxmBsnVfiVer15
                   return OFOxmBsnVfiVer15.READER.readFrom(bb);
               case 0x33304:
                   // discriminator value 0x33304L=0x33304L for class OFOxmBsnVfiMaskedVer15
                   return OFOxmBsnVfiMaskedVer15.READER.readFrom(bb);
               case 0x32a04:
                   // discriminator value 0x32a04L=0x32a04L for class OFOxmBsnVxlanNetworkIdVer15
                   return OFOxmBsnVxlanNetworkIdVer15.READER.readFrom(bb);
               case 0x32b08:
                   // discriminator value 0x32b08L=0x32b08L for class OFOxmBsnVxlanNetworkIdMaskedVer15
                   return OFOxmBsnVxlanNetworkIdMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004e02:
                   // discriminator value 0x80004e02L=0x80004e02L for class OFOxmIpv6ExthdrVer15
                   return OFOxmIpv6ExthdrVer15.READER.readFrom(bb);
               case (int) 0x80004f04:
                   // discriminator value 0x80004f04L=0x80004f04L for class OFOxmIpv6ExthdrMaskedVer15
                   return OFOxmIpv6ExthdrMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004801:
                   // discriminator value 0x80004801L=0x80004801L for class OFOxmMplsBosVer15
                   return OFOxmMplsBosVer15.READER.readFrom(bb);
               case (int) 0x80004902:
                   // discriminator value 0x80004902L=0x80004902L for class OFOxmMplsBosMaskedVer15
                   return OFOxmMplsBosMaskedVer15.READER.readFrom(bb);
               case (int) 0x80004c08:
                   // discriminator value 0x80004c08L=0x80004c08L for class OFOxmTunnelIdVer15
                   return OFOxmTunnelIdVer15.READER.readFrom(bb);
               case (int) 0x80004d10:
                   // discriminator value 0x80004d10L=0x80004d10L for class OFOxmTunnelIdMaskedVer15
                   return OFOxmTunnelIdMaskedVer15.READER.readFrom(bb);
               case 0x33604:
                   // discriminator value 0x33604L=0x33604L for class OFOxmBsnIfpClassIdVer15
                   return OFOxmBsnIfpClassIdVer15.READER.readFrom(bb);
               case 0x33708:
                   // discriminator value 0x33708L=0x33708L for class OFOxmBsnIfpClassIdMaskedVer15
                   return OFOxmBsnIfpClassIdMaskedVer15.READER.readFrom(bb);
               case (int) 0x80005201:
                   // discriminator value 0x80005201L=0x80005201L for class OFOxmPbbUcaVer15
                   return OFOxmPbbUcaVer15.READER.readFrom(bb);
               case (int) 0x80005302:
                   // discriminator value 0x80005302L=0x80005302L for class OFOxmPbbUcaMaskedVer15
                   return OFOxmPbbUcaMaskedVer15.READER.readFrom(bb);
               case (int) 0x80005604:
                   // discriminator value 0x80005604L=0x80005604L for class OFOxmActsetOutputVer15
                   return OFOxmActsetOutputVer15.READER.readFrom(bb);
               case (int) 0x80005708:
                   // discriminator value 0x80005708L=0x80005708L for class OFOxmActsetOutputMaskedVer15
                   return OFOxmActsetOutputMaskedVer15.READER.readFrom(bb);
               case (int) 0x80005804:
                   // discriminator value 0x80005804L=0x80005804L for class OFOxmPacketTypeVer15
                   return OFOxmPacketTypeVer15.READER.readFrom(bb);
               case (int) 0x80005908:
                   // discriminator value 0x80005908L=0x80005908L for class OFOxmPacketTypeMaskedVer15
                   return OFOxmPacketTypeMaskedVer15.READER.readFrom(bb);
               case (int) 0x80005402:
                   // discriminator value 0x80005402L=0x80005402L for class OFOxmTcpFlagsVer15
                   return OFOxmTcpFlagsVer15.READER.readFrom(bb);
               case (int) 0x80005504:
                   // discriminator value 0x80005504L=0x80005504L for class OFOxmTcpFlagsMaskedVer15
                   return OFOxmTcpFlagsMaskedVer15.READER.readFrom(bb);
               default:
                   throw new OFParseError("Unknown value for discriminator typeLen of class OFOxmVer15: " + typeLen);
            }
        }
    }
}
