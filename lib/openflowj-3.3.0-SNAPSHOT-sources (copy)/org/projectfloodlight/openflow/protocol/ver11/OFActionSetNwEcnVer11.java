// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_class.java
// Do not modify

package org.projectfloodlight.openflow.protocol.ver11;

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
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

class OFActionSetNwEcnVer11 implements OFActionSetNwEcn {
    private static final Logger logger = LoggerFactory.getLogger(OFActionSetNwEcnVer11.class);
    // version: 1.1
    final static byte WIRE_VERSION = 2;
    final static int LENGTH = 8;

        private final static IpEcn DEFAULT_NW_ECN = IpEcn.NONE;

    // OF message fields
    private final IpEcn nwEcn;
//
    // Immutable default instance
    final static OFActionSetNwEcnVer11 DEFAULT = new OFActionSetNwEcnVer11(
        DEFAULT_NW_ECN
    );

    // package private constructor - used by readers, builders, and factory
    OFActionSetNwEcnVer11(IpEcn nwEcn) {
        if(nwEcn == null) {
            throw new NullPointerException("OFActionSetNwEcnVer11: property nwEcn cannot be null");
        }
        this.nwEcn = nwEcn;
    }

    // Accessors for OF message fields
    @Override
    public OFActionType getType() {
        return OFActionType.SET_NW_ECN;
    }

    @Override
    public IpEcn getNwEcn() {
        return nwEcn;
    }

    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_11;
    }



    public OFActionSetNwEcn.Builder createBuilder() {
        return new BuilderWithParent(this);
    }

    static class BuilderWithParent implements OFActionSetNwEcn.Builder {
        final OFActionSetNwEcnVer11 parentMessage;

        // OF message fields
        private boolean nwEcnSet;
        private IpEcn nwEcn;

        BuilderWithParent(OFActionSetNwEcnVer11 parentMessage) {
            this.parentMessage = parentMessage;
        }

    @Override
    public OFActionType getType() {
        return OFActionType.SET_NW_ECN;
    }

    @Override
    public IpEcn getNwEcn() {
        return nwEcn;
    }

    @Override
    public OFActionSetNwEcn.Builder setNwEcn(IpEcn nwEcn) {
        this.nwEcn = nwEcn;
        this.nwEcnSet = true;
        return this;
    }
    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_11;
    }



        @Override
        public OFActionSetNwEcn build() {
                IpEcn nwEcn = this.nwEcnSet ? this.nwEcn : parentMessage.nwEcn;
                if(nwEcn == null)
                    throw new NullPointerException("Property nwEcn must not be null");

                //
                return new OFActionSetNwEcnVer11(
                    nwEcn
                );
        }

    }

    static class Builder implements OFActionSetNwEcn.Builder {
        // OF message fields
        private boolean nwEcnSet;
        private IpEcn nwEcn;

    @Override
    public OFActionType getType() {
        return OFActionType.SET_NW_ECN;
    }

    @Override
    public IpEcn getNwEcn() {
        return nwEcn;
    }

    @Override
    public OFActionSetNwEcn.Builder setNwEcn(IpEcn nwEcn) {
        this.nwEcn = nwEcn;
        this.nwEcnSet = true;
        return this;
    }
    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_11;
    }

//
        @Override
        public OFActionSetNwEcn build() {
            IpEcn nwEcn = this.nwEcnSet ? this.nwEcn : DEFAULT_NW_ECN;
            if(nwEcn == null)
                throw new NullPointerException("Property nwEcn must not be null");


            return new OFActionSetNwEcnVer11(
                    nwEcn
                );
        }

    }


    final static Reader READER = new Reader();
    static class Reader implements OFMessageReader<OFActionSetNwEcn> {
        @Override
        public OFActionSetNwEcn readFrom(ByteBuf bb) throws OFParseError {
            int start = bb.readerIndex();
            // fixed value property type == 8
            short type = bb.readShort();
            if(type != (short) 0x8)
                throw new OFParseError("Wrong type: Expected=OFActionType.SET_NW_ECN(8), got="+type);
            int length = U16.f(bb.readShort());
            if(length != 8)
                throw new OFParseError("Wrong length: Expected=8(8), got="+length);
            if(bb.readableBytes() + (bb.readerIndex() - start) < length) {
                // Buffer does not have all data yet
                bb.readerIndex(start);
                return null;
            }
            if(logger.isTraceEnabled())
                logger.trace("readFrom - length={}", length);
            IpEcn nwEcn = IpEcn.readByte(bb);
            // pad: 3 bytes
            bb.skipBytes(3);

            OFActionSetNwEcnVer11 actionSetNwEcnVer11 = new OFActionSetNwEcnVer11(
                    nwEcn
                    );
            if(logger.isTraceEnabled())
                logger.trace("readFrom - read={}", actionSetNwEcnVer11);
            return actionSetNwEcnVer11;
        }
    }

    public void putTo(PrimitiveSink sink) {
        FUNNEL.funnel(this, sink);
    }

    final static OFActionSetNwEcnVer11Funnel FUNNEL = new OFActionSetNwEcnVer11Funnel();
    static class OFActionSetNwEcnVer11Funnel implements Funnel<OFActionSetNwEcnVer11> {
        private static final long serialVersionUID = 1L;
        @Override
        public void funnel(OFActionSetNwEcnVer11 message, PrimitiveSink sink) {
            // fixed value property type = 8
            sink.putShort((short) 0x8);
            // fixed value property length = 8
            sink.putShort((short) 0x8);
            message.nwEcn.putTo(sink);
            // skip pad (3 bytes)
        }
    }


    public void writeTo(ByteBuf bb) {
        WRITER.write(bb, this);
    }

    final static Writer WRITER = new Writer();
    static class Writer implements OFMessageWriter<OFActionSetNwEcnVer11> {
        @Override
        public void write(ByteBuf bb, OFActionSetNwEcnVer11 message) {
            // fixed value property type = 8
            bb.writeShort((short) 0x8);
            // fixed value property length = 8
            bb.writeShort((short) 0x8);
            message.nwEcn.writeByte(bb);
            // pad: 3 bytes
            bb.writeZero(3);


        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("OFActionSetNwEcnVer11(");
        b.append("nwEcn=").append(nwEcn);
        b.append(")");
        return b.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OFActionSetNwEcnVer11 other = (OFActionSetNwEcnVer11) obj;

        if (nwEcn == null) {
            if (other.nwEcn != null)
                return false;
        } else if (!nwEcn.equals(other.nwEcn))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((nwEcn == null) ? 0 : nwEcn.hashCode());
        return result;
    }

}
