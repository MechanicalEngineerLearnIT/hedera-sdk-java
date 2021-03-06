package com.hedera.hashgraph.sdk;

import com.hedera.hashgraph.proto.GetBySolidityIDQuery;
import com.hedera.hashgraph.proto.GetBySolidityIDResponse;
import com.hedera.hashgraph.proto.Query;
import com.hedera.hashgraph.proto.QueryHeader;
import com.hedera.hashgraph.proto.Response;
import com.hedera.hashgraph.proto.SmartContractServiceGrpc;

import io.grpc.MethodDescriptor;

public final class GetBySolidityIdQuery extends QueryBuilder<GetBySolidityIDResponse, GetBySolidityIdQuery> {
    private final GetBySolidityIDQuery.Builder builder = inner.getGetBySolidityIDBuilder();

    /**
     * @deprecated {@link Client} should now be provided to {@link #execute(Client)}
     */
    @Deprecated
    public GetBySolidityIdQuery(Client client) {
        super(client);
    }

    public GetBySolidityIdQuery() {
        super((Client) null);
    }

    public GetBySolidityIdQuery setSolidityId(String solidityId) {
        builder.setSolidityID(solidityId);
        return this;
    }

    @Override
    protected QueryHeader.Builder getHeaderBuilder() {
        return builder.getHeaderBuilder();
    }

    @Override
    protected MethodDescriptor<Query, Response> getMethod() {
        return SmartContractServiceGrpc.getGetBySolidityIDMethod();
    }

    @Override
    protected GetBySolidityIDResponse fromResponse(Response raw) {
        return raw.getGetBySolidityID();
    }

    @Override
    protected void doValidate() {
        require(builder.getSolidityID(), ".setSolidityId() required");
    }
}
