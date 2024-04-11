// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AutomatedCheckout.proto

package com.example.automatedcheckout;

/**
 * Protobuf type {@code CheckOutRequest}
 */
public  final class CheckOutRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CheckOutRequest)
    CheckOutRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CheckOutRequest.newBuilder() to construct.
  private CheckOutRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CheckOutRequest() {
    roomNumber_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CheckOutRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            roomNumber_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.automatedcheckout.AutomatedCheckoutProto.internal_static_CheckOutRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.automatedcheckout.AutomatedCheckoutProto.internal_static_CheckOutRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.automatedcheckout.CheckOutRequest.class, com.example.automatedcheckout.CheckOutRequest.Builder.class);
  }

  public static final int ROOMNUMBER_FIELD_NUMBER = 1;
  private volatile java.lang.Object roomNumber_;
  /**
   * <code>string roomNumber = 1;</code>
   */
  public java.lang.String getRoomNumber() {
    java.lang.Object ref = roomNumber_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      roomNumber_ = s;
      return s;
    }
  }
  /**
   * <code>string roomNumber = 1;</code>
   */
  public com.google.protobuf.ByteString
      getRoomNumberBytes() {
    java.lang.Object ref = roomNumber_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      roomNumber_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getRoomNumberBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, roomNumber_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getRoomNumberBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, roomNumber_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.automatedcheckout.CheckOutRequest)) {
      return super.equals(obj);
    }
    com.example.automatedcheckout.CheckOutRequest other = (com.example.automatedcheckout.CheckOutRequest) obj;

    boolean result = true;
    result = result && getRoomNumber()
        .equals(other.getRoomNumber());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ROOMNUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getRoomNumber().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.automatedcheckout.CheckOutRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.automatedcheckout.CheckOutRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code CheckOutRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CheckOutRequest)
      com.example.automatedcheckout.CheckOutRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.automatedcheckout.AutomatedCheckoutProto.internal_static_CheckOutRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.automatedcheckout.AutomatedCheckoutProto.internal_static_CheckOutRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.automatedcheckout.CheckOutRequest.class, com.example.automatedcheckout.CheckOutRequest.Builder.class);
    }

    // Construct using com.example.automatedcheckout.CheckOutRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      roomNumber_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.automatedcheckout.AutomatedCheckoutProto.internal_static_CheckOutRequest_descriptor;
    }

    @java.lang.Override
    public com.example.automatedcheckout.CheckOutRequest getDefaultInstanceForType() {
      return com.example.automatedcheckout.CheckOutRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.automatedcheckout.CheckOutRequest build() {
      com.example.automatedcheckout.CheckOutRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.automatedcheckout.CheckOutRequest buildPartial() {
      com.example.automatedcheckout.CheckOutRequest result = new com.example.automatedcheckout.CheckOutRequest(this);
      result.roomNumber_ = roomNumber_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.automatedcheckout.CheckOutRequest) {
        return mergeFrom((com.example.automatedcheckout.CheckOutRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.automatedcheckout.CheckOutRequest other) {
      if (other == com.example.automatedcheckout.CheckOutRequest.getDefaultInstance()) return this;
      if (!other.getRoomNumber().isEmpty()) {
        roomNumber_ = other.roomNumber_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.automatedcheckout.CheckOutRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.automatedcheckout.CheckOutRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object roomNumber_ = "";
    /**
     * <code>string roomNumber = 1;</code>
     */
    public java.lang.String getRoomNumber() {
      java.lang.Object ref = roomNumber_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        roomNumber_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string roomNumber = 1;</code>
     */
    public com.google.protobuf.ByteString
        getRoomNumberBytes() {
      java.lang.Object ref = roomNumber_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        roomNumber_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string roomNumber = 1;</code>
     */
    public Builder setRoomNumber(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      roomNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string roomNumber = 1;</code>
     */
    public Builder clearRoomNumber() {
      
      roomNumber_ = getDefaultInstance().getRoomNumber();
      onChanged();
      return this;
    }
    /**
     * <code>string roomNumber = 1;</code>
     */
    public Builder setRoomNumberBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      roomNumber_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:CheckOutRequest)
  }

  // @@protoc_insertion_point(class_scope:CheckOutRequest)
  private static final com.example.automatedcheckout.CheckOutRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.automatedcheckout.CheckOutRequest();
  }

  public static com.example.automatedcheckout.CheckOutRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CheckOutRequest>
      PARSER = new com.google.protobuf.AbstractParser<CheckOutRequest>() {
    @java.lang.Override
    public CheckOutRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CheckOutRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CheckOutRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CheckOutRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.automatedcheckout.CheckOutRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

