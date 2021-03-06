// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template const_serializer.java
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
import org.projectfloodlight.openflow.protocol.OFBsnHashAlgorithmType;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFBsnHashAlgorithmTypeSerializerVer15 {

    public final static short BSN_HASH_ALGORITHM_CRC16XOR8_VAL = (short) 0x0;
    public final static short BSN_HASH_ALGORITHM_CRC16XOR4_VAL = (short) 0x1;
    public final static short BSN_HASH_ALGORITHM_CRC16XOR2_VAL = (short) 0x2;
    public final static short BSN_HASH_ALGORITHM_CRC16XOR1_VAL = (short) 0x3;
    public final static short BSN_HASH_ALGORITHM_CRC16_VAL = (short) 0x4;
    public final static short BSN_HASH_ALGORITHM_XOR16_VAL = (short) 0x5;
    public final static short BSN_HASH_ALGORITHM_CRC16CCITT_VAL = (short) 0x6;
    public final static short BSN_HASH_ALGORITHM_CRC32LO_VAL = (short) 0x7;
    public final static short BSN_HASH_ALGORITHM_CRC32HI_VAL = (short) 0x8;

    public static OFBsnHashAlgorithmType readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readShort());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFBsnHashAlgorithmType e) {
        bb.writeShort(toWireValue(e));
    }

    public static void putTo(OFBsnHashAlgorithmType e, PrimitiveSink sink) {
        sink.putShort(toWireValue(e));
    }

    public static OFBsnHashAlgorithmType ofWireValue(short val) {
        switch(val) {
            case BSN_HASH_ALGORITHM_CRC16XOR8_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC16XOR8;
            case BSN_HASH_ALGORITHM_CRC16XOR4_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC16XOR4;
            case BSN_HASH_ALGORITHM_CRC16XOR2_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC16XOR2;
            case BSN_HASH_ALGORITHM_CRC16XOR1_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC16XOR1;
            case BSN_HASH_ALGORITHM_CRC16_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC16;
            case BSN_HASH_ALGORITHM_XOR16_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_XOR16;
            case BSN_HASH_ALGORITHM_CRC16CCITT_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC16CCITT;
            case BSN_HASH_ALGORITHM_CRC32LO_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC32LO;
            case BSN_HASH_ALGORITHM_CRC32HI_VAL:
                return OFBsnHashAlgorithmType.BSN_HASH_ALGORITHM_CRC32HI;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFBsnHashAlgorithmType in version 1.5: " + val);
        }
    }


    public static short toWireValue(OFBsnHashAlgorithmType e) {
        switch(e) {
            case BSN_HASH_ALGORITHM_CRC16XOR8:
                return BSN_HASH_ALGORITHM_CRC16XOR8_VAL;
            case BSN_HASH_ALGORITHM_CRC16XOR4:
                return BSN_HASH_ALGORITHM_CRC16XOR4_VAL;
            case BSN_HASH_ALGORITHM_CRC16XOR2:
                return BSN_HASH_ALGORITHM_CRC16XOR2_VAL;
            case BSN_HASH_ALGORITHM_CRC16XOR1:
                return BSN_HASH_ALGORITHM_CRC16XOR1_VAL;
            case BSN_HASH_ALGORITHM_CRC16:
                return BSN_HASH_ALGORITHM_CRC16_VAL;
            case BSN_HASH_ALGORITHM_XOR16:
                return BSN_HASH_ALGORITHM_XOR16_VAL;
            case BSN_HASH_ALGORITHM_CRC16CCITT:
                return BSN_HASH_ALGORITHM_CRC16CCITT_VAL;
            case BSN_HASH_ALGORITHM_CRC32LO:
                return BSN_HASH_ALGORITHM_CRC32LO_VAL;
            case BSN_HASH_ALGORITHM_CRC32HI:
                return BSN_HASH_ALGORITHM_CRC32HI_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFBsnHashAlgorithmType in version 1.5: " + e);
        }
    }

}
