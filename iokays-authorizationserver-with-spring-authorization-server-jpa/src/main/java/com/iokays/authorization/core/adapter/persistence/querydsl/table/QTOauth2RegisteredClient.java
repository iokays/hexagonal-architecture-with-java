package com.iokays.authorization.core.adapter.persistence.querydsl.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTOauth2RegisteredClient is a Querydsl query type for TOauth2RegisteredClient
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTOauth2RegisteredClient extends com.querydsl.sql.RelationalPathBase<TOauth2RegisteredClient> {

    public static final QTOauth2RegisteredClient tOauth2RegisteredClient = new QTOauth2RegisteredClient("T_OAUTH2_REGISTERED_CLIENT");
    private static final long serialVersionUID = 1332452789;
    public final StringPath authorizationGrantTypes = createString("authorizationGrantTypes");

    public final StringPath clientAuthenticationMethods = createString("clientAuthenticationMethods");

    public final StringPath clientId = createString("clientId");

    public final DateTimePath<java.sql.Timestamp> clientIdIssuedAt = createDateTime("clientIdIssuedAt", java.sql.Timestamp.class);

    public final StringPath clientName = createString("clientName");

    public final StringPath clientSecret = createString("clientSecret");

    public final DateTimePath<java.sql.Timestamp> clientSecretExpiresAt = createDateTime("clientSecretExpiresAt", java.sql.Timestamp.class);

    public final StringPath clientSettings = createString("clientSettings");

    public final NumberPath<Integer> concurrencyVersion = createNumber("concurrencyVersion", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath postLogoutRedirectUris = createString("postLogoutRedirectUris");

    public final StringPath redirectUris = createString("redirectUris");

    public final SimplePath<byte[]> registeredClientId = createSimple("registeredClientId", byte[].class);

    public final StringPath scopes = createString("scopes");

    public final StringPath tokenSettings = createString("tokenSettings");

    public final com.querydsl.sql.PrimaryKey<TOauth2RegisteredClient> constraint2 = createPrimaryKey(id);

    public QTOauth2RegisteredClient(String variable) {
        super(TOauth2RegisteredClient.class, forVariable(variable), "PUBLIC", "T_OAUTH2_REGISTERED_CLIENT");
        addMetadata();
    }

    public QTOauth2RegisteredClient(String variable, String schema, String table) {
        super(TOauth2RegisteredClient.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTOauth2RegisteredClient(String variable, String schema) {
        super(TOauth2RegisteredClient.class, forVariable(variable), schema, "T_OAUTH2_REGISTERED_CLIENT");
        addMetadata();
    }

    public QTOauth2RegisteredClient(Path<? extends TOauth2RegisteredClient> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_OAUTH2_REGISTERED_CLIENT");
        addMetadata();
    }

    public QTOauth2RegisteredClient(PathMetadata metadata) {
        super(TOauth2RegisteredClient.class, metadata, "PUBLIC", "T_OAUTH2_REGISTERED_CLIENT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(authorizationGrantTypes, ColumnMetadata.named("AUTHORIZATION_GRANT_TYPES").withIndex(5).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(clientAuthenticationMethods, ColumnMetadata.named("CLIENT_AUTHENTICATION_METHODS").withIndex(6).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(clientId, ColumnMetadata.named("CLIENT_ID").withIndex(7).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(clientIdIssuedAt, ColumnMetadata.named("CLIENT_ID_ISSUED_AT").withIndex(8).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6).notNull());
        addMetadata(clientName, ColumnMetadata.named("CLIENT_NAME").withIndex(9).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientSecret, ColumnMetadata.named("CLIENT_SECRET").withIndex(10).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientSecretExpiresAt, ColumnMetadata.named("CLIENT_SECRET_EXPIRES_AT").withIndex(11).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6).notNull());
        addMetadata(clientSettings, ColumnMetadata.named("CLIENT_SETTINGS").withIndex(12).ofType(Types.VARCHAR).withSize(2000).notNull());
        addMetadata(concurrencyVersion, ColumnMetadata.named("CONCURRENCY_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(postLogoutRedirectUris, ColumnMetadata.named("POST_LOGOUT_REDIRECT_URIS").withIndex(13).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(redirectUris, ColumnMetadata.named("REDIRECT_URIS").withIndex(14).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(registeredClientId, ColumnMetadata.named("REGISTERED_CLIENT_ID").withIndex(15).ofType(Types.VARBINARY).withSize(1000).notNull());
        addMetadata(scopes, ColumnMetadata.named("SCOPES").withIndex(16).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(tokenSettings, ColumnMetadata.named("TOKEN_SETTINGS").withIndex(17).ofType(Types.VARCHAR).withSize(2000).notNull());
    }

}

