// Copyright (c) 2008 The Board of Trustees of The Leland Stanford Junior University
// Copyright (c) 2011, 2012 Open Networking Foundation
// Copyright (c) 2012, 2013 Big Switch Networks, Inc.
// This library was generated by the LoxiGen Compiler.
// See the file LICENSE.txt which should have been included in the source distribution

// Automatically generated by LOXI from template of_class.java
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import io.netty.buffer.ByteBuf;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

class OFBsnGentableBucketStatsRequestVer14 implements OFBsnGentableBucketStatsRequest {
    private static final Logger logger = LoggerFactory.getLogger(OFBsnGentableBucketStatsRequestVer14.class);
    // version: 1.4
    final static byte WIRE_VERSION = 5;
    final static int LENGTH = 26;

        private final static long DEFAULT_XID = 0x0L;
        private final static Set<OFStatsRequestFlags> DEFAULT_FLAGS = ImmutableSet.<OFStatsRequestFlags>of();

    // OF message fields
    private final long xid;
    private final Set<OFStatsRequestFlags> flags;
    private final GenTableId tableId;
//

    // package private constructor - used by readers, builders, and factory
    OFBsnGentableBucketStatsRequestVer14(long xid, Set<OFStatsRequestFlags> flags, GenTableId tableId) {
        if(flags == null) {
            throw new NullPointerException("OFBsnGentableBucketStatsRequestVer14: property flags cannot be null");
        }
        if(tableId == null) {
            throw new NullPointerException("OFBsnGentableBucketStatsRequestVer14: property tableId cannot be null");
        }
        this.xid = xid;
        this.flags = flags;
        this.tableId = tableId;
    }

    // Accessors for OF message fields
    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_14;
    }

    @Override
    public OFType getType() {
        return OFType.STATS_REQUEST;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public OFStatsType getStatsType() {
        return OFStatsType.EXPERIMENTER;
    }

    @Override
    public Set<OFStatsRequestFlags> getFlags() {
        return flags;
    }

    @Override
    public long getExperimenter() {
        return 0x5c16c7L;
    }

    @Override
    public long getSubtype() {
        return 0x5L;
    }

    @Override
    public GenTableId getTableId() {
        return tableId;
    }



    public OFBsnGentableBucketStatsRequest.Builder createBuilder() {
        return new BuilderWithParent(this);
    }

    static class BuilderWithParent implements OFBsnGentableBucketStatsRequest.Builder {
        final OFBsnGentableBucketStatsRequestVer14 parentMessage;

        // OF message fields
        private boolean xidSet;
        private long xid;
        private boolean flagsSet;
        private Set<OFStatsRequestFlags> flags;
        private boolean tableIdSet;
        private GenTableId tableId;

        BuilderWithParent(OFBsnGentableBucketStatsRequestVer14 parentMessage) {
            this.parentMessage = parentMessage;
        }

    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_14;
    }

    @Override
    public OFType getType() {
        return OFType.STATS_REQUEST;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public OFBsnGentableBucketStatsRequest.Builder setXid(long xid) {
        this.xid = xid;
        this.xidSet = true;
        return this;
    }
    @Override
    public OFStatsType getStatsType() {
        return OFStatsType.EXPERIMENTER;
    }

    @Override
    public Set<OFStatsRequestFlags> getFlags() {
        return flags;
    }

    @Override
    public OFBsnGentableBucketStatsRequest.Builder setFlags(Set<OFStatsRequestFlags> flags) {
        this.flags = flags;
        this.flagsSet = true;
        return this;
    }
    @Override
    public long getExperimenter() {
        return 0x5c16c7L;
    }

    @Override
    public long getSubtype() {
        return 0x5L;
    }

    @Override
    public GenTableId getTableId() {
        return tableId;
    }

    @Override
    public OFBsnGentableBucketStatsRequest.Builder setTableId(GenTableId tableId) {
        this.tableId = tableId;
        this.tableIdSet = true;
        return this;
    }


        @Override
        public OFBsnGentableBucketStatsRequest build() {
                long xid = this.xidSet ? this.xid : parentMessage.xid;
                Set<OFStatsRequestFlags> flags = this.flagsSet ? this.flags : parentMessage.flags;
                if(flags == null)
                    throw new NullPointerException("Property flags must not be null");
                GenTableId tableId = this.tableIdSet ? this.tableId : parentMessage.tableId;
                if(tableId == null)
                    throw new NullPointerException("Property tableId must not be null");

                //
                return new OFBsnGentableBucketStatsRequestVer14(
                    xid,
                    flags,
                    tableId
                );
        }

    }

    static class Builder implements OFBsnGentableBucketStatsRequest.Builder {
        // OF message fields
        private boolean xidSet;
        private long xid;
        private boolean flagsSet;
        private Set<OFStatsRequestFlags> flags;
        private boolean tableIdSet;
        private GenTableId tableId;

    @Override
    public OFVersion getVersion() {
        return OFVersion.OF_14;
    }

    @Override
    public OFType getType() {
        return OFType.STATS_REQUEST;
    }

    @Override
    public long getXid() {
        return xid;
    }

    @Override
    public OFBsnGentableBucketStatsRequest.Builder setXid(long xid) {
        this.xid = xid;
        this.xidSet = true;
        return this;
    }
    @Override
    public OFStatsType getStatsType() {
        return OFStatsType.EXPERIMENTER;
    }

    @Override
    public Set<OFStatsRequestFlags> getFlags() {
        return flags;
    }

    @Override
    public OFBsnGentableBucketStatsRequest.Builder setFlags(Set<OFStatsRequestFlags> flags) {
        this.flags = flags;
        this.flagsSet = true;
        return this;
    }
    @Override
    public long getExperimenter() {
        return 0x5c16c7L;
    }

    @Override
    public long getSubtype() {
        return 0x5L;
    }

    @Override
    public GenTableId getTableId() {
        return tableId;
    }

    @Override
    public OFBsnGentableBucketStatsRequest.Builder setTableId(GenTableId tableId) {
        this.tableId = tableId;
        this.tableIdSet = true;
        return this;
    }
//
        @Override
        public OFBsnGentableBucketStatsRequest build() {
            long xid = this.xidSet ? this.xid : DEFAULT_XID;
            Set<OFStatsRequestFlags> flags = this.flagsSet ? this.flags : DEFAULT_FLAGS;
            if(flags == null)
                throw new NullPointerException("Property flags must not be null");
            if(!this.tableIdSet)
                throw new IllegalStateException("Property tableId doesn't have default value -- must be set");
            if(tableId == null)
                throw new NullPointerException("Property tableId must not be null");


            return new OFBsnGentableBucketStatsRequestVer14(
                    xid,
                    flags,
                    tableId
                );
        }

    }


    final static Reader READER = new Reader();
    static class Reader implements OFMessageReader<OFBsnGentableBucketStatsRequest> {
        @Override
        public OFBsnGentableBucketStatsRequest readFrom(ByteBuf bb) throws OFParseError {
            int start = bb.readerIndex();
            // fixed value property version == 5
            byte version = bb.readByte();
            if(version != (byte) 0x5)
                throw new OFParseError("Wrong version: Expected=OFVersion.OF_14(5), got="+version);
            // fixed value property type == 18
            byte type = bb.readByte();
            if(type != (byte) 0x12)
                throw new OFParseError("Wrong type: Expected=OFType.STATS_REQUEST(18), got="+type);
            int length = U16.f(bb.readShort());
            if(length != 26)
                throw new OFParseError("Wrong length: Expected=26(26), got="+length);
            if(bb.readableBytes() + (bb.readerIndex() - start) < length) {
                // Buffer does not have all data yet
                bb.readerIndex(start);
                return null;
            }
            if(logger.isTraceEnabled())
                logger.trace("readFrom - length={}", length);
            long xid = U32.f(bb.readInt());
            // fixed value property statsType == 65535
            short statsType = bb.readShort();
            if(statsType != (short) 0xffff)
                throw new OFParseError("Wrong statsType: Expected=OFStatsType.EXPERIMENTER(65535), got="+statsType);
            Set<OFStatsRequestFlags> flags = OFStatsRequestFlagsSerializerVer14.readFrom(bb);
            // pad: 4 bytes
            bb.skipBytes(4);
            // fixed value property experimenter == 0x5c16c7L
            int experimenter = bb.readInt();
            if(experimenter != 0x5c16c7)
                throw new OFParseError("Wrong experimenter: Expected=0x5c16c7L(0x5c16c7L), got="+experimenter);
            // fixed value property subtype == 0x5L
            int subtype = bb.readInt();
            if(subtype != 0x5)
                throw new OFParseError("Wrong subtype: Expected=0x5L(0x5L), got="+subtype);
            GenTableId tableId = GenTableId.read2Bytes(bb);

            OFBsnGentableBucketStatsRequestVer14 bsnGentableBucketStatsRequestVer14 = new OFBsnGentableBucketStatsRequestVer14(
                    xid,
                      flags,
                      tableId
                    );
            if(logger.isTraceEnabled())
                logger.trace("readFrom - read={}", bsnGentableBucketStatsRequestVer14);
            return bsnGentableBucketStatsRequestVer14;
        }
    }

    public void putTo(PrimitiveSink sink) {
        FUNNEL.funnel(this, sink);
    }

    final static OFBsnGentableBucketStatsRequestVer14Funnel FUNNEL = new OFBsnGentableBucketStatsRequestVer14Funnel();
    static class OFBsnGentableBucketStatsRequestVer14Funnel implements Funnel<OFBsnGentableBucketStatsRequestVer14> {
        private static final long serialVersionUID = 1L;
        @Override
        public void funnel(OFBsnGentableBucketStatsRequestVer14 message, PrimitiveSink sink) {
            // fixed value property version = 5
            sink.putByte((byte) 0x5);
            // fixed value property type = 18
            sink.putByte((byte) 0x12);
            // fixed value property length = 26
            sink.putShort((short) 0x1a);
            sink.putLong(message.xid);
            // fixed value property statsType = 65535
            sink.putShort((short) 0xffff);
            OFStatsRequestFlagsSerializerVer14.putTo(message.flags, sink);
            // skip pad (4 bytes)
            // fixed value property experimenter = 0x5c16c7L
            sink.putInt(0x5c16c7);
            // fixed value property subtype = 0x5L
            sink.putInt(0x5);
            message.tableId.putTo(sink);
        }
    }


    public void writeTo(ByteBuf bb) {
        WRITER.write(bb, this);
    }

    final static Writer WRITER = new Writer();
    static class Writer implements OFMessageWriter<OFBsnGentableBucketStatsRequestVer14> {
        @Override
        public void write(ByteBuf bb, OFBsnGentableBucketStatsRequestVer14 message) {
            // fixed value property version = 5
            bb.writeByte((byte) 0x5);
            // fixed value property type = 18
            bb.writeByte((byte) 0x12);
            // fixed value property length = 26
            bb.writeShort((short) 0x1a);
            bb.writeInt(U32.t(message.xid));
            // fixed value property statsType = 65535
            bb.writeShort((short) 0xffff);
            OFStatsRequestFlagsSerializerVer14.writeTo(bb, message.flags);
            // pad: 4 bytes
            bb.writeZero(4);
            // fixed value property experimenter = 0x5c16c7L
            bb.writeInt(0x5c16c7);
            // fixed value property subtype = 0x5L
            bb.writeInt(0x5);
            message.tableId.write2Bytes(bb);


        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("OFBsnGentableBucketStatsRequestVer14(");
        b.append("xid=").append(xid);
        b.append(", ");
        b.append("flags=").append(flags);
        b.append(", ");
        b.append("tableId=").append(tableId);
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
        OFBsnGentableBucketStatsRequestVer14 other = (OFBsnGentableBucketStatsRequestVer14) obj;

        if( xid != other.xid)
            return false;
        if (flags == null) {
            if (other.flags != null)
                return false;
        } else if (!flags.equals(other.flags))
            return false;
        if (tableId == null) {
            if (other.tableId != null)
                return false;
        } else if (!tableId.equals(other.tableId))
            return false;
        return true;
    }

    @Override
    public boolean equalsIgnoreXid(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OFBsnGentableBucketStatsRequestVer14 other = (OFBsnGentableBucketStatsRequestVer14) obj;

        // ignore XID
        if (flags == null) {
            if (other.flags != null)
                return false;
        } else if (!flags.equals(other.flags))
            return false;
        if (tableId == null) {
            if (other.tableId != null)
                return false;
        } else if (!tableId.equals(other.tableId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime *  (int) (xid ^ (xid >>> 32));
        result = prime * result + ((flags == null) ? 0 : flags.hashCode());
        result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
        return result;
    }

    @Override
    public int hashCodeIgnoreXid() {
        final int prime = 31;
        int result = 1;

        // ignore XID
        result = prime * result + ((flags == null) ? 0 : flags.hashCode());
        result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
        return result;
    }

}
