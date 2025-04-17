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
 * QTClientRegistrations is a Querydsl query type for TClientRegistrations
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTClientRegistrations extends com.querydsl.sql.RelationalPathBase<TClientRegistrations> {

    public static final QTClientRegistrations tClientRegistrations = new QTClientRegistrations("T_CLIENT_REGISTRATIONS");
    private static final long serialVersionUID = -1507439166;
    public final StringPath authorizationGrantType = createString("authorizationGrantType");

    public final StringPath authorizationUri = createString("authorizationUri");

    public final StringPath clientAuthenticationMethod = createString("clientAuthenticationMethod");

    public final StringPath clientId = createString("clientId");

    public final StringPath clientName = createString("clientName");

    public final SimplePath<byte[]> clientRegistrationId = createSimple("clientRegistrationId", byte[].class);

    public final SimplePath<Object> clientRegistrationType = createSimple("clientRegistrationType", Object.class);

    public final StringPath clientSecret = createString("clientSecret");

    public final NumberPath<Integer> concurrencyVersion = createNumber("concurrencyVersion", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jwkSetUri = createString("jwkSetUri");

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath redirectUri = createString("redirectUri");

    public final SimplePath<Object[]> scopes = createSimple("scopes", Object[].class);

    public final StringPath tokenUri = createString("tokenUri");

    public final StringPath userInfoUri = createString("userInfoUri");

    public final StringPath userNameAttributeName = createString("userNameAttributeName");

    public final com.querydsl.sql.PrimaryKey<TClientRegistrations> constraint4 = createPrimaryKey(id);

    public QTClientRegistrations(String variable) {
        super(TClientRegistrations.class, forVariable(variable), "PUBLIC", "T_CLIENT_REGISTRATIONS");
        addMetadata();
    }

    public QTClientRegistrations(String variable, String schema, String table) {
        super(TClientRegistrations.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTClientRegistrations(String variable, String schema) {
        super(TClientRegistrations.class, forVariable(variable), schema, "T_CLIENT_REGISTRATIONS");
        addMetadata();
    }

    public QTClientRegistrations(Path<? extends TClientRegistrations> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_CLIENT_REGISTRATIONS");
        addMetadata();
    }

    public QTClientRegistrations(PathMetadata metadata) {
        super(TClientRegistrations.class, metadata, "PUBLIC", "T_CLIENT_REGISTRATIONS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(authorizationGrantType, ColumnMetadata.named("AUTHORIZATION_GRANT_TYPE").withIndex(5).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(authorizationUri, ColumnMetadata.named("AUTHORIZATION_URI").withIndex(6).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientAuthenticationMethod, ColumnMetadata.named("CLIENT_AUTHENTICATION_METHOD").withIndex(7).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientId, ColumnMetadata.named("CLIENT_ID").withIndex(8).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientName, ColumnMetadata.named("CLIENT_NAME").withIndex(9).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientRegistrationId, ColumnMetadata.named("CLIENT_REGISTRATION_ID").withIndex(10).ofType(Types.VARBINARY).withSize(255));
        addMetadata(clientRegistrationType, ColumnMetadata.named("CLIENT_REGISTRATION_TYPE").withIndex(11).ofType(Types.OTHER).withSize(11));
        addMetadata(clientSecret, ColumnMetadata.named("CLIENT_SECRET").withIndex(12).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(concurrencyVersion, ColumnMetadata.named("CONCURRENCY_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(jwkSetUri, ColumnMetadata.named("JWK_SET_URI").withIndex(13).ofType(Types.VARCHAR).withSize(255));
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(redirectUri, ColumnMetadata.named("REDIRECT_URI").withIndex(14).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(scopes, ColumnMetadata.named("SCOPES").withIndex(15).ofType(Types.ARRAY).withSize(65536).notNull());
        addMetadata(tokenUri, ColumnMetadata.named("TOKEN_URI").withIndex(16).ofType(Types.VARCHAR).withSize(255));
        addMetadata(userInfoUri, ColumnMetadata.named("USER_INFO_URI").withIndex(17).ofType(Types.VARCHAR).withSize(255));
        addMetadata(userNameAttributeName, ColumnMetadata.named("USER_NAME_ATTRIBUTE_NAME").withIndex(18).ofType(Types.VARCHAR).withSize(255));
    }

}

