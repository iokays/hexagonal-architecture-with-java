package com.iokays.core.application.service.table;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.processing.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QTOauthUsers is a Querydsl query type for TOauthUsers
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTOauthUsers extends com.querydsl.sql.RelationalPathBase<TOauthUsers> {

    public static final QTOauthUsers tOauthUsers = new QTOauthUsers("T_OAUTH_USERS");
    private static final long serialVersionUID = -304241014;
    public final StringPath address = createString("address");

    public final StringPath birthdate = createString("birthdate");

    public final StringPath claim = createString("claim");

    public final StringPath clientRegistrationId = createString("clientRegistrationId");

    public final NumberPath<Integer> concurrencyVersion = createNumber("concurrencyVersion", Integer.class);

    public final DateTimePath<java.sql.Timestamp> createdDate = createDateTime("createdDate", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final BooleanPath emailVerified = createBoolean("emailVerified");

    public final StringPath familyName = createString("familyName");

    public final StringPath gender = createString("gender");

    public final StringPath givenName = createString("givenName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.sql.Timestamp> lastModifiedDate = createDateTime("lastModifiedDate", java.sql.Timestamp.class);

    public final StringPath locale = createString("locale");

    public final StringPath middleName = createString("middleName");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath oauthUserId = createString("oauthUserId");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final BooleanPath phoneNumberVerified = createBoolean("phoneNumberVerified");

    public final StringPath picture = createString("picture");

    public final StringPath preferredUsername = createString("preferredUsername");

    public final StringPath profile = createString("profile");

    public final StringPath subject = createString("subject");

    public final StringPath updatedAt = createString("updatedAt");

    public final StringPath website = createString("website");

    public final StringPath zoneinfo = createString("zoneinfo");

    public final com.querydsl.sql.PrimaryKey<TOauthUsers> constraintE = createPrimaryKey(id);

    public QTOauthUsers(String variable) {
        super(TOauthUsers.class, forVariable(variable), "PUBLIC", "T_OAUTH_USERS");
        addMetadata();
    }

    public QTOauthUsers(String variable, String schema, String table) {
        super(TOauthUsers.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTOauthUsers(String variable, String schema) {
        super(TOauthUsers.class, forVariable(variable), schema, "T_OAUTH_USERS");
        addMetadata();
    }

    public QTOauthUsers(Path<? extends TOauthUsers> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "T_OAUTH_USERS");
        addMetadata();
    }

    public QTOauthUsers(PathMetadata metadata) {
        super(TOauthUsers.class, metadata, "PUBLIC", "T_OAUTH_USERS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("ADDRESS").withIndex(5).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(birthdate, ColumnMetadata.named("BIRTHDATE").withIndex(6).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(claim, ColumnMetadata.named("CLAIM").withIndex(7).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientRegistrationId, ColumnMetadata.named("CLIENT_REGISTRATION_ID").withIndex(8).ofType(Types.VARCHAR).withSize(40).notNull());
        addMetadata(concurrencyVersion, ColumnMetadata.named("CONCURRENCY_VERSION").withIndex(4).ofType(Types.INTEGER).withSize(32).notNull());
        addMetadata(createdDate, ColumnMetadata.named("CREATED_DATE").withIndex(2).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(email, ColumnMetadata.named("EMAIL").withIndex(9).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(emailVerified, ColumnMetadata.named("EMAIL_VERIFIED").withIndex(10).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(familyName, ColumnMetadata.named("FAMILY_NAME").withIndex(11).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(gender, ColumnMetadata.named("GENDER").withIndex(12).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(givenName, ColumnMetadata.named("GIVEN_NAME").withIndex(13).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(64).notNull());
        addMetadata(lastModifiedDate, ColumnMetadata.named("LAST_MODIFIED_DATE").withIndex(3).ofType(Types.TIMESTAMP).withSize(26).withDigits(6).notNull());
        addMetadata(locale, ColumnMetadata.named("LOCALE").withIndex(14).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(middleName, ColumnMetadata.named("MIDDLE_NAME").withIndex(15).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(16).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(nickname, ColumnMetadata.named("NICKNAME").withIndex(17).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(oauthUserId, ColumnMetadata.named("OAUTH_USER_ID").withIndex(18).ofType(Types.VARCHAR).withSize(40).notNull());
        addMetadata(phoneNumber, ColumnMetadata.named("PHONE_NUMBER").withIndex(19).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(phoneNumberVerified, ColumnMetadata.named("PHONE_NUMBER_VERIFIED").withIndex(20).ofType(Types.BOOLEAN).withSize(1).notNull());
        addMetadata(picture, ColumnMetadata.named("PICTURE").withIndex(21).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(preferredUsername, ColumnMetadata.named("PREFERRED_USERNAME").withIndex(22).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(profile, ColumnMetadata.named("PROFILE").withIndex(23).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(subject, ColumnMetadata.named("SUBJECT").withIndex(24).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(25).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(website, ColumnMetadata.named("WEBSITE").withIndex(26).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(zoneinfo, ColumnMetadata.named("ZONEINFO").withIndex(27).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

