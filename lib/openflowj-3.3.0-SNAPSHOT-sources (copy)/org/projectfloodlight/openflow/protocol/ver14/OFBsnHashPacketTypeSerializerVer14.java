// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template const_serializer.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver14;

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
import org.projectfloodlight.openflow.protocol.OFBsnHashPacketType;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFBsnHashPacketTypeSerializerVer14 {

    public final static byte BSN_HASH_PACKET_L2_VAL = (byte) 0x0;
    public final static byte BSN_HASH_PACKET_L2GRE_VAL = (byte) 0x1;
    public final static byte BSN_HASH_PACKET_IPV4_VAL = (byte) 0x3;
    public final static byte BSN_HASH_PACKET_IPV6_VAL = (byte) 0x4;
    public final static byte BSN_HASH_PACKET_MPLS_VAL = (byte) 0x5;
    public final static byte BSN_HASH_PACKET_SYMMETRIC_VAL = (byte) 0x6;

    public static OFBsnHashPacketType readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readByte());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFBsnHashPacketType e) {
        bb.writeByte(toWireValue(e));
    }

    public static void putTo(OFBsnHashPacketType e, PrimitiveSink sink) {
        sink.putByte(toWireValue(e));
    }

    public static OFBsnHashPacketType ofWireValue(byte val) {
        switch(val) {
            case BSN_HASH_PACKET_L2_VAL:
                return OFBsnHashPacketType.BSN_HASH_PACKET_L2;
            case BSN_HASH_PACKET_L2GRE_VAL:
                return OFBsnHashPacketType.BSN_HASH_PACKET_L2GRE;
            case BSN_HASH_PACKET_IPV4_VAL:
                return OFBsnHashPacketType.BSN_HASH_PACKET_IPV4;
            case BSN_HASH_PACKET_IPV6_VAL:
                return OFBsnHashPacketType.BSN_HASH_PACKET_IPV6;
            case BSN_HASH_PACKET_MPLS_VAL:
                return OFBsnHashPacketType.BSN_HASH_PACKET_MPLS;
            case BSN_HASH_PACKET_SYMMETRIC_VAL:
                return OFBsnHashPacketType.BSN_HASH_PACKET_SYMMETRIC;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFBsnHashPacketType in version 1.4: " + val);
        }
    }


    public static byte toWireValue(OFBsnHashPacketType e) {
        switch(e) {
            case BSN_HASH_PACKET_L2:
                return BSN_HASH_PACKET_L2_VAL;
            case BSN_HASH_PACKET_L2GRE:
                return BSN_HASH_PACKET_L2GRE_VAL;
            case BSN_HASH_PACKET_IPV4:
                return BSN_HASH_PACKET_IPV4_VAL;
            case BSN_HASH_PACKET_IPV6:
                return BSN_HASH_PACKET_IPV6_VAL;
            case BSN_HASH_PACKET_MPLS:
                return BSN_HASH_PACKET_MPLS_VAL;
            case BSN_HASH_PACKET_SYMMETRIC:
                return BSN_HASH_PACKET_SYMMETRIC_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFBsnHashPacketType in version 1.4: " + e);
        }
    }

}
