package com.iokays.core.application.service.table;

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
 * QTOauth2AuthorizationConsent is a Querydsl query type for TOauth2AuthorizationConsent
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTOauth2AuthorizationConsent extends com.querydsl.sql.RelationalPathBase<TOauth2AuthorizationConsent> {

    public static final QTOauth2AuthorizationConsent tOauth2AuthorizationConsent = new QTOauth2AuthorizationConsent("T_OAUTH2_AUTHORIZATION_CONSENT");
    private static final long serialVersionUID = 1747278165;
    public final StringPath authorities = createString("authorities");

    public final SimplePath<byte[]> authorizationConsentId = createSimple("authorizationConsentId", byte[].class);

    public final NumberPath<Integer> concurrencyVersion = createNumber("concurrencyVersion", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath principalName = createString("principalName");

    public final SimplePath<byte[]> registeredClientId = createSimple("registeredClientId", byte[].class);

    public final com.querydsl.sql.PrimaryKey<TOauth2AuthorizationConsent> constraintC = createPrimaryKey(id);

    public QTOauth2AuthorizationConsent(String variable) {
        super(TOauth2AuthorizationConsent.class, forVariable(variable), "PUBLIC", "T_OAUTH2_AUTHORIZATION_CONSENT");
        addMetadata();
    }

    public QTOauth2AuthorizationConsent(String variable, String schema, String table) {
        super(TOauth2AuthorizationConsent.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTOauth2AuthorizationConsent(String variable, String schema) {
        super(TOauth2AuthorizationConsent.class, forVariable(variable), schema, "T_OAUTH2_AUTHORIZATION_CONSENT");
        addMetadata();
    }

    public QTOauth2AuthorizationConsent(Path<? extends TOauth2AuthorizationConsent> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_OAUTH2_AUTHORIZATION_CONSENT");
        addMetadata();
    }

    public QTOauth2AuthorizationConsent(PathMetadata metadata) {
        super(TOauth2AuthorizationConsent.class, metadata, "PUBLIC", "T_OAUTH2_AUTHORIZATION_CONSENT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(authorities, ColumnMetadata.named("AUTHORITIES").withIndex(5).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(authorizationConsentId, ColumnMetadata.named("AUTHORIZATION_CONSENT_ID").withIndex(6).ofType(Types.VARBINARY).withSize(255));
        addMetadata(concurrencyVersion, ColumnMetadata.named("CONCURRENCY_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(principalName, ColumnMetadata.named("PRINCIPAL_NAME").withIndex(7).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(registeredClientId, ColumnMetadata.named("REGISTERED_CLIENT_ID").withIndex(8).ofType(Types.VARBINARY).withSize(255).notNull());
    }

}

