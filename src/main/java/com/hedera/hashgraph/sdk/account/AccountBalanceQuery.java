package com.hedera.hashgraph.sdk.account;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.QueryBuilder;
import com.hedera.hashgraph.proto.CryptoGetAccountBalanceQuery;
import com.hedera.hashgraph.proto.Query;
import com.hedera.hashgraph.proto.QueryHeader;
import com.hedera.hashgraph.proto.Response;
import com.hedera.hashgraph.proto.CryptoServiceGrpc;

import javax.annotation.Nullable;

import io.grpc.MethodDescriptor;

// `CryptoGetAccountBalanceQuery`
public final class AccountBalanceQuery extends QueryBuilder<Long, AccountBalanceQuery> {
    private final CryptoGetAccountBalanceQuery.Builder builder = inner.getCryptogetAccountBalanceBuilder();

    /**
     * @deprecated {@link Client} should now be provided to {@link #execute(Client)}
     */
    @Deprecated
    public AccountBalanceQuery(@Nullable Client client) {
        super(client);

        // a payment transaction is required but is not processed so it can have zero value
        setPaymentAmount(0);
    }

    public AccountBalanceQuery() {
        super();

        // a payment transaction is required but is not processed so it can have zero value
        setPaymentAmount(0);
    }

    @Override
    protected QueryHeader.Builder getHeaderBuilder() {
        return builder.getHeaderBuilder();
    }

    public AccountBalanceQuery setAccountId(AccountId account) {
        builder.setAccountID(account.toProto());
        return this;
    }

    @Override
    protected void doValidate() {
        require(builder.hasAccountID(), ".setAccountId() required");
        require(getHeaderBuilder().hasPayment(),
            "AccountBalanceQuery requires a payment for validation but it is not processed; "
                + "one would have been created automatically but the given Client did not have "
                + "an operator ID or key set. You must instead manually create, sign and then set "
                + "a payment transaction with .setPayment().");
    }

    @Override
    protected MethodDescriptor<Query, Response> getMethod() {
        return CryptoServiceGrpc.getCryptoGetBalanceMethod();
    }

    @Override
    protected Long fromResponse(Response raw) {
        return raw.getCryptogetAccountBalance().getBalance();
    }
}
