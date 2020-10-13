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
import org.projectfloodlight.openflow.protocol.OFTableFeaturesFailedCode;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;

public class OFTableFeaturesFailedCodeSerializerVer15 {

    public final static short BAD_TABLE_VAL = (short) 0x0;
    public final static short BAD_METADATA_VAL = (short) 0x1;
    public final static short EPERM_VAL = (short) 0x5;
    public final static short BAD_CAPA_VAL = (short) 0x6;
    public final static short BAD_MAX_ENT_VAL = (short) 0x7;
    public final static short BAD_FEATURES_VAL = (short) 0x8;
    public final static short BAD_COMMAND_VAL = (short) 0x9;
    public final static short TOO_MANY_VAL = (short) 0xa;

    public static OFTableFeaturesFailedCode readFrom(ByteBuf bb) throws OFParseError {
        try {
            return ofWireValue(bb.readShort());
        } catch (IllegalArgumentException e) {
            throw new OFParseError(e);
        }
    }

    public static void writeTo(ByteBuf bb, OFTableFeaturesFailedCode e) {
        bb.writeShort(toWireValue(e));
    }

    public static void putTo(OFTableFeaturesFailedCode e, PrimitiveSink sink) {
        sink.putShort(toWireValue(e));
    }

    public static OFTableFeaturesFailedCode ofWireValue(short val) {
        switch(val) {
            case BAD_TABLE_VAL:
                return OFTableFeaturesFailedCode.BAD_TABLE;
            case BAD_METADATA_VAL:
                return OFTableFeaturesFailedCode.BAD_METADATA;
            case EPERM_VAL:
                return OFTableFeaturesFailedCode.EPERM;
            case BAD_CAPA_VAL:
                return OFTableFeaturesFailedCode.BAD_CAPA;
            case BAD_MAX_ENT_VAL:
                return OFTableFeaturesFailedCode.BAD_MAX_ENT;
            case BAD_FEATURES_VAL:
                return OFTableFeaturesFailedCode.BAD_FEATURES;
            case BAD_COMMAND_VAL:
                return OFTableFeaturesFailedCode.BAD_COMMAND;
            case TOO_MANY_VAL:
                return OFTableFeaturesFailedCode.TOO_MANY;
            default:
                throw new IllegalArgumentException("Illegal wire value for type OFTableFeaturesFailedCode in version 1.5: " + val);
        }
    }


    public static short toWireValue(OFTableFeaturesFailedCode e) {
        switch(e) {
            case BAD_TABLE:
                return BAD_TABLE_VAL;
            case BAD_METADATA:
                return BAD_METADATA_VAL;
            case EPERM:
                return EPERM_VAL;
            case BAD_CAPA:
                return BAD_CAPA_VAL;
            case BAD_MAX_ENT:
                return BAD_MAX_ENT_VAL;
            case BAD_FEATURES:
                return BAD_FEATURES_VAL;
            case BAD_COMMAND:
                return BAD_COMMAND_VAL;
            case TOO_MANY:
                return TOO_MANY_VAL;
            default:
                throw new IllegalArgumentException("Illegal enum value for type OFTableFeaturesFailedCode in version 1.5: " + e);
        }
    }

}