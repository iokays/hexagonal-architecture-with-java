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
 * QTOauth2Authorization is a Querydsl query type for TOauth2Authorization
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTOauth2Authorization extends com.querydsl.sql.RelationalPathBase<TOauth2Authorization> {

    public static final QTOauth2Authorization tOauth2Authorization = new QTOauth2Authorization("T_OAUTH2_AUTHORIZATION");
    private static final long serialVersionUID = -310457167;
    public final DateTimePath<java.sql.Timestamp> accessTokenExpiresAt = createDateTime("accessTokenExpiresAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> accessTokenIssuedAt = createDateTime("accessTokenIssuedAt", java.sql.Timestamp.class);

    public final StringPath accessTokenMetadata = createString("accessTokenMetadata");

    public final StringPath accessTokenScopes = createString("accessTokenScopes");

    public final StringPath accessTokenType = createString("accessTokenType");

    public final StringPath accessTokenValue = createString("accessTokenValue");

    public final StringPath attributes = createString("attributes");

    public final DateTimePath<java.sql.Timestamp> authorizationCodeExpiresAt = createDateTime("authorizationCodeExpiresAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> authorizationCodeIssuedAt = createDateTime("authorizationCodeIssuedAt", java.sql.Timestamp.class);

    public final StringPath authorizationCodeMetadata = createString("authorizationCodeMetadata");

    public final StringPath authorizationCodeValue = createString("authorizationCodeValue");

    public final StringPath authorizationGrantType = createString("authorizationGrantType");

    public final SimplePath<byte[]> authorizationId = createSimple("authorizationId", byte[].class);

    public final StringPath authorizedScopes = createString("authorizedScopes");

    public final NumberPath<Integer> concurrencyVersion = createNumber("concurrencyVersion", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> deviceCodeExpiresAt = createDateTime("deviceCodeExpiresAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> deviceCodeIssuedAt = createDateTime("deviceCodeIssuedAt", java.sql.Timestamp.class);

    public final StringPath deviceCodeMetadata = createString("deviceCodeMetadata");

    public final StringPath deviceCodeValue = createString("deviceCodeValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath oidcIdTokenClaims = createString("oidcIdTokenClaims");

    public final DateTimePath<java.sql.Timestamp> oidcIdTokenExpiresAt = createDateTime("oidcIdTokenExpiresAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> oidcIdTokenIssuedAt = createDateTime("oidcIdTokenIssuedAt", java.sql.Timestamp.class);

    public final StringPath oidcIdTokenMetadata = createString("oidcIdTokenMetadata");

    public final StringPath oidcIdTokenValue = createString("oidcIdTokenValue");

    public final StringPath principalName = createString("principalName");

    public final DateTimePath<java.sql.Timestamp> refreshTokenExpiresAt = createDateTime("refreshTokenExpiresAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> refreshTokenIssuedAt = createDateTime("refreshTokenIssuedAt", java.sql.Timestamp.class);

    public final StringPath refreshTokenMetadata = createString("refreshTokenMetadata");

    public final StringPath refreshTokenValue = createString("refreshTokenValue");

    public final SimplePath<byte[]> registeredClientId = createSimple("registeredClientId", byte[].class);

    public final StringPath state = createString("state");

    public final DateTimePath<java.sql.Timestamp> userCodeExpiresAt = createDateTime("userCodeExpiresAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> userCodeIssuedAt = createDateTime("userCodeIssuedAt", java.sql.Timestamp.class);

    public final StringPath userCodeMetadata = createString("userCodeMetadata");

    public final StringPath userCodeValue = createString("userCodeValue");

    public final com.querydsl.sql.PrimaryKey<TOauth2Authorization> constraint7 = createPrimaryKey(id);

    public QTOauth2Authorization(String variable) {
        super(TOauth2Authorization.class, forVariable(variable), "PUBLIC", "T_OAUTH2_AUTHORIZATION");
        addMetadata();
    }

    public QTOauth2Authorization(String variable, String schema, String table) {
        super(TOauth2Authorization.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTOauth2Authorization(String variable, String schema) {
        super(TOauth2Authorization.class, forVariable(variable), schema, "T_OAUTH2_AUTHORIZATION");
        addMetadata();
    }

    public QTOauth2Authorization(Path<? extends TOauth2Authorization> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_OAUTH2_AUTHORIZATION");
        addMetadata();
    }

    public QTOauth2Authorization(PathMetadata metadata) {
        super(TOauth2Authorization.class, metadata, "PUBLIC", "T_OAUTH2_AUTHORIZATION");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(accessTokenExpiresAt, ColumnMetadata.named("ACCESS_TOKEN_EXPIRES_AT").withIndex(5).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(accessTokenIssuedAt, ColumnMetadata.named("ACCESS_TOKEN_ISSUED_AT").withIndex(6).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(accessTokenMetadata, ColumnMetadata.named("ACCESS_TOKEN_METADATA").withIndex(7).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(accessTokenScopes, ColumnMetadata.named("ACCESS_TOKEN_SCOPES").withIndex(8).ofType(Types.VARCHAR).withSize(1000));
        addMetadata(accessTokenType, ColumnMetadata.named("ACCESS_TOKEN_TYPE").withIndex(9).ofType(Types.VARCHAR).withSize(255));
        addMetadata(accessTokenValue, ColumnMetadata.named("ACCESS_TOKEN_VALUE").withIndex(10).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(attributes, ColumnMetadata.named("ATTRIBUTES").withIndex(11).ofType(Types.VARCHAR).withSize(40000).notNull());
        addMetadata(authorizationCodeExpiresAt, ColumnMetadata.named("AUTHORIZATION_CODE_EXPIRES_AT").withIndex(12).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(authorizationCodeIssuedAt, ColumnMetadata.named("AUTHORIZATION_CODE_ISSUED_AT").withIndex(13).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(authorizationCodeMetadata, ColumnMetadata.named("AUTHORIZATION_CODE_METADATA").withIndex(14).ofType(Types.VARCHAR).withSize(255));
        addMetadata(authorizationCodeValue, ColumnMetadata.named("AUTHORIZATION_CODE_VALUE").withIndex(15).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(authorizationGrantType, ColumnMetadata.named("AUTHORIZATION_GRANT_TYPE").withIndex(16).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(authorizationId, ColumnMetadata.named("AUTHORIZATION_ID").withIndex(17).ofType(Types.VARBINARY).withSize(255));
        addMetadata(authorizedScopes, ColumnMetadata.named("AUTHORIZED_SCOPES").withIndex(18).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(concurrencyVersion, ColumnMetadata.named("CONCURRENCY_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(deviceCodeExpiresAt, ColumnMetadata.named("DEVICE_CODE_EXPIRES_AT").withIndex(19).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(deviceCodeIssuedAt, ColumnMetadata.named("DEVICE_CODE_ISSUED_AT").withIndex(20).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(deviceCodeMetadata, ColumnMetadata.named("DEVICE_CODE_METADATA").withIndex(21).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(deviceCodeValue, ColumnMetadata.named("DEVICE_CODE_VALUE").withIndex(22).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(oidcIdTokenClaims, ColumnMetadata.named("OIDC_ID_TOKEN_CLAIMS").withIndex(23).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(oidcIdTokenExpiresAt, ColumnMetadata.named("OIDC_ID_TOKEN_EXPIRES_AT").withIndex(24).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(oidcIdTokenIssuedAt, ColumnMetadata.named("OIDC_ID_TOKEN_ISSUED_AT").withIndex(25).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(oidcIdTokenMetadata, ColumnMetadata.named("OIDC_ID_TOKEN_METADATA").withIndex(26).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(oidcIdTokenValue, ColumnMetadata.named("OIDC_ID_TOKEN_VALUE").withIndex(27).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(principalName, ColumnMetadata.named("PRINCIPAL_NAME").withIndex(28).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(refreshTokenExpiresAt, ColumnMetadata.named("REFRESH_TOKEN_EXPIRES_AT").withIndex(29).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(refreshTokenIssuedAt, ColumnMetadata.named("REFRESH_TOKEN_ISSUED_AT").withIndex(30).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(refreshTokenMetadata, ColumnMetadata.named("REFRESH_TOKEN_METADATA").withIndex(31).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(refreshTokenValue, ColumnMetadata.named("REFRESH_TOKEN_VALUE").withIndex(32).ofType(Types.VARCHAR).withSize(4000));
        addMetadata(registeredClientId, ColumnMetadata.named("REGISTERED_CLIENT_ID").withIndex(33).ofType(Types.VARBINARY).withSize(255));
        addMetadata(state, ColumnMetadata.named("STATE").withIndex(34).ofType(Types.VARCHAR).withSize(500).notNull());
        addMetadata(userCodeExpiresAt, ColumnMetadata.named("USER_CODE_EXPIRES_AT").withIndex(35).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(userCodeIssuedAt, ColumnMetadata.named("USER_CODE_ISSUED_AT").withIndex(36).ofType(Types.TIMESTAMP_WITH_TIMEZONE).withSize(32).withDigits(6));
        addMetadata(userCodeMetadata, ColumnMetadata.named("USER_CODE_METADATA").withIndex(37).ofType(Types.VARCHAR).withSize(2000));
        addMetadata(userCodeValue, ColumnMetadata.named("USER_CODE_VALUE").withIndex(38).ofType(Types.VARCHAR).withSize(4000));
    }

}

