package com.hedera.sdk.account;

import com.hedera.sdk.*;
import com.hedera.sdk.crypto.Key;
import com.hedera.sdk.proto.CryptoServiceGrpc;
import com.hedera.sdk.proto.CryptoUpdateTransactionBody;
import com.hedera.sdk.proto.Transaction;
import com.hedera.sdk.proto.TransactionResponse;
import io.grpc.MethodDescriptor;
import java.time.Duration;
import java.time.Instant;

// `CryptoUpdateTransaction`
public final class AccountUpdateTransaction extends TransactionBuilder<AccountUpdateTransaction> {
    private final CryptoUpdateTransactionBody.Builder builder = inner.getBodyBuilder()
        .getCryptoUpdateAccountBuilder();

    public AccountUpdateTransaction(Client client) {
        super(client);
    }

    AccountUpdateTransaction() {
        super(null);
    }

    public AccountUpdateTransaction setAccountForUpdate(AccountId accountId) {
        builder.setAccountIDToUpdate(accountId.toProto());
        return this;
    }

    public AccountUpdateTransaction setKey(Key key) {
        builder.setKey(key.toKeyProto());
        return this;
    }

    public AccountUpdateTransaction setProxyAccount(AccountId accountId) {
        builder.setProxyAccountID(accountId.toProto());
        return this;
    }

    public AccountUpdateTransaction setProxyFraction(int proxyFraction) {
        builder.setProxyFraction(proxyFraction);
        return this;
    }

    public AccountUpdateTransaction setSendRecordThreshold(long sendRecordThreshold) {
        builder.setSendRecordThreshold(sendRecordThreshold);
        return this;
    }

    public AccountUpdateTransaction setReceiveRecordThreshold(long receiveRecordThreshold) {
        builder.setReceiveRecordThreshold(receiveRecordThreshold);
        return this;
    }

    public AccountUpdateTransaction setAutoRenewPeriod(Duration autoRenewPeriod) {
        builder.setAutoRenewPeriod(DurationHelper.durationFrom(autoRenewPeriod));
        return this;
    }

    public AccountUpdateTransaction setExpirationTime(Instant expirationTime) {
        builder.setExpirationTime(TimestampHelper.timestampFrom(expirationTime));
        return this;
    }

    @Override
    protected void doValidate() {
        require(builder.hasAccountIDToUpdate(), ".setAccountForUpdate() required");
        require(builder.hasKey(), ".setKey() required");
    }

    @Override
    protected MethodDescriptor<Transaction, TransactionResponse> getMethod() {
        return CryptoServiceGrpc.getUpdateAccountMethod();
    }
}