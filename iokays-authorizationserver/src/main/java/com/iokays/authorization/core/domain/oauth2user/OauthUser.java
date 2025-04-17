package com.iokays.authorization.core.domain.oauth2user;

import com.iokays.authorization.core.domain.clientregistration.RegistrationId;
import com.iokays.authorization.core.domain.oauth2user.command.SaveOauthUser;
import com.iokays.common.domain.jpa.ConcurrencySafeEntity;
import jakarta.persistence.*;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Oauth2User: 设置为单独一个领域层，是为了简化 user层。 可能系统没有自己的用户体系。
 * 如果系统同时支持表单和Oauth2登录， 并且表单用户为系统的核心用户模块，Oauth2作为登录授权的一种方式，将该部分放到user领域层，并建立一对多的关系。
 */
@Entity
@Table(name = "t_oauth_users", uniqueConstraints = {@UniqueConstraint(name = "UK_SUBJECT", columnNames = {"subject", "client_registration_id"})})
public class OauthUser extends ConcurrencySafeEntity<OauthUser> {

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "oauth_user_id", length = 40, unique = true, nullable = false))
    private OauthUserId oauthUserId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "client_registration_id", length = 40, nullable = false))
    private RegistrationId registrationId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String givenName;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String preferredUsername;

    @Column(nullable = false)
    private String profile;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean emailVerified;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birthdate;

    @Column(nullable = false)
    private String zoneinfo;

    @Column(nullable = false)
    private String locale;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean phoneNumberVerified;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private String claim;

    protected OauthUser() {
        super();
    }

    public OauthUser(final SaveOauthUser command) {
        this();
        this.oauthUserId = OauthUserId.makeUserId();
        this.registrationId = command.registrationId();
        this.set(command);
    }

    public void update(final SaveOauthUser command) {
        this.set(command);
    }


    private void set(final SaveOauthUser command) {
        this.subject = command.subject();
        this.name = StringUtils.defaultString(command.name());
        this.givenName = StringUtils.defaultString(command.givenName());
        this.familyName = StringUtils.defaultString(command.familyName());
        this.middleName = StringUtils.defaultString(command.middleName());
        this.nickname = StringUtils.defaultString(command.nickname());
        this.preferredUsername = StringUtils.defaultString(command.preferredUsername());
        this.profile = StringUtils.defaultString(command.profile());
        this.picture = StringUtils.defaultString(command.picture());
        this.website = StringUtils.defaultString(command.website());
        this.email = StringUtils.defaultString(command.email());
        this.emailVerified = BooleanUtils.toBooleanDefaultIfNull(command.emailVerified(), false);
        this.gender = StringUtils.defaultString(command.gender());

        this.birthdate = StringUtils.defaultString(command.birthdate());
        this.zoneinfo = StringUtils.defaultString(command.zoneinfo());
        this.locale = StringUtils.defaultString(command.locale());
        this.phoneNumber = StringUtils.defaultString(command.phoneNumber());
        this.phoneNumberVerified = BooleanUtils.toBooleanDefaultIfNull(command.phoneNumberVerified(), false);
        this.address = StringUtils.defaultString(command.address());
        this.updatedAt = StringUtils.defaultString(command.updatedAt());
        this.claim = StringUtils.defaultString(command.claim());
    }

    public OauthUserInfo info() {
        return OauthUserInfo.builder()
                .oauthUserId(this.oauthUserId)
                .registrationId(this.registrationId)
                .subject(this.subject)
                .name(this.name)
                .givenName(this.givenName)
                .familyName(this.familyName)
                .middleName(this.middleName)
                .nickname(this.nickname)
                .preferredUsername(this.preferredUsername)
                .profile(this.profile)
                .picture(this.picture)
                .website(this.website)
                .email(this.email)
                .emailVerified(this.emailVerified)
                .gender(this.gender)
                .birthdate(this.birthdate)
                .zoneinfo(this.zoneinfo)
                .locale(this.locale)
                .phoneNumber(this.phoneNumber)
                .phoneNumberVerified(this.phoneNumberVerified)
                .claim(this.claim)
                .build();
    }


    @Override
    public boolean sameIdentityAs(OauthUser other) {
        return false;
    }

}
